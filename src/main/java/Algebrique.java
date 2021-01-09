import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

public class Algebrique {
    String line;
    double result;
    Map <String, Double> variables;

    Algebrique() {
    }

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
    public void testExpressionBuilder1(){
        double result = new ExpressionBuilder("cos(x)")
                .variable("x")
                .build()
                .setVariable("x", Math.PI)
                .evaluate();
        double expected = Math.cos(Math.PI);
    }

    public void evaluate() {
        result = new ExpressionBuilder(line)
                .build().evaluate();
    }
}
