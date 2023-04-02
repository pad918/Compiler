package tokenizer.tokenfactory;

import tokenizer.Token;

public class KeywordTokenFactory extends TokenFactory{
    @Override
    public Token tryParseNext(String code) {
        code = code.trim();
        for (String key: Token.keywords) {
            if(code.equals(key)){
                return new Token(Token.TokenType.Keyword, key);
            }
        }
        return null;
    }
}
