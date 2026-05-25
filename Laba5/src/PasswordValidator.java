import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (isValidPassword(password)) {
            System.out.println("Пароль корректный!");
        } else {
            System.out.println("Пароль НЕ корректный!");
            System.out.println("Требования к паролю:");
            System.out.println("- Длина от 8 до 16 символов");
            System.out.println("- Только латинские буквы и цифры");
            System.out.println("- Хотя бы одна заглавная буква");
            System.out.println("- Хотя бы одна цифра");
        }

        scanner.close();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }


        String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
