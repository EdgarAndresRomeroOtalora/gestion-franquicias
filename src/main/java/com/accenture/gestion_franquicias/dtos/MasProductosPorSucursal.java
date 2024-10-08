package com.accenture.gestion_franquicias.dtos;

public class MasProductosPorSucursal {
    private String nombreSucursal;
    private String nombreProducto;
    private int cantidadProducto;

    public MasProductosPorSucursal(String nombreSucursal, String nombreProducto, int cantidadProducto) {
        this.nombreSucursal = nombreSucursal;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
