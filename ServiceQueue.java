public class ServiceQueue {
    private Student[] queue;
    private int front, rear, count, capacity;

    public ServiceQueue(int capacity) {
        this.capacity = capacity;
        queue = new Student[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull() { return count == capacity; }

    private void resize() {
        int newCapacity = capacity * 2;
        Student[] newQueue = new Student[newCapacity];
        for (int i = 0; i < count; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }
        queue = newQueue;
        capacity = newCapacity;
        front = 0;
        rear = count - 1;
    }

    public void enqueue(Student s) {
        if (isFull()) resize();
        rear = (rear + 1) % capacity;
        queue[rear] = s;
        count++;
    }

    public Student dequeue() {
        if (isEmpty()) return null;
        Student s = queue[front];
        front = (front + 1) % capacity;
        count--;
        return s;
    }

    public Student peek() {
        if (isEmpty()) return null;
        return queue[front];
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Waiting students (front to rear):");
        for (int i = 0; i < count; i++) {
            System.out.println(queue[(front + i) % capacity]);
        }
    }
}