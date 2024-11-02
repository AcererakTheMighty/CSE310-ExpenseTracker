import java.time.LocalDate;

public class ExpenseTrackerTest {
    public static void main(String[] args) {
        // Create an instance of ExpenseTracker
        ExpenseTracker tracker = new ExpenseTracker();

        // Add some transactions to the tracker
        tracker.addTransaction(new Transaction(LocalDate.now(), 50.0, "Food", "Groceries"));
        tracker.addTransaction(new Transaction(LocalDate.now(), 20.0, "Transportation", "Gas"));
        tracker.addTransaction(new Transaction(LocalDate.now().minusDays(2), 100.0, "Entertainment", "Concert"));

        // Set a budget for the Food category
        tracker.setBudget("Food", 75.0);

        // Print out the total expenses for each category
        System.out.println("Total spent on Food: $" + tracker.getTotalExpensesByCategory("Food"));
        System.out.println("Total spent on Transportation: $" + tracker.getTotalExpensesByCategory("Transportation"));
        System.out.println("Total spent on Entertainment: $" + tracker.getTotalExpensesByCategory("Entertainment"));

        // Check and print the budget status for Food
        tracker.printBudgetStatus("Food");

        // Example: Display all transactions in the Food category
        System.out.println("\nTransactions in 'Food' category:");
        for (Transaction t : tracker.getTransactionsByCategory("Food")) {
            System.out.println(t);
        }

        // Example: Display transactions in a date range
        LocalDate startDate = LocalDate.now().minusDays(3);
        LocalDate endDate = LocalDate.now();
        System.out.println("\nTransactions from " + startDate + " to " + endDate + ":");
        for (Transaction t : tracker.getTransactionsInDateRange(startDate, endDate)) {
            System.out.println(t);
        }
    }
}
