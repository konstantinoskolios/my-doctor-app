package com.example.springbootkeycloaksimpleui.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleUIController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/public-page")
    public String publicPage() {
        return "public";
    }

    @GetMapping("/admin-page")
    public String adminPage(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("email", user.getEmail());
        return "admin-page";
    }

}