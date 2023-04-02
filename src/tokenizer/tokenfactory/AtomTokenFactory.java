package tokenizer.tokenfactory;

import tokenizer.Token;

public class AtomTokenFactory extends TokenFactory{
    @Override
    public Token tryParseNext(String code) {
        code = code.trim();
        for (String atom: Token.atoms) {
            if(atom.equals(code))
                return new Token(Token.TokenType.Atom, atom);
        }
        return null;
    }
}
