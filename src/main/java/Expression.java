import java.util.HashMap;
import java.util.Map;

public class Expression {
  private Map<String,Object> operands;
    String Operator;
  public Expression(Object x, Object y, String o)  {
      operands = new HashMap<>();
      operands.put("x",x);
      operands.put("y",y);
      Operator = o;
  }

  public void setX(Object x){
      operands.replace("x",x);
  }

  public void setY(Object y){
      operands.replace("y",y);
  }

  /* check si l'expression contiens une variable symbolique */
  public boolean containsSymbVar(){
      Object x = operands.get("x");
      Object y = operands.get("y");
      if(x instanceof Variable || y instanceof Variable) return true;
      if(x instanceof Expression)
          if(((Expression) x).containsSymbVar()) return true;
      if(y instanceof Expression)
          if(((Expression) y).containsSymbVar()) return true;
      return false;
  }

  /* effectue la substitution */
  public boolean subt(Variable v, Object value){
      Object x = operands.get("x");
      Object y = operands.get("y");
      if(x instanceof Variable)
          if(((Variable)x).getName().equals(v.getName())){
              setX(value);
              return true;
          }
      if(x instanceof Expression)
          if (((Expression) x).subt(v, value)) return true;

      if(y instanceof Variable)
          if(((Variable)y).getName().equals(v.getName())){
              setY(value);
              return true;
          }
      if(y instanceof Expression)
          if(((Expression) y).subt(v,value)) return true;
      return false;
  }

    @Override
    public String toString(){
        String str = "(";
        Object x = operands.get("x"); // first element
        Object y = operands.get("y"); // second

        if(x instanceof Variable) str += ((Variable) x).getName(); //value of first operand
        else if (x instanceof Expression) str += x.toString(); //string of value of first
        else str += RPN.doubleToString(x);

        str += Operator;

        if(y instanceof Variable) str += ((Variable) y).getName(); // value of second operand
        else if (y instanceof Expression) str += y.toString();
        else str += RPN.doubleToString(y);
        return str + ")";
    }


}
