package ru.sce;

import ru.sce.data.SQLDefinition;
import ru.sce.estimator.Estimator;
import ru.sce.estimator.EstimatorImpl;
import ru.sce.inputDataProvider.SimpleScriptProvider;
import ru.sce.inputDataProvider.ScriptProvider;
import ru.sce.parser.Parser;
import ru.sce.parser.parserImpl.StreamParserImpl;
import ru.sce.scorer.ExpressionsScorerImpl;
import ru.sce.scorer.PredicatesScorerImpl;
import ru.sce.scorer.TableScorerImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScriptProvider sc = new SimpleScriptProvider();
        String sqlScript = sc.getScript();

        Parser p = new StreamParserImpl();
        SQLDefinition def = p.parse(sqlScript);

        Estimator e = new EstimatorImpl.EstimatorBuilder()
                .setExpressionScorer(new ExpressionsScorerImpl())
                .setPredicateScorer(new PredicatesScorerImpl())
                .build();

        int result = e.estimate(def);

        System.out.println("Total score: " + result);
    }
}
