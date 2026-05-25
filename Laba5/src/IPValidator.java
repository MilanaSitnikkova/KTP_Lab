import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class IPValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите IP-адрес для проверки: ");
        String ipAddress = scanner.nextLine();

        if (isValidIPv4(ipAddress)) {
            System.out.println("IP-адрес корректный!");
        } else {
            System.out.println("IP-адрес НЕ корректный!");
            System.out.println("Требования к IPv4-адресу:");
            System.out.println("- 4 числа, разделённых точками");
            System.out.println("- Каждое число от 0 до 255");
            System.out.println("- Без ведущих нулей (кроме самого числа 0)");
        }

        scanner.close();
    }

    public static boolean isValidIPv4(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }


        String octet = "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])";
        String regex = "^" + octet + "\\." + octet + "\\." + octet + "\\." + octet + "$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ip);

        return matcher.matches();
    }
}