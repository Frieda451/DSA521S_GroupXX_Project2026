public class DailyStatistics {
    public static void displayStatistics(ServiceTimeArray arr) {
        int[] times = arr.getTimes();
        int n = times.length;
        if (n == 0) {
            System.out.println("No service times recorded.");
            return;
        }
        int total = 0;
        int highest = times[0];
        int lowest = times[0];
        int over10 = 0;
        for (int i = 0; i < n; i++) {
            total += times[i];
            if (times[i] > highest) highest = times[i];
            if (times[i] < lowest) lowest = times[i];
            if (times[i] > 10) over10++;
        }
        double average = (double) total / n;
        System.out.println("Total students served: " + n);
        System.out.println("Total service time (min): " + total);
        System.out.println("Average service time (min): " + average);
        System.out.println("Highest service time (min): " + highest);
        System.out.println("Lowest service time (min): " + lowest);
        System.out.println("Services longer than 10 min: " + over10);
    }
}