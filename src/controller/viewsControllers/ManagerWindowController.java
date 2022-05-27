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

/**
 *
 * @author Abdalaziz Abdallah
 */
public class ManagerWindowController {

    private DAO theDAO;

    public ManagerWindowController() {
        this.theDAO = new DAO();
    }

    public void upProduct(Product product) {
        DAO.updateProduct(product);
    }

    public Boolean insertNewCustomer(Customer newCustomer) {
        return DAO.insertNewCustomer(newCustomer);
    }

    public void updateCustomerInfo(Customer customer) {
        DAO.updateCustomerInfo(customer);
    }

    public Boolean insetNewDepartment(Department department) {
       return DAO.insertNewDepartment(department);
    }

    public void updateDepartment(Department newDepartment) {
        DAO.updateDepartment(newDepartment);
    }

    public void saveUserAction(String action, String time) {
        DAO.saveUserAction(action,time);
    }
}
