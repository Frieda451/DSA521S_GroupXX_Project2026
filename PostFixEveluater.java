public class PostfixEvaluator {
    public static int evaluate(String expression) {
        ArrayStack stack = new ArrayStack(10);
        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    default: throw new IllegalArgumentException("Unknown operator: " + token);
                }
            }
        }
        return stack.pop();
    }
}