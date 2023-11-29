package com.splitwise.controller;

import com.splitwise.service.SplitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SplitService splitService;

    private String validExpenseJson;
    private String invalidExpenseJson;

    @BeforeEach
    public void setup() {
        validExpenseJson = "{\"label\":\"Dinner\",\"paidBy\":\"User1\",\"amount\":120.0,\"createdDate\":\"01-01-2023\",\"users\":[\"User1\",\"User2\"],\"expenseType\":\"EQUAL\"}";
        invalidExpenseJson = "{\"label\":\"Dinner\",\"paidBy\":\"User1\",\"amount\":\"invalid\",\"createdDate\":\"01-01-2023\",\"users\":[\"User1\",\"User2\"],\"expenseType\":\"EQUAL\"}";
    }

    @Test
    public void testCreateExpenseWhenValidExpenseThenReturnOk() throws Exception {
        mockMvc.perform(post("/v1/expense/createExpense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validExpenseJson))
                .andExpect(status().isOk())
                .andExpect(content().json(validExpenseJson));
    }

    @Test
    public void testCreateExpenseWhenInvalidExpenseThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/v1/expense/createExpense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidExpenseJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUserBalanceThenReturnSuccess() throws Exception {
        mockMvc.perform(get("/v1/expense/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("sucess"));
    }
}