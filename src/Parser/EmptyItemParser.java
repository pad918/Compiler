package Parser;

import Parser.item.EmptyItem;
import Parser.item.Item;
import tokenizer.Token;

import java.text.ParseException;
import java.util.ArrayList;

public class EmptyItemParser extends ItemParser{
    @Override
    public Item parse(ArrayList<Token> tokens) throws ParseException {
        return new EmptyItem();
    }
}
