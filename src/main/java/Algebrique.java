import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.util.*;

public class Algebrique {
    String expression; // line of user
    Object result; // stock the result
    private Stack<Object> stack; // stack of result


    /* CONSTRUCTOR */
    Algebrique(Stack<Object> s) { //package-private
        this.stack = s;
    }

    /* GETTERS && SETTERS */
    public void setExpression(String line) {
        this.expression = line;
    }

    // evaluate the algebric calculus without variables
    public boolean evaluate()  throws UnknownFunctionOrVariableException {
        if (expression.length() > 1) { // if the length > 1 we can evaluate this expression
            try {
                result = new ExpressionBuilder(expression)
                        .build().evaluate();
                stack.push(result);
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
