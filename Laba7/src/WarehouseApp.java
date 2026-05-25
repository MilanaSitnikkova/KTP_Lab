import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Product {
    private final String name;
    private final int weight;

    public Product(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

class Warehouse {
    private final List<Product> products = new ArrayList<>();
    private final String name;

    public Warehouse(String name) {
        this.name = name;
    }

    public synchronized void addProduct(Product product) {
        products.add(product);
    }

    public synchronized List<Product> takeProducts() {
        if (products.isEmpty()) {
            return null; // Склад пуст
        }

        List<Product> batch = new ArrayList<>();
        int currentWeight = 0;

        while (!products.isEmpty()) {
            Product nextProduct = products.get(0);

            if (currentWeight + nextProduct.getWeight() <= 150) {
                currentWeight += nextProduct.getWeight();
                batch.add(products.remove(0));
            } else {
                if (batch.isEmpty()) {
                    batch.add(products.remove(0));
                }
                break;
            }
        }
        return batch;
    }

    public synchronized boolean isEmpty() {
        return products.isEmpty();
    }
}

class LoaderRealization implements Runnable {
    private final String loaderName;
    private final Warehouse sourceWarehouse;
    private final Warehouse destinationWarehouse;

    public LoaderRealization(String loaderName, Warehouse sourceWarehouse, Warehouse destinationWarehouse) {
        this.loaderName = loaderName;
        this.sourceWarehouse = sourceWarehouse;
        this.destinationWarehouse = destinationWarehouse;
    }

    @Override
    public void run() {
        System.out.println("[" + loaderName + "] начал работу.");
        try {
            while (!sourceWarehouse.isEmpty()) {
                List<Product> batch = sourceWarehouse.takeProducts();

                if (batch == null || batch.isEmpty()) {
                    break;
                }

                int batchWeight = batch.stream().mapToInt(Product::getWeight).sum();
                System.out.println("[" + loaderName + "] собрал партию весом " + batchWeight + " кг и понёс на новый склад.");

                Thread.sleep(1000);

                for (Product product : batch) {
                    destinationWarehouse.addProduct(product);
                }
                System.out.println("[" + loaderName + "] успешно разгрузил " + batchWeight + " кг.");
            }
        } catch (InterruptedException e) {
            System.out.println("[" + loaderName + "] был прерван.");
            Thread.currentThread().interrupt();
        }
        System.out.println("[" + loaderName + "] завершил свою работу.");
    }
}

public class WarehouseApp {
    public static void main(String[] args) {
        Warehouse source = new Warehouse("Исходный склад");
        Warehouse destination = new Warehouse("Новый склад");

        // Заполняем исходный склад демонстрационным набором товаров разного веса
        source.addProduct(new Product("Диван", 85));
        source.addProduct(new Product("Стиральная машина", 60));
        source.addProduct(new Product("Холодильник", 90));
        source.addProduct(new Product("Телевизор", 25));
        source.addProduct(new Product("Микроволновка", 15));
        source.addProduct(new Product("Кресло", 45));
        source.addProduct(new Product("Обогреватель", 10));
        source.addProduct(new Product("Комод", 70));
        source.addProduct(new Product("Пылесос", 12));

        System.out.println("--- Старт переноса товаров со склада ---");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable loader1 = new LoaderRealization("Грузчик-1", source, destination);
        Runnable loader2 = new LoaderRealization("Грузчик-2", source, destination);
        Runnable loader3 = new LoaderRealization("Грузчик-3", source, destination);

        executorService.execute(loader1);
        executorService.execute(loader2);
        executorService.execute(loader3);

        executorService.shutdown();

        try {
            if (executorService.awaitTermination(5, TimeUnit.MINUTES)) {
                System.out.println("--- Все товары успешно перенесены на новый склад! ---");
            }
        } catch (InterruptedException e) {
            System.err.println("Процесс выполнения был прерван.");
            Thread.currentThread().interrupt();
        }
    }
}
