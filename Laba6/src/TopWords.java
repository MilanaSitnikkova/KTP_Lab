import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TopWords {

    public static void main(String[] args) {
        String filePath = "text.txt";

        createTestFile(filePath);

        File file = new File(filePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при открытии файла: " + e.getMessage());
            return;
        }

        Map<String, Integer> wordCountMap = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next()
                    .toLowerCase()
                    .replaceAll("[^a-zA-Zа-яА-Я0-9]", "");

            if (word.length() < 2) {
                continue;
            }

            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        scanner.close();

        if (wordCountMap.isEmpty()) {
            System.out.println("Файл пуст или не содержит слов.");
            return;
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCountMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println("\nТОП-10 САМЫХ ЧАСТЫХ СЛОВ");
        System.out.printf("%-5s %-25s %-10s%n", "№", "Слово", "Количество");

        int limit = Math.min(10, list.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.printf("%-5d %-25s %-10d%n", (i + 1), entry.getKey(), entry.getValue());
        }

        System.out.println("\nСТАТИСТИКА");
        System.out.println("Всего уникальных слов: " + wordCountMap.size());
        int totalWords = 0;
        for (Map.Entry<String, Integer> entry : list) {
            totalWords += entry.getValue();
        }
        System.out.println("Всего слов в файле: " + totalWords);
    }

    private static void createTestFile(String filePath) {
        String content =
                "The quick brown fox jumps over the lazy dog. " +
                        "The dog is lazy but the fox is quick. " +
                        "Java is a programming language. " +
                        "Java is widely used for web development. " +
                        "The quick brown fox jumps again. " +
                        "Programming in Java can be fun. " +
                        "Java programming requires practice. " +
                        "The lazy dog sleeps all day. " +
                        "The quick fox hunts at night. " +
                        "Hello world this is a test file for word counting. " +
                        "We need to find the most frequent words in this text. " +
                        "The quick brown fox is a famous pangram. " +
                        "Java Java Java this word appears many times. " +
                        "Programming programming programming is fun fun fun";

        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(content);
            System.out.println("Создан тестовый файл: " + filePath);
            System.out.println("\nСодержимое файла:");
            System.out.println(content);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }
    }
}
