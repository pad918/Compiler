import Parser.ExpressionItemParser;
import Parser.ExpressionItemParser.BasicExpressionCombiner;
import Parser.ItemParser;
import Parser.combiner.ItemCombiner;
import Parser.item.ExpressionItem;
import Parser.item.IdentifierItem;
import Parser.item.Item;
import Parser.item.LiteralItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class ExpressionItemParserTest extends ItemParser {
    @Test
    void testBasicExpressionCombiner(){
        BasicExpressionCombiner comb = new BasicExpressionCombiner();
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new LiteralItem(LiteralItem.LiteralType.Integer, 10));
        try{
            Item i = comb.combine(items);
            assertTrue(i instanceof ExpressionItem);
        }
        catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testBasicExpression(){
        String basicExpr = "i 10 j k l mmm 100 111111";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            while(1<tokens.size()) {
                ExpressionItemParser e = new ExpressionItemParser();
                Item it = e.parse(tokens);
                System.out.println(it);
            }
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void testParaExpression(){
        String basicExpr = "((((((((((((((((((((bigParaIdent)))))))))))))))))))) (10000) (anExampleVariable)";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            while(1<tokens.size()) {
                ExpressionItemParser e = new ExpressionItemParser();
                Item it = e.parse(tokens);
                System.out.println(it);
            }
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void testIndexCombiner(){
        ArrayList<Item> stack = new ArrayList<Item>();
        stack.add(new IdentifierItem("arr"));
        stack.add(new ExpressionItemParser.BasicExpressionCombiner());
        stack.add(new LiteralItem(LiteralItem.LiteralType.Integer, 10));
        //stack.add(new ExpressionItemParser.IndexItemCombiner());
        ItemCombiner b = new ExpressionItemParser.IndexItemCombiner();
        try {
            Item i = b.combine(stack);
            System.out.println(i);
        }
        catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void testIndexExpression(){
        String basicExpr = "arr[(10)] arr1[arr2[arr3[arr4]]]";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            while(1<tokens.size()) {
                ExpressionItemParser e = new ExpressionItemParser();
                Item it = e.parse(tokens);
                System.out.println(it);
            }
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }
}