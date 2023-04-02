package Parser.combiner;

import Parser.item.Item;

import java.text.ParseException;
import java.util.ArrayList;

// Combines items on a stack into another type of item
public abstract class ItemCombiner extends Item {

    public abstract Item combine(ArrayList<Item> stack) throws ParseException;

    @Override
    public String toString() {
        return "ITEM COMBINER, ERROR";
    }
}
