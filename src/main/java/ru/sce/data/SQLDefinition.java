package ru.sce.data;

import ru.sce.parser.parserImpl.SQLReservedWords;

import java.util.ArrayList;
import java.util.List;

//TODO joins

public class SQLDefinition {
    public List<String> expressions;
    public List<String> tables;
    public List<String> predicates;
    public List<String> groups;
    public List<String> orders;

    public void add(String ctx, String value){
        if (SQLReservedWords.SELECT.equals(ctx)){
            this.addExpression(value);
        }

        if (SQLReservedWords.FROM.equals(ctx)){
            this.addTables(value);
        }

        if (SQLReservedWords.WHERE.equals(ctx)){
            this.addPredicates(value);
        }

        if (SQLReservedWords.GROUP_BY.equals(ctx)){
            this.addGroups(value);
        }

        if (SQLReservedWords.ORDER_BY.equals(ctx)){
            this.addOrders(value);
        }
    }

    private void addExpression(String value){
        if (this.expressions == null)
            this.expressions = new ArrayList<>();
        this.expressions.add(value);
    }

    private void addTables(String value){
        if (this.tables == null)
            this.tables = new ArrayList<>();
        this.tables.add(value);
    }

    private void addPredicates(String value){
        if (this.predicates == null)
            this.predicates = new ArrayList<>();
        this.predicates.add(value);
    }

    private void addGroups(String value){
        if (this.groups == null)
            this.groups = new ArrayList<>();
        this.groups.add(value);
    }

    private void addOrders(String value){
        if (this.orders == null)
            this.orders = new ArrayList<>();
        this.orders.add(value);
    }

}
