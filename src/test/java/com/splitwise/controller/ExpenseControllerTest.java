package com.splitwise.controller;

import com.splitwise.service.SplitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SplitServiceImpl splitService;

    private String validExpenseJson;
    private String invalidExpenseJson;
    private String validUserJson;
    private String invalidUserJson;

    @BeforeEach
    public void setup() {
        validExpenseJson = "{\"label\":\"Dinner\",\"paidBy\":\"User1\",\"amount\":120.0,\"createdDate\":\"01-01-2023\",\"users\":[\"User1\",\"User2\"],\"expenseType\":\"EQUAL\"}";
        invalidExpenseJson = "{\"label\":\"Dinner\",\"paidBy\":\"User1\",\"amount\":\"invalid\",\"createdDate\":\"01-01-2023\",\"users\":[\"User1\",\"User2\"],\"expenseType\":\"EQUAL\"}";
        validUserJson = "{\"id\":\"123\",\"name\":\"John\",\"email\":\"john@example.com\",\"phoneNo\":\"1234567890\"}";
        invalidUserJson = "{\"name\":\"\",\"phoneNo\":\"123\"}";
    }

    @Test
    public void testCreateExpenseWhenValidExpenseThenReturnOk() throws Exception {
        mockMvc.perform(post("/v1/expense/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validExpenseJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Expense added successfully"));
    }

    @Test
    public void testCreateExpenseWhenInvalidExpenseThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/v1/expense/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidExpenseJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("INVALID REQUEST"));
    }

    @Test
    public void testUserBalanceThenReturnSuccess() throws Exception {
        mockMvc.perform(get("/v1/expense/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    public void testRegisterUserWhenValidUserThenReturnOk() throws Exception {
        mockMvc.perform(post("/v1/expense/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUserJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Invitation sent to user"));
    }

/*    @Test
    public void testRegisterUserWhenInvalidUserThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/v1/expense/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("INVALID REQUEST"));
    }*/
}
