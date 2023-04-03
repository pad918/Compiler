package Parser.item;

public class FunctionCallItem extends ExpressionItem{
    IdentifierItem function;
    ExpressionItem[] arguments;

    public FunctionCallItem(IdentifierItem function, ExpressionItem[] arguments){
        this.function  = function;
        this.arguments = arguments;
    }
    @Override
    public String toString() {
        String name = "FUNCTION CALL ITEM: \n" + function.toString() + "\n{\n";
        for (Item i: arguments) {
            name += "\t" + i.toString() + ",\n";
        }
        return name + "}";
    }
}
