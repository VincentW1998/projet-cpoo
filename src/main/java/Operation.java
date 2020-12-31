import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Operation  {

    public Map <String, BiFunction<Double,Double, Double>> op_map = new HashMap<>();

    public Operation (){
        op_map.put("+", Double::sum);
        op_map.put("-", (x, y) -> x - y);
        op_map.put("*", (x, y) -> x * y);
        op_map.put("/", (x, y) -> x / y);
    }






}



