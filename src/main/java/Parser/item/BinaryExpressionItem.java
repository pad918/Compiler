package Parser.item;

public class BinaryExpressionItem extends ExpressionItem{
    ExpressionItem leftExpr, rightExpr;
    BinaryOperatorItem operator;

    public BinaryExpressionItem(ExpressionItem l, ExpressionItem r, BinaryOperatorItem b){
        this.rightExpr = r;
        this.leftExpr  = l;
        this.operator  = b;
    }

    @Override
    public String toString() {
        return "("+leftExpr.toString() + ") " + operator.toString() + " (" + rightExpr.toString()+")";
    }
}
