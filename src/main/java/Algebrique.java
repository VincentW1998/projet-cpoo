import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.util.Map;
import java.util.Stack;

public class Algebrique {
    String line; // line of user
    double result; // stock the result
    Map <String, Double> variables; // map of variables
    private Stack<Double> stack; // stack of result

    /* CONSTRUCTOR */
    Algebrique(Stack<Double> s) { //package-private
        this.stack = s;
    }

    /* GETTERS && SETTERS */
    public double getResult() {
        return result;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    // evaluate the algebric calculus without variables
    public boolean evaluate()  throws UnknownFunctionOrVariableException {
        try {
            result = new ExpressionBuilder(line)
                    .build().evaluate();
            stack.push(result);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}
