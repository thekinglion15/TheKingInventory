/*
 * Created by Gabriel Alexander De León Lizardo
 * Matricula: 100479203
 * Copyright © 2021 Gabriel Alexander De León Lizardo. All rights reserved
 */
package prjinventario;

public class DatosProveedor {
    private int codigo;
    private String rnc;
    private String nombre;
    private String articulo;
    private String total;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
