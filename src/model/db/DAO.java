/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import Helpers.ConstantHelper;
import model.Customer;
import model.Department;
import model.Product;
import oracle.jdbc.OracleConnection;

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
//            Statement stmt = this.connection.createStatement();
//
//            String sql = "select * from product";
//
//            ResultSet rs = stmt.executeQuery(sql);
//
//            System.out.println(sql);
//
//

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Customer loginCustomer(String userName, String pass) {
        try {
            System.out.println("out");
            Statement stmt = DAO.connection.createStatement();

            ResultSet rs = stmt.executeQuery("select distinct * from Customer where customerId = '"+userName +  "' and password = '" + pass +"'");
            System.out.println("select * from Customer where customerId = '"+userName +  "' and password = '" + pass +"'");
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

    private static ArrayList<Department> getAllDepartments() {
        try {
            ArrayList<Department> departments = new ArrayList<>();
            System.out.println("out");
            Statement stmt = DAO.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct * from department");

            while(rs.next()) {


                String departmentID = rs.getString("departmentID");
                System.out.println("departmentID: " + departmentID);

                String departmentName = rs.getString("departmentName");
                System.out.println("departmentName: " + departmentName);

                String description = rs.getString("description");
                System.out.println("description: " + description);

                Department department = new Department(departmentName,description);
                department.setDepartmentID(departmentID);
                departments.add(department);

            }
            return departments;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return  null;
    }

    public static Boolean insertNewCustomer(Customer newCustomer) {

        ArrayList<Customer> customers = DAO.getAllCustomers();
        for (Customer cus: customers
             ) {
            if (cus.getCustomerId().trim().equals(newCustomer.getCustomerId().trim())) {
                return false;
            }
        }

        try {
            System.out.println("in inserting new Customer");
            Statement stmt = DAO.connection.createStatement();
            ResultSet rs = stmt.executeQuery("insert into Customer values" +
                    "('" +
                    newCustomer.getCustomerId() +
                    "','" +
                    newCustomer.getCustomerName() +
                    "','" +
                    newCustomer.getAddress() +
                    "','" +
                    newCustomer.getUserName() +
                    "','" +
                    "123" +
                    "')");
            return true;
        } catch (Exception exception) {
            System.out.println("Exception in inserting new Customer");
            System.out.println(exception);
        }
        return false;
    }

    public static void updateCustomerInfo(Customer customer) {
        try {
            System.out.println("in update Customer");
            Statement stmt = DAO.connection.createStatement();
            String sql = "update Customer set customerId = " +
                    "'" +
                    customer.getCustomerId().trim() +
                    "', customerName = '" +
                    customer.getCustomerName().trim() +
                    "',address = '" +
                    customer.getAddress().trim() +
                    "', userName = '" +
                    customer.getUserName().trim() +
                    "' where customerId = '" + customer.getCustomerId().trim() + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
        } catch (Exception exception) {
            System.out.println("Exception in update Customer");
            System.out.println(exception);
        }
    }

    public static ArrayList<Department> getDepartmentsWithProducts() {
        try {
            ArrayList<Department> departments = DAO.getAllDepartments();
            for (Department dep: departments
                 ) {

                Statement stmt = DAO.connection.createStatement();
                String sql = "select distinct * from product where departmentID = '"+dep.getDepartmentID().trim()+"'";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(sql);
                while(rs.next()) {


                    String IDProduct = rs.getString("IDProduct");
                    System.out.println("IDProduct: " + IDProduct);

                    String productName = rs.getString("productName");
                    System.out.println("productName: " + productName);

                    Double price = rs.getDouble("price");
                    System.out.println("price: " + price);

                    int quanity = rs.getInt("quanity");
                    System.out.println("quanity: " + quanity);

                    String descriptionProd = rs.getString("description");
                    System.out.println("description: " + descriptionProd);

                    Product prod = new Product(productName,price,quanity,descriptionProd);
                    prod.setIDProduct(IDProduct);
                   dep.getListProduct().add(prod);

                }

            }

            return departments;
        } catch (Exception exception) {
            System.out.println("exception in get all departments");
        }
        return  null;
    }



    public static ArrayList<Customer> getAllCustomers() {
       try {
           Statement stmt = DAO.connection.createStatement();
           ResultSet rs = stmt.executeQuery("select distinct * from Customer");
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

    public static boolean insertNewProduct(Product product, String idDepartment) {
        try {
            System.out.println("in insert new product..");
            Statement stmt = DAO.connection.createStatement();

            ResultSet rs = stmt.executeQuery("insert into product values('"
                    +idDepartment + "','"
                    +product.getIDProduct().trim()+"','"+product.getProductName().trim() +"','"+
                            product.getPrice() + "','" + product.getQuanity() +"','" + product.getDescription() + "')");

            return true;
        } catch (Exception exception) {
            System.out.println("error in insert new product..");
            System.out.println(exception);
            return false;
        }
    }

    public static void updateProduct(Product product) {
        try {
            System.out.println("in Update product..");
            Statement stmt = DAO.connection.createStatement();
            System.out.println("update product set IDProduct = '"+
                                                   product.getIDProduct() +
                    "', description = '"+product.getDescription().trim() + "', price = " +
                                                   product.getPrice() + ",quanity = " + product.getQuanity() +
                    ","+ "productName = '" + product.getProductName().trim() + "' " +
                                                 " where IDProduct = '" + product.getIDProduct() + "'");

            String updateStatment = "update product set IDProduct = '" +
                    product.getIDProduct()+
                    "',"+
                    "";
                stmt.executeUpdate("update product set IDProduct = '" +
                        product.getIDProduct() +
                        "', description = '"+product.getDescription().trim()+"', price = "+
                        product.getPrice() + ",quanity = " + product.getQuanity() +
                        ",productName = '" + product.getProductName().trim() + "' "+
                        " where IDProduct = '" + product.getIDProduct() + "'");
            System.out.println("updated..");

        } catch (Exception exception) {
            System.out.println(" errpr in Update product..");
            System.out.println(exception);

        }
    }

    public static boolean insertNewDepartment(Department newDepartment) {

        ArrayList<Department> allDepartments = DAO.getAllDepartments();
        for (Department dep: allDepartments
             ) {
            if (dep.getDepartmentID().equals(newDepartment.getDepartmentID())) {
                return false;
            }
        }

        try {
            System.out.println("in insert new Department..");
            Statement stmt = DAO.connection.createStatement();
            String sql = "insert into department values('" +
                    newDepartment.getDepartmentID() +
                    "','" +
                    newDepartment.getDepartmentName()
                    +
                    "','"
                    +newDepartment.getDescription()
                    + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception exception) {
            System.out.println("Exception in insert new Department..");
            System.out.println(exception);

        }
        return false;
    }

    public static void updateDepartment(Department newDepartment) {
        try {

            Statement stmt = DAO.connection.createStatement();
            String sql = "update department set departmentName = '"
                    + newDepartment.getDepartmentName().trim()
                    + "', description = '"
                    + newDepartment.getDescription().trim()
                    + "' where departmentID = '"
                    + newDepartment.getDepartmentID().trim()
                    + "'"
                    ;
            stmt.executeUpdate(sql);
            System.out.println("in Update Department..");
        } catch (Exception exception) {
            System.out.println(" Exception in Update Department..");
            System.out.println(exception);

        }
    }

    public static void saveUserAction(String action, String time) {
        try {

            Statement stmt = DAO.connection.createStatement();
            String sql = "insert into userActions values ('" +
                    action +
                    "','" +
                    time +
                    "')";
            stmt.executeUpdate(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in save user action..");
            System.out.println(exception);

        }
    }

}



/* CUSTOMER
create table Customer (
customerId char(20),
customerName char(20),
address char(20),
userName char(20),
password char(100)

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

String sql = "create table userActions(action varchar(20), time varchar(100))";

insert into Product values('1','1','item1',15.4,15,'this is a test description');
 */