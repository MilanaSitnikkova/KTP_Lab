import java.util.HashMap;
import java.util.Scanner;

public class OnlineStore {
    private HashMap<String, Product> productMap = new HashMap<>();

    public void addProduct(String article, Product product) {
        productMap.put(article, product);
        System.out.println("Товар добавлен: " + article);
    }

    public Product findProduct(String article) {
        return productMap.get(article);
    }

    public void removeProduct(String article) {
        if (productMap.remove(article) != null) {
            System.out.println("Товар удалён: " + article);
        } else {
            System.out.println("Товар не найден");
        }
    }

    public void showAll() {
        if (productMap.isEmpty()) {
            System.out.println("Нет товаров");
        } else {
            productMap.forEach((article, product) ->
                    System.out.println(article + " -> " + product));
        }
    }

    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Магазин ---");
            System.out.println("1. Добавить товар");
            System.out.println("2. Найти товар по артикулу");
            System.out.println("3. Удалить товар");
            System.out.println("4. Показать все товары");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Артикул: ");
                    String article = scanner.nextLine();
                    System.out.print("Название: ");
                    String name = scanner.nextLine();
                    System.out.print("Описание: ");
                    String desc = scanner.nextLine();
                    System.out.print("Цена: ");
                    double price = scanner.nextDouble();
                    System.out.print("Количество на складе: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    store.addProduct(article, new Product(name, desc, price, stock));
                    break;
                case 2:
                    System.out.print("Артикул: ");
                    article = scanner.nextLine();
                    Product p = store.findProduct(article);
                    System.out.println(p != null ? p : "Не найден");
                    break;
                case 3:
                    System.out.print("Артикул: ");
                    article = scanner.nextLine();
                    store.removeProduct(article);
                    break;
                case 4:
                    store.showAll();
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
}