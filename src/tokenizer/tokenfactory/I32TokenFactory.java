package tokenizer.tokenfactory;

import tokenizer.Token;

public class I32TokenFactory extends ComplexTokenFactory<Integer>{
    @Override
    public Token tryParseNext(String code) {
        code = code.trim();
        Integer val;
        try{
            val = Integer.parseInt(code);
        }
        catch (Exception e){
            return null;
        }
        return new Token(Token.TokenType.IntegerLitteral, val.toString());
    }


}
