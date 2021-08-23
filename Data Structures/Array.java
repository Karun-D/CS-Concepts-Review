@SuppressWarnings("unchecked")
public class Array<T> {
    private T[] arr;
    private int capacity = 0;
    private int len = 0;

    public Array() {
        this(16);
    }

    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    public void clean() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }

        len = 0;
    }

    public void add(T elem) {
        // If the capacity is exceeded, double the capacity
        if (len + 1 >= capacity) {
            if (capacity == 0) {
                capacity++;
            } else {
                capacity *= 2;
            }

            // Create a new array with double the capacity
            T[] new_arr = (T[]) new Object[capacity];

            // Copy over the original elements to the new array, and overwrite the original
            // array
            for (int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }

            arr = new_arr;
        }

        arr[len++] = elem;
    }

    public T removeAt(int index) {
        T data = arr[index];

        // Shift elements over to the left by 1 position to overwrite element of
        // interest
        for (int i = index; i < len - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[--len] = null;

        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    public int indexAt(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(Object obj) {
        return indexAt(obj) != -1;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < len; i++) {
            output += arr[i].toString() + " ";
        }

        return output;
    }
}
