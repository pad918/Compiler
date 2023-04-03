package Parser;

import Parser.combiner.FunctionCombiner;
import Parser.combiner.ItemCombiner;
import Parser.item.*;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class ExpressionItemParser extends ItemParser{

    /*
    * Combines simple expressions that only contains one expression, no multi expr.
    *   This includes (EXPR), INTEGER, STRING, e.t.c.
    * */
    public static class BasicExpressionCombiner extends ItemCombiner{
        @Override
        public Item combine(ArrayList<Item> stack) throws ParseException {
            //Creates one expression item from either of the following:
            //  1. One expr item
            //  2. One integer item    (which is an expression item)
            //  3. One identifier item (which is an expression item)
            Item combined = null;
            while (combined==null){
                Item top = stack.get(stack.size()-1);
                stack.remove(top);
                if(top instanceof ItemCombiner) {
                    stack.add(((ItemCombiner) top).combine(stack));
                } else if(top instanceof ExpressionItem){
                    combined = top;
                }
            }
            return combined;
        }
    }

    public static class IndexItemCombiner extends ItemCombiner{
        @Override
        public Item combine(ArrayList<Item> stack) throws ParseException {
            //Creates one index expression from two expressions,
            //  in the form expr[expr], for example arr[10]
            IndexItem combined = null;
            ExpressionItem index = null;
            while (combined==null){
                Item top = stack.get(stack.size()-1);
                stack.remove(top);
                if(top instanceof ItemCombiner) {
                    stack.add(((ItemCombiner) top).combine(stack));
                } else if(top instanceof ExpressionItem){
                    if(index==null)
                        index = (ExpressionItem) top;
                    else{
                        combined = new IndexItem((ExpressionItem) top, index);
                    }
                }
            }
            return combined;
        }
    }

    @Override
    public void init() {
        //Define the states and the transitions that are valid!

        State start = new State(new Transition[0]);
        initialState = start;

        State done = new State(new Transition[0], true);

        State closeExpression = new State(new Transition[]{
                new Transition(done, null)
        }, new ItemCombiner[]{
                new BasicExpressionCombiner()
        });
        State para2 = new State(new Transition[]{
                new Transition(closeExpression, new ExpectAtomParser(")"))
        });
        State para1 = new State(new Transition[]{
                new Transition(para2, new ExpressionItemParser()) // <-- PROBLEMBARNET!
        });

        // Start must exist before para1...
        start.addTransition(new Transition(closeExpression, new LiteralParser()));
        start.addTransition(new Transition(closeExpression, new IdentifierItemParser()));
        start.addTransition(new Transition(para1, new ExpectAtomParser("(")));

        currentState = initialState;
    }

}
