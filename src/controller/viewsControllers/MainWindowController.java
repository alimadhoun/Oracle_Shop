/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.viewsControllers;

import model.db.DAO;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class MainWindowController {

    private DAO theDAO;

    public MainWindowController() {
        this.theDAO = new DAO();
        this.theDAO.getTest();
    }
    public void saveUserAction(String action, String time) {
        DAO.saveUserAction(action,time);
    }

}
