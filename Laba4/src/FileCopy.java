import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFile = "source.txt";
        String destFile = "destination.txt";

        // Создаём тестовый файл
        createTestFile(sourceFile);

        // Используем try-with-resources (автоматическое закрытие)
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destFile)) {

            System.out.println("Начинаем чтение из файла: " + sourceFile);

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            int totalBytes = 0;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
                System.out.println("Прочитано и записано " + bytesRead + " байт");
            }

            System.out.println("Успешно скопировано " + totalBytes + " байт");
            System.out.println("Файл скопирован: " + sourceFile + " -> " + destFile);

        } catch (FileNotFoundException e) {
            System.out.println("ОШИБКА при открытии: файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ОШИБКА при чтении или записи: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTestFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                pw.println("Строка 1: Проверка чтения и записи");
                pw.println("Строка 2: Обработка ошибок IOException");
                pw.println("Строка 3: Демонстрация работы");
                System.out.println("Тестовый файл создан: " + fileName);
            } catch (IOException e) {
                System.out.println("Не удалось создать тестовый файл");
            }
        }
    }
}
