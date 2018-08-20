package com.example.georgi.allaboutthemoney.utils.models;

public class Dream {
    public String name;
    public double cost;
    public double progress;
    public String period;
    public String category;

    public Dream() {
        //public constructor needed for firebase
    }

    public Dream(String name, double cost,double progress, String period, String category) {
        this.name = name;
        this.cost = cost;
        this.progress = progress;
        this.period = period;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category: " + category +
                "\n"
                + "Name: " +  name
                + "\n"
                + "Cost: " + cost +
                "\n"
                + "Due date: " + period +
                "\n";
    }
}
