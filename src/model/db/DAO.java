/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import model.Customer;
import model.Department;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
            Statement stmt = this.connection.createStatement();

//            ResultSet rs = stmt.executeQuery("insert into Product values('1','item1',15.4,15,'this is a test description')");
            ResultSet rs = stmt.executeQuery("select * from Product");

            while(rs.next()){
                //Display values
                System.out.println("departmentID: " + rs.getString("departmentID"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Customer loginCustomer(String userName, String pass) {
        try {
            System.out.println("out");
            //            PreparedStatement statement = DAO.connection.prepareStatement("select * from Customer where username = ? and password = ?");
//            statement.setString(1,userName);
//            statement.setString(2,pass);
//            statement.executeUpdate();
//            ResultSet rs = statement.getResultSet();
//            System.out.println(rs.toString());

            Statement stmt = DAO.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customer where username = '"+userName +  "' and password = '" + pass+"'");

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
                System.out.println("password: " + password);
                return new Customer(customerId,customerName,address,userName,password);

            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    public static Department getDepartment(String idDepartment) {
        try {
            System.out.println("out");
            Statement stmt = DAO.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from department where departmentID = '"+idDepartment +"'");
            Department department;
            while(rs.next()) {
                //Display values

                String departmentID = rs.getString("departmentID");
                System.out.println("departmentID: " + departmentID);

                String departmentName = rs.getString("departmentName");
                System.out.println("departmentName: " + departmentName);

                String description = rs.getString("description");
                System.out.println("description: " + description);

                ResultSet rss = stmt.executeQuery("select * from Product where departmentID = '"+idDepartment +"'");
                ArrayList<Product> listProduct = new ArrayList<>();

                while (rss.next()) {



                    String IDProduct = rs.getString("IDProduct");
                    System.out.println("IDProduct: " + IDProduct);

                    String productName = rs.getString("productName");
                    System.out.println("productName: " + productName);

                    Double price = rs.getDouble("price");
                    System.out.println("price: " + price);

                    int quanity = rs.getInt("quanity");
                    System.out.println("quanity: " + quanity);

                    String descriptionProd = rs.getString("description");
                    System.out.println("description: " + description);

                    Product prod = new Product(productName,price,quanity,descriptionProd);
                    listProduct.add(prod);
                }
                department = new Department(departmentName,description);
                department.setDepartmentID(departmentID);
                department.setListProduct(listProduct);
                return  department;

            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return  null;
    }

    public static ArrayList<Customer> getAllCustomers() {
       try {
           Statement stmt = DAO.connection.createStatement();
           ResultSet rs = stmt.executeQuery("select * from Customer");
           System.out.println("in get customer..");
           ArrayList<Customer> customers = new ArrayList<Customer>();
           while (rs.next()) {

               String customerId = rs.getString("customerId");
               System.out.println("customerId: " + customerId);

               String customerName = rs.getString("customerName");
               System.out.println("customerName: " + customerName);

               String address = rs.getString("address");
               System.out.println("address: " + address);

               String userName = rs.getString("userName");
               System.out.println("userName: " + userName);

               String password = rs.getString("password");
               System.out.println("password: " + password);
               customers.add(new Customer(customerId,customerName,address,userName));
           }
           return customers;
       } catch (Exception exception) {
           System.out.println(exception);
       }
       return null;
    }

}



/* CUSTOMER
create table Customer (
customerId char(20),
customerName char(20),
address char(20),
userName char(20),
password char(20)

);


insert into Customer values('1','ali','gaza','ali','123');
*/


/*
department
    create table department(
    departmentID char(25),
    departmentName char(25),
    description char(100)
    )

    insert into DEPARTMENT values('1','it','this is the id department')
 */

/*
create table Product(
departmentID char(25),
IDProduct char(20),
productName char(30),
price NUMERIC(20,2),
quanity NUMERIC(25),
description char(100)
);


insert into Product values('1','1','item1',15.4,15,'this is a test description');
 */