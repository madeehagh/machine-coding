package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ExpenseManager {

  Map<String, User> userMap;
  Map<String,  Double> balanceSheet;

  public ExpenseManager() {
    this.userMap = new HashMap<>();
    this.balanceSheet = new HashMap<>();
  }

  public void addUser(User user) {
    this.userMap.put(user.getId(), user);
    this.balanceSheet.put(user.getId(), (double) 0);
  }

  public void addExpense(String label,
                         String paidBy,
                         double amount,
                         List<Split> splitList,
                         SplitService.ExpenseType expenseType) {
    Expense expense =
            SplitService.createExpense(
                    paidBy,
                    amount,
                    splitList,
                    expenseType,
                    label);
    if (expense != null) {
      for (Split split : expense.getSplits()) {
        User paidTo = split.getUser();
        //double balances = balanceSheet.get(paidBy);
        balanceSheet.put(paidTo.getId(),
                balanceSheet.getOrDefault(paidTo, (double)0) + split.getAmount());
      }
    }
  }

  public void showBalanceForUser(String userId) {
    User currentUser = userMap.get(userId);
    if (currentUser == null) {
      System.out.println("Invalid User Input");
    }
    System.out.println(balanceSheet.get(currentUser.getId()));
  }
}