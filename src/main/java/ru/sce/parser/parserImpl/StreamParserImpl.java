package ru.sce.parser.parserImpl;

import ru.sce.data.SQLDefinition;
import ru.sce.parser.Parser;

import java.io.StringReader;

public class StreamParserImpl implements Parser {

    private int openBrCn = 0;
    private int lastPos = 0;

    private String ctx = null;
    private StringBuilder phraseBuilder = new StringBuilder();
    private SQLDefinition result = new SQLDefinition();

    @Override
    public SQLDefinition parse(String sqlString) {

        StringReader reader = new StringReader(Utils.replaceNewLineToken(sqlString,Tokens.SPACE_TOKEN));
        try {
            int c = 0;

            while (c != -1) {
                c = reader.read();
                char sym = (char) c;

                sym = spaceTokenProc(sym);
                sym = commaTokenProc(sym);
                sym = bracketTokenProc(sym);
                sym = endTokenProc(sym);
                phraseBuilder.append(sym);
            }
        } catch (Exception e) {
        }

        return result;
    }

    private char endTokenProc(char sym){
        if (sym == Tokens.END_STRING_TOKEN){
            result.add(ctx,phraseBuilder.toString());
        }
        return sym;
    }

    private char bracketTokenProc(char sym) {
        if (sym == Tokens.OPEN_BRACKET_TOKEN) {
            openBrCn++;
        } else if (sym == Tokens.CLOSE_BRACKET_TOKEN) {
            openBrCn--;
        }
        return sym;
    }

    private char spaceTokenProc(char sym) {
        char res = sym;
        if (openBrCn == 0 && sym == Tokens.SPACE_TOKEN) {
            String word = phraseBuilder.substring(lastPos).trim().toLowerCase();

            if (word.length() > 0) {
                if (SQLReservedWords.isReservedWord(word)) {
                    result.add(ctx, phraseBuilder.substring(0, lastPos));
                    phraseBuilder.setLength(0);
                    ctx = word;
                    res = (char) 0;
                }
            } else {
                res = (char) 0;
            }

            lastPos = phraseBuilder.length();
        }
        return res;
    }

    private char commaTokenProc(char sym) {
        if (openBrCn == 0 && sym == Tokens.COMMA_TOKEN) {
            result.add(ctx, phraseBuilder.toString());
            phraseBuilder.setLength(0);
            lastPos = phraseBuilder.length();
            return (char) 0;
        }
        return sym;
    }
}
