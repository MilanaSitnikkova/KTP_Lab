import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SumTask implements Callable<Long> {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;

    public SumTask(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public Long call() throws Exception {
        long localSum = 0;
        for (int i = startIndex; i < endIndex; i++) {
            localSum += array[i];
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Посчитал сумму с индекса "
                + startIndex + " по " + (endIndex - 1) + ". Локальная сумма: " + localSum);
        return localSum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] array = {
                5, 10, 15, 20, 25, 30, 35, 40, 45, 50,
                55, 60, 65, 70, 75, 80, 85, 90, 95, 100
        };

        System.out.println("Старт параллельного подсчета суммы массива");

        int numberOfThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        int sizePerThread = array.length / numberOfThreads;

        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * sizePerThread;
            int end = (i == numberOfThreads - 1) ? array.length : (start + sizePerThread);

            Callable<Long> task = new SumTask(array, start, end);
            Future<Long> futureResult = executor.submit(task);
            futures.add(futureResult);
        }

        executor.shutdown();

        long totalSum = 0;

        try {
            for (Future<Long> future : futures) {
                totalSum += future.get();
            }

            System.out.println("\nРезультат вычислений");
            System.out.println("Финальная сумма всех элементов массива: " + totalSum);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Произошла ошибка при выполнении потоков: " + e.getMessage());
        }
    }
}