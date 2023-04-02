package Parser.item;

public class IndexItem extends ExpressionItem {
    // In the form arr[index];
    ExpressionItem arr;
    ExpressionItem index;

    public IndexItem(ExpressionItem arr, ExpressionItem index){
        this.arr = arr;
        this.index = index;
    }

    @Override
    public String toString() {
        return "IndexItem: {\n\t" + arr + "[" + index + "]\n}";
    }
}
