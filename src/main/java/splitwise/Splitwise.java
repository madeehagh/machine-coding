package splitwise;

import splitwise.entity.Split;
import splitwise.entity.User;
import splitwise.service.ExpenseManager;
import splitwise.service.SplitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splitwise {
  public static void main(String[] args) {
    ExpenseManager expenseManager = new ExpenseManager();
    expenseManager.addUser(new User("1", "abc", "abc@gmail.com", "46876410"));
    expenseManager.addUser(new User("2", "xyz", "xyz@gmail.com", "32746283"));
    expenseManager.addUser(new User("3", "stu", "stu@gmail.com", "56783456983"));

    Scanner scanner = new Scanner(System.in);
    while (true) {

      System.out.println("Select option: Create splitwise.entity.Expense [1], Show Balance [2]");
      String input = scanner.nextLine();
      switch (input) {
        case "1":
          createExpense(expenseManager, scanner);
          break;

        case "2":
          showExpense(expenseManager, scanner);
          break;

        default:
          System.out.println("Invalid Option Selected");
          break;
      }
    }
  }

  private static void showExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("Enter userid for whom you want to see balance:");
    String userId = scanner.nextLine();
    expenseManager.showBalanceForUser(userId);
  }

  private static void createExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("Choose split type:");
    System.out.println("1. Equal, 2. Exact, 3. Percent");

    String splitType = scanner.nextLine();
    switch (splitType) {
      case "Equal":
        createEqualExpense(expenseManager, scanner);
        break;

      default:
        System.out.println("Invalid option selected.");
        break;
    }
  }

  private static void createEqualExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("paid by:");
    String paidBy = scanner.nextLine();
    System.out.println("Enter No. of users");
    int num = Integer.parseInt(scanner.nextLine());
    System.out.println("Item spent on: ");
    String label = scanner.nextLine();
    System.out.println("Enter amount: ");
    double amount = Double.parseDouble(scanner.nextLine());
    List<Split> splits = new ArrayList<>();
    splits.add(new Split(expenseManager.getUserMap().get(paidBy), amount));
    for (int i=1; i< num; i++) {
      System.out.println("splitwise.entity.User id: " );
      String userId = scanner.nextLine();
      splits.add(new Split(expenseManager.getUserMap().get(userId), amount));
    }
    expenseManager.addExpense(label,paidBy,amount,splits, SplitService.ExpenseType.EQUAL);
  }
}
