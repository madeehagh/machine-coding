package com.splitwise.service;

import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

  public static String createExpense(
          String paidBy,
          double amount,
          List<User> splitList,
          ExpenseType expenseType,
          String label) {

    return "";

   /* switch (expenseType) {
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
    }*/
  }

}
