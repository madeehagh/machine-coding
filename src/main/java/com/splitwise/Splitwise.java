package com.splitwise;

import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.service.ExpenseManager;
import com.splitwise.service.SplitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Splitwise {
  private static final String OPTION_CREATE_EXPENSE = "1";
  private static final String OPTION_SHOW_BALANCE = "2";

  public static void main(String[] args) {
    ExpenseManager expenseManager = new ExpenseManager();
    expenseManager.addUser(new User("1", "abc", "abc@gmail.com", "46876410"));
    expenseManager.addUser(new User("2", "xyz", "xyz@gmail.com", "32746283"));
    expenseManager.addUser(new User("3", "stu", "stu@gmail.com", "56783456983"));

    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Select option: Create Expense [1], Show Balance [2]");
      String input = scanner.nextLine();

      switch (input) {
        case OPTION_CREATE_EXPENSE:
          createExpense(expenseManager, scanner);
          break;

        case OPTION_SHOW_BALANCE:
          showExpense(expenseManager, scanner);
          break;

        default:
          System.out.println("Invalid Option Selected");
          break;
      }
    }
  }

  private static void showExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("Enter user ID for whom you want to see the balance:");
    String userId = scanner.nextLine();
    expenseManager.showBalanceForUser(userId);
  }

  private static void createExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("Choose split type:");
    System.out.println("1. Equal, 2. Exact, 3. Percent");

    String splitType = scanner.nextLine();
    switch (splitType) {
      case "1":
        createEqualExpense(expenseManager, scanner);
        break;

      default:
        System.out.println("Invalid option selected.");
        break;
    }
  }

  private static void createEqualExpense(ExpenseManager expenseManager, Scanner scanner) {
    System.out.println("Paid by:");
    String paidBy = scanner.nextLine();
    System.out.println("Enter the number of users:");
    int num = Integer.parseInt(scanner.nextLine());
    System.out.println("Item spent on:");
    String label = scanner.nextLine();
    System.out.println("Enter the amount:");
    double amount = Double.parseDouble(scanner.nextLine());
    List<Split> splits = new ArrayList<>();
    splits.add(new Split(expenseManager.getUserMap().get(paidBy), amount));
    for (int i = 1; i < num; i++) {
      System.out.println("User ID:");
      String userId = scanner.nextLine();
      splits.add(new Split(expenseManager.getUserMap().get(userId), amount));
    }
    expenseManager.addExpense(label, paidBy, amount, splits, SplitService.ExpenseType.EQUAL);
  }
}