package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;

public abstract class SplitService {
    public abstract void addExpense(Expense expense);

    public abstract String showBalance(String userId);

    public abstract void registerUser(User user);

    public abstract void sendInvite(String user);
}
