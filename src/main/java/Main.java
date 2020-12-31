import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String [] args) {
        System.out.println("Hello World !");
        repl();
    }


    // Lancement de la boucle interactive
    public static void repl() {
        Stack <Double> stack = new Stack<Double>(); // create the stack for numbers
        Scanner input = new Scanner(System.in);
         Operator op = new Operator(); // initialise the map for operation
        while(true) {
            display(); // print ">"
            String number = input.nextLine(); // get the line of user
            if(isOperator(number, op)){ // if the input is a operator and unless 2 number in stack
                if (stack.size() > 1) {
                    double y = stack.pop(); // take the second element
                    double x = stack.pop(); // take the first element
                    double result = op.op_map.get(number).apply(x,y); // result of the calcul between 2 numbers
                    stack.push(result); // push the result at the top of stack
                }
                else { // error operator need 2 numbers
                    System.out.println("invalid calculus : operator need 2 numbers");
                    continue;
                }
            }
            else {
                if(isDouble(number)) { // we change the string to double and store it in stack
                    double d = stringToDouble(number);
                    stack.push(d);
                }
                else
                    continue;
            }
            displayStack(stack); // print the top of the stack
        }
//        input.close();
    }

    // check if the string is a known operator
    public static boolean isOperator(String s, Operator op) {
        return op.op_map.containsKey(s);
    }

    // check if the string is a double
    public static boolean isDouble(String s) throws NumberFormatException {
        try {
            final double v = Double.parseDouble(s);
            return true;
        }
        catch (Exception e) {
            System.out.println("Please type a double");
            return false;
        }
    }

    // change sinng to double
    public static double stringToDouble(String s)  {
            return Double.parseDouble(s);
    }

    // change double to string
    public static String doubleToString(double d) {
        return String.valueOf(d);
    }

    // print the symbol >
    public static void display(){
        System.out.print("> ");
    }

    // print the number at the top of the stack
    public static void displayStack(Stack<Double> stack){
        System.out.println(stack.peek());
    }
}
