package splitwise.service;

import splitwise.entity.Expense;
import splitwise.entity.Split;

import java.util.Date;
import java.util.List;

public class SplitService {

  public static Expense createExpense(String paidBy, double amount, List<Split> splitList, ExpenseType expenseType, String label) {


    switch (expenseType) {
      case EQUAL:
        int splitSize = splitList.size();
        double amt = amount/ splitSize;
        for (Split split : splitList) {
          split.setAmount(amt);
        }
        return new Expense(label,paidBy,amount, new Date(), splitList);
      default:
        System.out.println("Invalid expense");
        return null;
    }
  }

  public static enum ExpenseType {
    EQUAL,
    PERCENTAGE,
    EXACT
  }
}
