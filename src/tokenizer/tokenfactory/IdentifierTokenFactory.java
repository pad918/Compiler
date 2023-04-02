package tokenizer.tokenfactory;

import tokenizer.Token;

public class IdentifierTokenFactory extends TokenFactory{
    @Override
    public Token tryParseNext(String code) {
        code = code.trim();
        //Test if identifier name is valid
        // Rules:
        //  1. no whitespace
        //  2. starts with non-special, non-number character
        //  3. ...

        return new Token(Token.TokenType.Identifier, code);
    }
}
