/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;

public class ClassDatabase {
    private final String url = "jdbc:mysql://localhost:3306/thekinginventory";
    private final String user = "root";
    private final String pass = "kl15112000";
    
    private static Connection conexion;
    private static ClassDatabase instancia;
    
    private ClassDatabase(){}
    
    public Connection conectar()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
            
            return conexion;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        return conexion;
    }
    
    public void cierra() throws SQLException
    {
        try
        {
            conexion.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            conexion.close();
        }
        finally
        {
            conexion.close();
        }
    }
    
    public static ClassDatabase getInstance()
    {
        if(instancia == null)
        {
            instancia = new ClassDatabase();
        }
        
        return instancia;
    }
}
