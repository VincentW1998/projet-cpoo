import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Operator {

    public Map <String, BiFunction<Double,Double, Double>> op_map = new HashMap<>();

    public Operator (){
        op_map.put("+", Double::sum);
        op_map.put("-", (x, y) -> x - y);
        op_map.put("*", (x, y) -> x * y);
        op_map.put("/", (x, y) -> x / y);
    }




}



