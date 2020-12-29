package ru.sce.scorer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionsScorerImpl implements Scorer {

    private Map<String,Integer> ORACLE_SPEC_EXPRESSIONS = new HashMap<>(){{
        put("to_date",10);
        put("to_char",7);
        put("to_number",7);
        put("keep",7);
        put("concat",1);
        put("coalesce",1);
    }};

    @Override
    public int score(String expression) {
        int result =0;
        String lowerExpression = expression.toLowerCase();

        for(Map.Entry<String,Integer> e:ORACLE_SPEC_EXPRESSIONS.entrySet()){
            if (lowerExpression.contains(e.getKey()))
                result += e.getValue();
        }
        return result;
    }
}
