import java.util.LinkedList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class History{
    private LinkedList <Object> history;
    private Map <String, Object> variables;
    private Map <String, Function <String,Boolean> > cmd;
    private Stack <Object> stack;


    public History(Stack s){
        history = new LinkedList <> ();
        variables = new HashMap<>();
        cmd= new HashMap<>();
        stack = s;
        initCmds();
    }

    public  void initCmds() {
        cmd.put("hist",(i) -> {return histRPN(i); });
        cmd.put("pile",(i) -> {return pile(i);});
        cmd.put("!",(c) -> store(c));
        cmd.put("?",(c) -> getVal(c));
    }
    public void save(){
        if(!stack.isEmpty())
            history.addLast(stack.peek());
    }


    /* ajoute le double a la pos i dans history a la fin de history
       histOrPile se charge de l'ajouter a la pile */
    public boolean histRPN(String s){
       int i = getNumber(s);
        if(history.isEmpty()) {
            System.out.println("the history is empty");
            return false;
        }
        if(Math.abs(i) > stack.size()) {
            System.out.println("index out of bounds, please select an other number");
            return false;
        }
        if(i < 0){
           stack.push(history.get(history.size() + i ));
        }
        else {
            stack.push(history.get(i));
        }
        return true;
    }


    public boolean pile(String s){
        int i = getNumber(s);
        if(stack.isEmpty()){
            System.out.println("the stack is empty");
            return false;
        }
        Stack <Object> acc = new Stack<>();
        int target; // la position a laquelle s'arreter
        if(Math.abs(i) > stack.size()){
            System.out.println("index out of bounds, please select an other number");
            return false;
        }
        if(i < 0) target = stack.size() + i;
        else target =  i;
        while (stack.size() != target + 1) acc.push(stack.pop());
        Object d = stack.peek();
        while (!acc.isEmpty()) stack.push(acc.pop());
        stack.push(d);
        return true;
    }

    public boolean store(String str){
        if(stack.isEmpty()){
            System.out.println("the stack is empty");
            return false;
        }
        variables.put(str,stack.pop());
        return true;
    }

    public boolean getVal(String str){
        if(!variables.containsKey(str)){
            System.out.println("the variable you are trying to access does not exist");
            return false;
        }
        stack.push(variables.get(str));
        return true;
    }







    //    ************ FILTRES ***********

    public Boolean isCmd(String s) {
        if(s.equals("p")){
            dispAll();
            return false;
        }
        if(cmd.containsKey(s.substring(0, 1))) return true;
        if (s.length() > 4) return cmd.containsKey(s.substring(0, 4));
        return false;
    }

    public int getNumber(String s) { // prend un string (123) et rend 123
        String snum = s.substring(1,s.length()-1);
        return Integer.parseInt(snum);
    }

    public static boolean isInteger(String s) throws NumberFormatException {
        try {
            final int v = Integer.parseInt(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Boolean whichCmd(String s){

        if (s.charAt(0) == '!' || s.charAt(0) == '?'){
           return cmd.get(s.substring(0,1)).apply(s.substring(1));
        }
        else return histOrPile(s);
    }

    public void dispAll(){
        System.out.println("HISTORY :");
        for(int i=0;i<history.size();i++)
            System.out.print(history.get(i) + " | ");
        System.out.println("\nSTACK :");
        Stack <Object> acc = new Stack<>();
        while (!stack.isEmpty())acc.push(stack.pop());
        while (!acc.isEmpty()){
            System.out.print(acc.peek()+ " | ");
            stack.push(acc.pop());
        }
        System.out.println("\nVariables Stockes : " + variables.toString());
    }

    public boolean histOrPile (String s){
        String command = s.substring(0, 4);
        int i;
        //verifie que la taille est suffisament grande pour que la commande soit bonne soit de la forme hist(x);
        if (s.length() < 7){
            System.out.println("Syntax Error");
            return false;
        }
        try {
            String arg = s.substring(4, s.length());
            if(s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') { // on verifie que la commande est bien parenthesé
                System.out.println("Syntax Error");
                return false;
            }
            arg = arg.substring(1,arg.length()-1); // on retire les parenteses
            if(!isInteger(arg)){
                System.out.println("Illegal argument, please type a number");
                return false;
            }
            if(!cmd.get(command).apply(arg)) return false;
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
