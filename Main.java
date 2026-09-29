import java.util.Scanner;

public class Main {
    private static ServiceQueue queue = new ServiceQueue(10);
    private static StudentLinkedList records = new StudentLinkedList();
    private static ServiceTimeArray serviceTimes = new ServiceTimeArray(100);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCAMPUS SERVICE CENTRE");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student (remove from queue)");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record (Linked List)");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics");
            System.out.println("9. Sort service times");
            System.out.println("10. Run sorting experiment");
            System.out.println("11. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Student No: ");
                    String no = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Service Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Estimated Time (min): ");
                    int time = scanner.nextInt();
                    scanner.nextLine();
                    queue.enqueue(new Student(no, name, type, time));
                    System.out.println("Student added to queue.");
                    break;
                case 2:
                    Student served = queue.dequeue();
                    if (served != null) {
                        System.out.println("Now serving: " + served);
                        serviceTimes.add(served.getServiceTime());
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;
                case 3:
                    queue.displayQueue();
                    break;
                case 4:
                    System.out.print("Student No: ");
                    String rNo = scanner.nextLine();
                    System.out.print("Name: ");
                    String rName = scanner.nextLine();
                    System.out.print("Service Type: ");
                    String rType = scanner.nextLine();
                    System.out.print("Estimated Time (min): ");
                    int rTime = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Position (1=beginning, 0=end): ");
                    int pos = scanner.nextInt();
                    scanner.nextLine();
                    Student s = new Student(rNo, rName, rType, rTime);
                    if (pos == 0) records.insertEnd(s);
                    else records.insertStudent(s, pos);
                    System.out.println("Record added.");
                    break;
                case 5:
                    records.displayStudents();
                    break;
                case 6:
                    System.out.print("Enter Student No to search: ");
                    String searchNo = scanner.nextLine();
                    Student found = records.searchStudent(searchNo);
                    if (found != null) System.out.println("Found: " + found);
                    else System.out.println("Record not found.");
                    break;
                case 7:
                    System.out.print("Enter Student No to remove: ");
                    String delNo = scanner.nextLine();
                    if (records.deleteStudent(delNo)) System.out.println("Record removed.");
                    else System.out.println("Record not found.");
                    break;
                case 8:
                    DailyStatistics.displayStatistics(serviceTimes);
                    break;
                case 9:
                    int[] times = serviceTimes.getTimes();
                    if (times.length == 0) {
                        System.out.println("No service times to sort.");
                    } else {
                        SortingAlgorithms.quickSort(times);
                        System.out.print("Sorted service times: ");
                        for (int t : times) System.out.print(t + " ");
                        System.out.println();
                    }
                    break;
                case 10:
                    SortingExperiment.run();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}