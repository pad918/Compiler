package Parser;

import Parser.item.Item;
import Parser.item.LiteralItem;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

// To be changed!
public class LiteralParser extends ItemParser{
    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        Token token = tokens.get(0);
        if(token==null)
            throw new ParseException("Null token", 0);

        if(token.type!= Token.TokenType.IntegerLitteral)
            throw new ParseException("Token not of the expected type: Integer literal", 0);

        tokens.remove(0);
        int val = Integer.parseInt(token.value);
        return new LiteralItem(LiteralItem.LiteralType.Integer, val);
    }
}
