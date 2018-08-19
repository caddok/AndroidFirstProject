package com.example.georgi.allaboutthemoney.utils.models;

public class MonthlySummary {
    public String month;
    public int income;
    public int taxes;

    public MonthlySummary() {

    }

    public MonthlySummary(String month) {
        this.month = month;
    }

    public MonthlySummary(String month, int income, int taxes) {
        this.month = month;
        this.income = income;
        this.taxes = taxes;
    }
}
