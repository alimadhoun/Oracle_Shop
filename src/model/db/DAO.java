/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class DAO {

    // "DAO class" insert all queries as function here

    private static Connection connection;
    public static DAO shared = new DAO();
    public DAO() {
        try {
            connection = DBConnection.getInstance().getConn();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getTest() {
        try {
//            Statement stmt = this.connection.createStatement();
//            ResultSet rs = stmt.executeQuery("insert into Customer values('1','ali','gaza','ali','123')");
//            while(rs.next()){
//                //Display values
////                System.out.println("message: " + rs.getString("message"));
//            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Customer loginCustomer(String userName, String pass) {
        try {
            System.out.println("out");
            Statement stmt = DAO.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customer where username = '"+userName +  "' and password = '" + pass+"'");

//            PreparedStatement statement = DAO.connection.prepareStatement("select * from Customer where username = ? and password = ?");
//            statement.setString(1,userName);
//            statement.setString(2,pass);
//            statement.executeUpdate();
//            ResultSet rs = statement.getResultSet();
//            System.out.println(rs.toString());
            while(rs.next()) {
                //Display values
                String userNmae = rs.getString("username");
                System.out.println("userName: " + userNmae);

                String customerId = rs.getString("customerId");
                System.out.println("customerId: " + customerId);

                String address = rs.getString("address");
                System.out.println("address: " + address);

                String customerName = rs.getString("customerName");
                System.out.println("customerName: " + customerName);

                String password = rs.getString("password");
                System.out.println("customerName: " + customerName);
                return new Customer(customerId,customerName,address,userName,password);

            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

}
