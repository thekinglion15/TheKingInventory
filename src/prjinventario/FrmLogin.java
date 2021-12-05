/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

//Importando todos los componentes de la libreria Swing
import java.sql.*;
import javax.swing.JOptionPane;

public class FrmLogin extends javax.swing.JFrame {

    ClassDatabase ObjDB = ClassDatabase.getInstance();
    private int intentos = 0;

    public FrmLogin() {
        initComponents();
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtUser = new javax.swing.JTextField();
        TxtPass = new javax.swing.JPasswordField();
        BtnEntrar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        LbImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Contraseña");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Gabriel De León © 2021");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        TxtUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        getContentPane().add(TxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 160, -1));

        TxtPass.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        getContentPane().add(TxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 160, 20));

        BtnEntrar.setBackground(new java.awt.Color(255, 255, 255));
        BtnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Entrar Grande.png"))); // NOI18N
        BtnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 32, 32));

        BtnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar Grande.png"))); // NOI18N
        BtnCancelar.setToolTipText("");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 32, 32));

        LbImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inventory_Login.jpg"))); // NOI18N
        LbImagen.setToolTipText("");
        getContentPane().add(LbImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEntrarActionPerformed
        String usuarioForm, contrasenaForm, usuarioDB, contrasenaDB, script;
        
        usuarioForm = TxtUser.getText();
        contrasenaForm = TxtPass.getText();
        usuarioDB = "";
        contrasenaDB = "";
        script = "select usuario, contrasena from login";
        
        intentos++;
        
        //Conexion y sacar los datos de la base de datos
        try
        {
            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);
            
            while(resultado.next())
            {
                usuarioDB = resultado.getString(1);
                contrasenaDB = resultado.getString(2);
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        
        //Evaluacion para el acceso
        if (intentos < 3)
        {
            if (usuarioForm.equals(usuarioDB))
            {
                if (contrasenaForm.equals(contrasenaDB))
                {
                    FrmPrincipal ObjPrincipal = new FrmPrincipal();
                    this.dispose();
                    ObjPrincipal.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                    
                    TxtPass.setText("");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Usuario incorrecto");
                
                TxtUser.setText("");
                TxtPass.setText("");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Ha superado la cantidad de intentos\nEl programa procedera a cerrarse");
            this.dispose();
        }
    }//GEN-LAST:event_BtnEntrarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEntrar;
    private javax.swing.JLabel LbImagen;
    private javax.swing.JPasswordField TxtPass;
    private javax.swing.JTextField TxtUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
