package Parser.item;

import java.text.ParseException;

public class BinaryOperatorItem extends Item{
    public enum Operator{NONE, PLUS, MINUS, MULTIPLY, DIVIDE}
    Operator type;

    public BinaryOperatorItem(String name) throws ParseException{
        switch (name){
            case "+":
                type = Operator.PLUS;
                break;
            case "-":
                type = Operator.MINUS;
                break;
            case "*":
                type = Operator.MULTIPLY;
                break;
            case "/":
                type = Operator.DIVIDE;
                break;
            default:
                throw new ParseException("name " + name + " is not a binary operator", 0);
        }
    }
    @Override
    public String toString() {
        return type.toString();
    }
}
