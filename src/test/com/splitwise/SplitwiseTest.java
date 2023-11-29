package com.splitwise;

import com.splitwise.entity.Split;
import com.splitwise.entity.User;
import com.splitwise.service.ExpenseManager;
import com.splitwise.service.SplitService;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SplitwiseTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ExpenseManager expenseManager;

    @BeforeAll
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        expenseManager = new ExpenseManager();
        expenseManager.addUser(new User("1", "abc", "abc@gmail.com", "46876410"));
        expenseManager.addUser(new User("2", "xyz", "xyz@gmail.com", "32746283"));
        expenseManager.addUser(new User("3", "stu", "stu@gmail.com", "56783456983"));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainWhenCreateExpenseOptionSelectedThenExpenseCreated() {
        String input = "1\n1\n1\n2\nitem\n100\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Splitwise.main(new String[]{});
        Assertions.assertTrue(outContent.toString().contains("Expense created successfully"));
    }

    @Test
    public void testMainWhenCreateExpenseOptionSelectedAndInvalidSplitTypeThenErrorMessageDisplayed() {
        String input = "1\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Splitwise.main(new String[]{});
        Assertions.assertTrue(outContent.toString().contains("Invalid option selected."));
    }

    @Test
    public void testMainWhenShowBalanceOptionSelectedThenBalanceDisplayed() {
        // Create an expense first
        List<Split> splits = new ArrayList<>();
        splits.add(new Split(expenseManager.getUserMap().get("1"), 100.0));
        splits.add(new Split(expenseManager.getUserMap().get("2"), 100.0));
        expenseManager.addExpense("item", "1", 200.0, splits, SplitService.ExpenseType.EQUAL);

        String input = "2\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Splitwise.main(new String[]{});
        Assertions.assertTrue(outContent.toString().contains("User abc owes amount 100.0 to xyz"));
    }

    @Test
    public void testMainWhenShowBalanceOptionSelectedAndInvalidUserIdThenErrorMessageDisplayed() {
        String input = "2\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Splitwise.main(new String[]{});
        Assertions.assertTrue(outContent.toString().contains("Invalid splitwise.entity.User Input"));
    }
}