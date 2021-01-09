import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.util.*;

public class Algebrique implements Parser{
    String expression; // line of user
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
    public String getExpression() {
        return expression;
    }

    public void setExpression(String line) {
        this.expression = line;
    }
//
//    Function multipleVar = new Function("multipleVar", variables.size()) {
//        @Override
//        public double apply(double... args) {
//
//        }
//    }

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

    public boolean evaluateVar(String variable) {
        try {
            if(variables.containsKey(variable)) {
                result = new ExpressionBuilder(expression)
                        .variable(variable)
                        .build()
                        .setVariable(variable, variables.get(variable))
                        .evaluate();
                stack.push(result);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> parser(String s) {
        String arr[] = s.split(" ");
        return Arrays.asList(arr);
    }

    @Override
    public List<String> getVar(List<String> ls) {
        return null;
    }
}
