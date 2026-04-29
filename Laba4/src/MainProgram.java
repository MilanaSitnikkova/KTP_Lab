import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// ======================== CustomInputStreamException ========================
class CustomInputStreamException extends Exception {
    public CustomInputStreamException(String message) {
        super(message);
    }
}

// ======================== ExceptionLogger ========================
class ExceptionLogger {
    private static final String LOG_FILE = "exceptions.log";

    public static void log(Exception e) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter); //Получение текущего времени

            pw.println("[" + timestamp + "] " + e.getClass().getSimpleName() + ": " + e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                pw.println("    at " + element.toString());
            }
            pw.println("---");
            pw.flush();

            System.out.println("Исключение залогировано в файл: " + LOG_FILE);
        } catch (IOException ioEx) {
            System.out.println("Ошибка при записи в лог-файл: " + ioEx.getMessage());
        }
    }
}

class NumberReader {
    public static int readInteger() throws CustomInputStreamException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        String input = scanner.nextLine();

        try {
            int number = Integer.parseInt(input);
            System.out.println("Успешное преобразование: " + input + " -> " + number);
            return number;
        } catch (NumberFormatException e) {
            throw new CustomInputStreamException("Некорректный ввод: '" + input + "' не является целым числом");
        }
    }
}

public class MainProgram {
    public static void main(String[] args) {
        System.out.println("Программа проверки ввода целого числа");
        System.out.println("(Для выхода нажмите Ctrl+C или введите число)");
        System.out.println();

        boolean continueLoop = true;
        int attempts = 0;

        while (continueLoop && attempts < 3) {
            attempts++;
            System.out.println("Попытка " + attempts );

            try {
                int number = NumberReader.readInteger();
                System.out.println("УСПЕХ! Вы ввели число: " + number);
                continueLoop = false;
            } catch (CustomInputStreamException e) {
                System.out.println("ОШИБКА: " + e.getMessage());
                System.out.println("Тип исключения: " + e.getClass().getSimpleName());
                ExceptionLogger.log(e);

                if (attempts >= 3) {
                    System.out.println("Превышено количество попыток. Программа завершается.");
                } else {
                    System.out.println("Попробуйте снова...");
                    System.out.println();
                }
            }
        }
    }
}