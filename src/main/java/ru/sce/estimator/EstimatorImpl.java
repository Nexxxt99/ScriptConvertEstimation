package ru.sce.estimator;

import ru.sce.data.SQLDefinition;
import ru.sce.scorer.Scorer;

import java.util.List;

public class EstimatorImpl implements Estimator {
    private Scorer expressionScorer;
    private Scorer tableScorer;
    private Scorer predicateScorer;
    /*rest*/

    @Override
    public int estimate(SQLDefinition definition) {
        int totalScore = 0;

        totalScore += scoreExpressions(definition);
        totalScore += scoreTables(definition);
        totalScore += scorePredicates(definition);

        /*rest*/

        return totalScore;
    }

    private int scoreExpressions(SQLDefinition def) {
        return score(expressionScorer, def.expressions);
    }

    private int scoreTables(SQLDefinition def) {
        return score(tableScorer, def.tables);
    }

    private int scorePredicates(SQLDefinition def) {
        return score(predicateScorer, def.predicates);
    }

    private int score(Scorer scorer, List<String> expressions) {
        int result = 0;
        if (scorer != null && expressions != null)
            for (String expr : expressions)
                result += scorer.score(expr);

        return result;
    }

    public static class EstimatorBuilder {
        private EstimatorImpl estimator;

        public EstimatorBuilder() {
            estimator = new EstimatorImpl();
        }

        public EstimatorBuilder setExpressionScorer(Scorer s) {
            estimator.expressionScorer = s;
            return this;
        }

        public EstimatorBuilder setTableScorer(Scorer s) {
            estimator.tableScorer = s;
            return this;
        }

        public EstimatorBuilder setPredicateScorer(Scorer s) {
            estimator.predicateScorer = s;
            return this;
        }

        public Estimator build() {
            return estimator;
        }
    }
}
