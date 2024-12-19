package com.expense_track;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Expense {
    private String description;
    private int amount;
    private String path;
    private int id;
    private LocalDate date;

    public int getId() {
        return id;
    }

    public Expense(String description, int amount, String path) {
        this.description = description;
        this.amount = amount;
        this.path = path;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Expense [description=" + description + ", amount=" + amount + ", path=" + path + ", id=" + id
                + ", date=" + date + "]";
    }

    public Expense() {
    }

    public Expense(String path) {
        this.path = path;
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

    public void setId(int id) {
        this.id = id;
    }

    // Save expense by appending to the existing list of expenses
    public int saveExpense(int id) {
        int currentId = id;
        ObjectMapper mapper = new ObjectMapper();

        // Register the JavaTimeModule to handle LocalDate
        mapper.registerModule(new JavaTimeModule());

        List<Expense> expensesList = new ArrayList<>();
        try {
            // Try reading the existing expenses list
            File dbFile = new File(this.path);
            if (dbFile.exists()) {
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {
                });
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



    public static List<Expense> getListBd(String path) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register the module for Java 8 date/time types

        List<Expense> expensesList = new ArrayList<>();

        try {
            // Read the file and map it to a list of expenses
            File dbFile = new File(path);
            if (dbFile.exists()) {
                // Deserialize the file content into a list of Expense objects
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return expensesList;
    }

    // Get the last ID from the file
    public static int getLastId(String path) {
        // Initialize ObjectMapper and register the JavaTimeModule for LocalDate
        // handling
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register the module for Java 8 date/time types

        List<Expense> expensesList = new ArrayList<>();
        int lastIdExpense = 0;

        try {
            // Read the file and map it to a list of expenses
            File dbFile = new File(path);
            if (dbFile.exists()) {
                // Deserialize the file content into a list of Expense objects
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {
                });

                if (!expensesList.isEmpty()) {
                    // Get the last expense's ID
                    lastIdExpense = expensesList.get(expensesList.size() - 1).id;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return lastIdExpense;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void getExpenseById(int parsedId) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register the module for Java 8 date/time types

        List<Expense> expensesList = new ArrayList<>();

        try {
            // Read the file and map it to a list of expenses
            File dbFile = new File(path);
            if (dbFile.exists()) {
                // Deserialize the file content into a list of Expense objects
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        if (!expensesList.isEmpty()){
            for (Expense ex: expensesList){
                if (ex.getId() == parsedId){
                    this.setAmount(ex.getAmount());
                    this.setDate(ex.getDate());
                    this.setId(ex.getId());
                    this.setPath(ex.getPath());
                    this.setDescription(ex.getDescription());
                }
            }
        }
    }
    public void updateExpense() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register the module for Java 8 date/time types

        List<Expense> expensesList = new ArrayList<>();

        File dbFile = new File(path);
        
        try {
            // Read the file and map it to a list of expenses
            if (dbFile.exists()) {
                // Deserialize the file content into a list of Expense objects
                expensesList = mapper.readValue(dbFile, new TypeReference<List<Expense>>() {
                });
                mapper.writerWithDefaultPrettyPrinter().writeValue(dbFile, new ArrayList<Expense>());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        if (!expensesList.isEmpty()){
            for (Expense ex: expensesList){
                if (ex.getId() == this.getId()){
                    ex.setAmount(this.getAmount());
                    ex.setDate(this.getDate());
                    ex.setId(this.getId());
                    ex.setPath(this.getPath());
                    ex.setDescription(this.getDescription());
                }
            }
        }
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(dbFile, expensesList);
        } catch (StreamWriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
