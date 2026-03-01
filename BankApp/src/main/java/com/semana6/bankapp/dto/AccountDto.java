package com.semana6.bankapp.dto;

import java.time.LocalDate;



public class AccountDto {
    public enum AccountType {
        CORRENTE,
        POUPANCA
    }

    public enum AccountStatus {
        ATIVO,
        BLOQUEADO
    }

    private int id;
    private int customer_id;
    public AccountType account_type ;
    private float balance;
    public AccountStatus status;
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

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus(){
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
}
