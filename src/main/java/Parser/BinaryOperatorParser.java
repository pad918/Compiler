package Parser;

import Parser.item.BinaryOperatorItem;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class BinaryOperatorParser extends ItemParser {

    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        Token token = tokens.get(0);
        Item i = new BinaryOperatorItem(token.value);
        tokens.remove(token);
        return i;
    }
}
