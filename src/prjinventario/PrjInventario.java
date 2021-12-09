/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;

public class PrjInventario {
    
    public static void main(String[] args) {        
        //Creando los objetos necesarios
        ClassDatabase ObjDB = ClassDatabase.getInstance();
        
        FrmPresentacion ObjPresentacion = new FrmPresentacion();
        FrmLogin ObjLogin = new FrmLogin();
        
        //Mostrando el formulario de presentacion
        ObjPresentacion.setVisible(true);
        
        //Conectando con la base de datos
        try
        {
            ObjDB.conectar();
            Statement script = ObjDB.conectar().createStatement();
            script.execute("SET GLOBAL time_zone = '-3:00';");
            System.out.println("Conexion exitosa");
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        
        //Si se logra conectar sin problemas, se procede a cerrar presentacion
        ObjPresentacion.dispose();
        
        //Mostrando el formulario de login
        ObjLogin.setVisible(true);
    }
}
