import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {
    List<T> heap = null;
    
    public PriorityQueue() {
        this(1);
    }

    public PriorityQueue(int size) {
        heap = new ArrayList<>(size);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void clear() {
        heap.clear();
    }

    public boolean contains(T elem) {
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(elem)) {
                return true;
            }
        }

        return false;
    }

    public void add (T elem) {
        if (elem.equals(null)) {
            throw new IllegalArgumentException();
        }

        heap.add(elem);
        int lastIndex = size() - 1;
        swim(lastIndex);
    }

    private void swim(int k) {
        int parent = (k - 1) / 2;

        while (k > 0 && less(parent, k)) {
            swap (parent, k);
            k = parent;
            parent = (k - 1) / 2;
        }
    } 

    private void swap(int i, int j) {
        T elemOne = heap.get(i);
        T elemTwo = heap.get(j);

        heap.set(i, elemTwo);
        heap.set(j, elemOne);
    }

    private boolean less(int i, int j) {
        T elemOne = heap.get(i);
        T elemTwo = heap.get(j);

        return elemOne.compareTo(elemTwo) <= 0;
    }

    public boolean remove(T elem) {
        if (isEmpty()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(elem)) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    private T removeAt(int k) {
        if (isEmpty()) {
            return null;
        }

        int lastIndex = size() - 1;
        T removedData = heap.get(k);
        swap(k, lastIndex);
        heap.remove(lastIndex);

        sink(k);

        return removedData;
    }

    private void sink(int k) {
        int heapSize = size();

        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;

            if (right < heapSize && less(right, left)) {
                smallest = right;
            }

            if (left >= heapSize || less(k, smallest)) {
                break;
            }

            swap(k, smallest);
            k = smallest;
        }
    }

    public boolean isMinHeap(int k) {
        int heapSize = size();

        if (k >= heapSize) {
            return true;
        }

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (left < heapSize && !less(k , left)) {
            return false;
        }

        if (right < heapSize && !less(k , right)) {
            return false;
        }

        return isMinHeap(left) && isMinHeap(right);
    }

    public T peak() {
        if (isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        return removeAt(0);
    }
}
