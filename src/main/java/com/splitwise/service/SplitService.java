/*
package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SplitService {

  public static Expense createExpense(
          String paidBy,
          double amount,
          List<User> splitList,
          ExpenseType expenseType,
          String label) {


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
*/
