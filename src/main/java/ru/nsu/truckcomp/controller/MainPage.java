package ru.nsu.truckcomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {
    @GetMapping("/")
    public String authorization() {
        return "login.mustache.html";
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @GetMapping("/recover")
    public String pwdRecovery(){
        return "recover";
    }
}
