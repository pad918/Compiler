package Parser;

import Parser.combiner.FunctionCombiner;
import Parser.combiner.ItemCombiner;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class FunctionItemParser extends ItemParser{
    private static ArrayList<State> stateGraph;

    //Must be static, or infinite recursions will happen!
    static {
        //Define the states and the transisitons that are valid!
        stateGraph = new ArrayList<State>();
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

        // Add all states to the graph (not necessary...) TO BE REMOVED!
        stateGraph.add(finalState);
        stateGraph.add(bodyState);
        stateGraph.add(parseSingleArg);
        stateGraph.add(parseAllArgs);
        stateGraph.add(argList);
        stateGraph.add(start);

    }
    public FunctionItemParser(){
        currentState = stateGraph.get(stateGraph.size()-1);
    }
}
