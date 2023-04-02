package Parser.combiner;

import Parser.FunctionItem;
import Parser.IdentifierItemParser;
import Parser.item.IdentifierItem;
import Parser.item.Item;
import Parser.item.LiteralItem;

import java.text.ParseException;
import java.util.ArrayList;

//Combines a list of identifiers into a function
public class FunctionCombiner extends ItemCombiner {

    @Override
    public Item combine(ArrayList<Item> stack) throws ParseException {
        if(!(stack.get(0) instanceof IdentifierItem))
            throw new ParseException("Could not combine function, expected a Literal", 0);
        IdentifierItem name = (IdentifierItem) stack.get(0);
        stack.remove(0);
        IdentifierItem[] args = new IdentifierItem[stack.size()];
        int i=0;
        while (!stack.isEmpty()) {
            if(!(stack.get(0) instanceof IdentifierItem))
                throw new ParseException("Could not combine function, expected a Identifier", 0);
            args[i++] = (IdentifierItem) stack.get(0);
            stack.remove(0);
        }
        FunctionItem item = new FunctionItem(name, args);
        return item;
    }

    @Override
    public String toString() {
        return "FUNCTION PARSER, SHOULD BE AUTOMATICALLY REMOVED!";
    }
}
