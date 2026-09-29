public class ServiceTimeArray {
    private int[] times;
    private int size;

    public ServiceTimeArray(int capacity) {
        times = new int[capacity];
        size = 0;
    }

    public void add(int time) {
        if (size == times.length) {
            int[] newTimes = new int[times.length * 2];
            System.arraycopy(times, 0, newTimes, 0, size);
            times = newTimes;
        }
        times[size++] = time;
    }

    public int[] getTimes() {
        int[] copy = new int[size];
        System.arraycopy(times, 0, copy, 0, size);
        return copy;
    }

    public int size() { return size; }
}