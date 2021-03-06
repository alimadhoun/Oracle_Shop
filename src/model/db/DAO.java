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

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  Customer loginCustomer(String userName, String pass) {
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

    private  ArrayList<Department> getAllDepartments() {
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

    public  Boolean insertNewCustomer(Customer newCustomer) {

        ArrayList<Customer> customers = DAO.shared.getAllCustomers();
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

    public  void updateCustomerInfo(Customer customer) {
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

    public  ArrayList<Department> getDepartmentsWithProducts() {
        try {
            ArrayList<Department> departments = DAO.shared.getAllDepartments();
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



    public  ArrayList<Customer> getAllCustomers() {
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

    public  boolean insertNewProduct(Product product, String idDepartment) {
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

    public  void updateProduct(Product product) {
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

    public  boolean insertNewDepartment(Department newDepartment) {

        ArrayList<Department> allDepartments = DAO.shared.getAllDepartments();
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

    public  void updateDepartment(Department newDepartment) {
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

    public  void saveUserAction(String action, String time) {
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

     private  ArrayList<String> getAllIDSInCartForCustomer(Customer customer) {
        ArrayList<String> ids = new ArrayList<String>();
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "select * from cart where CustomerID = '" + customer.getCustomerId().trim() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                String productID = rs.getString("productID");
                System.out.println(productID);
                ids.add(productID);
            }
        } catch (Exception exception) {
            System.out.println(" Exception in fetching cart product ids..");
            System.out.println(exception);

        }
        return ids;
    }

    public  ArrayList<Product> fetchAllCartForCustomer(Customer customer) {
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<String> ids = getAllIDSInCartForCustomer(customer);
        for (String id: ids
             ) {

            try {
                Statement stmt = DAO.connection.createStatement();
                String sql = "select * from product where IDProduct = '" + id + "'";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(sql);
                while (rs.next()) {
                    String IDProduct = rs.getString("IDProduct");
                    System.out.println("IDProduct: " + IDProduct);

                    String productName = rs.getString("productName");
                    System.out.println("productName: " + productName);

                    Double price = rs.getDouble("price");
                    System.out.println("price: " + price);

//                    int quanity = rs.getInt("quanity");
                    int quanity = getQuentityForProductInCart(customer,IDProduct.trim());
                    System.out.println("quanity: " + quanity);

                    String descriptionProd = rs.getString("description");
                    System.out.println("description: " + descriptionProd);

                    Product prod = new Product(productName,price,quanity,descriptionProd);
                    prod.setIDProduct(IDProduct);
                    products.add(prod);
                }
            } catch (Exception exception) {
                System.out.println(" Exception in fetching cart product ids..");
                System.out.println(exception);

            }

        }

        return products;
    }

    private  int getQuentityForProductInCart(Customer customer, String product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "select * from cart where CustomerID = '" +
                    customer.getCustomerId().trim() +
                    "' and productID = '" +
                    product +
                    "' ";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                return rs.getInt("quanity");
            }
        } catch (Exception exception) {
            System.out.println(" Exception in getQuentityForProductInCart");
            System.out.println(exception);

        }
        return 0;
    }

    public  void addToCart(Product product, Customer customer) {
        ArrayList<Product> allInCart = fetchAllCartForCustomer(customer);
        for (Product pro: allInCart
             ) {
            if (pro.getIDProduct().trim().equals(product.getIDProduct().trim())) {
                System.out.println("Already in cart..");
                pro.setQuanity(product.getQuanity());
                updateItemInCart(customer,pro);
                return;
            }
        }

        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "insert into cart values('" +
                    customer.getCustomerId().trim() +
                    "','" +
                    product.getIDProduct().trim() +
                    "','" +
                    product.getQuanity() +
                    "')";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in adding new product to cart");
            System.out.println(exception);

        }

    }

    public  Double getSumOfCart(Customer customer) {
        Double sum = 0.0;
        ArrayList<Product> allInCart = fetchAllCartForCustomer(customer);
        for (Product prod:allInCart
             ) {
            sum += (prod.getQuanity() * prod.getPrice());
        }
        return sum;
    }

    private  void updateItemInCart(Customer customer, Product product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "update cart set quanity = '" +
                     product.getQuanity() +
                    "' where CustomerID = '" +
                    customer.getCustomerId().trim() +
                    "' and productID = '" +
                    product.getIDProduct().trim() +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in updating product in cart");
            System.out.println(exception);

        }
    }

    public  void removeItemFromCart(String customer, Product product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "delete from cart where CustomerID = '" +
                    customer.trim() +
                    "' and productID = '" +
                    product.getIDProduct().trim() +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in deleting product in cart");
            System.out.println(exception);

        }
    }

    public  void addToFav(Customer customer, Product product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "insert into fav values('" +
                    customer.getCustomerId().trim() +
                    "','" +
                    product.getIDProduct().trim() +
                    "')";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in adding product to fav");
            System.out.println(exception);

        }
    }

    public  void removeFromFav(Customer customer, Product product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "delete from fav where CustomerID = '" +
                    customer.getCustomerId().trim() +
                    "' and productID = '" +
                    product.getIDProduct().trim() +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in adding product to fav");
            System.out.println(exception);

        }
    }

    private  Product getProductDetails(String productID) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "select * from product where IDProduct = '" +
                    productID.trim() +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
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
                return prod;
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }


        return null;
    }

    public  ArrayList<Product> getAllFromFav(Customer customer) {
        ArrayList<Product> favs = new ArrayList<>();
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "select * from fav where CustomerID = '" +
                    customer.getCustomerId().trim() +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {

                String productID = rs.getString("productID");
                Product product = getProductDetails(productID);
                favs.add(product);
            }

        } catch (Exception exception) {
            System.out.println(" Exception in checking product in fav");
            System.out.println(exception);

        }
        return favs;
    }

    public  boolean checkIfProductInFav(Customer customer,String product) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "select * from fav where CustomerID = '" +
                    customer.getCustomerId().trim() +
                    "' and productID = '" +
                    product +
                    "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                return true;
            }

        } catch (Exception exception) {
            System.out.println(" Exception in checking product in fav");
            System.out.println(exception);

        }
        return false;
    }

    public  void insertNewOrder(String order_id,String customerID, String address) {
        try {
            Statement stmt = DAO.connection.createStatement();
            String sql = "insert into orders values('" +
                    order_id +
                    "','" +
                    customerID +
                    "','" +
                    address +
                    "')";

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
        } catch (Exception exception) {
            System.out.println(" Exception in adding product in orders");
            System.out.println(exception);

        }
    }
    public  void checkOut(Customer customer) {
        ArrayList<Product> cart = fetchAllCartForCustomer(customer);
        ArrayList<Department> departments = getDepartmentsWithProducts();
        ArrayList<Product> shopAllProducts = new ArrayList<>();
        for (Department dep: departments
             ) {
            for (Product pr:dep.getListProduct()
                 ) {
                shopAllProducts.add(pr);
            }
        }


        for (Product pro:cart
             ) {
            Product originalProduct = null;
            for (Product pr:shopAllProducts
                 ) {
                if (pr.getIDProduct().trim().equals(pro.getIDProduct().trim())){
                    originalProduct = pr;
                }
            }
            if (originalProduct == null) {
                return;
            }

            try {
                Statement stmt = DAO.connection.createStatement();
                String sql;
                if ((originalProduct.getQuanity() - pro.getQuanity()) > 0) {
                    sql = "update product set quanity = " +
                            originalProduct.getQuanity() +
                            " - " +
                            pro.getQuanity() +
                            " where IDProduct = '" +
                            originalProduct.getIDProduct().trim() +
                            "'";
                } else {
                    sql = "delete from product where IDProduct = '" +
                            pro.getIDProduct().trim() +
                            "'";
                }

                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(sql);
            } catch (Exception exception) {
                System.out.println(" Exception in adding product in orders");
                System.out.println(exception);

            }

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

//create table cart (CustomerID varchar(25), productID varchar(25), quanity NUMERIC(25))
//"create table fav (CustomerID varchar(25), productID varchar(25))"
//"create table orders(order_id varchar(25),CustomerID varchar(25),address varchar(250))"