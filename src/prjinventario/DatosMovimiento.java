/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

public class DatosMovimiento {
    private int codigomov;
    private int codigo;
    private String compania;
    private String articulo;
    private String descripcion;
    private int cantidad;
    private String tipo;
    private String fecha;

    public int getCodigomov() {
        return codigomov;
    }

    public void setCodigomov(int codigomov) {
        this.codigomov = codigomov;
    }
    /*
    public int getCodigoMov() {
        return codigomov;
    }

    public void setCodigoMov(int codigomov) {
        this.codigomov = codigomov;
    }
    */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }
    
    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
}
