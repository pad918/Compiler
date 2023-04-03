import Parser.ExpressionItemParser;
import Parser.ExpressionItemParser.BasicExpressionCombiner;
import Parser.ItemParser;
import Parser.combiner.ItemCombiner;
import Parser.item.*;
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
        String basicExpr = "((((ExampleVariable))))";
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

    @Test
    void testBinaryExpressionCombiner() throws ParseException{
        ArrayList<Item> stack = new ArrayList<Item>();
        stack.add(new IdentifierItem("arr"));
        stack.add(new BinaryOperatorItem("+"));
        stack.add(new LiteralItem(LiteralItem.LiteralType.Integer, 10));
        //stack.add(new ExpressionItemParser.IndexItemCombiner());
        ItemCombiner b = new ExpressionItemParser.BinaryExpressionItemCombiner();
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
    void testBinaryExpression(){
        String basicExpr = "var1*(10*var2)/(var3-100)";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            //while(1<tokens.size()) {
                ExpressionItemParser e = new ExpressionItemParser();
                Item it = e.parse(tokens);
                System.out.println(it);
            //}
            assertTrue(it instanceof BinaryExpressionItem);
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void functionCallCombiner(){
        ArrayList<Item> stack = new ArrayList<Item>();
        stack.add(new IdentifierItem("fun1"));
        stack.add(new ExpressionItemParser.FunctionIdentifierCombiner());
        stack.add(new IdentifierItem("x"));
        stack.add(new IdentifierItem("y"));
        stack.add(new IdentifierItem("z"));
        //stack.add(new ExpressionItemParser.IndexItemCombiner());
        ItemCombiner b = new ExpressionItemParser.FunctionCallItemCombiner();
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
    void functionCallItem(){
        String basicExpr = "fun(x, y, z)";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            ExpressionItemParser e = new ExpressionItemParser();
            Item it = e.parse(tokens);
            System.out.println(it);
            assertTrue(it instanceof FunctionCallItem);
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

    @Test
    void advancedExpression(){
        String basicExpr = "50 * fun((100)*x, 100/y, 50*x*x)";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(basicExpr);

        try {
            ExpressionItemParser e = new ExpressionItemParser();
            Item it = e.parse(tokens);
            System.out.println(it);
            assertTrue(it instanceof BinaryExpressionItem);
        }catch (ParseException pe){
            pe.printStackTrace();
            fail();
        }
    }

}