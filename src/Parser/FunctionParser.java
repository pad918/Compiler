package Parser;

import tokenizer.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;

// PROTOTYPE PARSER!
public class FunctionParser {
    enum State{START, ARG_LIST, PARSE_ARGS, ARG_PARSED, BODY, DONE};
    private State state = State.START;
    public ArrayList<String> parseFunction(ArrayList<Token> tokens){
        ArrayList<String> parsed = new ArrayList<String>();
        while (state != State.DONE){
            if(tokens.isEmpty())
                throw new IllegalStateException("Ran out of tokens");

            Token token = tokens.get(0);
            switch (state){
                case START:
                        if(token.type != Token.TokenType.Identifier){
                            throw new IllegalStateException("Functions must start with an identifier");
                        }
                        state = State.ARG_LIST;
                        parsed.add("Fn: " + token.value);
                        break;
                case ARG_LIST:
                    if(!token.value.equals("("))
                        throw new IllegalStateException("Function argument list not found");
                    state = State.PARSE_ARGS;
                    break;
                case PARSE_ARGS:
                    if(token.value.equals(")")){
                        state = State.BODY;
                        break;
                    }
                    if(token.type== Token.TokenType.Identifier){
                        parsed.add("Arg: " + token.value);
                        state = State.ARG_PARSED;
                        break;
                    }
                    throw new IllegalStateException("Incorrect syntax in argument list");
                    //break;
                case ARG_PARSED:
                    if(token.value.equals(",")){
                        state = State.PARSE_ARGS;
                        break;
                    }
                    else if(token.value.equals(")")){
                        state = State.BODY;
                        break;
                    }
                    throw new IllegalStateException("Incorrect syntax in argument list");
                    //break;
                case BODY:
                    // DO DO LATER WHEN BODY EXISTS!
                    state = State.DONE;
                    break;
            }
            tokens.remove(0);
        }
        return parsed;
    }



}
