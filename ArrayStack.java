/**
 * ArrayStack.java  (used by Task A3 - Postfix Expression Evaluation)
 *
 * A LIFO stack of numbers implemented with an array and a "top" index.
 * java.util.Stack / Deque are NOT used.
 *
 *   index:  0    1    2   ...
 *          [ 5 ][ 5 ][   ]
 *                 ^ top = 1
 */
public class ArrayStack {
    private double[] items;
    private int top; // index of the top element, -1 when empty

    public ArrayStack(int capacity) {
        items = new double[capacity];
        top = -1;
    }

    /** push(): place a value on top. The array doubles in size if it is full. */
    public void push(double value) {
        if (top == items.length - 1) {
            grow();
        }
        top++;
        items[top] = value;
    }

    /** pop(): remove and return the top value. Throws if the stack is empty (underflow). */
    public double pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow - not enough operands");
        }
        double value = items[top];
        top--;
        return value;
    }

    /** peek(): return the top value without removing it. */
    public double peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return items[top];
    }

    public boolean isEmpty() { return top == -1; }
    public int size()        { return top + 1; }

    /** Manual resize - copies element by element (no Arrays.copyOf). */
    private void grow() {
        double[] bigger = new double[items.length * 2];
        for (int i = 0; i < items.length; i++) {
            bigger[i] = items[i];
        }
        items = bigger;
    }

    /** Shows the stack bottom -> top, e.g. [5, 5] */
    public String contents() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(PostfixEvaluator.format(items[i]));
            if (i < top) sb.append(", ");
        }
        sb.append("]  <- top");
        return sb.toString();
    }
}
