package com.expense_track;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        final String PATH = "/home/carlos/Desktop/expense_tracker/expense_track/src/main/resources/db/db.json";
        
        // Users can add an expense with a description and amount.
        final String TEXT = "ENTER A COMMAND: \n-add:\nupdate-\ndelete\n-list\n-summary\n-summary(month)\n";
        boolean flag = true;
        Scanner sc = new Scanner(System.in);

        // Get the last ID from the JSON file or initialize to 0 if the file doesn't exist
        int currentID = Expense.getLastId(PATH);

        while (flag) {
            System.out.println(TEXT);
            String[] command = sc.nextLine().split(" ");

            if (command.length > 0) {
                if (command[0].trim().equals("exit")) {
                    flag = false;
                } else {
                    if (command[0].trim().equals("add")) {
                        System.out.println("\n---ADD");
                        if (Arrays.asList(command).contains("--description")
                                && Arrays.asList(command).contains("--amount")) {
                            String commandsAsString = String.join(" ", command);

                            // Regular expressions to extract description and amount
                            Pattern patternDescription = Pattern.compile("--description\\s+\"(.*?)\"");
                            Matcher matcherDescription = patternDescription.matcher(commandsAsString);
                            Pattern patternAmount = Pattern.compile("--amount\\s+\"(.*?)\"");
                            Matcher matcherAmount = patternAmount.matcher(commandsAsString);

                            if (matcherDescription.find() && matcherAmount.find()) {
                                String description = matcherDescription.group(1); // Extract the content inside quotes
                                int amount = -1;
                                try{
                                    amount = Integer.parseInt(matcherAmount.group(1));
                                }catch(Exception e){
                                    System.out.println("PLEASE ENTER A VALID amount");
                                }
                                if (amount >= 0) {
                                    Expense ex = new Expense(description, amount, PATH);
                                    currentID = ex.saveExpense(currentID);
                                    System.out.println("EXPENSE: " + ex);
                                }
                            } else {
                                System.out.println("No description or amount found.");
                            }
                        } else {
                            System.out.println("PLEASE INSERT ALL THE VALUES");
                        }
                    }
                }
            }
            flag = false;
        }
        sc.close();
    }
}
