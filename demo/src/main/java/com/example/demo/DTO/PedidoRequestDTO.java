package com.example.demo.DTO;

import java.util.List;

public class PedidoRequestDTO {
    private Long clienteId;
    private List<ItemDTO> items;

    // Getters y setters
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    // Clase interna para representar cada ítem del carrito
    public static class ItemDTO {
        private Long productoId;
        private List<Long> adicionales;

        // Getters y setters
        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public List<Long> getAdicionales() {
            return adicionales;
        }

        public void setAdicionales(List<Long> adicionales) {
            this.adicionales = adicionales;
        }
    }
}
