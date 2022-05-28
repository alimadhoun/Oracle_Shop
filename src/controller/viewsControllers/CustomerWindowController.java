/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.viewsControllers;

import model.Customer;
import model.Department;
import model.Product;
import model.db.DAO;

import java.util.ArrayList;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class CustomerWindowController {



    public CustomerWindowController() {

    }

    public void saveUserAction(String action, String time) {
        DAO.shared.saveUserAction(action,time);
    }

    public ArrayList<Department> getAllDepartments() {
        return DAO.shared.getDepartmentsWithProducts();
    }

    public void addToCart(Product product, Customer customer) {
        DAO.shared.addToCart(product,customer);
    }

    public void addToFav(Customer customer, Product product) {
        DAO.shared.addToFav(customer,product);
    }

    public void removeFromFav(Customer customer, Product product) {
        DAO.shared.removeFromFav(customer,product);
    }

    public boolean checkIfProductInFav(Customer customer, String product) {
        return DAO.shared.checkIfProductInFav(customer,product);
    }

    public ArrayList<Product> getAllInFav(Customer CustomerID) {
        return DAO.shared.getAllFromFav(CustomerID);
    }
}
