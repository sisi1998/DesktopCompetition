/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Siwar
 */
public class DataSource {
    private String url="jdbc:mysql://localhost:3306/integration3";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static DataSource instance;

    private DataSource() {
       try {
           cnx = DriverManager.getConnection(url ,login ,pwd);
           System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
           System.err.print(ex.getMessage());
           //logger.getLogger(DataSource.className()).log(level.SEVERE,null,ex);
        }
    }
    
    
    public static DataSource getInstance(){
           if (instance==null)
               instance=new DataSource();
           return instance;
            }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    }
    
    

