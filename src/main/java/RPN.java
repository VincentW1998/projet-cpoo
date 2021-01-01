import java.util.Stack;

public class RPN {
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

    public static boolean Rpn(String number, Operator op, Stack <Double> stack){
        if(isOperator(number, op)){ // if the input is a operator and unless 2 number in stack
            if (stack.size() > 1) {
                double y = stack.pop(); // take the second element
                double x = stack.pop(); // take the first element
                double result = op.op_map.get(number).apply(x,y); // result of the calcul between 2 numbers
                stack.push(result); // push the result at the top of stack
                return true;
            }
            else  // error operator need 2 numbers
                System.out.println("invalid calculus : operator need 2 numbers");
        }
        else
            if(isDouble(number)) { // we change the string to double and store it in stack
                double d = stringToDouble(number);
                stack.push(d);
                return true;
            }
        return false;
    }
}