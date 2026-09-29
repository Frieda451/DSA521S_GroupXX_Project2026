import java.util.Scanner;

class ArrayStack {
    private double[] items;
    private int top;

    public ArrayStack(int capacity) {
        items = new double[capacity];
        top = -1;
    }

    public void push(double value) {
        if (top == items.length - 1) {
            grow();
        }

        top++;
        items[top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            throw new IllegalStateException(
                "Stack underflow - not enough operands"
            );
        }

        double value = items[top];
        top--;

        return value;
    }

    public double peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    private void grow() {
        double[] bigger = new double[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            bigger[i] = items[i];
        }

        items = bigger;
    }

    public String contents() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i <= top; i++) {
            sb.append(PostfixEvaluator.format(items[i]));

            if (i < top) {
                sb.append(", ");
            }
        }

        sb.append("] <- top");

        return sb.toString();
    }
}


class PostfixEvaluator {

    public static double evaluate(String expression) {

        String[] tokens = expression.trim().split("\\s+");

        ArrayStack stack = new ArrayStack(10);

        for (String token : tokens) {

            if (isNumber(token)) {

                double number = Double.parseDouble(token);
                stack.push(number);

            } else {

                if (stack.size() < 2) {
                    throw new IllegalArgumentException(
                        "Not enough operands for operator: " + token
                    );
                }

                double right = stack.pop();
                double left = stack.pop();

                double result;

                switch (token) {

                    case "+":
                        result = left + right;
                        break;

                    case "-":
                        result = left - right;
                        break;

                    case "*":
                        result = left * right;
                        break;

                    case "/":
                        if (right == 0) {
                            throw new ArithmeticException(
                                "Cannot divide by zero"
                            );
                        }

                        result = left / right;
                        break;

                    default:
                        throw new IllegalArgumentException(
                            "Unknown operator: " + token
                        );
                }

                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException(
                "Invalid postfix expression"
            );
        }

        return stack.pop();
    }


    private static boolean isNumber(String token) {

        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static String format(double value) {

        if (value == (long) value) {
            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("POSTFIX EXPRESSION EVALUATOR");
        System.out.println("----------------------------");

        System.out.print("Enter postfix expression: ");

        String expression = input.nextLine();

        try {

            double answer = PostfixEvaluator.evaluate(expression);

            System.out.println("Result: " +
                PostfixEvaluator.format(answer));

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        input.close();
    }
}
