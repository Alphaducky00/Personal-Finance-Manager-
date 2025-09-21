# Personal Finance Manager

A Java-based console application for managing personal income and expense transactions with file storage and editing capabilities.

## Features

### 1. Transaction Management
- **Add Transactions**: Record income or expense transactions with amount, date, and type
- **Input Validation**: Ensures valid dates, positive amounts, and proper formatting
- **Dual File Storage**: Automatically saves income and expense transactions to separate files

### 2. File Operations
- **Read Files**: View complete transaction history from income or expense files
- **File Validation**: Handles empty files and displays appropriate messages
- **Clear Files**: Option to completely clear transaction history with confirmation prompts

### 3. Search & Filter
- **Date-based Search**: Find transactions by specific dates (yyyy-MM-dd format)
- **Smart Paragraph Detection**: Groups related transaction data together
- **Multiple Results**: Displays all transactions matching the search criteria

### 4. Transaction Editing
- **Edit Amount**: Modify transaction amounts while preserving other details
- **Edit Dates**: Update transaction dates with format validation
- **Live Preview**: Shows changes before confirming updates
- **Undo Option**: Cancel changes during the editing process
- **File Persistence**: Updates are automatically saved back to the original files

### 5. User Interface
- **Menu-driven Navigation**: Simple numbered menu system
- **Input Validation**: Comprehensive error handling for all user inputs
- **Confirmation Prompts**: Safety checks before destructive operations
- **Clear Instructions**: Step-by-step guidance for all operations

## Technical Details

- **Language**: Java
- **Storage**: Text files (income.txt, expense.txt)
- **Date Format**: yyyy-MM-dd
- **Amount Format**: Decimal with 2 decimal places
- **Architecture**: Modular design with separate classes for different functionalities

## File Structure
```
income.txt    - Stores all income transactions
expense.txt   - Stores all expense transactions
```

## Usage
Run the main class and follow the menu prompts to:
1. Read existing transaction files
2. Add new transactions
3. Search and edit existing transactions
4. Clear transaction history
5. Exit the application

## Data Format
Each transaction is stored as a three-line paragraph:
```
Transaction type: [Income/Expense]
Transaction amount: [XX.XX]
Transaction date: [yyyy-MM-dd]
```

Transactions are separated by blank lines for easy parsing and editing.