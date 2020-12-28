package ru.sce.parser;

import java.util.List;

public interface Parser {
    List<String> parse(String sqlString);
}
