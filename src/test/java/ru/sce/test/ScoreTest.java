package ru.sce.test;

import org.junit.jupiter.api.Test;
import ru.sce.data.SQLDefinition;
import ru.sce.estimator.Estimator;
import ru.sce.estimator.EstimatorImpl;
import ru.sce.inputDataProvider.ScriptProvider;
import ru.sce.inputDataProvider.SimpleScriptProvider;
import ru.sce.parser.Parser;
import ru.sce.parser.parserImpl.StreamParserImpl;
import ru.sce.scorer.ExpressionsScorerImpl;
import ru.sce.scorer.PredicatesScorerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    private Estimator estimator = new EstimatorImpl.EstimatorBuilder()
            .setExpressionScorer(new ExpressionsScorerImpl())
            .setPredicateScorer(new PredicatesScorerImpl())
            .build();

    private Parser parser = new StreamParserImpl();

    @Test
    void simple(){
        String sqlScript = "select deptno, dname from dept";

        SQLDefinition def = parser.parse(sqlScript);
        assertEquals(estimator.estimate(def),0);
    }

    @Test
    void difficult(){
        String sqlScript = "select d.dname as department, count(e.empno) as total_employees, " +
                "(select min(id) from emp) as t,\n"+
                "max(ename||' ('||job||')') keep (dense_rank first order by hiredate desc) last_hired_employee\n" +
                "from dept d, emp e\n" +
                "where d.deptno = e.deptno(+)";

        SQLDefinition def = parser.parse(sqlScript);
        assertEquals(estimator.estimate(def),17);
    }
}
