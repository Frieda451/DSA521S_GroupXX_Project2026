import java.util.Random;
import java.util.Arrays;

public class SortingExperiment {
    public static void run() {
        int[] sizes = {20, 50, 100, 500};
        System.out.printf("%-15s %-10s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        for (int size : sizes) {
            int[] original = new int[size];
            Random rand = new Random(42);
            for (int i = 0; i < size; i++) original[i] = rand.nextInt(1000);

            runSort("Selection Sort", size, original, 1);
            runSort("Insertion Sort", size, original, 2);
            runSort("Merge Sort", size, original, 3);
            runSort("Quick Sort", size, original, 4);
        }
        int[] almostSorted = new int[100];
        for (int i = 0; i < 100; i++) almostSorted[i] = i;
        int[] positions = {10, 25, 40, 60, 80};
        for (int pos : positions) {
            if (pos + 1 < almostSorted.length) {
                int temp = almostSorted[pos]; almostSorted[pos] = almostSorted[pos+1]; almostSorted[pos+1] = temp;
            }
        }
        System.out.println("\nAlmost-sorted test (n=100):");
        runSort("Selection Sort", 100, almostSorted, 1);
        runSort("Insertion Sort", 100, almostSorted, 2);
        runSort("Merge Sort", 100, almostSorted, 3);
        runSort("Quick Sort", 100, almostSorted, 4);
    }

    private static void runSort(String name, int size, int[] original, int algo) {
        int[] arr = Arrays.copyOf(original, original.length);
        long start = System.nanoTime();
        SortingAlgorithms.Result res = null;
        switch (algo) {
            case 1: res = SortingAlgorithms.selectionSort(arr); break;
            case 2: res = SortingAlgorithms.insertionSort(arr); break;
            case 3: res = SortingAlgorithms.mergeSort(arr); break;
            case 4: res = SortingAlgorithms.quickSort(arr); break;
        }
        long end = System.nanoTime();
        System.out.printf("%-15s %-10d %-15d %-15d%n", name, size, res.comparisons, (end - start));
    }
}