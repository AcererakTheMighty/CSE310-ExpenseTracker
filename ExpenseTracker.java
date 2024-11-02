import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// Interface defining the structure for transaction management
interface TransactionManager {
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByCategory(String category);
    List<Transaction> getTransactionsInDateRange(LocalDate startDate, LocalDate endDate);
    double getTotalExpensesByCategory(String category);
    void setBudget(String category, double amount);
    boolean checkBudget(String category);
}

// Transaction class representing a single financial transaction
class Transaction {
    private final LocalDate date;
    private final double amount;
    private final String category;
    private final String description;

    public Transaction(LocalDate date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Amount: $" + amount + ", Category: " + category + ", Description: " + description;
    }
}

// ExpenseTracker class implementing the TransactionManager interface
class ExpenseTracker implements TransactionManager {
    private final List<Transaction> transactions = new ArrayList<>();
    private final Map<String, Double> categoryBudgets = new HashMap<>();

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionsInDateRange(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalExpensesByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public void setBudget(String category, double amount) {
        categoryBudgets.put(category, amount);
    }

    @Override
    public boolean checkBudget(String category) {
        double totalSpent = getTotalExpensesByCategory(category);
        if (categoryBudgets.containsKey(category)) {
            double budget = categoryBudgets.get(category);
            return totalSpent > budget;
        }
        return false;
    }

    public void printBudgetStatus(String category) {
        if (categoryBudgets.containsKey(category)) {
            double budget = categoryBudgets.get(category);
            double totalSpent = getTotalExpensesByCategory(category);
            System.out.println("Category: " + category);
            System.out.println("Budget: $" + budget + ", Total Spent: $" + totalSpent);
            if (totalSpent > budget) {
                System.out.println("Alert: You have exceeded your budget for " + category + "!");
            } else {
                System.out.println("You are within the budget for " + category + ".");
            }
        } else {
            System.out.println("No budget set for category: " + category);
        }
    }
}
