import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class RowMaxTask implements Callable<Integer> {
    private final int[] row;
    private final int rowIndex;

    public RowMaxTask(int[] row, int rowIndex) {
        this.row = row;
        this.rowIndex = rowIndex;
    }

    @Override
    public Integer call() throws Exception {
        int max = row[0];
        for (int val : row) {
            if (val > max) {
                max = val;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Закончил обработку строки "
                + rowIndex + ". Локальный максимум: " + max);
        return max;
    }
}

public class MatrixMaxFinder {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 14, -5, 8, 22},
                {45, 2, 89, 11, 0},
                {-10, 56, 4, 91, 17},
                {12, 73, 5, 33, 64}
        };

        System.out.println("Старт поиска максимального элемента в матрице");

        int numberOfThreads = 2;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            Callable<Integer> task = new RowMaxTask(matrix[i], i);
            Future<Integer> futureResult = executor.submit(task);
            futures.add(futureResult);
        }

        executor.shutdown();

        int globalMax = Integer.MIN_VALUE;

        try {
            for (Future<Integer> future : futures) {
                int localMax = future.get();
                if (localMax > globalMax) {
                    globalMax = localMax;
                }
            }

            System.out.println("\nРезультат вычислений");
            System.out.println("Наибольший элемент в матрице равен: " + globalMax);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Произошла ошибка во время многопоточных вычислений: " + e.getMessage());
        }
    }
}