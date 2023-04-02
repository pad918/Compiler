package Parser;

import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

/*
* Must contain: An exception that can be thrown, token list,
* the next state to goto if transition succeeds.
* */
public class Transition {
    private final ItemParser parser;
    private final State nextState;
    public final String name;

    public Transition(State nextState, ItemParser parser){
        this("", nextState, parser);
    }
    public Transition(String name, State nextState, ItemParser parser){
        this.name = name;
        this.nextState = nextState;
        this.parser = parser;
    }


    public Item parse(ArrayList<Token> tokens) throws ParseException {
        if(parser==null)
            return null;
        Item i = parser.parse(tokens);
        return i;
    }
    public State getNextState(){
        return nextState;
    }
}
