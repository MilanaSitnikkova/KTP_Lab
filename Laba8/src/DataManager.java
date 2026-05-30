import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

public class DataManager {
    private List<Item> dataset = new ArrayList<>();
    private final List<Object> processors = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void registerDataProcessor(Object processor) {
        if (processor != null) {
            processors.add(processor);
            System.out.println("Зарегистрирован обработчик: " + processor.getClass().getSimpleName());
        }
    }

    public void loadData(String source) {
        System.out.println("Загрузка данных из файла: " + source);
        dataset.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    dataset.add(Item.fromString(line));
                }
            }
            System.out.println("Успешно загружено записей: " + dataset.size());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void processData() {
        System.out.println("--- Старт многопоточной обработки ---");

        for (Object processorInstance : processors) {
            Method[] methods = processorInstance.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    DataProcessor annotation = method.getAnnotation(DataProcessor.class);
                    System.out.println("Подготовка задачи: " + annotation.description());

                    Callable<List<Item>> task = () -> {
                        @SuppressWarnings("unchecked")
                        List<Item> result = (List<Item>) method.invoke(processorInstance, dataset);
                        return result;
                    };

                    try {
                        Future<List<Item>> future = executorService.submit(task);
                        dataset = future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Ошибка в потоке при вызове " + method.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("--- Обработка завершена ---");
    }

    public void saveData(String destination) {
        System.out.println("Сохранение результатов в файл: " + destination);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            for (Item record : dataset) {
                writer.write(record.toString());
                writer.newLine();
            }
            System.out.println("Успешно сохранено.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}