import java.util.LinkedList;

// Класс для хранения пары ключ-значение
class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

// Реализация хэш-таблицы с цепочками
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            LinkedList<Entry<K, V>> list = table[index];
            for (Entry<K, V> entry : list) {
                if (entry.getKey().equals(key)) {
                    list.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        HashTable<String, String> table = new HashTable<>();
        table.put("apple", "5");
        table.put("banana", "3");
        table.put("orange", "7");
        table.put("banana", "10");

        System.out.println("banana: " + table.get("banana"));
        System.out.println("apple: " + table.get("apple"));

        table.remove("banana");
        System.out.println("banana after remove: " + table.get("banana"));
        System.out.println("Size: " + table.size());
    }
}
