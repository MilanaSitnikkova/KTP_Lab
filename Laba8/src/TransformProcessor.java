import java.util.List;
import java.util.stream.Collectors;

public class TransformProcessor {
    public List<Item> transformData(List<Item> dataset) {
        System.out.println("[" + Thread.currentThread().getName() + "] Выполнение трансформации...");
        return dataset.stream()
                .map(record -> {
                    record.setValue(record.getValue() * 1.10);
                    record.setTextData(record.getTextData().toUpperCase());
                    return record;
                })
                .collect(Collectors.toList());
    }
}