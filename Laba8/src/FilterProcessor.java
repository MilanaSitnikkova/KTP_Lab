import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {
    public List<Item> filterLowValues(List<Item> dataset) {
        System.out.println("[" + Thread.currentThread().getName() + "] Выполнение фильтрации...");
        return dataset.stream()
                .filter(record -> record.getValue() >= 100.0)
                .collect(Collectors.toList());
    }
}
