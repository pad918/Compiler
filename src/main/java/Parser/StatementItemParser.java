package Parser;

import Parser.combiner.ItemCombiner;
import Parser.item.ExpressionItem;
import Parser.item.Item;
import Parser.item.StatementItem;

import java.text.ParseException;
import java.util.ArrayList;

public class StatementItemParser extends ItemParser {

    public static class ReturnStatementCombiner extends ExpressionStatementCombiner{
        @Override
        public Item combine(ArrayList<Item> stack) throws ParseException {
            Item i = super.combine(stack);
            return new StatementItem.ReturnStatement((StatementItem.ExpressionStatement) i);
        }
    }

    public static class ExpressionStatementCombiner extends ItemCombiner{
        @Override
        public Item combine(ArrayList<Item> stack) throws ParseException {
            StatementItem combined = null;
            while(combined==null) {
                if(stack.isEmpty())
                    throw new ParseException("Can not combine 0 items", 0);
                //Combine
                Item top = stack.get(stack.size() - 1);
                stack.remove(top);
                if (top instanceof ItemCombiner) {
                    stack.remove(top);
                    ((ItemCombiner) top).combine(stack);
                } else if (top instanceof ExpressionItem) {
                    combined = new StatementItem.ExpressionStatement((ExpressionItem) top);
                } else {
                    throw new ParseException("Found unexpected item: " + top, 0);
                }
            }
            return combined;
        }
    }

    public static class StatementCombiner extends ItemCombiner{

        @Override
        public Item combine(ArrayList<Item> stack) throws ParseException {
            if(stack.isEmpty())
                throw new ParseException("Can not combine 0 items", 0);

            //Combine
            Item top = stack.get(stack.size()-1);
            if(top instanceof ItemCombiner) {
                stack.remove(top);
                stack.add(((ItemCombiner) top).combine(stack));
            }

            if(stack.size()!=1)
                throw new ParseException("Statement combiner found more than one statement", 0);

            Item item = stack.get(0);
            if(!(item instanceof StatementItem))
                throw new ParseException("Found item that is not a statment item", 0);

            return item;
        }
    }

    @Override
    public void init() {
        State start = new State(new Transition[0]);
        State done = new State(new Transition[0], true);
        initialState = start;

        State closeStatement = new State(
                new Transition[]{
                        new Transition(done, null)
                },
                new ItemCombiner[]{
                        new StatementCombiner()
                }
        );
        /********* COMPUND STATEMENTS (BLOCKS) ***********/
        //State compund

        /*********       BASIC STATEMENTS!     ************/

        State expressionStatement = new State(
                new Transition[]{
                        new Transition(closeStatement, new ExpectAtomParser(";"))
                },
                new ItemCombiner[]{
                        new ExpressionStatementCombiner()
                }
        );

        State returnStatement2 = new State(
                new Transition[]{
                        new Transition(closeStatement, new ExpectAtomParser(";"))
                },
                new ItemCombiner[]{
                        new ReturnStatementCombiner()
                }
        );

        State returnStatement1 = new State(
                new Transition[]{
                        new Transition(returnStatement2, new ExpressionItemParser())
                }
        );


        //Add start transistions!
        start.addTransition(new Transition(expressionStatement, new ExpressionItemParser()));
        start.addTransition(new Transition(returnStatement1, new ExpectAtomParser("return")));
        start.addTransition(new Transition(closeStatement, new ExpectAtomParser(";")));

        currentState = initialState;
    }
}
