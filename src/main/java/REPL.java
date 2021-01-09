import java.util.Scanner;
import java.util.Stack;

public class REPL {
    private Stack stack;
    private Operator op;
    private History history;

    public REPL(Stack s){
        stack = s;
        op = new Operator(); // initialise the map for operation
        history = new History(stack);
    }

    // Lancement de la boucle interactive
    public void run() {
        Scanner input = new Scanner(System.in);
        while(true) {
            display(); // print ">"
            String cmd = input.nextLine(); // get the line of user
            if(checkCmd(cmd)) {
                history.save();
                displayStack();
            }
            else continue;
        }
    }

    public boolean checkCmd(String cmd){
        if(RPN.isOperator(cmd, op) || RPN.isDouble(cmd) || cmd.charAt(0) == '$' || cmd.equals("subst"))
            return RPN.Rpn(cmd, op, stack);
        if(history.isCmd(cmd))
            return history.whichCmd(cmd);
        System.out.println("Enter a double or a valid command !");
        return false;
    }


    // print the symbol >
    public static void display(){
        System.out.print("> ");
    }

    // print the number at the top of the stack
    public void displayStack(){
        if(stack.peek() instanceof Variable){
            System.out.println( stack.peek().toString());
        }
        else{
            System.out.println(stack.peek());
        }
    }
}
