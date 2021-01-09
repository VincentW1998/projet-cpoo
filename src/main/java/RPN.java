import java.util.Stack;

public class RPN {

    public static boolean Rpn(String number, Operator op, Stack <Object> stack){
        int i = 1;
        switch (i){
            case 1: // operation
                if(isOperator(number, op)){ // if the input is a operator and unless 2 number in stack
                    if (stack.size() > 1)
                        return calculate(number, op, stack);
                    System.out.println("invalid calculus : operator need 2 numbers");  // error operator need 2 numbers
                    return false;
                }
                i++;

            case 2: // symbolic variables (EXT 4)
                if(number.charAt(0) == '$'){
                    return addSymbVar(number,stack);
                }
                i++;
            case 3:
                if(number.equals("subst")) {
                    return substitute(stack);
                }
                i++;

            case 4:// we change the string to double and store it in stack
                if(isDouble(number)) {
                    double d = stringToDouble(number);
                    stack.push(d);
                    return true;
                }
                i++;
        }
        return false;
    }

    /* effectue une substitution sur target */
    public static boolean substitute(Stack <Object> stack){
        Algebrique alge = new Algebrique(stack);
        Object where = stack.pop();
        if(!(where instanceof Variable)){
            System.out.println("you cannot use subst, previous output is not a variable");
            return false;
        }

        Object value = stack.pop();
        Object target = stack.pop();
        if(!(target instanceof Expression)){
            System.out.println("there is no expression to subtitute");
            return false;
        }
        if(!((Expression)target).subt((Variable)where, value)){
            System.out.println("the variable does not exist");
            return false;
        }
        if(((Expression) target).containsSymbVar())
            stack.push(target);
        else{
            alge.setExpression(target.toString());
            alge.evaluate();
        }
        return true;
    }

    /* ajoute un variable symbolique a la pile */
    public static boolean addSymbVar(String var,Stack <Object> stack){
        if(var.length() > 1){
            String variable = var.substring(1);
            if(isInteger(variable) || isDouble(variable)){
                System.out.println("you cannot use an integer or a double as a variable name, only strings are autorised");
                return false;
            }
            stack.push(new Variable(variable));
            return true;
        }
        System.out.println("no variable specified, please add the name of your variable after the $");
        return false;
    }

    // evalue le calcul
    public static boolean calculate(String operator, Operator op, Stack <Object> stack){
        Object y = stack.pop(); // take the second element
        Object x = stack.pop(); // take the first element
        if(x instanceof Double && y instanceof Double) {
            Double res = op.op_map.get(operator).apply((Double) x, (Double) y); // result of the operation between 2 numbers
            stack.push(res); // push the result at the top of stack
            return true;
        }
        Expression o = new Expression(x,y,operator);
        stack.push(o);
        return true;
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
            return false;
        }
    }

    // change sinng to double
    public static double stringToDouble(String s)  {
        return Double.parseDouble(s);
    }

    // change double to string
    public static String doubleToString(Object d) {
        return String.valueOf(d);
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
}