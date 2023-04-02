package Parser.item;

public class IdentifierItem extends Item{
    String name;
    public IdentifierItem(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Identifier: " + name;
    }
}
