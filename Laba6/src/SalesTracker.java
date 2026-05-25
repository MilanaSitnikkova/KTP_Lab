import java.util.EmptyStackException;

class GenericStack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public GenericStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = (T) elements[--size];
        elements[size] = null;
        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length * 2;
            Object[] newElements = new Object[newSize];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }
}

public class SalesTracker {
    public static void main(String[] args) {
        System.out.println("--- Тестирование стека со строками (String) ---");
        GenericStack<String> stringStack = new GenericStack<>();

        stringStack.push("Первый");
        stringStack.push("Второй");
        stringStack.push("Третий");

        System.out.println("Текущий размер стека: " + stringStack.size());
        System.out.println("Элемент на вершине (peek): " + stringStack.peek());

        System.out.println("Извлекаем (pop): " + stringStack.pop());
        System.out.println("Извлекаем (pop): " + stringStack.pop());
        System.out.println("Новая вершина стека: " + stringStack.peek());

        stringStack.push("Четвертый");
        System.out.println("Извлекаем после добавления (pop): " + stringStack.pop());
        System.out.println("Извлекаем последний (pop): " + stringStack.pop());
        System.out.println("Пуст ли стек? " + stringStack.isEmpty());


        System.out.println("\n--- Тестирование стека с числами (Integer) ---");
        GenericStack<Integer> integerStack = new GenericStack<>();

        integerStack.push(100);
        integerStack.push(200);
        integerStack.push(300);

        System.out.println("Элемент на вершине: " + integerStack.peek());
        while (!integerStack.isEmpty()) {
            System.out.println("Удален элемент: " + integerStack.pop());
        }

        try {
            integerStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("\nУспешно обработано исключение: Попытка вызвать pop() для пустого стека.");
        }
    }
}