public class Item {
    private int id;
    private String textData;
    private double value;

    public Item(int id, String textData, double value) {
        this.id = id;
        this.textData = textData;
        this.value = value;
    }

    public int getId() { return id; }
    public String getTextData() { return textData; }
    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }
    public void setTextData(String textData) { this.textData = textData; }

    @Override
    public String toString() {
        return id + "," + textData + "," + value;
    }

    public static Item fromString(String line) {
        String[] parts = line.split(",");
        return new Item(Integer.parseInt(parts[0].trim()), parts[1].trim(), Double.parseDouble(parts[2].trim()));
    }
}