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
public class CartCheckoutWindowController {
  
//    private DAO theDAO;

    public CartCheckoutWindowController() {
//        this.theDAO = new DAO();
    }

    public Double getTotalPriceInCart(Customer customer) {
        return DAO.shared.getSumOfCart(customer);
    }

    public ArrayList<Product> fetchAllCart(Customer customer) {
        return DAO.shared.fetchAllCartForCustomer(customer);
    }
}
