package com.example.springldapclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/pagina_publica")
    public String getInicio() {
        return "pagina_publica";
    }

    @GetMapping("/panel")
    public String getPanel() {
        return "panel";
    }

}

