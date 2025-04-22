package com.example.demo.controlador;

import com.example.demo.entidades.Producto;
import com.example.demo.entidades.Adicional;
import java.util.List;

public class ProductosResponse {
    private List<Producto> productosList;
    private List<Adicional> adicionalesList;

    public ProductosResponse(List<Producto> productosList, List<Adicional> adicionalesList) {
        this.productosList = productosList;
        this.adicionalesList = adicionalesList;
    }

    public List<Producto> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Producto> productosList) {
        this.productosList = productosList;
    }

    public List<Adicional> getAdicionalesList() {
        return adicionalesList;
    }

    public void setAdicionalesList(List<Adicional> adicionalesList) {
        this.adicionalesList = adicionalesList;
    }
}