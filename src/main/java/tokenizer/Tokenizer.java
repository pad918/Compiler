package tokenizer;

import tokenizer.tokenfactory.*;

import java.util.ArrayList;
import java.util.Arrays;

// TO DO -->
// Token separetors include: Any whitespace,
// any transition from normal characters to special characters!
// ex =a or l? or


// Special character can only form single tokens!
public class Tokenizer {
    private static final String normalCharacters = "abcdefghijklmnopqrstuvwxyz";
    private static final String specialCharacters = "!?-+=[]{}()<>;:,";
    private TokenFactory[] factories;

    public Tokenizer(){
        factories = new TokenFactory[]{
                new KeywordTokenFactory(),
                new AtomTokenFactory(),
                new I32TokenFactory(),
                new IdentifierTokenFactory()
        };
    }

    public ArrayList<Token> parseTokens(String code){
        ArrayList<Token> tokens = new ArrayList<>();

        StringBuilder toParse = new StringBuilder(code);
        StringBuilder tokenBuilder = new StringBuilder();

        while (!toParse.isEmpty()){
            tokenBuilder.append(toParse.charAt(0));

            boolean startWithSpecialCharacter = specialCharacters.contains(tokenBuilder.substring(0, 1));
            if (toParse.length()==1 || startWithSpecialCharacter ||
                    isTransition(toParse.charAt(0), toParse.charAt(1))){
                String tokenToParse = tokenBuilder.toString().trim();
                if(!tokenToParse.equals("")){
                    tokens.add(tryParse(tokenToParse));
                }
                tokenBuilder = new StringBuilder();
            }
            toParse.delete(0, 1); // Remove first char
        }
        //ADD EOF
        tokens.add(new Token(Token.TokenType.EOF, ""));
        // Fin cast i java...
        return tokens;
    }

    private Token tryParse(String tokenString){
        Token parsed = null;
        for (TokenFactory factory: factories) {
            parsed = factory.tryParseNext(tokenString);
            if(parsed!=null)
                return parsed;
        }
        return null; // Throw exception?
    }

    private boolean isTransition(char curr, char next){
        if(next==' ')
            return true;

        boolean currentIsSpecial = specialCharacters.contains(new String(new char[]{curr}));

        boolean nextIsSpecial = specialCharacters.contains(new String(new char[]{next}));

        if(currentIsSpecial != nextIsSpecial)
            return true;

        return false;
    }

}
