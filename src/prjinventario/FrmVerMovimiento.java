/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FrmVerMovimiento extends javax.swing.JFrame {

    ClassDatabase ObjDB = ClassDatabase.getInstance();
    Connection conexion;
    String codigoSeleccion;
    
    public FrmVerMovimiento() {
        initComponents();
        
        //Poniendo a que salga en medio de la pantalla
        setLocationRelativeTo(null);
        
        String script = "select * from movimiento";
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
    
    public void JasperReport()
    {
        //Extraigo los datos de la base para pasarlo a la clase
        //String script = "select codigo, articulo, descripcion, cantidad, tipo from movimiento where codigomov = " + codigoSeleccion;
        String script = "select * from movimiento where codigomov = " + codigoSeleccion;
        
        try
        {
            DatosMovimiento datos = new DatosMovimiento();
                
            Statement query = ObjDB.conectar().createStatement();
            ResultSet resultado = query.executeQuery(script);

            while(resultado.next())
            {
                datos.setCodigoMov(12345);
                datos.setCodigo(12345);
                datos.setCompania("vbnjk");
                datos.setArticulo("vhjk");
                datos.setDescripcion("ghjk");
                datos.setCantidad(234);
                datos.setTipo("ghjk");
                datos.setFecha("bjk");
                /*
                datos.setCodigoMov(Integer.parseInt(resultado.getString(1)));
                datos.setCodigo(Integer.parseInt(resultado.getString(2)));
                datos.setCompania(resultado.getString(3));
                datos.setArticulo(resultado.getString(4));
                datos.setDescripcion(resultado.getString(5));
                datos.setCantidad(Integer.parseInt(resultado.getString(6)));
                datos.setTipo(resultado.getString(7));
                datos.setFecha(resultado.getString(8));
                
                System.out.println(datos.getCodigoMov());
                System.out.println(datos.getCodigo());
                System.out.println(datos.getCompania());
                System.out.println(datos.getArticulo());
                System.out.println(datos.getDescripcion());
                System.out.println(datos.getCantidad());
                System.out.println(datos.getTipo());
                System.out.println(datos.getFecha());*/
            }
            
            //Lista para guardar elementos
            List<DatosMovimiento> lista = new ArrayList<DatosMovimiento>();

            /* Agregar elementos a la lista */
            lista.add(datos);
            
            //Convertir lista en JRBeanCollectionDataSource
            JRBeanCollectionDataSource JRBeanItems = new JRBeanCollectionDataSource(lista);
            
            //Mapa para contener los parámetros del informe Jaspers
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("CollectionBeanParam", JRBeanItems);
            
            //Leer el archivo jrxml y crear el objeto JasperDesign
            InputStream archivo = new FileInputStream(new File("C:\\Users\\kinglion\\Desktop\\JasperReport\\JRXML\\Movimiento.jrxml"));
            JasperDesign JasperDesign = JRXmlLoader.load(archivo);
            
            //Compilar jrxml con la ayuda de la clase JasperReport
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            System.out.println("0");
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
        BtnReporte = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BtnSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movimientos");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(BtnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 32, 32));

        tabla.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Movimiento", "Cod. Articulo", "Cliente", "Articulo", "Descripción", "Cantidad", "Tipo", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setResizable(false);
            tabla.getColumnModel().getColumn(5).setResizable(false);
            tabla.getColumnModel().getColumn(6).setResizable(false);
            tabla.getColumnModel().getColumn(7).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 610, 250));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 310));

        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

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

    private void BtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReporteActionPerformed
        JasperReport();
    }//GEN-LAST:event_BtnReporteActionPerformed

    private void MenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_MenuSalirActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int seleccion = tabla.rowAtPoint(evt.getPoint());
        
        codigoSeleccion = String.valueOf(tabla.getValueAt(seleccion, 0));
    }//GEN-LAST:event_tablaMouseClicked

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVerMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVerMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVerMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVerMovimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVerMovimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnReporte;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JMenuItem MenuSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
