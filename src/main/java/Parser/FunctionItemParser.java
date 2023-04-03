package Parser;

import Parser.combiner.FunctionCombiner;
import Parser.combiner.ItemCombiner;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class FunctionItemParser extends ItemParser{

    @Override
    public void init() {
        //Define the states and the transisitons that are valid!
        State finalState = new State(new Transition[0], true, new ItemCombiner[]{
                new FunctionCombiner()
        });
        State bodyState = new State(new Transition[]{
                new Transition(finalState, null) // <-- TO BE CHANGED!
        });
        State parseSingleArg = new State(new Transition[]{
                new Transition(bodyState, new ExpectAtomParser(")"))
        });
        State parseAllArgs = new State(new Transition[]{
                new Transition(bodyState, new ExpectAtomParser(")"))
        });
        parseAllArgs.addTransition(
                new Transition(parseSingleArg, new IdentifierItemParser())
        );
        parseSingleArg.addTransition(
                new Transition(parseAllArgs, new ExpectAtomParser(","))
        );

        State argList = new State(new Transition[]{
                new Transition(parseAllArgs, new ExpectAtomParser("("))
        });

        State start = new State(new Transition[]{
                new Transition(argList, new IdentifierItemParser())
        });

        initialState = start;
        currentState = initialState;
    }
}
