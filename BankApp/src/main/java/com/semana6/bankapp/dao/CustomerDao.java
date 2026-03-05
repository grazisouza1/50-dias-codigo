package com.semana6.bankapp.dao;

import com.semana6.bankapp.dto.AccountDto;
import com.semana6.bankapp.dto.CustomerDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao {

    Connection conn;
    PreparedStatement pstm;

    public boolean emailAlreadyExists(String email) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT 1 FROM customers WHERE email = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        }
    }

    public boolean validateLogin(String email, String senha) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT 1 FROM customers WHERE email = ? AND pass_hash = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            return rs.next();
        }
    }

    public boolean cpfAlreadyExists(String cpf) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT 1 FROM customers WHERE cpf_cnpj = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cpf);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        }
    }

    public void registerCustomer(CustomerDto objCustomer) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "INSERT INTO customers (first_name, last_name, email, phone_number, cpf_cnpj, pass_hash, created_at) values(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1,objCustomer.getFirstName());
            pstm.setString(2,objCustomer.getLastName());
            pstm.setString(3,objCustomer.getEmail());
            pstm.setString(4,objCustomer.getPhoneNumber());
            pstm.setString(5,objCustomer.getCpfCnpj());
            pstm.setString(6,objCustomer.getPassHash());
            pstm.setDate(7, Date.valueOf(java.time.LocalDate.now()));

            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();

            if (rs.next()) {
                objCustomer.setId(rs.getInt(1));
            }
        }

    }

    public CustomerDto authenticate(String email, String senha) throws SQLException {

        conn = new ConexaoDB().connectDB();

        String sql = "SELECT * FROM customers WHERE email = ? AND pass_hash = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CustomerDto customer = new CustomerDto();
                customer.setId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setEmail(rs.getString("email"));
                return customer;
            }
        }

        return null;
    }

    public CustomerDto searchCustomerByPhone(String phoneNumber) throws SQLException {
        Connection conn = new ConexaoDB().connectDB();

        String sql = "SELECT * FROM customers WHERE phone_number = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, phoneNumber);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                CustomerDto cliente = new CustomerDto();
                cliente.setId(rs.getInt("customer_id"));
                cliente.setPhoneNumber(rs.getString("phone_number"));
                cliente.setFirstName(rs.getString("first_name"));
                cliente.setLastName(rs.getString("last_name"));
                return cliente;
            }
        }
        return null;
    }
}
