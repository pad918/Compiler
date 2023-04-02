package Parser;

import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class ExpressionItemParser extends ItemParser{
    private static ArrayList<State> stateGraph;

    static {
        //Define the states and the transisitons that are valid!
        stateGraph = new ArrayList<State>();
        State end = new State(new Transition[0], true);
        State para2 = new State(new Transition[]{
           new Transition(end, new ExpectAtomParser(")"))
        });
        State para1 = new State(new Transition[]{
           new Transition(para2, new ExpressionItemParser()) // Can not be done in static block!!!
        });
        State start = new State(new Transition[]{
                new Transition(end, new LiteralParser()),
                new Transition(end, new IdentifierItemParser()),
                new Transition(para1, new ExpectAtomParser("("))
        });

        // Add all states to the graph
        stateGraph.add(start);
        stateGraph.add(para1);
        stateGraph.add(para2);
        stateGraph.add(end);
    }

    public ExpressionItemParser(){

    }

    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        currentState = stateGraph.get(0);
        /*
        * To do:
        *   Make this recursive so that it can find patterns like:
        *       (exp+exp), which in itself is a type of expression.
        * */
        ArrayList<Item> preParsed = preParse(tokens);
        // Pre-parsed contains a single expression item, which may be
        // able to be expanded further. Try this by using the "advanced transitions"

        return preParsed.get(0);
    }
}
