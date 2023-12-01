package com.splitwise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_balance")
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long userId;
    private double balance;

    public UserBalance(long userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public double getBalance() {
        return this.balance;
    }
}
