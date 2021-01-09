import java.util.Stack;

public class Main {
    public static void main(String [] args) {
        Stack <Object> stack = new Stack<Object>(); // create the stack for numbers
        REPL repl = new REPL(stack);
        repl.run();
    }
}
