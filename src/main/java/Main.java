import Parser.*;
import Parser.combiner.ItemCombiner;
import Parser.item.ExpressionItem;
import Parser.item.IdentifierItem;
import Parser.item.Item;
import Parser.item.LiteralItem;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.text.ParseException;
import java.util.ArrayList;

/*
*   TO DO:
*       Rewrite functionCombiner to work like a stack.
*
* */


public class Main {
    public static void main(String[] args) {
        //BlockStructure s = new BlockStructure();
        //s.parseFrom(testBlock);
        String assignment = "fun() funnt();"; //"((var1))+var2";
        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.parseTokens(assignment);

        //Print all tokens
        for (Token t: tokens) {
            System.out.println(t.getString());
        }
        try {
            for(int i =0; i<2; i++) {
                FunctionItemParser parser = new FunctionItemParser();
                //ExpressionItemParser parser = new ExpressionItemParser();
                Item parsed = parser.parse(tokens);
                System.out.println(parsed.toString());
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        System.out.println("TESTING:::");


    }
}