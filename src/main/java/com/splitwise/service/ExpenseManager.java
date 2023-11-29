package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;

public abstract class ExpenseManager {
    public abstract void registerUser(User user);

    // Add expense to expenses list and update balanceSheet based on splits
    public abstract void addExpense(Expense expense);

    public abstract String showBalanceForUser(User user);

}
