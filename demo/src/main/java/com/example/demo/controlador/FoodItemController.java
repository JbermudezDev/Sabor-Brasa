package com.example.demo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Index")
    public class FoodItemController {
        //http://localhost:8090/Index.html
        @GetMapping("/Index")
        public String MostrarIndex() {
        return "Index";

        }
  
}
