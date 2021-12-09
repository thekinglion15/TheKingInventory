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

public class FrmMantEmpleados extends javax.swing.JFrame {
    
    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoBuscar;
    final String tabla = "empleado";
    
    public FrmMantEmpleados(String codBuscar) {
        initComponents();
        
        codigoBuscar = codBuscar;
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        if(codigoBuscar == "0")
        {
            TxtCodigo.setText("");
            TxtCedula.setText("");
            TxtNombre.setText("");
            CmbSexo.setSelectedIndex(0);
            TxtTelefono.setText("");
            TxtArea.setText("");
            TxtSueldo.setText("");
        }
        else
        {
            String script = "select * from empleado where codigo = " + codigoBuscar;
            
            try
            {
                String codigo = "", cedula = "", nombre = "", sexo = "", telefono = "", area= "", sueldo = "";
                
                Statement query = ObjDB.conectar().createStatement();
                ResultSet resultado = query.executeQuery(script);

                while(resultado.next())
                {
                    codigo = resultado.getString(1);
                    cedula = resultado.getString(2);
                    nombre = resultado.getString(3);
                    sexo = resultado.getString(4);
                    telefono = resultado.getString(5);
                    area = resultado.getString(6);
                    sueldo = resultado.getString(7);
                }
                
                TxtCodigo.setText(codigo);
                TxtCedula.setText(cedula);
                TxtNombre.setText(nombre);
                CmbSexo.setSelectedItem(sexo);
                TxtTelefono.setText(telefono);
                TxtArea.setText(area);
                TxtSueldo.setText(sueldo);
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
        String cedula = TxtCedula.getText();
        String nombre = TxtNombre.getText();
        String sexo = (String) CmbSexo.getSelectedItem();
        String telefono = TxtTelefono.getText();
        String area = TxtArea.getText();
        String sueldo = TxtSueldo.getText();
        
        if(codigo.equals(""))
        {
            if(!cedula.equals("") && !nombre.equals("") && !telefono.equals("") && !area.equals("") && !sueldo.equals("") && !sexo.equals("--Seleccionar--"))
            {
                try
                {
                    PreparedStatement guardar = ObjDB.conectar().prepareStatement("insert into empleado values(?,?,?,?,?,?,?)");

                    guardar.setString(1, "0");
                    guardar.setString(2, cedula);
                    guardar.setString(3, nombre);
                    guardar.setString(4, sexo);
                    guardar.setString(5, telefono);
                    guardar.setString(6, area);
                    guardar.setString(7, sueldo);
                    guardar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos guardados");

                    TxtCodigo.setText("");
                    TxtCedula.setText("");
                    TxtNombre.setText("");
                    CmbSexo.setSelectedIndex(0);
                    TxtTelefono.setText("");
                    TxtArea.setText("");
                    TxtSueldo.setText("");
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
            if(!cedula.equals("") && !nombre.equals("") && !telefono.equals("") && !area.equals("") && !sueldo.equals("") && !sexo.equals("--Seleccionar--"))
            {
                try
                {
                    PreparedStatement actualizar = ObjDB.conectar().prepareStatement("update empleado set cedula = ?, nombre = ?, sexo = ?, telefono = ?, area = ?, sueldo = ? where codigo = ?");

                    actualizar.setString(1, cedula);
                    actualizar.setString(2, nombre);
                    actualizar.setString(3, sexo);
                    actualizar.setString(4, telefono);
                    actualizar.setString(5, area);
                    actualizar.setString(6, sueldo);
                    actualizar.setString(7, codigo);
                    actualizar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos actualizados");

                    TxtCodigo.setText("");
                    TxtCedula.setText("");
                    TxtNombre.setText("");
                    CmbSexo.setSelectedIndex(0);
                    TxtTelefono.setText("");
                    TxtArea.setText("");
                    TxtSueldo.setText("");  
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
                PreparedStatement eliminar = ObjDB.conectar().prepareStatement("delete from empleado where codigo = ?");

                eliminar.setString(1, codigo);
                eliminar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Datos eliminados");

                TxtCodigo.setText("");
                TxtCedula.setText("");
                TxtNombre.setText("");
                CmbSexo.setSelectedIndex(0);
                TxtTelefono.setText("");
                TxtArea.setText("");
                TxtSueldo.setText(""); 
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
        String script = "select codigo, cedula, nombre, area, sueldo from empleado";
        
        try
        {
            //Lista para guardar elementos
            List<DatosEmpleados> lista = new ArrayList<DatosEmpleados>();
            
            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);

            while(resultado.next())
            {
                DatosEmpleados datos = new DatosEmpleados();
                
                datos.setCodigo(Integer.parseInt(resultado.getString(1)));
                datos.setCedula(resultado.getString(2));
                datos.setNombre(resultado.getString(3));
                datos.setArea(resultado.getString(4));
                datos.setSueldo(Double.parseDouble(resultado.getString(5)));
                
                //Agregar elementos a la lista
                lista.add(datos);
            }
            
            //Convertir lista en JRBeanCollectionDataSource
            JRBeanCollectionDataSource JRBeanItems = new JRBeanCollectionDataSource(lista);
            
            //Mapa para contener los parámetros del informe Jaspers
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("CollectionBeanParam", JRBeanItems);
            
            //Leer el archivo jrxml y crear el objeto JasperDesign
            InputStream archivo = new FileInputStream(new File("C:\\Users\\kinglion\\Desktop\\JasperReport\\JRXML\\Empleado.jrxml"));
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
        TxtArea = new javax.swing.JTextField();
        TxtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TxtSueldo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtTelefono = new javax.swing.JFormattedTextField();
        CmbSexo = new javax.swing.JComboBox<>();
        TxtCedula = new javax.swing.JFormattedTextField();
        BtnReporte = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNuevo = new javax.swing.JMenuItem();
        MenuBuscar = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuEliminar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Empleado");
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
        jLabel2.setText("Sueldo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 70, 20));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Codigo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 20));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Cédula");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Sexo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, 20));

        TxtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        TxtCodigo.setEnabled(false);
        jPanel1.add(TxtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 80, -1));

        TxtArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 160, -1));

        TxtNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, 20));

        TxtSueldo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 100, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Área");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 70, 20));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Teléfono");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 70, 20));

        try {
            TxtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###) ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtTelefono.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 100, -1));

        CmbSexo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "Masculino", "Femenino" }));
        jPanel1.add(CmbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 120, -1));

        try {
            TxtCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtCedula.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPanel1.add(TxtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 100, -1));

        BtnReporte.setBackground(new java.awt.Color(255, 255, 255));
        BtnReporte.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        BtnReporte.setForeground(new java.awt.Color(255, 255, 255));
        BtnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Reporte.png"))); // NOI18N
        BtnReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReporteActionPerformed(evt);
            }
        });
        jPanel1.add(BtnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 32, 32));

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
        TxtCedula.setText("");
        TxtNombre.setText("");
        CmbSexo.setSelectedIndex(0);
        TxtTelefono.setText("");
        TxtArea.setText("");
        TxtSueldo.setText("");
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
        TxtCedula.setText("");
        TxtNombre.setText("");
        CmbSexo.setSelectedIndex(0);
        TxtTelefono.setText("");
        TxtArea.setText("");
        TxtSueldo.setText("");
    }//GEN-LAST:event_MenuNuevoActionPerformed

    private void MenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_MenuGuardarActionPerformed

    private void MenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_MenuEliminarActionPerformed

    private void BtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReporteActionPerformed
        JasperReport();
    }//GEN-LAST:event_BtnReporteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMantEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMantEmpleados(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnReporte;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JComboBox<String> CmbSexo;
    private javax.swing.JMenuItem MenuBuscar;
    private javax.swing.JMenuItem MenuEliminar;
    private javax.swing.JMenuItem MenuGuardar;
    private javax.swing.JMenuItem MenuNuevo;
    private javax.swing.JMenuItem MenuSalir;
    public javax.swing.JTextField TxtArea;
    private javax.swing.JFormattedTextField TxtCedula;
    public javax.swing.JTextField TxtCodigo;
    public javax.swing.JTextField TxtNombre;
    public javax.swing.JTextField TxtSueldo;
    private javax.swing.JFormattedTextField TxtTelefono;
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
