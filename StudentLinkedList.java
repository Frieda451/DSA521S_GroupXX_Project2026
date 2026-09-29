public class StudentLinkedList {
    private StudentNode head;

    public StudentLinkedList() {
        head = null;
    }

    public void insertStudent(Student s, int position) {
        StudentNode newNode = new StudentNode(s);
        if (position <= 1 || head == null) {
            newNode.next = head;
            head = newNode;
            return;
        }
        StudentNode current = head;
        int index = 1;
        while (index < position - 1 && current.next != null) {
            current = current.next;
            index++;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public void insertBeginning(Student s) { insertStudent(s, 1); }
    public void insertEnd(Student s) {
        StudentNode newNode = new StudentNode(s);
        if (head == null) { head = newNode; return; }
        StudentNode current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    public boolean deleteStudent(String studentNo) {
        if (head == null) return false;
        if (head.data.getStudentNo().equals(studentNo)) {
            head = head.next;
            return true;
        }
        StudentNode current = head;
        while (current.next != null) {
            if (current.next.data.getStudentNo().equals(studentNo)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Student searchStudent(String studentNo) {
        StudentNode current = head;
        while (current != null) {
            if (current.data.getStudentNo().equals(studentNo)) return current.data;
            current = current.next;
        }
        return null;
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        StudentNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}