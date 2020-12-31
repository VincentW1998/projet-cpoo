import java.util.Scanner;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.logging.MemoryHandler;

public class Main {



    public static void main(String [] args) {
        System.out.println("Hello World !");
        repl();
    }




    // Lancement de la boucle interactive
    public static void repl() {
        Stack <String> stack = new Stack<String>();
        Scanner input = new Scanner(System.in);
         Operation op = new Operation();
        while(true) {
            display();
            String number = input.nextLine();
            if(isOperation(number, op) && stack.size() > 1){ // if the input is a operation
                double y = stringToDouble(stack.pop());
                double x = stringToDouble(stack.pop());
                double result = op.op_map.get(number).apply(x,y);
                String resultStack = doubleToString(result);
                stack.push(resultStack);
            }
            else
                stack.push(number);

            displayStack(stack);

        }
//        input.close();
    }

    public static boolean isOperation(String s, Operation op) {
        return op.op_map.containsKey(s);
    }

    public static double stringToDouble(String s) {
        return Double.parseDouble(s);
    }

    public static String doubleToString(double d) {
        return String.valueOf(d);
    }



    public static void display(){
        System.out.print("> ");
    }
    public static void displayStack(Stack<String> stack){
        System.out.println(stack.peek());
    }
}
