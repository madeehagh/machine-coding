package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SplitServiceImpl extends SplitService{

 @Autowired
 ExpenseManager expenseManager;

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


  /**
   * @param expense
   */
  @Override
  public void addExpense(Expense expense) {

  }

  /**
   * @param userId
   * @return
   */
  @Override
  public String showBalance(String userId) {
    return null;
  }

 /**
  * @param user
  */
 @Override
 public void registerUser(User user) {

 }

 /**
  * @param user
  */
 @Override
 public void sendInvite(String user) {

 }
}
