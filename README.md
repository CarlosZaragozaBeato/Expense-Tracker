# Expense Tracker Program
<a href="https://roadmap.sh/projects/expense-tracker">Link project</a>

A simple command-line-based expense tracker written in Java. This program allows users to manage their expenses by adding, updating, deleting, and viewing them. Additionally, it supports generating a summary of total expenses, either for all time or filtered by a specific month.

## Features

- **Add an expense**: Input a description and amount.
- **List expenses**: View all expenses with their details.
- **Update an expense**: Modify the description or amount of an existing expense.
- **Delete an expense**: Remove an expense by its ID.
- **View a summary**: Calculate and view the total amount of expenses.
- **View monthly summary**: View the total expenses for a specific month.

## Requirements

- Java 11 or higher
- An IDE or terminal that can run Java programs
- A JSON file to store the expenses (`db.json`)

## File Structure

```
expense_tracker/
├── db/
│   └── db.json      # The database file where expenses are stored
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/
│       │   │   └── expense_track/
│       │   │       ├── Expense.java  # Expense model class
│       │   │       └── Main.java     # Main program logic
└── README.md        # Project documentation
```

## Setup

1. **Clone the repository** or download the project files.
2. **Open the project** in your Java IDE (e.g., IntelliJ IDEA, Eclipse) or use a text editor and terminal.
3. Ensure that you have Java 11 or higher installed. You can check your Java version by running:

   ```bash
   java -version
   ```

4. **Run the `Main.java`** class to start the application. It will read from the `db/db.json` file, where expenses are stored.

## Commands

You can execute the following commands in the terminal/console to interact with the program.

### 1. Add an Expense
Adds a new expense with a description and amount.

```
add --description "<description>" --amount "<amount>"
```

- Example:  
  ```
  add --description "Lunch" --amount "15"
  ```

### 2. List Expenses
Lists all expenses saved in the JSON file.

```
list
```

- Example:  
  ```
  list
  ```

### 3. Update an Expense
Updates an existing expense by specifying its ID and updating the description or amount.

```
update --id <expense_id> --description "<new_description>" --amount "<new_amount>"
```

- Example:  
  ```
  update --id 1 --description "Dinner" --amount "20"
  ```

### 4. Delete an Expense
Deletes an expense by its ID.

```
delete --id <expense_id>
```

- Example:  
  ```
  delete --id 2
  ```

### 5. View Summary
Displays the total amount of all expenses.

```
summary
```

- Example:  
  ```
  summary
  ```

### 6. View Monthly Summary
Displays the total amount of expenses for a specific month.

```
summary --month <month_number>
```

- Example:  
  ```
  summary --month 12
  ```

### 7. Exit the Program
Exits the application.

```
exit
```

## JSON Database (`db.json`)

The expenses are stored in a JSON file located in the `src/main/resources/db/db.json` directory. If the file does not exist, it will be created automatically when an expense is added.

Here’s an example of how the database might look:

```json
[
  {
    "id": 1,
    "description": "Lunch",
    "amount": 15,
    "date": "2024-12-19"
  },
  {
    "id": 2,
    "description": "Dinner",
    "amount": 25,
    "date": "2024-12-19"
  }
]
```

Each expense object contains:
- **id**: Unique identifier for the expense
- **description**: Description of the expense
- **amount**: Amount of the expense
- **date**: Date of the expense (in `yyyy-mm-dd` format)

## Troubleshooting

- **Error: `Scanner closed`**  
  This error occurs if the scanner is closed before all user input has been processed. Make sure to only close the scanner after all user interactions are completed.

## Contributing

Feel free to contribute to the project by forking the repository and submitting a pull request with improvements, bug fixes, or new features.

## License

This project is open-source and available under the MIT License.

---
