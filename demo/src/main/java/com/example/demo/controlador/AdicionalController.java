package com.example.demo.controlador;

import com.example.demo.entidades.Adicional;
import com.example.demo.entidades.Cliente;
import com.example.demo.entidades.Producto;
import com.example.demo.servicio.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adicionales")
public class AdicionalController {

    @Autowired
    private AdicionalService adicionalService;

   @GetMapping("/all")
    public String mostrarAdicional(Model model) {
        List<Adicional> adicionales = adicionalService.searchAll();
        model.addAttribute("adicionales", adicionales);
        return "ListadoAdicional";
    }


    @GetMapping("/view/{id}")
    public String verAdicional(@PathVariable("id") Long id, Model model) {
        Optional<Adicional> adicional = adicionalService.findById(id);
        if (adicional.isPresent()) {
            model.addAttribute("adicional", adicional.get());
            return "VerAdicional";
        } else {
            return "redirect:/adicionales/all";
        }
    }
     @GetMapping("/agregar")
    public String mostrarFormularioAgregarAdicional(Model model) {
        model.addAttribute("adicional", new Adicional());
        return "AgregarAdicional";
    }


    @PostMapping("/add")
    public String agregarAdicional(@ModelAttribute Adicional adicional) {
        adicionalService.add(adicional);
        return "redirect:/adicionales/all";
    }

    @PostMapping("/delete/{id}")
    public String eliminarAdicional(@PathVariable("id") Long id) {
        adicionalService.deleteById(id);
        return "redirect:/adicionales/all";
    }

    @GetMapping("/update/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Adicional> adicional = adicionalService.findById(id);
        if (adicional.isPresent()) {
            model.addAttribute("adicional", adicional.get());
            return "EditarAdicional";
        } else {
            return "redirect:/adicionales/all";
        }
    }

    @PostMapping("/update/{id}")
    public String modificarAdicional(@ModelAttribute Adicional adicional, @PathVariable("id") Long id) {
        adicional.setId(id);
        adicionalService.update(adicional);
        return "redirect:/adicionales/all";
    }
}
