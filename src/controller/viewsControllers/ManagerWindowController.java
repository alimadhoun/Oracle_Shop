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



    public ManagerWindowController() {

    }

    public void upProduct(Product product) {
        DAO.shared.updateProduct(product);
    }

    public Boolean insertNewCustomer(Customer newCustomer) {
        return DAO.shared.insertNewCustomer(newCustomer);
    }

    public void updateCustomerInfo(Customer customer) {
        DAO.shared.updateCustomerInfo(customer);
    }

    public Boolean insetNewDepartment(Department department) {
       return DAO.shared.insertNewDepartment(department);
    }

    public void updateDepartment(Department newDepartment) {
        DAO.shared.updateDepartment(newDepartment);
    }

    public void saveUserAction(String action, String time) {
        DAO.shared.saveUserAction(action,time);
    }
}
