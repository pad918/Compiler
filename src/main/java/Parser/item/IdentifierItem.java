package Parser.item;

public class IdentifierItem extends ExpressionItem{

    public static class FunctionIdentifierItem extends IdentifierItem{

        public FunctionIdentifierItem(IdentifierItem i){
            super(i.name);
        }
        public FunctionIdentifierItem(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "FunctionIdentifier: " + name;
        }
    }
    String name;
    public IdentifierItem(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Identifier: " + name;
    }
}
