package com.example.georgi.allaboutthemoney.utils.repository.base;

import java.util.List;
import java.util.function.Consumer;

public interface Repository<T> {
    void getAllFromQuery(Consumer<List<T>> action);

    /*void getAllExpensesForCurrentDay(String date, Consumer<List<T>> action);

    void getAllExpensesForTheWeek(String date, Consumer<List<T>> action);

    void getAllExpensesForTheMonth(String monthNumber, Consumer<List<T>> action);
*/
    void addDream(T dream,Consumer<T> action);

    void addExpense(T expense,Consumer<T> action);

    void addSummary(T summary,Consumer<T> action);
}
