package Parser;

import Parser.combiner.ItemCombiner;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

/*  Solving the static state problem:
        Every parser has to have unique states to avoid
        resetting each other states and other problems.

        To avoid infinite recursion lazy initialization can
        be used to initialize the states in a parser

* */

public abstract class ItemParser {
    protected State currentState;
    protected State initialState; // should be final?
    public Item parse(ArrayList<Token> tokens) throws ParseException{
        ArrayList<Item> preParsedItems = preParse(tokens);
        Item combined = combine(preParsedItems);
        currentState = initialState; // Reset
        return combined;
    }

    // Lazy initialize the state graph to avoid infinite recursion
    // when a parser has references to parser of its own kind.
    public void init(){}

    /*
    * Goes through the states and parser collects all generated non-null items
    * The individual parser only has to put the preParsed items together to one
    * type. Ex (ident, ident, ident) --> fun(a, b) for a functionItem
    * */
    ArrayList<Item> preParse(ArrayList<Token> tokens) throws ParseException{
        ArrayList<Item> items = new ArrayList<Item>();
        if(currentState==null)
            init();
        while (!currentState.isFinalState()) {
            boolean foundTransition = false;
            for (Transition t : currentState) {

                //Try if the transition is valid or not
                try {
                    Item i = t.parse(tokens);
                    State last = currentState;
                    //if success ==> change state and save i
                    currentState = t.getNextState();
                    if(i!=null)
                        items.add(i);
                    last.addPostCombiners(items);
                    foundTransition = true;
                    break;
                } catch (ParseException pe) {}
            }
            if(!foundTransition)
                throw new ParseException("No transition was valid", 1);
        }
        //Add postCombines of the last state!
        currentState.addPostCombiners(items);
        return items;
    }

    Item combine(ArrayList<Item> stack) throws ParseException{
        //Go downwards up, NOT DONE, JUST AN EXAMPLE!
        Item last = stack.get(stack.size()-1);
        if(!(last instanceof ItemCombiner))
            throw new ParseException("Did not find an item combiner, can not parse!", 0);
        ItemCombiner combiner = (ItemCombiner) last;
        stack.remove(last);
        return combiner.combine(stack);
    }

}
