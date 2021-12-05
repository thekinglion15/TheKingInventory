/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class FrmBuscarUsuario extends javax.swing.JFrame {

    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String DBTabla, Fpadre, codigoSeleccion;
    
    public FrmBuscarUsuario(String padre, String padre2) {
        initComponents();
        
        DBTabla = padre;
        Fpadre = padre2;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        String script = "select codigo, nombre from " + DBTabla;
        DefaultTableModel modelo;
        
        try
        {
            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);
            ResultSetMetaData dato = resultado.getMetaData();
            ArrayList<Object[]> lista = new ArrayList<>();

            while(resultado.next())
            {
                Object[] filas = new Object[dato.getColumnCount()];
                
                for(int i = 0; i < filas.length; i++)
                {
                    filas[i] = resultado.getObject(i + 1);
                }
                
                lista.add(filas);
            }
            
            modelo = (DefaultTableModel) this.tabla.getModel();
            
            for(int i = 0; i < lista.size(); i++)
            {
                modelo.addRow(lista.get(i));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnSalir = new javax.swing.JButton();
        BtnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuSeleccionar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        BtnSalir.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir Grande.png"))); // NOI18N
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 32, 32));

        BtnSeleccionar.setBackground(new java.awt.Color(255, 255, 255));
        BtnSeleccionar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        BtnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Seleccionar Grande.png"))); // NOI18N
        BtnSeleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeleccionarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 32, 32));

        tabla.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(307);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 370, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 250));

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        MenuSeleccionar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuSeleccionar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Seleccionar.png"))); // NOI18N
        MenuSeleccionar.setMnemonic('V');
        MenuSeleccionar.setText("Seleccionar");
        jMenu1.add(MenuSeleccionar);

        MenuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        MenuSalir.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir.png"))); // NOI18N
        MenuSalir.setText("Salir");
        MenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(MenuSalir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        switch(DBTabla)
        {
            case "usuarios":
                FrmMantUser objUsuario = new FrmMantUser(null);
                
                objUsuario.setVisible(true);
            break;
            
            case "cliente":
                FrmMantCliente objCliente = new FrmMantCliente(null);
                
                objCliente.setVisible(true);
            break;
            
            case "stock":
                if(Fpadre.equals("stock"))
                {
                    FrmMantStock objStock = new FrmMantStock(null);
                
                    objStock.setVisible(true);
                }
                if(Fpadre.equals("movimiento"))
                {
                    FrmMovimiento objMovimiento = new FrmMovimiento(null);
                
                    objMovimiento.setVisible(true);
                }
            break;
            
            case "empleado":
                FrmMantEmpleados objEmpleado = new FrmMantEmpleados(null);
                
                objEmpleado.setVisible(true);
            break;
            
            case "proveedor":
                FrmMantProveedor objProveedor = new FrmMantProveedor(null);
                
                objProveedor.setVisible(true);
            break;
        }
        
        this.dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void MenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalirActionPerformed
        switch(DBTabla)
        {
            case "usuarios":
                FrmMantUser objUsuario = new FrmMantUser(null);
                
                objUsuario.setVisible(true);
            break;
            
            case "cliente":
                FrmMantCliente objCliente = new FrmMantCliente(null);
                
                objCliente.setVisible(true);
            break;
            
            case "stock":
                if(Fpadre.equals("stock"))
                {
                    FrmMantStock objStock = new FrmMantStock(null);
                
                    objStock.setVisible(true);
                }
                if(Fpadre.equals("movimiento"))
                {
                    FrmMovimiento objMovimiento = new FrmMovimiento(null);
                
                    objMovimiento.setVisible(true);
                }
            break;
            
            case "empleado":
                FrmMantEmpleados objEmpleado = new FrmMantEmpleados(null);
                
                objEmpleado.setVisible(true);
            break;
            
            case "proveedor":
                FrmMantProveedor objProveedor = new FrmMantProveedor(null);
                
                objProveedor.setVisible(true);
            break;
        }
        
        this.dispose();
    }//GEN-LAST:event_MenuSalirActionPerformed

    private void BtnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeleccionarActionPerformed
        switch(DBTabla)
        {
            case "usuarios":
                FrmMantUser objUsuario = new FrmMantUser(codigoSeleccion);
                
                objUsuario.setVisible(true);
            break;
            
            case "cliente":
                FrmMantCliente objCliente = new FrmMantCliente(codigoSeleccion);
                
                objCliente.setVisible(true);
            break;
            
            case "stock":
                if(Fpadre.equals("stock"))
                {
                    FrmMantStock objStock = new FrmMantStock(codigoSeleccion);
                
                    objStock.setVisible(true);
                }
                if(Fpadre.equals("movimiento"))
                {
                    FrmMovimiento objMovimiento = new FrmMovimiento(codigoSeleccion);
                
                    objMovimiento.setVisible(true);
                }
            break;
            
            case "empleado":
                FrmMantEmpleados objEmpleado = new FrmMantEmpleados(codigoSeleccion);
                
                objEmpleado.setVisible(true);
            break;
            
            case "proveedor":
                FrmMantProveedor objProveedor = new FrmMantProveedor(codigoSeleccion);
                
                objProveedor.setVisible(true);
            break;
        }
        
        this.dispose();
    }//GEN-LAST:event_BtnSeleccionarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int seleccion = tabla.rowAtPoint(evt.getPoint());
        
        codigoSeleccion = String.valueOf(tabla.getValueAt(seleccion, 0));
    }//GEN-LAST:event_tablaMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuscarUsuario(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSalir;
    private javax.swing.JButton BtnSeleccionar;
    private javax.swing.JMenuItem MenuSalir;
    private javax.swing.JMenuItem MenuSeleccionar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
