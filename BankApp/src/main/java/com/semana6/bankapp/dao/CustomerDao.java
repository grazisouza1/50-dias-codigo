package com.semana6.bankapp.dao;

import com.semana6.bankapp.dto.CustomerDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {

    Connection conn;
    PreparedStatement pstm;

    public boolean emailJaExiste(String email) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT 1 FROM customers WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean cpfJaExiste(String cpf) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT 1 FROM customers WHERE cpf_cnpj = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void cadastrarCliente(CustomerDto objCustomer) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "INSERT INTO customers (first_name, last_name, email, phone_number, cpf_cnpj, pass_hash, created_at) values(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1,objCustomer.getFirstName());
            pstm.setString(2,objCustomer.getLastName());
            pstm.setString(3,objCustomer.getEmail());
            pstm.setString(4,objCustomer.getPhoneNumber());
            pstm.setString(5,objCustomer.getCpfCnpj());
            pstm.setString(6,objCustomer.getPassHash());
            pstm.setDate(7, Date.valueOf(java.time.LocalDate.now()));

            pstm.executeUpdate();
        }
    }
}
