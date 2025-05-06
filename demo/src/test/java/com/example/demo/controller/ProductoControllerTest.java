package com.example.demo.controller;

import com.example.demo.controlador.ProductoController;
import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import com.example.demo.servicio.ProductoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private AdicionalService adicionalService;

    private final ObjectMapper mapper = new ObjectMapper();



    @Test
    public void testVerProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(2L);
        producto.setNombre("Pizza");

        when(productoService.findById(2L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/productos/find/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pizza"));
    }

    @Test
    public void testAgregarProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(3L);
        producto.setNombre("Taco");

        when(productoService.add(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/productos/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Taco"));
    }

    @Test
    public void testModificarProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(4L);
        producto.setNombre("Sopa");
    
        when(productoService.findById(4L)).thenReturn(Optional.of(producto));
       
        doNothing().when(productoService).save(any(Producto.class));
        
        
    
        mockMvc.perform(put("/productos/update/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Sopa"));
    }
}
