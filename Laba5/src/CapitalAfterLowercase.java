import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapitalAfterLowercase {
    public static void main(String[] args) {
        String text = "Пример текста: eXample, иванИванов, приветМир, javaПрограммирование, aBc, xYz, но не ABcd и не a bc.";

        System.out.println("Исходный текст:");
        System.out.println(text);
        System.out.println("\nРезультат обработки:");

        String result = highlightCapitalAfterLowercase(text);
        System.out.println(result);
    }

    public static String highlightCapitalAfterLowercase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String regex = "([a-zа-яё])([A-ZА-ЯЁ])";
        String replacement = "$1!$2!";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        return matcher.replaceAll(replacement);
    }
}