package tokenizer;

public class Token {

    public enum TokenType {Atom, Keyword, Identifier, IntegerLitteral, StringLitteral, EOF};

    public static final String[] atoms = new String[]{
            ";", ":", "!", ";", ",", "*", "+", "-", "/", "%", "&", "[", "]",
            "{", "}", "(", ")", "=", "?", ".", "<", ">" // . <--
    };

    public static final String[] keywords = new String[]{
            "int", "while", "for", "else", "return"
    };
    public final TokenType type;

    public final String value;

    public Token(TokenType type, String value){
        this.type  = type;
        this.value = value;
    }
    public String getString(){
        return "{" + type.toString() +
                ", " + value + "}";
    }

}
