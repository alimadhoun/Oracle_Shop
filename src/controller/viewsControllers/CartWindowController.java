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
  


    public CartWindowController() {

    }

    public ArrayList<Product> fetchAllInCart(Customer customer) {
        return DAO.shared.fetchAllCartForCustomer(customer);
    }

    public Double getTotalPriceInCart(Customer customer) {
        return DAO.shared.getSumOfCart(customer);
    }
    public void removeFromCart(String customer, Product product) {
        DAO.shared.removeItemFromCart(customer,product);
    }

    public void addNewOrder(String orderID, String customerID, String address) {
        DAO.shared.insertNewOrder(orderID,customerID,address);
    }

    public void checkOut(Customer customer) {
        DAO.shared.checkOut(customer);
    }
//    public void clearUserCart(Customer customer) {
//        DAO.clearUserCart(customer);
//    }
}
