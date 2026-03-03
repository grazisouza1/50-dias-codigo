package com.semana6.bankapp.dao;

import com.semana6.bankapp.dto.AccountDto;
import com.semana6.bankapp.dto.CustomerDto;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AccountDao {

    Connection conn;
    PreparedStatement pstm;

    public void cadastrarConta(AccountDto objAccountDto) throws SQLException {
        conn = new ConexaoDB().connectDB();

        String sql = "INSERT INTO accounts (account_id, customer_id, account_type, balance, status, created_at) values(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, objAccountDto.getId());
            pstm.setInt(2, objAccountDto.getCustomer_id());
            pstm.setString(3, objAccountDto.getAccount_type().name());
            pstm.setFloat(4, objAccountDto.getBalance());
            pstm.setString(5, objAccountDto.getStatus().name());
            pstm.setDate(6, Date.valueOf(java.time.LocalDate.now()));

            pstm.executeUpdate();
        }
    }

    public AccountDto buscarContaPorClienteId(int customerId) throws SQLException {

        Connection conn = new ConexaoDB().connectDB();

        String sql = "SELECT * FROM accounts WHERE customer_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, customerId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AccountDto conta = new AccountDto();
                conta.setId(rs.getInt("account_id"));
                conta.setCustomer_id(rs.getInt("customer_id"));
                conta.setBalance(rs.getFloat("balance"));
                return conta;
            }
        }

        return null;
    }

    public void atualizarSaldo (int accountId, float novoSaldo) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        Connection conn = new ConexaoDB().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setFloat(1, novoSaldo);
        pstm.setInt(2, accountId);

        pstm.executeUpdate();
    }

    public AccountDto buscarContaPorCustomerId(int id) throws SQLException {
        Connection conn = new ConexaoDB().connectDB();

        String sql = "SELECT * FROM accounts WHERE customer_id = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                AccountDto conta = new AccountDto();
                conta.setId(rs.getInt("account_id"));
                conta.setCustomer_id(rs.getInt("customer_id"));
                conta.setBalance(rs.getFloat("balance"));
                return conta;
            }
        }

        return null;
    }
}

