package splitwise;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ExpenseManager {

  Map<String, User> userMap;
  Map<User, Map<User, Double>> balanceSheet;

  public ExpenseManager() {
    this.userMap = new HashMap<>();
    this.balanceSheet = new HashMap<>();
  }

  public void addUser(User user) {
    this.userMap.put(user.getId(), user);
    this.balanceSheet.put(user, new HashMap<>());
  }

  public void addExpense(String label, String paidBy, double amount, List<Split> splitList, SplitService.ExpenseType expenseType){
      List<Expense> expenses = new ArrayList<>();
      User paidByUser = userMap.get(paidBy);
      Expense expense = SplitService.createExpense(paidBy, amount, splitList, expenseType, label);
      if (null != expense) {
        expenses.add(expense);
      }

    for (Split split : expense.getSplits()) {
      User paidTo = split.getUser();
      Map<User, Double> balances = balanceSheet.get(paidByUser);
      if (!balances.containsKey(paidTo)) {
        balances.put(paidTo, 0.0);
      }
      balances.put(paidTo, balances.get(paidTo)+split.getAmount());
      balances = balanceSheet.get(paidTo);

      if (!balances.containsKey(paidByUser)) {
        balances.put(paidByUser, 0.0);
      }
      balances.put(paidByUser, balances.get(paidByUser) - split.getAmount());
    }

  }

  public void showBalanceForUser(String userId) {
    User currentUser = userMap.get(userId);
    if (null == currentUser) {
      System.out.println("Invalid User Input");
      return;
    }
    for (Map.Entry<User, Double> userBalance : balanceSheet.get(currentUser).entrySet()) {
      if (userBalance.getKey() != currentUser) {
        printBalance(userId, userBalance);
      }
    }
  }

  private void printBalance(String userId, Map.Entry<User, Double> userBalance) {
    if (userBalance.getValue() != 0) {
      if (userBalance.getValue() < 0) {
        System.out.println("User " + userMap.get(userId).getName() + " owes amount " + Math.abs(userBalance.getValue())
            + " to " + userBalance.getKey().getName());
        return;
      }
      System.out.println(userBalance.getKey().getName()+
          " owes "+ Math.abs(userBalance.getValue())+" to "+userMap.get(userId).getName());
      return;
    }
    else {
      System.out.println(" No balance for user " + userId);
    }
  }
}
