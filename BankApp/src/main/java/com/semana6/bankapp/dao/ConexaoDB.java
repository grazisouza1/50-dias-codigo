package com.semana6.bankapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
     public Connection connectDB() {
         Connection conn = null;

         try {
             String url = "jdbc:mysql://localhost:3306/bank_app_db?user=bank_user&password=Valid@12";
             conn = DriverManager.getConnection(url);

             System.out.println("Recuperei a conexão");

             conn.close();
         } catch (SQLException e){
             System.out.println(e);
         }

         return conn;
     }
}
