package com.example.demo.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demo.entidades.NotFoundException;

@ControllerAdvice
@Controller
public class CustomErrorController implements ErrorController {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(Model model, NotFoundException ex) {
        model.addAttribute("id", ex.getId());
        return "PaginaError";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);

        return "PaginaError";
    }

}