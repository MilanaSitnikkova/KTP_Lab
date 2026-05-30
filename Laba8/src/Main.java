import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFile = "source_data.csv";
        String outputFile = "processed_data.csv";

        createDummyFile(inputFile);

        DataManager dataManager = new DataManager();

        dataManager.registerDataProcessor(new FilterProcessor());
        dataManager.registerDataProcessor(new TransformProcessor());
        dataManager.registerDataProcessor(new AggregationProcessor());

        dataManager.loadData(inputFile);
        dataManager.processData();
        dataManager.saveData(outputFile);
    }

    private static void createDummyFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("1, Keyboard, 45.50\n");
            writer.write("2, Mouse, 15.00\n");
            writer.write("3, Monitor, 250.00\n");
            writer.write("4, Laptop, 1200.00\n");
            writer.write("5, USB Cable, 5.99\n");
            writer.write("6, Desk Chair, 150.00\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
