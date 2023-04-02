package Parser.item;

public class LiteralItem extends Item{

    public static enum LiteralType{Integer};

    private final LiteralType type;

    private int value;

    public LiteralItem(LiteralType type, int value){
        this.type  = type;
        this.value = value;
    }

    public String toString(){
        return "Literal {" + type.toString() + ": " + value + "}";
    }
}
