package com.semana6.bankapp.dao;

import com.semana6.bankapp.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDao {

    Connection conn;
    PreparedStatement pstm;

    public void cadastrarCliente(CustomerDto objCustomer){
        String sql = "INSERT INTO customers (first_name, last_name, email) values(?, ?, ?)";

        conn = new ConexaoDB().connectDB();

        try {
            pstm =  conn.prepareStatement(sql);
            pstm.setString(1,objCustomer.getFirstName());
            pstm.setString(2,objCustomer.getLastName());
            pstm.setString(3,objCustomer.getEmail());
        } catch (Exception erro) {
            System.out.println("CostumerDao" + erro);
        }
    }
}
