package ru.sce;

import ru.sce.inputDataProvider.SimpleTextScriptProvider;
import ru.sce.inputDataProvider.ScriptProvider;
import ru.sce.parser.Parser;
import ru.sce.parser.parserImpl.SimpleParserImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScriptProvider sc = new SimpleTextScriptProvider();
        String sqlScript = sc.getScript();
        System.out.println(sqlScript);

        Parser p = new SimpleParserImpl();
        List res = p.parse(sqlScript);
        for (int i=0;i<res.size();i++)
            System.out.println(i + ":" + res.get(i));


    }
}
