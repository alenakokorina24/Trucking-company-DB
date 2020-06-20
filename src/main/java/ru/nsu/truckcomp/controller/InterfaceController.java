package ru.nsu.truckcomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterfaceController {

    @GetMapping("/")
    public String authorization() {
        return "login.mustache.html";
    }

    @GetMapping("/queries")
    public String queries() {
        return "queries.mustache.html";
    }

    @GetMapping("/register")
    public String registration() {
        return "register.mustache.html";
    }

    @GetMapping("/recover")
    public String pwdRecovery(){
        return "recover";
    }
}
