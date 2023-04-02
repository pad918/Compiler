package Parser;

import Parser.item.IdentifierItem;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class IdentifierParser extends ItemParser{

    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        Token token = tokens.get(0);
        if(token==null)
            throw new ParseException("Null token", 0);

        if(token.type!=Token.TokenType.Identifier)
            throw new ParseException("Wrong token type, expected an Identifier", 0);

        return new IdentifierItem(token.value);
    }
}
