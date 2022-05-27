/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.viewsControllers;

import model.Department;
import model.db.DAO;

import java.util.ArrayList;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class CustomerWindowController {

    private DAO theDAO;

    public CustomerWindowController() {
        this.theDAO = new DAO();
    }

    public void saveUserAction(String action, String time) {
        DAO.saveUserAction(action,time);
    }

    public ArrayList<Department> getAllDepartments() {
        return DAO.getDepartmentsWithProducts();
    }
}
