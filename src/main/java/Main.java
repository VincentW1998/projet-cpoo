import java.util.Stack;

public class Main {
    public static void main(String [] args) {
        Stack <Double> stack = new Stack<Double>(); // create the stack for numbers
        REPL repl = new REPL(stack);
        repl.run();
    }
}
