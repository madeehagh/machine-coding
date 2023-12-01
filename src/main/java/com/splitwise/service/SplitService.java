package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.enums.UserStatus;

public abstract class SplitService {
    public abstract void addExpense(Expense expense);

    public abstract String showBalance(String userId);

    public abstract void registerUser(User user);

    public abstract UserStatus sendInvite(String user);
}
