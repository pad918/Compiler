package tokenizer.tokenfactory;

import tokenizer.Token;

public abstract class TokenFactory {
    public abstract Token tryParseNext(String code);
}
