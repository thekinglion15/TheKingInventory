/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;
import javax.swing.JOptionPane;

public class FrmMantStock extends javax.swing.JFrame {
    
    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoBuscar;
    final String tabla = "stock";
    
    public FrmMantStock(String codBuscar) {
        initComponents();
        
        codigoBuscar = codBuscar;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        if(codigoBuscar == null || codigoBuscar == "0")
        {
            TxtCodigo.setText("");
            TxtArticulo.setText("");
            TxtCantidad.setValue(0);
            TxtPrecio.setText("");
            TxtAlmacen.setText("");
        }
        else
        {
            String script = "select * from stock where codigo = " + codigoBuscar;
            
            try
            {
                String codigo = "", articulo = "", cantidad = "", precio = "", almacen = "";
                
                Statement query = ObjDB.conectar().createStatement();
                ResultSet resultado = query.executeQuery(script);

                while(resultado.next())
                {
                    codigo = resultado.getString(1);
                    articulo = resultado.getString(2);
                    cantidad = resultado.getString(3);
                    precio = resultado.getString(4);
                    almacen = resultado.getString(5);
                }
                
                TxtCodigo.setText(codigo);
                TxtArticulo.setText(articulo);
                TxtCantidad.setValue(Integer.parseInt(cantidad));
                TxtPrecio.setText(precio);
                TxtAlmacen.setText(almacen);
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex);
            }
        }
    }
    
    public void Guardar()
    {
        String codigo = TxtCodigo.getText();
        String articulo = TxtArticulo.getText();
        String cantidad = String.valueOf(TxtCantidad.getValue());
        String precio = TxtPrecio.getText();
        String almacen = TxtAlmacen.getText(); 
        
        if(codigo.equals(""))
        {
            if(!articulo.equals("") && !cantidad.equals("0") && !precio.equals("") && !almacen.equals(""))
            {
                try
                {
                    PreparedStatement guardar = ObjDB.conectar().prepareStatement("insert into stock values(?,?,?,?,?)");

                    guardar.setString(1, "0");
                    guardar.setString(2, articulo);
                    guardar.setString(3, cantidad);
                    guardar.setString(4, precio);
                    guardar.setString(5, almacen);
                    guardar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos guardados");

                    TxtCodigo.setText("");
                    TxtArticulo.setText("");
                    TxtCantidad.setValue(0);
                    TxtPrecio.setText("");  
                    TxtAlmacen.setText("");
                }
                catch(Exception ex)
                {
                    System.out.println("Error: " + ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Datos vacios");
            }
        }
        else
        {
            if(!articulo.equals("") && !cantidad.equals("0") && !precio.equals("") && !almacen.equals(""))
            {
                try
                {
                    PreparedStatement actualizar = ObjDB.conectar().prepareStatement("update stock set nombre = ?, cantidad = ?, precio = ?, almacen = ? where codigo = ?");

                    actualizar.setString(1, articulo);
                    actualizar.setString(2, cantidad);
                    actualizar.setString(3, precio);
                    actualizar.setString(4, almacen);
                    actualizar.setString(5, codigo);
                    actualizar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos actualizados");

                    TxtCodigo.setText("");
                    TxtArticulo.setText("");
                    TxtCantidad.setValue(0);
                    TxtPrecio.setText("");  
                    TxtAlmacen.setText("");
                }
                catch(Exception ex)
                {
                    System.out.println("Error: " + ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Datos vacios");
            }
        }
    }
    
    public void Eliminar()
    {
        String codigo = TxtCodigo.getText();
        
        if(!codigo.equals(""))
        {
            try
            {
                PreparedStatement eliminar = ObjDB.conectar().prepareStatement("delete from stock where codigo = ?");

                eliminar.setString(1, codigo);
                eliminar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Datos eliminados");

                TxtCodigo.setText("");
                TxtArticulo.setText("");
                TxtCantidad.setValue(0);
                TxtPrecio.setText("");  
                TxtAlmacen.setText("");
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Busque a quien desea eliminar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtCodigo = new javax.swing.JTextField();
        TxtPrecio = new javax.swing.JTextField();
        TxtArticulo = new javax.swing.JTextField();
        TxtAlmacen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtCantidad = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNuevo = new javax.swing.JMenuItem();
        MenuBuscar = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuEliminar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        BtnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        BtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo Grande.png"))); // NOI18N
        BtnNuevo.setFocusPainted(false);
        BtnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 32, 32));

        BtnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar Grande.png"))); // NOI18N
        BtnBuscar.setFocusPainted(false);
        BtnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 32, 32));

        BtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar Grande.png"))); // NOI18N
        BtnGuardar.setFocusPainted(false);
        BtnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 32, 32));

        BtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar Grande.png"))); // NOI18N
        BtnEliminar.setFocusPainted(false);
        BtnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 32, 32));

        BtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        BtnSalir.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir Grande.png"))); // NOI18N
        BtnSalir.setFocusPainted(false);
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 32, 32));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Valor");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 40, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Codigo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Articulo");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Cantidad");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 70, 20));

        TxtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtCodigo.setEnabled(false);
        jPanel1.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 80, -1));

        TxtPrecio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 80, -1));

        TxtArticulo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 170, -1));

        TxtAlmacen.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 170, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Almacen");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, 20));

        TxtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 180));

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        MenuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        MenuNuevo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"))); // NOI18N
        MenuNuevo.setText("Nuevo");
        MenuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(MenuNuevo);

        MenuBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        MenuBuscar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        MenuBuscar.setText("Buscar");
        MenuBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBuscarActionPerformed(evt);
            }
        });
        jMenu1.add(MenuBuscar);

        MenuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        MenuGuardar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar.png"))); // NOI18N
        MenuGuardar.setText("Guardar");
        MenuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(MenuGuardar);

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

        jMenu2.setText("Edicion");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        MenuEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        MenuEliminar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        MenuEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        MenuEliminar.setText("Eliminar");
        MenuEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEliminarActionPerformed(evt);
            }
        });
        jMenu2.add(MenuEliminar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void MenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_MenuSalirActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario(tabla, "stock");
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        TxtCodigo.setText("");
        TxtArticulo.setText("");
        TxtCantidad.setValue(0);
        TxtPrecio.setText("");  
        TxtAlmacen.setText("");
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void MenuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBuscarActionPerformed
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario(tabla, "stock");
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuBuscarActionPerformed

    private void MenuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNuevoActionPerformed
        TxtCodigo.setText("");
        TxtArticulo.setText("");
        TxtCantidad.setValue(0);
        TxtPrecio.setText("");  
        TxtAlmacen.setText("");
    }//GEN-LAST:event_MenuNuevoActionPerformed

    private void MenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_MenuGuardarActionPerformed

    private void MenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_MenuEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMantStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMantStock(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JMenuItem MenuBuscar;
    private javax.swing.JMenuItem MenuEliminar;
    private javax.swing.JMenuItem MenuGuardar;
    private javax.swing.JMenuItem MenuNuevo;
    private javax.swing.JMenuItem MenuSalir;
    public javax.swing.JTextField TxtAlmacen;
    public javax.swing.JTextField TxtArticulo;
    private javax.swing.JSpinner TxtCantidad;
    public javax.swing.JTextField TxtCodigo;
    public javax.swing.JTextField TxtPrecio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
