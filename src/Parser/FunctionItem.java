package Parser;

import Parser.item.IdentifierItem;
import Parser.item.Item;
import Parser.item.LiteralItem;

/*
* Should have:
*   IdentifierItem: Name
*   IdentifierItem: Arguments (what about type info?)
*
* */
public class FunctionItem extends Item {
    IdentifierItem name;
    IdentifierItem[] arguments;

    public FunctionItem(IdentifierItem name, IdentifierItem[] arguments){
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        String str = "Function: " + name + "\n";
        for (IdentifierItem arg: arguments) {
            str += "\tArg: " + arg + "\n";
        }
        return str;
    }
}
