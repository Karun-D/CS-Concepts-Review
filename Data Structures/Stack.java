import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class Stack<T> {
    private int len;
    private int capacity;
    private Object[] data;

    public Stack() {
        capacity = 16;
        data = new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(T elem) {
        if (len == capacity) {
            if (capacity == 0) {
                capacity++;
            } else {
                capacity *= 2;
            }

            Object[] new_arr = new Object[capacity];

            for (int i = 0; i < len; i++) {
                new_arr[i] = data[i];
            }

            data = new_arr;
        }

        data[len++] = elem;
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        T elem = (T) data[--len];
        data[len] = null;
        return elem;
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return (T) data[len - 1];
    }
}
