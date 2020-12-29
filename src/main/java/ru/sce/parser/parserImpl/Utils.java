package ru.sce.parser.parserImpl;

public class Utils {

    public static String replaceNewLineToken(String str,char token){
        return str.replace(Tokens.NEW_LINE_TOKEN,token);
    }
}
