package Parser.item;

public abstract class StatementItem extends Item{
    public static class ExpressionStatement extends StatementItem{
        protected ExpressionItem expression;

        public ExpressionStatement(ExpressionItem expression) {
            this.expression = expression;
        }

        @Override
        public String toString() {
            return "statement: " + expression;
        }
    }

    public static class ReturnStatement extends ExpressionStatement{
        public ReturnStatement(ExpressionStatement e){
            super(e.expression);
        }
        @Override
        public String toString() {
            return "Return statement: " + expression;
        }
    }
}
