package com.clinicBackEnd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/anonymous")
    public String helloGuest() {
        return "Hello guest";
    }

    @GetMapping("/admin/a")
    public RedirectView helloAdmin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/");
        return redirectView;
    }

    @GetMapping("/index")
    public RedirectView helloUser() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/");
        return redirectView;
    }

    @GetMapping("/logout")
    public String logout() {
        return "log out";
    }

}
