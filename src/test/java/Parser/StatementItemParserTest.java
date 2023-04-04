package Parser;

import Parser.item.BinaryExpressionItem;
import Parser.item.IdentifierItem;
import Parser.item.Item;
import Parser.item.StatementItem;
import org.junit.jupiter.api.Test;
import Parser.StatementItemParser.*;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StatementItemParserTest extends ItemParser {
    @Test
    void basicStatementCombiner(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new IdentifierItem("var1"));
        items.add(new ExpressionStatementCombiner());
        StatementCombiner combiner = new StatementCombiner();
        try {
            Item stamenet = combiner.combine(items);
            System.out.println(stamenet);
        }
        catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void expressionStatmentParsing(){
        String basicExpr = "(x*y*z/1000)*fun(1000);";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            StatementItemParser e = new StatementItemParser();
            Item it = e.parse(tokens);
            System.out.println(it);
            assertTrue(it instanceof StatementItem);
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void returnStatementParsing(){
        String basicExpr = "return fun(fun(x))*y*z;";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            StatementItemParser e = new StatementItemParser();
            Item it = e.parse(tokens);
            System.out.println(it);
            assertTrue(it instanceof StatementItem.ReturnStatement);
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

}