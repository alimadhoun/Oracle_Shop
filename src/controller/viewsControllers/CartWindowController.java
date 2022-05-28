/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.viewsControllers;

import model.Customer;
import model.Product;
import model.db.DAO;

import java.util.ArrayList;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class CartWindowController {
  
    private DAO theDAO;

    public CartWindowController() {
        this.theDAO = new DAO();
    }

    public ArrayList<Product> fetchAllInCart(Customer customer) {
        return DAO.fetchAllCartForCustomer(customer);
    }

    public Double getTotalPriceInCart(Customer customer) {
        return DAO.getSumOfCart(customer);
    }
    public void removeFromCart(String customer, Product product) {
        DAO.removeItemFromCart(customer,product);
    }

    public void addNewOrder(String orderID, String customerID, String address) {
        DAO.insertNewOrder(orderID,customerID,address);
    }

    public void checkOut(Customer customer) {
        DAO.checkOut(customer);
    }
//    public void clearUserCart(Customer customer) {
//        DAO.clearUserCart(customer);
//    }
}
