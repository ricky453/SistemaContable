/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author José Lopez
 */
public class Conexion {
     public Connection conexion;
     public  Statement st;
    
        
            
   
    public  Conexion() throws ErrorSistemaContable{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/sistemacontable", "root", "");  
            st = conexion.createStatement();
            
        } catch (Exception e) {
            throw new ErrorSistemaContable("Conexion.conexion()",e.getMessage());

        }
    }
    
    public Connection conectarBase() throws ErrorSistemaContable {
      if (conexion == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/sistemacontable", "root", "301997");
         } catch (Exception ex) {
            
         }
      }
      return conexion;
   }
    
}
