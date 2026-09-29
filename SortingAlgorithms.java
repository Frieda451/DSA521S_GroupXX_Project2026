public class SortingAlgorithms {
    public static class Result {
        int comparisons;
        int swapsOrShifts;
        public Result(int c, int s) { comparisons = c; swapsOrShifts = s; }
    }

    public static Result selectionSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = arr[i]; arr[i] = arr[minIndex]; arr[minIndex] = temp;
                swaps++;
            }
        }
        return new Result(comparisons, swaps);
    }

    public static Result insertionSort(int[] arr) {
        int comparisons = 0, shifts = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
        return new Result(comparisons, shifts);
    }

    public static Result mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        int[] comp = new int[1];
        mergeSort(arr, temp, 0, arr.length - 1, comp);
        return new Result(comp[0], 0);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, int[] comp) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, temp, left, mid, comp);
        mergeSort(arr, temp, mid + 1, right, comp);
        merge(arr, temp, left, mid, right, comp);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, int[] comp) {
        for (int i = left; i <= right; i++) temp[i] = arr[i];
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            comp[0]++;
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }

    public static Result quickSort(int[] arr) {
        int[] comp = new int[1];
        quickSort(arr, 0, arr.length - 1, comp);
        return new Result(comp[0], 0);
    }

    private static void quickSort(int[] arr, int low, int high, int[] comp) {
        if (low < high) {
            int p = partition(arr, low, high, comp);
            quickSort(arr, low, p - 1, comp);
            quickSort(arr, p + 1, high, comp);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] comp) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comp[0]++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
}