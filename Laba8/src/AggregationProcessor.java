import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AggregationProcessor {
    public List<Item> sortData(List<Item> dataset) {
        System.out.println("[" + Thread.currentThread().getName() + "] Выполнение сортировки...");
        return dataset.stream()
                .sorted(Comparator.comparingDouble(Item::getValue).reversed())
                .collect(Collectors.toList());
    }
}