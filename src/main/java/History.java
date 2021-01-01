import java.util.LinkedList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class History{
    static LinkedList <Double> history;
    static Map <String, String> valeurs;
    static Map <String, Function <Integer,Double> > cmd;
    static Stack <Double> stack;

    public History(Stack s){
        history = new LinkedList <Double> ();
        valeurs = new HashMap<>();
        cmd= new HashMap<>();
        stack = s;
        initCmds();
    }

    public static void initCmds() {
//        cmd.put("hist",(i) -> histRPN(i));
//        cmd.put("pile",(i) -> pile(i));
//        cmd.put("!",(c) -> store(c));
//        cmd.put("?",(c) -> getVal(c));
        cmd.put("hist",(i) -> {System.out.println("hist " + i); return 2.2;});
        cmd.put("pile",(i) -> {System.out.println("pile " + i); return 2.2;});
        cmd.put("!",(c) -> {System.out.println("store " + c) ; return 2.2;});
        cmd.put("?",(c) -> {System.out.println("get " + c) ; return 2.2;});
    }
//    public void save(double res){
//        history.addLast(res);
//    }
//
//    public void histRPN(int i) {
//        if(!history.empty()) System.out.println("the history is empty");
//        if(i < 0) System.out.println(history.get(history.size() - i));
//        else System.out.println(history.get(i));
//    }
//
//    public void pile(int i){
//        Stack <String> acc = new Stack<String>();
//        while (stack.size() - 1) acc.push(stack.pop);
//        String d = stack.peek();
//        System.println(d);
//        while (!s.isEmpty()) stack.push(s.pop());
//        stack.push(d);
//    }
//
//    public void store(String str){
//        map.put(str.substring(1),stack.pop());
//        history.addLast(stack.peek());
//
//    }
//
//    public void pop2(){
//        history.removeLast();
//        history.removeLast();
//    }

    //    ************ FILTRE ***********
    public static boolean isInteger(String s) throws NumberFormatException {
        try {
            final int v = Integer.parseInt(s);
            return true;
        }
        catch (Exception e) {
            System.out.println("Illegal argument, please type a number");
            return false;
        }
    }

    public static boolean isCmd(String s) {
        if (s.length() > 4) return cmd.containsKey(s.substring(0, 4));
        return cmd.containsKey(s.substring(0, 1));
    }

    public static int getNumber(String s)throws Exception { // prend un string (123) et rend 123
        if(s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
            System.out.println("Syntax Error");
            throw new Exception();
        }
        String snum = s.substring(1,s.length()-1);
        if(!isInteger(snum)) throw new Exception(); //check si l'argument est bien un int
        return Integer.parseInt(snum);
    }

    public static boolean histOrPile (String s){
        String command = s.substring(0, 4);
        int i;
        //verifie que la taille est suffisament grande pour que la commande soit bonne soit de la forme hist(x);
        if (s.length() < 7){
            System.out.println("Syntax Error");
            return false;
        }
        try {
            i = getNumber(s.substring(4, s.length()));
            cmd.get(command).apply(i);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public Boolean whichCmd(String s){
        if (s.charAt(0) == '!' || s.charAt(0) == '?'){
            System.out.println("not implemented");
            return true;
        }
        else return histOrPile(s);
    }

//************** TEMPORAIRE *****************

    public static void display(){
        System.out.print("> ");
    }

//    public static void main(String[] args){
//        initCmds();
//
//        Scanner input = new Scanner(System.in);
//        Operator op = new Operator(); // initialise the map for operation
//        while(true) {
//            display(); // print ">"
//            String command = input.nextLine(); // get the line of user
//            if(isCmd(command)) {
//
//                    if (command.charAt(0) == '!' || command.charAt(0) == '?') System.out.println("not implemented");
//                    else histOrPile(command);
//            }
//            else {
//                System.out.println("unknown command");
//                continue;
//            }
////            displayStack(stack); // print the top of the stack
//        }
////        input.close();
//    }

}
