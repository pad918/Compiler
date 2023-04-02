package Parser;

import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class ExpectAtomParser extends ItemParser{

    String expectedAtom;

    public ExpectAtomParser(String atomName){
        this.expectedAtom = atomName;
    }
    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        Token token = tokens.get(0);
        if(token==null)
            throw new ParseException("Null token", 0);
        if(!token.value.equals(expectedAtom))
            throw new ParseException("Did not find the expected atom", 0);
        tokens.remove(0);
        return null;
    }
}
