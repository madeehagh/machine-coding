package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExpenseManagerImpl extends ExpenseManager{

  Map<String, User> userMap; //key: userID, value: User
  Map<String,  Double> balanceSheet; //key: user Id, value: amount (DR/CR)

    /**
     * @param user
     */
    @Override
    public void registerUser(User user) {

    }

    /**
     * @param expense
     */
    @Override
    public void addExpense(Expense expense) {

    }

    /**
     * @param user
     * @return
     */
    @Override
    public String showBalanceForUser(User user) {
        return null;
    }
}
