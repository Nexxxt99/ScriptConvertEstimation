package ru.sce.parser;

import ru.sce.data.SQLDefinition;

public interface Parser {
    SQLDefinition parse(String sqlString);
}
