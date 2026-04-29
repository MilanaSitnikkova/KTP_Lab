public class Primes {
    public static void main(String[] args) {
        System.out.println("Простые числа меньше 100:");
        for (int i = 2; i < 100; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean isPrime(int n) {
        // Проверяем, делится ли n на какое-либо число от 2 до n-1
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false; // Если нашёлся делитель - число не простое
            }
        }
        return true; // Если делителей не нашлось - число простое
    }
}
