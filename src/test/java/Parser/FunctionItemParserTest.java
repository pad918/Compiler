package Parser;

import Parser.item.Item;
import org.junit.jupiter.api.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FunctionItemParserTest extends ItemParser {
    @Test
    void noArgs(){
        String fun = "example()";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(fun);

        FunctionItemParser parser = new FunctionItemParser();
        try{
            Item i = parser.parse(tokens);
            System.out.println(i);
            assertTrue(i instanceof FunctionItem);
        } catch (ParseException pe){
            fail();
        }
    }

    @Test
    void withArguments(){
        String fun = "example(x, y, zzz)";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(fun);

        FunctionItemParser parser = new FunctionItemParser();
        try{
            Item i = parser.parse(tokens);
            System.out.println(i);
            assertTrue(i instanceof FunctionItem);
        } catch (ParseException pe){
            fail();
        }
    }

}