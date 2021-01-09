import java.util.HashMap;
import java.util.Map;

public class Expression {
  private Map<String,Object> operands;
  String Operator;
  Operator op;
  public Expression(Object x, Object y, String o)  {
      operands = new HashMap<>();
      operands.put("x",x);
      operands.put("y",y);
      Operator = o;
      op = new Operator();
  }

  public void setX(Object x){
      operands.replace("x",x);
  }

  public void setY(Object y){
      operands.replace("y",y);
  }

  public boolean contains(Variable v){
      Object x = operands.get("x");
      Object y = operands.get("y");
      if(x instanceof Variable)
          if(((Variable)x).getName().equals(v.getName())) return true;
      if(x instanceof Expression)
          if (((Expression) x).contains(v)) return true;

      if(y instanceof Variable)
          if(((Variable)y).getName().equals(v.getName())) return true;
      if(y instanceof Expression)
          if(((Expression) y).contains(v)) return true;
      return false;
  }

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
        Object x = operands.get("x");
        Object y = operands.get("y");

        if(x instanceof Variable) str += ((Variable) x).getName();
        else if (x instanceof Expression) str += x.toString();
        else str += RPN.doubleToString((Double) x);

        str += Operator;

        if(y instanceof Variable) str += ((Variable) y).getName();
        else if (y instanceof Expression) str += y.toString();
        else str += RPN.doubleToString((Double) y);
        return str + ")";
    }


}
