package com.expense_track;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Users can add an expense with a description and amount.
        final String TEXT = "ENTER A COMMAND: \n-add:\nupdate-\ndelete\n-list\n-summary\n-summary(month)\n";
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
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
                                if (amount >= 0){
                                    
                                }

                            } else {
                                System.out.println("No description or ammount found.");
                            }

                        } else {
                            System.out.println("PLEASE INSERT ALL THE VALUES");
                        }
                    }
                }
            }
            flag = false;
            // add --description "DESCRIPTION" --amount "2"
        }
        sc.close();
    }
}
