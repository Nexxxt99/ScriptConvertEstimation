package ru.sce.estimator;

import ru.sce.data.SQLDefinition;

public interface Estimator {
    int estimate(SQLDefinition definition);
}
