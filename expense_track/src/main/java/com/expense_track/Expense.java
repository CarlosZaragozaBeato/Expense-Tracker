package com.expense_track;

import java.util.List;

public class Expense {
    private String description;
    private int amount;
    private String path;

    public Expense(String description, int amount) {
        this.description = description;
        this.amount = amount;
        this.path = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }




    public static void saveExpense(Expense expense) {
        
    }

    // public List<Expense> listExpense(){

    // }

}
