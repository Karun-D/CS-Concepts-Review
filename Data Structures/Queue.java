@SuppressWarnings("unchecked")
public class Queue<T> {
    private Object[] data;
    private int front;
    private int rear;

    public Queue(int capacity) {
        data = new Object[capacity];
        front = 0;
        rear = 0;
    }

    public void enqueue(T elem) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        data[rear++] = elem;
        rear = adjustIndex(rear, data.length);
    }
  
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, data.length);
        return (T) data[front++];
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        front = adjustIndex(front, data.length);
        return (T) data[front];
    }

    public int size() {
        return adjustIndex(rear + data.length - front, data.length);
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (front + data.length - rear) % data.length == 1;
    }

    private int adjustIndex(int index, int size) {
        return index >= size ? index - size : index;
    }
}
