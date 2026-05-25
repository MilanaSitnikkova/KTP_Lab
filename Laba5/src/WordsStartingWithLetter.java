import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class WordsStartingWithLetter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = "Java is a powerful programming language. " +
                "Just learning Java can be fun. " +
                "Jump into the world of programming with Java. " +
                "JavaScript is also popular, but Java is different. " +
                "The quick brown fox jumps over the lazy dog. " +
                "Apple, banana, cherry, date, elderberry. " +
                "Пример текста на русском: привет, Программирование, java, Москва, мир, Солнце.";

        System.out.println("Исходный текст:");
        System.out.println(text);
        System.out.println();

        System.out.print("Введите букву для поиска слов: ");
        String letterInput = scanner.nextLine();

        if (letterInput == null || letterInput.isEmpty()) {
            System.out.println("Буква не введена!");
            scanner.close();
            return;
        }

        char letter = letterInput.charAt(0);

        System.out.print("Учитывать регистр? (да/нет): ");
        boolean caseSensitive = scanner.nextLine().equalsIgnoreCase("да");

        System.out.print("Выводить только уникальные слова? (да/нет): ");
        boolean uniqueOnly = scanner.nextLine().equalsIgnoreCase("да");

        findWordsStartingWith(text, letter, caseSensitive, uniqueOnly);

        scanner.close();
    }

    public static void findWordsStartingWith(String text, char letter, boolean caseSensitive, boolean uniqueOnly) {
        if (text == null || text.isEmpty()) {
            System.out.println("Текст пуст!");
            return;
        }

        String wordPattern = "\\b[" + getLetterPattern(letter, caseSensitive) + "][A-Za-zА-Яа-яЁё0-9]*\\b";

        Pattern pattern = Pattern.compile(wordPattern);
        Matcher matcher = pattern.matcher(text);

        List<String> words = new ArrayList<>();

        while (matcher.find()) {
            String word = matcher.group();
            words.add(word);
        }

        if (uniqueOnly) {
            words = words.stream().distinct().toList();
        }

        System.out.println("\nСлова, начинающиеся с буквы '" + letter + "':");

        if (words.isEmpty()) {
            System.out.println("  Слова не найдены.");
        } else {
            for (int i = 0; i < words.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + words.get(i));
            }
            System.out.println("\nВсего найдено: " + words.size());
        }
    }

    private static String getLetterPattern(char letter, boolean caseSensitive) {
        if (caseSensitive) {
            return String.valueOf(letter);
        } else {
            return Character.toLowerCase(letter) + "" + Character.toUpperCase(letter);
        }
    }
}