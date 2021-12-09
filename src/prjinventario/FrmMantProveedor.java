/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FrmMantProveedor extends javax.swing.JFrame {
    
    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoBuscar;
    final String tabla = "proveedor";
    
    public FrmMantProveedor(String codBuscar) {
        initComponents();
        
        codigoBuscar = codBuscar;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        if(codigoBuscar == "0")
        {
            TxtCodigo.setText("");
            TxtRNC.setText("");
            TxtCompania.setText("");
            TxtDireccion.setText("");
            TxtTelefono.setText("");
            TxtArticulo.setText("");
            TxtTotal.setText("");
        }
        else
        {
            String script = "select * from proveedor where codigo = " + codigoBuscar;
            
            try
            {
                String codigo = "", rnc = "", compania = "", direccion = "", telefono = "", articulo = "", total = "";
                
                Statement query = ObjDB.conectar().createStatement();
                ResultSet resultado = query.executeQuery(script);

                while(resultado.next())
                {
                    codigo = resultado.getString(1);
                    rnc = resultado.getString(2);
                    compania = resultado.getString(3);
                    direccion = resultado.getString(4);
                    telefono = resultado.getString(5);
                    articulo = resultado.getString(6);
                    total = resultado.getString(7);
                }
                
                TxtCodigo.setText(codigo);
                TxtRNC.setText(rnc);
                TxtCompania.setText(compania);
                TxtDireccion.setText(direccion);
                TxtTelefono.setText(telefono);
                TxtArticulo.setText(articulo);
                TxtTotal.setText(total);
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
        String rnc = TxtRNC.getText();
        String compania = TxtCompania.getText();
        String direccion = TxtDireccion.getText();
        String telefono = TxtTelefono.getText();
        String articulo = TxtArticulo.getText();
        String total = TxtTotal.getText();
        
        if(codigo.equals(""))
        {
            if(!rnc.equals("") && !compania.equals("") && !direccion.equals("") && !telefono.equals("") && !articulo.equals("") && !total.equals(""))
            {
                try
                {
                    PreparedStatement guardar = ObjDB.conectar().prepareStatement("insert into proveedor values(?,?,?,?,?,?,?)");

                    guardar.setString(1, "0");
                    guardar.setString(2, rnc);
                    guardar.setString(3, compania);
                    guardar.setString(4, direccion);
                    guardar.setString(5, telefono);
                    guardar.setString(6, articulo);
                    guardar.setString(7, total);
                    guardar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos guardados");

                    TxtCodigo.setText("");
                    TxtRNC.setText("");
                    TxtCompania.setText("");
                    TxtDireccion.setText("");
                    TxtTelefono.setText("");
                    TxtArticulo.setText("");
                    TxtTotal.setText("");
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
            if(!rnc.equals("") && !compania.equals("") && !direccion.equals("") && !telefono.equals("") && !articulo.equals("") && !total.equals(""))
            {
                try
                {
                    PreparedStatement actualizar = ObjDB.conectar().prepareStatement("update proveedor set rnc = ?, nombre = ?, direccion = ?, telefono = ?, articulo = ?, total = ? where codigo = ?");

                    actualizar.setString(1, rnc);
                    actualizar.setString(2, compania);
                    actualizar.setString(3, direccion);
                    actualizar.setString(4, telefono);
                    actualizar.setString(5, articulo);
                    actualizar.setString(6, total);
                    actualizar.setString(7, codigo);
                    actualizar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos actualizados");

                    TxtCodigo.setText("");
                    TxtRNC.setText("");
                    TxtCompania.setText("");
                    TxtDireccion.setText("");
                    TxtTelefono.setText("");
                    TxtArticulo.setText("");
                    TxtTotal.setText("");  
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
                PreparedStatement eliminar = ObjDB.conectar().prepareStatement("delete from proveedor where codigo = ?");

                eliminar.setString(1, codigo);
                eliminar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Datos eliminados");

                TxtCodigo.setText("");
                TxtRNC.setText("");
                TxtCompania.setText("");
                TxtDireccion.setText("");
                TxtTelefono.setText("");
                TxtArticulo.setText("");
                TxtTotal.setText("");
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
    
    public void JasperReport()
    {
        //Extraigo los datos de la base para pasarlo a la clase
        String script = "select codigo, rnc, nombre, articulo, total from proveedor";
        
        try
        {
            //Lista para guardar elementos
            List<DatosProveedor> lista = new ArrayList<DatosProveedor>();
            
            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);

            while(resultado.next())
            {
                DatosProveedor datos = new DatosProveedor();
                
                datos.setCodigo(Integer.parseInt(resultado.getString(1)));
                datos.setRnc(resultado.getString(2));
                datos.setNombre(resultado.getString(3));
                datos.setArticulo(resultado.getString(4));
                datos.setTotal(resultado.getString(5));
                
                //Agregar elementos a la lista
                lista.add(datos);
            }
            
            //Convertir lista en JRBeanCollectionDataSource
            JRBeanCollectionDataSource JRBeanItems = new JRBeanCollectionDataSource(lista);
            
            //Mapa para contener los parámetros del informe Jaspers
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("CollectionBeanParam", JRBeanItems);
            
            //Leer el archivo jrxml y crear el objeto JasperDesign
            InputStream archivo = new FileInputStream(new File("C:\\Users\\kinglion\\Desktop\\JasperReport\\JRXML\\Proveedor.jrxml"));
            JasperDesign JasperDesign = JRXmlLoader.load(archivo);
            
            //Compilar jrxml con la ayuda de la clase JasperReport
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            
            //Usando el objeto JasperReport para generar PDF
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parametros, new JREmptyDataSource());
            
            //Llamar al motor Jasper para mostrar el informe en la ventana JasperViewer
            JasperViewer.viewReport(JasperPrint);

            System.out.println("Archivo generado");
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
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
        TxtArticulo = new javax.swing.JTextField();
        TxtCompania = new javax.swing.JTextField();
        TxtRNC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtDireccion = new javax.swing.JTextField();
        TxtTelefono = new javax.swing.JFormattedTextField();
        TxtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        BtnReporte1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNuevo = new javax.swing.JMenuItem();
        MenuBuscar = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuEliminar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");
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
        jPanel1.add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 32, 32));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Articulo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 70, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Codigo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("RNC");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Teléfono");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 70, 20));

        TxtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtCodigo.setEnabled(false);
        jPanel1.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 80, -1));

        TxtArticulo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 160, -1));

        TxtCompania.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtCompania, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, -1));

        TxtRNC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtRNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 160, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Compañia");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Direccion");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, 20));

        TxtDireccion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 160, -1));

        try {
            TxtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefono.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 100, -1));

        TxtTotal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 70, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Total");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 70, 20));

        BtnReporte1.setBackground(new java.awt.Color(255, 255, 255));
        BtnReporte1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnReporte1.setForeground(new java.awt.Color(255, 255, 255));
        BtnReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Reporte.png"))); // NOI18N
        BtnReporte1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReporte1ActionPerformed(evt);
            }
        });
        jPanel1.add(BtnReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 32, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 270));

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
        TxtRNC.setText("");
        TxtCompania.setText("");
        TxtDireccion.setText("");
        TxtTelefono.setText("");
        TxtArticulo.setText("");
        TxtTotal.setText("");
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
        TxtRNC.setText("");
        TxtCompania.setText("");
        TxtDireccion.setText("");
        TxtTelefono.setText("");
        TxtArticulo.setText("");
        TxtTotal.setText("");
    }//GEN-LAST:event_MenuNuevoActionPerformed

    private void MenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_MenuGuardarActionPerformed

    private void MenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_MenuEliminarActionPerformed

    private void BtnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReporte1ActionPerformed
        JasperReport();
    }//GEN-LAST:event_BtnReporte1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMantProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMantProveedor(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnReporte1;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JMenuItem MenuBuscar;
    private javax.swing.JMenuItem MenuEliminar;
    private javax.swing.JMenuItem MenuGuardar;
    private javax.swing.JMenuItem MenuNuevo;
    private javax.swing.JMenuItem MenuSalir;
    public javax.swing.JTextField TxtArticulo;
    public javax.swing.JTextField TxtCodigo;
    public javax.swing.JTextField TxtCompania;
    public javax.swing.JTextField TxtDireccion;
    public javax.swing.JTextField TxtRNC;
    private javax.swing.JFormattedTextField TxtTelefono;
    public javax.swing.JTextField TxtTotal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
