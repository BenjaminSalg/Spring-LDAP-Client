package com.example.springldapclient;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("user", user);
        return "inicio";
    }

    @GetMapping("/inicio")
    public String getInicio(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("user", user);
        return "inicio";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("model", model);
        return "login";
    }

    @GetMapping("/panel")
    public String getPanel(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("model", model);
        model.addAttribute("user", user);
        if (user != null) {
            model.addAttribute("rolesUsuario", new RolesUsuario(user.getAuthorities()));
        }
        return "panel";
    }
}



