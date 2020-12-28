package ru.sce.scorer;

import ru.sce.data.SQLDefinition;

public interface Scorer {
    int score(SQLDefinition definition);
}
