package com.splitwise.service;

import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.enums.ExpenseType;

import java.util.List;

public abstract class ExpenseManager {
    public abstract void addUser(User user);

    // Add expense to expenses list and update balanceSheet based on splits
    public abstract void addExpense(String label,
                                    User paidBy,
                                    double amount,
                                    List<Split> splits,
                                    ExpenseType expenseType);

    public abstract String showBalanceForUser(User user);

}
