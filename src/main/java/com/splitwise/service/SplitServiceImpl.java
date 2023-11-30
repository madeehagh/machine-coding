package com.splitwise.service;

import com.splitwise.entity.Expense;
import com.splitwise.entity.User;
import com.splitwise.entity.UserBalance;
import com.splitwise.enums.UserStatus;
import com.splitwise.respository.UserBalanceRepository;
import com.splitwise.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SplitServiceImpl extends SplitService {

 @Autowired
 ExpenseManager expenseManager;

 @Autowired
 UserBalanceRepository userBalanceRepository;

 @Autowired
 UserRepository userRepository;

 @Override
 public void addExpense(Expense expense) {
  // Implement the logic to add the expense
  // You can use the expenseManager to handle the expense
  expenseManager.addExpense(expense);
 }

 @Override
 public String showBalance(String userId) {
  // Implement the logic to show the balance for the given user
  // You can use the userBalanceRepository to fetch the user's balance
  // and return it as a string
  return userBalanceRepository.findByUserId(Long.parseLong(userId))
          .map(UserBalance::getBalance)
          .map(String::valueOf)
          .orElse("User not found");
 }

 @Override
 public void registerUser(User user) {
  if (user != null) {
   User savedUser = userRepository.save(user);
   userBalanceRepository.save(new UserBalance(savedUser.getId(), 0.0));
  }
 }

 @Override
 public UserStatus sendInvite(String user) {
  Optional<User> registeredUser = userRepository.findById(Long.parseLong(user));
  if (registeredUser.isEmpty()) {
   return UserStatus.NOT_FOUND;
  }
  return UserStatus.REGISTERED;
 }
}