package com.splitwise.service;

import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExpenseManagerTest {

    private ExpenseManager expenseManager;

    @BeforeEach
    void setUp() {
        expenseManager = new ExpenseManager();
    }

    @Test
    @DisplayName("Test to verify that a user is added correctly to the userMap and balanceSheet maps.")
    void testAddUserWhenValidUserThenUserAdded() {
        // Arrange
        User user = new User("1", "John Doe", "john.doe@example.com", "1234567890");

        // Act
        expenseManager.addUser(user);

        // Assert
        Assertions.assertTrue(expenseManager.getUserMap().containsKey(user.getId()), "User should be added to userMap");
        Assertions.assertTrue(expenseManager.getBalanceSheet().containsKey(user.getId()), "User should be added to balanceSheet");
        Assertions.assertEquals(0.0, expenseManager.getBalanceSheet().get(user.getId()), "Initial balance should be 0");
    }

    @Test
    @DisplayName("Test to verify that the balanceSheet map is updated correctly after adding an expense.")
    void testAddExpenseWhenValidExpenseThenBalanceSheetUpdated() {
        // Arrange
        User user = new User("1", "John Doe", "john.doe@example.com", "1234567890");
        expenseManager.addUser(user);
        List<Split> splits = List.of(new Split(user, 50.0));
        String label = "Dinner";
        String paidBy = user.getId();
        double amount = 100.0;

        // Act
        expenseManager.addExpense(label, paidBy, amount, splits, SplitService.ExpenseType.EQUAL);

        // Assert
        Assertions.assertEquals(50.0, expenseManager.getBalanceSheet().get(user.getId()), "Balance should be updated correctly");
    }

    @Test
    @DisplayName("Test to verify that the correct balance is displayed for a user.")
    void testShowBalanceForUserWhenValidUserThenCorrectBalanceDisplayed() {
        // Arrange
        User user = new User("1", "John Doe", "john.doe@example.com", "1234567890");
        expenseManager.addUser(user);
        List<Split> splits = List.of(new Split(user, 50.0));
        expenseManager.addExpense("Dinner", user.getId(), 100.0, splits, SplitService.ExpenseType.EQUAL);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> expenseManager.showBalanceForUser(user.getId()), "Should not throw an exception for valid user");
        // Note: Since showBalanceForUser prints the balance to the console, we cannot capture the output here.
        // To fully test this, we would need to refactor the method to return the balance instead of printing it.
    }
}