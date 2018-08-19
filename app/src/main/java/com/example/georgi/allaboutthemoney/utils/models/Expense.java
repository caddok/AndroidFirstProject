package com.example.georgi.allaboutthemoney.utils.models;

public class Expense {
    public String date;
    public String type;
    public int cost;

    public Expense() {
        //public constructor needed for firebase
    }

    public Expense(String date, String type, int cost) {
        this.date = date;
        this.type = type;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cost: " + cost +
                "\n" +
                "Date: " + date +
                "\n" + "Type: " +
                type +
                "\n";
    }
}
