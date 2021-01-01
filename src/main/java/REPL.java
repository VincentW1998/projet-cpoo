import java.util.Scanner;
import java.util.Stack;

public class REPL {

    // Lancement de la boucle interactive
    public static void repl(Stack stack) {
        Scanner input = new Scanner(System.in);
        Operator op = new Operator(); // initialise the map for operation
        History history = new History();
        while(true) {
            display(); // print ">"
            String number = input.nextLine(); // get the line of user
            if(RPN.Rpn(number,op,stack)){
                displayStack(stack);
            }
            else continue;
        }
//        input.close();
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
