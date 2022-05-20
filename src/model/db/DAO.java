/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Abdalaziz Abdallah
 */
public class DAO {

    //TODO: "DAO class" insert all queries as function here 

    private Connection connection;

    public DAO() {
        try {
            connection = DBConnection.getInstance().getConn();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getTest() {
        try {
            Statement stmt = this.connection.createStatement();
//            stmt.executeQuery("delete from test where message = 'heeeeeeey'");
//            stmt.executeQuery("insert into test values('welcome')");
            ResultSet rs = stmt.executeQuery("select * from test");
            while(rs.next()){
                //Display values
                System.out.println("message: " + rs.getString("message"));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
