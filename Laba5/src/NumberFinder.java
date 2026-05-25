import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFinder {
    public static void main(String[] args) {
        // Пример текста для тестирования
        String text = "The price of the product is $19.99, but you can also buy it for 10 or 100.5 dollars. " +
                "Negative numbers like -5 are also possible, as well as 0 and 12345.";


        Pattern pattern = Pattern.compile("-?\\d+(?:\\.\\d+)?");

        Matcher matcher = pattern.matcher(text);

        System.out.println("Найденные числа в тексте:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}