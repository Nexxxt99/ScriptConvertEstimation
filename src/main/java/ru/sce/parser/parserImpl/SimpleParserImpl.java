package ru.sce.parser.parserImpl;

import ru.sce.parser.Parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleParserImpl implements Parser {

    char space_token = ' ';
    char new_line_token = '\n';
    char comma_token = ',';
    char open_bracket_token = '(';
    char close_bracket_token = ')';

    List<String> structureWords = Arrays.asList("select","from","where","group","order");

    @Override
    public List<String> parse(String sqlString) {

        List<String> result = new ArrayList<>();

        StringReader reader = new StringReader(sqlString.replace(new_line_token,space_token));
        int c;

        StringBuilder phraseBuilder = new StringBuilder();
        StringBuilder wordBuilder = new StringBuilder();

        int pos = 0;
        try {
            while ((c = reader.read()) != -1) {
                char sym = (char) c;

                if (sym == space_token || sym == new_line_token) {
                    if (structureWords.contains(wordBuilder.toString().toLowerCase().trim())){
                        result.add(phraseBuilder.toString());
                        phraseBuilder.setLength(0);

                        result.add(wordBuilder.toString());

                    } else {
                        phraseBuilder.append(wordBuilder.toString());
                    }
                    wordBuilder.setLength(0);
                } else if (sym == open_bracket_token){
                    int cn = 0;
                    while (true){
                        if (sym == open_bracket_token)
                            cn++;
                        if (sym == close_bracket_token)
                            cn--;

                        wordBuilder.append(sym);
                        sym = (char) reader.read();
                        pos++;
                        if (cn == 0)
                            break;

                    }
                } else if (sym == comma_token){
                    phraseBuilder.append(wordBuilder.toString());
                    result.add(phraseBuilder.toString());
                    phraseBuilder.setLength(0);
                    wordBuilder.setLength(0);
                    continue;
                }

                wordBuilder.append(sym);
                pos++;
            }
        } catch (Exception e) {}

        return result;
    }


}
