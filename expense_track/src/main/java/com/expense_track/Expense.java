package com.expense_track;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Expense {
    private String description;
    private int amount;
    private String path;
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Expense [description=" + description + ", amount=" + amount + ", path=" + path + ", id=" + id + "]";
    }

    public Expense(String description, int amount, String path) {
        this.description = description;
        this.amount = amount;
        this.path = path;
    }

    public Expense() {}

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

    public void setId(int id) {
        this.id = id;
    }

    // Save expense by appending to the existing list of expenses
    public int saveExpense(int id) {
        int currentId = id; 
        ObjectMapper mapper = new ObjectMapper();
        List<Expense> expensesList = new ArrayList<>();

        try {
            // Try reading the existing expenses list
            File dbFile = new File(this.path);
            if (dbFile.exists()) {
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {});
            }

            // Increment the ID for the new expense
            currentId++;
            this.setId(currentId);

            // Add the new expense to the list
            expensesList.add(this);

            // Write the updated list back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(dbFile, expensesList);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return currentId;
    }

    // Get the last ID from the file
    public static int getLastId(String path) {
        ObjectMapper mapper = new ObjectMapper();
        List<Expense> expensesList = new ArrayList<>();
        int lastIdExpense = 0;
        
        try {
            // Read the file and map it to a list of expenses
            File dbFile = new File(path);
            if (dbFile.exists()) {
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {});
                if (!expensesList.isEmpty()) {
                    lastIdExpense = expensesList.get(expensesList.size() - 1).id;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return lastIdExpense;
    }
}
