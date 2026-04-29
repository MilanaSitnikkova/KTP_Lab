public class ArrayAverage {
    public static void main(String[] args) {
        // Пример массива с разными данными
        String[] input = {"10", "20", "abc", "30", "40"};

        int[] numbers = new int[input.length];
        int sum = 0;
        int count = 0;

        try {
            for (int i = 0; i <= input.length; i++) { // Ошибка: выхода за границы
                try {
                    numbers[i] = Integer.parseInt(input[i]);
                    sum += numbers[i];
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: элемент '" + input[i] + "' не является числом");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за границы массива");
        }

        if (count > 0) {
            double average = (double) sum / count;
            System.out.println("Среднее арифметическое: " + average);
        } else {
            System.out.println("Нет корректных чисел для вычисления");
        }
    }
}