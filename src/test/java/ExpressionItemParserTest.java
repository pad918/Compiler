import Parser.ExpressionItemParser;
import Parser.ExpressionItemParser.BasicExpressionCombiner;
import Parser.ItemParser;
import Parser.item.ExpressionItem;
import Parser.item.Item;
import Parser.item.LiteralItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
            fail();
        }
    }
}