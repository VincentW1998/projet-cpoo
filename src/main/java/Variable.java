public class Variable {
    private final String name;

    public Variable(String n){
        name = n;
    }

    public String getName() {
        String n = name;
        return n;
    }

    @Override
    public String toString() {
        return getName();
    }

}
