package ru.sce.parser.parserImpl;

import java.util.Arrays;
import java.util.List;

public class SQLReservedWords {

    public static List<String> structureWords = Arrays.asList("select", "from", "where", "group", "order", "and", "or");

    public static String SELECT = "select";
    public static String FROM = "from";
    public static String WHERE = "where";
    public static String GROUP_BY = "group";
    public static String ORDER_BY = "order";

    public static boolean isReservedWord(String word){
        return structureWords.contains(word);
    }
}
