package org.gildardoalvarado.DB;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;


public class Conexion {
    private static Connection conexion = null;
    public static Connection obtener() throws SQLException, ClassNotFoundException{
        if(conexion == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost/Conexion","root","admin");
            }catch(SQLException e){
                throw new SQLException(e);
            }catch(ClassNotFoundException e){
                throw new ClassCastException(e.getMessage());
            }
        }
        
        return conexion;
    }
    
    public static void cerrar() throws SQLException{
        if(conexion != null){
            conexion.close();
        }
    }      
}
