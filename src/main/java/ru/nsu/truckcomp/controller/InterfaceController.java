package ru.nsu.truckcomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterfaceController {

    @GetMapping("/")
    public String authorization() {
        return "login";
    }

    @GetMapping("/queries")
    public String queries() {
        return "queries";
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
