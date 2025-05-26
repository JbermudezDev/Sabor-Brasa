package com.example.demo.controlador;

import com.example.demo.DTO.PedidoRequestDTO;
import com.example.demo.entidades.*;
import com.example.demo.servicio.DomiciliarioService;
import com.example.demo.servicio.OperadorService;
import com.example.demo.servicio.PedidoService;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.text.NumberFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Element;
@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private OperadorService operadorService;

  @Autowired
  private DomiciliarioService domiciliarioService;

  //http://localhost:8090/pedidos/confirmar/id
  @PostMapping("/confirmar/{clienteId}")
  public ResponseEntity<?> confirmarPedido(@PathVariable Long clienteId) {
    Pedido pedido = pedidoService.confirmarPedido(clienteId);
    if (pedido == null) {
      return ResponseEntity.badRequest().body("Carrito no encontrado o vacío");
    }
    return ResponseEntity.ok(pedido);
  }

  //http://localhost:8090/pedidos/confirmar
  @PostMapping("/confirmar")
  public ResponseEntity<?> confirmarPedido(
    @RequestBody PedidoRequestDTO request
  ) {
    Pedido pedido = pedidoService.confirmarPedido(request);
    if (pedido == null) {
      return ResponseEntity
        .badRequest()
        .body("Carrito vacío o error en los datos");
    }
    return ResponseEntity.ok(pedido);
  }

  //http://localhost:8090/pedidos/cliente/id
  @GetMapping("/cliente/{clienteId}")
  public ResponseEntity<List<Pedido>> obtenerPedidosPorCliente(
    @PathVariable Long clienteId
  ) {
    List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(clienteId);
    return ResponseEntity.ok(pedidos);
  }

  //http://localhost:8090/pedidos/all
  @GetMapping("/all")
  public ResponseEntity<List<Pedido>> listarTodos() {
    return ResponseEntity.ok(pedidoService.listarTodos());
  }

  //http://localhost:8090/pedidos/actualizarPedido/id
  @PutMapping("/actualizarPedido/{pedidoId}")
  public ResponseEntity<?> actualizarEstado(
    @PathVariable Integer pedidoId,
    @RequestParam String estado, // ahora como String
    @RequestParam Integer operadorId,
    @RequestParam(required = false) Integer domiciliarioId
  ) {
    Operador operador = operadorService.findById(operadorId).orElse(null);
    if (operador == null) return ResponseEntity
      .badRequest()
      .body("Operador inválido");

    EstadoPedido estadoEnum;
    try {
      estadoEnum = EstadoPedido.valueOf(estado.toUpperCase());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body("Estado inválido");
    }

    Pedido actualizado = pedidoService.actualizarEstado(
      pedidoId,
      estadoEnum,
      operador,
      domiciliarioId
    );
    return ResponseEntity.ok(actualizado);
  }

  //http://localhost:8090/pedidos/asignarDomiciliario/id
  @PutMapping("/asignarDomiciliario/{pedidoId}")
  public ResponseEntity<?> asignarDomiciliario(
    @PathVariable Integer pedidoId,
    @RequestParam Integer domiciliarioId
  ) {
    Domiciliario domiciliario = domiciliarioService
      .buscarPorId(domiciliarioId)
      .orElse(null);
    if (domiciliario == null || !domiciliario.isDisponibilidad()) {
      return ResponseEntity
        .badRequest()
        .body("Domiciliario no disponible o no válido");
    }

    Pedido actualizado = pedidoService.asignarDomiciliario(
      pedidoId,
      domiciliario
    );
    return ResponseEntity.ok(actualizado);
    
  }

  @GetMapping("/por-dia")
public ResponseEntity<Map<String, Integer>> obtenerPedidosPorDia() {
    List<Pedido> pedidos = pedidoService.listarTodos();
    Map<String, Integer> conteoPorDia = new TreeMap<>();

    for (Pedido pedido : pedidos) {
        String fecha = pedido.getFechaCreacion().toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate().toString();
        conteoPorDia.put(fecha, conteoPorDia.getOrDefault(fecha, 0) + 1);
    }

    return ResponseEntity.ok(conteoPorDia);
}
  @GetMapping("/ingresos-por-semana")
public ResponseEntity<Map<String, Double>> obtenerIngresosPorSemana() {
    List<Pedido> pedidos = pedidoService.listarTodos();
    Map<Integer, Double> ingresos = new TreeMap<>();

    for (Pedido pedido : pedidos) {
        java.time.LocalDate fecha = pedido.getFechaCreacion()
            .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        int semana = fecha.get(java.time.temporal.WeekFields.ISO.weekOfWeekBasedYear());
        ingresos.put(semana, ingresos.getOrDefault(semana, 0.0) + pedido.getTotal());
    }

    // Convertir a formato "Semana X"
    Map<String, Double> respuesta = new LinkedHashMap<>();
    for (Map.Entry<Integer, Double> entry : ingresos.entrySet()) {
        respuesta.put("Semana " + entry.getKey(), entry.getValue());
    }

    return ResponseEntity.ok(respuesta);
}
@GetMapping("/recientes")
public ResponseEntity<List<Pedido>> obtenerPedidosRecientes() {
    List<Pedido> pedidos = pedidoService.listarTodos();
    pedidos.sort(Comparator.comparing(Pedido::getFechaCreacion).reversed());
    return ResponseEntity.ok(pedidos.stream().limit(5).toList());
}
// Productos más vendidos
@GetMapping("/productos-mas-vendidos")
public ResponseEntity<Map<String, Integer>> obtenerProductosMasVendidos() {
    List<Pedido> pedidos = pedidoService.listarTodos();
    Map<String, Integer> conteo = new HashMap<>();

    for (Pedido pedido : pedidos) {
        if (pedido.getCarrito() != null && pedido.getCarrito().getProductosSeleccionados() != null) {
            pedido.getCarrito().getProductosSeleccionados().forEach(item -> {
                String nombre = item.getProducto().getNombre();
                conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
            });
        }
    }
    return ResponseEntity.ok(conteo);
}

// Pedidos por estado
@GetMapping("/pedidos-por-estado")
public ResponseEntity<Map<String, Integer>> obtenerPedidosPorEstado() {
    List<Pedido> pedidos = pedidoService.listarTodos();
    Map<String, Integer> estados = new LinkedHashMap<>();

    estados.put("Recibido", 0);
    estados.put("Cocinando", 0);
    estados.put("Enviado", 0);
    estados.put("Entregado", 0);

    for (Pedido pedido : pedidos) {
        switch (pedido.getEstado()) {
            case RECIBIDO -> estados.put("Recibido", estados.get("Recibido") + 1);
            case COCINANDO -> estados.put("Cocinando", estados.get("Cocinando") + 1);
            case ENVIADO -> estados.put("Enviado", estados.get("Enviado") + 1);
            case ENTREGADO -> estados.put("Entregado", estados.get("Entregado") + 1);
        }
    }
    return ResponseEntity.ok(estados);
}
@GetMapping("/por-operador")
public ResponseEntity<Map<String, Integer>> pedidosPorOperador() {
    Map<String, Integer> resultado = new HashMap<>();
    for (Pedido pedido : pedidoService.listarTodos()) {
        if (pedido.getOperador() != null) {
            String nombre = pedido.getOperador().getNombre();
            resultado.put(nombre, resultado.getOrDefault(nombre, 0) + 1);
        }
    }
    return ResponseEntity.ok(resultado);
}

@GetMapping("/por-domiciliario")
public ResponseEntity<Map<String, Integer>> pedidosPorDomiciliario() {
    Map<String, Integer> resultado = new HashMap<>();
    for (Pedido pedido : pedidoService.listarTodos()) {
        if (pedido.getDomiciliario() != null) {
            String nombre = pedido.getDomiciliario().getNombre();
            resultado.put(nombre, resultado.getOrDefault(nombre, 0) + 1);
        }
    }
    return ResponseEntity.ok(resultado);
}

@GetMapping("/top-clientes")
public ResponseEntity<Map<String, Integer>> topClientesPorPedidos() {
    Map<String, Integer> resultado = new HashMap<>();
    for (Pedido pedido : pedidoService.listarTodos()) {
        String cliente = pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido();
        resultado.put(cliente, resultado.getOrDefault(cliente, 0) + 1);
    }
    return ResponseEntity.ok(resultado);
}

@GetMapping("/{id}/pdf")
public ResponseEntity<byte[]> generarPdfPedido(@PathVariable Integer id) {
    Optional<Pedido> optionalPedido = pedidoService.findById(id);
    if (optionalPedido.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Pedido pedido = optionalPedido.get();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Document document = new Document(PageSize.A4, 50, 50, 50, 50);

    try {
        PdfWriter.getInstance(document, baos);
        document.open();

        // Fuentes
        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        NumberFormat formatoPesos = NumberFormat.getNumberInstance(new Locale("es", "CO"));

        // Encabezado
        Paragraph header = new Paragraph("🍖 Sabor y Brasa - Factura de Pedido\n\n", tituloFont);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        // Información del pedido
        document.add(new Paragraph("🧾 Información del Pedido", headerFont));
        document.add(new Paragraph("ID: " + pedido.getId(), normalFont));
        document.add(new Paragraph("Fecha: " + pedido.getFechaCreacion(), normalFont));
        document.add(new Paragraph("Estado: " + pedido.getEstado(), normalFont));
        document.add(new Paragraph(" "));

        // Información del cliente
        document.add(new Paragraph("👤 Información del Cliente", headerFont));
        document.add(new Paragraph("Nombre: " + pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido(), normalFont));
        document.add(new Paragraph("Dirección: " + pedido.getCliente().getDireccion(), normalFont));
        document.add(new Paragraph("Teléfono: " + pedido.getCliente().getTelefono(), normalFont));
        document.add(new Paragraph(" "));

        // Línea separadora
        LineSeparator separator = new LineSeparator();
        document.add(separator);
        document.add(new Paragraph(" "));

        // Detalles del pedido - tabla
        document.add(new Paragraph("🛒 Detalles del Pedido", headerFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3); // Solo Producto, Precio, Adicionales
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3, 1.5f, 3});

        table.addCell(new PdfPCell(new Phrase("Producto", boldFont)));
        table.addCell(new PdfPCell(new Phrase("Precio", boldFont)));
        table.addCell(new PdfPCell(new Phrase("Adicionales", boldFont)));

        for (SeleccionarProductos sp : pedido.getCarrito().getProductosSeleccionados()) {
            Producto producto = sp.getProducto();
            String adicionales = "-";

            if (sp.getAdicionales() != null && !sp.getAdicionales().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Adicional adicional : sp.getAdicionales()) {
                    sb.append(adicional.getNombre())
                      .append(" ($")
                      .append(formatoPesos.format(adicional.getPrecio()))
                      .append(")\n");
                }
                adicionales = sb.toString().trim();
            }

            table.addCell(new Phrase(producto.getNombre(), normalFont));
            table.addCell(new Phrase("$" + formatoPesos.format(producto.getPrecio()), normalFont));
            table.addCell(new Phrase(adicionales, normalFont));
        }

        document.add(table);
        document.add(new Paragraph(" "));

        // Total
        Paragraph total = new Paragraph("💰 Total del Pedido: $" + formatoPesos.format(pedido.getTotal()), boldFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);

        // Cierre
        document.add(new Paragraph("\nGracias por tu compra en *Sabor y Brasa* 🍽️", normalFont));

        document.close();

        byte[] pdfBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("pedido_" + pedido.getId() + ".pdf", "pedido_" + pedido.getId() + ".pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

}
