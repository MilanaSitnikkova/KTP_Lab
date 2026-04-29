public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println(s + " - это палиндром");
            } else {
                System.out.println(s + " - НЕ палиндром");
            }
        }
    }

    // Метод для переворота строки
    public static String reverseString(String s) {
        String reversed = ""; // Пустая строка для результата
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed = reversed + s.charAt(i); // Добавляем символы в обратном порядке
        }
        return reversed;
    }

    // Метод для проверки, является ли строка палиндромом
    public static boolean isPalindrome(String s) {
        String reversed = reverseString(s);
        return s.equals(reversed); // Сравниваем исходную и перевёрнутую строки
    }
}