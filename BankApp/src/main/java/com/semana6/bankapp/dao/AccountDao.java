package com.semana6.bankapp.dao;

import com.semana6.bankapp.dto.AccountDto;
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
    }

