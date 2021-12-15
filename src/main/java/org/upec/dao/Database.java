package org.upec.dao;

import java.sql.*;

public class Database {

    private Connection conn;

    public Database(){
        try{
            String dbUrl = "jdbc:mysql://localhost:3306/TP1_GestionProcessus";
            String username = "lqlinh";
            String password = "123456";
    
            //get connection (step 1)
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(dbUrl, username, password);
    
        } catch (Exception e){
            System.out.println("error!");
            e.printStackTrace();
        }
    }    

    public ResultSet getData(){
        ResultSet resultSet_prof = null;
        try{
            Statement stmt = this.conn.createStatement();
            resultSet_prof = stmt.executeQuery("select * from PROFESSEUR");
            
            return resultSet_prof;
        } catch (Exception e){
          System.out.println("error in get_data(), class Database");
          e.printStackTrace();  

          return resultSet_prof;
        }
    }
}
