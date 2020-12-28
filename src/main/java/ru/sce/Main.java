package ru.sce;

import ru.sce.inputDataProvider.SimpleScriptProvider;
import ru.sce.inputDataProvider.ScriptProvider;
import ru.sce.parser.Parser;
import ru.sce.parser.parserImpl.StreamParserImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScriptProvider sc = new SimpleScriptProvider();
        String sqlScript = sc.getScript();
        System.out.println(sqlScript);

        Parser p = new StreamParserImpl();
        List res = p.parse(sqlScript);
        for (int i=0;i<res.size();i++)
            System.out.println(i + ":" + res.get(i));


    }
}
