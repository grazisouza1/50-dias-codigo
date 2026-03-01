package com.semana6.bankapp.dto;

import java.time.LocalDate;

public class AccountDto {
    private int id;
    private int customer_id;
    private enum account_type {
        CORRENTE, POUPANCA
    };
    private float balance;
    private enum status {
        ATIVO, BLOQUEADO
    }
    private LocalDate created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
