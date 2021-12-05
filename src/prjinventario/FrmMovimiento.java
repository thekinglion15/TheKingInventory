/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;
import javax.swing.JOptionPane;

public class FrmMovimiento extends javax.swing.JFrame {
    
    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoBuscar, cantidadStock;
    
    public FrmMovimiento(String codBuscar) {
        initComponents();
        
        codigoBuscar = codBuscar;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        try
        {
            String script = "select nombre from stock";

            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);

            while(resultado.next())
            {
                CmbArticulo.addItem(resultado.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
        
        if(codigoBuscar == null || codigoBuscar == "0")
        {
            TxtCodigo.setText("");
            CmbArticulo.setSelectedIndex(0);
            TxtDescripcion.setText("");
            TxtCantidad.setValue(0);
            CmbMov.setSelectedIndex(0);
        }
        else
        {
            String script = "select codigo, nombre, cantidad from stock where codigo = " + codigoBuscar;
            
            try
            {
                String codigo = "", articulo = "";
                
                Statement query = ObjDB.conectar().createStatement();
                ResultSet resultado = query.executeQuery(script);

                while(resultado.next())
                {
                    codigo = resultado.getString(1);
                    articulo = resultado.getString(2);
                    cantidadStock = resultado.getString(3);
                }
                
                TxtCodigo.setText(codigo);
                CmbArticulo.setSelectedItem(articulo);
                TxtDescripcion.setText("");
                TxtCantidad.setValue(Integer.parseInt(cantidadStock));
                CmbMov.setSelectedIndex(0);
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
        String articulo = (String) CmbArticulo.getSelectedItem();
        String descripcion = TxtDescripcion.getText();
        String cantidadMov = String.valueOf(TxtCantidad.getValue());
        String movimiento = (String) CmbMov.getSelectedItem();
        
        int cambio = 0;
        int stock = Integer.parseInt(cantidadStock);
        int mov = Integer.parseInt(cantidadMov);

        if(movimiento.equals("Entrada"))
        {
            cambio = stock + mov;
        }
        else if(movimiento.equals("Salida"))
        {
            cambio = stock - mov;
        }
        
        if(cambio < 0)
        {
            JOptionPane.showMessageDialog(null, "No se puede retirar esta cantidad");
        }
        else
        {
            if(!codigo.equals("") && !articulo.equals("--Seleccionar--") && !descripcion.equals("") && !cantidadMov.equals("0") && !movimiento.equals("--Seleccionar--"))
            {
                try
                {
                    PreparedStatement guardar = ObjDB.conectar().prepareStatement("insert into movimiento values(?,?,?,?,?,?)");

                    guardar.setString(1, "0");
                    guardar.setString(2, codigo);
                    guardar.setString(3, articulo);
                    guardar.setString(4, descripcion);
                    guardar.setString(5, cantidadMov);
                    guardar.setString(6, movimiento);
                    guardar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos guardados");

                    TxtCodigo.setText("");
                    CmbArticulo.setSelectedIndex(0);
                    TxtDescripcion.setText("");
                    TxtCantidad.setValue(0);
                    CmbArticulo.setSelectedIndex(0);

                    try
                    {
                        String movi = String.valueOf(cambio);
                        PreparedStatement actualizar = ObjDB.conectar().prepareStatement("update stock set cantidad = ? where codigo = ?");

                        actualizar.setString(1, movi);
                        actualizar.setString(2, codigo);
                        actualizar.executeUpdate();
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex);
                    }
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnBuscar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtCodigo = new javax.swing.JTextField();
        TxtCantidad = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtDescripcion = new javax.swing.JTextArea();
        CmbArticulo = new javax.swing.JComboBox<>();
        CmbMov = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuBuscar = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movimiento");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(BtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 32, 32));

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
        jPanel1.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 32, 32));

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
        jPanel1.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 32, 32));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Movimiento");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 70, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Codigo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Articulo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 70, 20));

        TxtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtCodigo.setEnabled(false);
        jPanel1.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 80, -1));

        TxtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 50, -1));

        TxtDescripcion.setColumns(15);
        TxtDescripcion.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TxtDescripcion.setRows(5);
        jScrollPane1.setViewportView(TxtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, 90));

        CmbArticulo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CmbArticulo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--" }));
        jPanel1.add(CmbArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 160, -1));

        CmbMov.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CmbMov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "Entrada", "Salida" }));
        jPanel1.add(CmbMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 120, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 70, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 280));

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

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
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario("stock", "movimiento");
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void MenuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBuscarActionPerformed
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario("stock", "movimiento");
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuBuscarActionPerformed

    private void MenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_MenuGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMovimiento(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JComboBox<String> CmbArticulo;
    private javax.swing.JComboBox<String> CmbMov;
    private javax.swing.JMenuItem MenuBuscar;
    private javax.swing.JMenuItem MenuGuardar;
    private javax.swing.JMenuItem MenuSalir;
    private javax.swing.JSpinner TxtCantidad;
    public javax.swing.JTextField TxtCodigo;
    private javax.swing.JTextArea TxtDescripcion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
