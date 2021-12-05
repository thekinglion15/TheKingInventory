/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.sql.*;
import javax.swing.JOptionPane;

public class FrmMantUser extends javax.swing.JFrame {
    
    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoBuscar;
    final String tabla = "usuarios";
    
    public FrmMantUser(String codBuscar) {
        initComponents();
        
        codigoBuscar = codBuscar;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        if(codigoBuscar == "0")
        {
            TxtCodigo.setText("");
            TxtNombre.setText("");
            TxtPass.setText("");
            TxtConfirmar.setText("");
        }
        else
        {
            String script = "select codigo, nombre, contrasena from usuarios where codigo = " + codigoBuscar;
            
            try
            {
                String codigo = "", nombre = "", contrasena = "";
                
                Statement query = ObjDB.conectar().createStatement();
                ResultSet resultado = query.executeQuery(script);

                while(resultado.next())
                {
                    codigo = resultado.getString(1);
                    nombre = resultado.getString(2);
                    contrasena = resultado.getString(3);
                }
                
                TxtCodigo.setText(codigo);
                TxtNombre.setText(nombre);
                TxtPass.setText(contrasena);
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
        String nombre = TxtNombre.getText();
        String contrasena = TxtPass.getText();
        String confirmar = TxtConfirmar.getText();
        
        if(codigo.equals(""))
        {
            if(!nombre.equals("") && !contrasena.equals("") && !confirmar.equals(""))
            {
                if(confirmar.equals(contrasena))
                {
                    try
                    {
                        PreparedStatement guardar = ObjDB.conectar().prepareStatement("insert into usuarios values(?,?,?)");

                        guardar.setString(1, "0");
                        guardar.setString(2, nombre);
                        guardar.setString(3, contrasena);
                        guardar.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Datos guardados");

                        TxtNombre.setText("");
                        TxtPass.setText("");
                        TxtConfirmar.setText("");  
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");

                    TxtPass.setText("");
                    TxtConfirmar.setText("");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Datos vacios");
            }
        }
        else
        {
            if(!nombre.equals("") && !contrasena.equals("") && !confirmar.equals(""))
            {
                if(confirmar.equals(contrasena))
                {
                    try
                    {
                        PreparedStatement actualizar = ObjDB.conectar().prepareStatement("update usuarios set nombre = ?, contrasena = ? where codigo = ?");

                        actualizar.setString(1, nombre);
                        actualizar.setString(2, contrasena);
                        actualizar.setString(3, codigo);
                        actualizar.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Datos actualizados");

                        TxtCodigo.setText("");
                        TxtNombre.setText("");
                        TxtPass.setText("");
                        TxtConfirmar.setText("");  
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");

                    TxtPass.setText("");
                    TxtConfirmar.setText("");
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
                PreparedStatement eliminar = ObjDB.conectar().prepareStatement("delete from usuarios where codigo = ?");

                eliminar.setString(1, codigo);
                eliminar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Datos eliminados");

                TxtCodigo.setText("");
                TxtNombre.setText("");
                TxtPass.setText("");
                TxtConfirmar.setText("");  
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
        TxtNombre = new javax.swing.JTextField();
        TxtConfirmar = new javax.swing.JPasswordField();
        TxtPass = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNuevo = new javax.swing.JMenuItem();
        MenuBuscar = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuEliminar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Usuario");
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
        jLabel2.setText("Confirmar");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Codigo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 70, 20));

        TxtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtCodigo.setEnabled(false);
        jPanel1.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 80, -1));

        TxtNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 160, -1));

        TxtConfirmar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 160, -1));

        TxtPass.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, -1));

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
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario(tabla, null);
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        TxtCodigo.setText("");
        TxtNombre.setText("");
        TxtPass.setText("");
        TxtConfirmar.setText("");
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void MenuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBuscarActionPerformed
        FrmBuscarUsuario objBuscar = new FrmBuscarUsuario(tabla, null);
        objBuscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuBuscarActionPerformed

    private void MenuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNuevoActionPerformed
        TxtCodigo.setText("");
        TxtNombre.setText("");
        TxtPass.setText("");
        TxtConfirmar.setText("");
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
            java.util.logging.Logger.getLogger(FrmMantUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMantUser(null).setVisible(true);
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
    public javax.swing.JTextField TxtCodigo;
    private javax.swing.JPasswordField TxtConfirmar;
    public javax.swing.JTextField TxtNombre;
    public javax.swing.JPasswordField TxtPass;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
