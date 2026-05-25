import java.util.EmptyStackException;

public class Stack<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Емкость стека должна быть больше 0");
        }
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            throw new StackOverflowError("Стек переполнен! Невозможно добавить элемент.");
        }
        data[size] = element;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        T element = data[size];
        data[size] = null; // Зануляем ссылку, чтобы помочь сборщику мусора (GC) освободить память
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);

        System.out.println("--- Шаг 1: Добавляем элементы 1, 2, 3 ---");
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("--- Шаг 2: Извлекаем верхний элемент (pop) ---");
        System.out.println("Извлечен: " + stack.pop());

        System.out.println("--- Шаг 3: Смотрим на верхний элемент (peek) ---");
        System.out.println("На вершине стека: " + stack.peek());

        System.out.println("--- Шаг 4: Добавляем элемент 4 ---");
        stack.push(4);

        System.out.println("--- Шаг 5: Извлекаем верхний элемент (pop) ---");
        System.out.println("Извлечен: " + stack.pop());

        System.out.println("--- Шаг 6: Извлекаем оставшиеся элементы ---");
        System.out.println("Извлечен: " + stack.pop()); // Выведет 2
        System.out.println("Извлечен: " + stack.pop()); // Выведет 1

        System.out.println("--- Шаг 7: Проверка на пустоту ---");
        System.out.println("Стек пуст? " + stack.isEmpty()); // Выведет true
    }
}