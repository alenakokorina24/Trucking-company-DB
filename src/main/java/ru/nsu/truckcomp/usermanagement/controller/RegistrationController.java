package ru.nsu.truckcomp.usermanagement.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.usermanagement.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerAccount")
    public String registerUserAccount(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String matchPassword,
                                      Map<String, Object> model) {
        if (!password.equals(matchPassword)) {
            model.put("message", "Passwords don't match.");
            return "/error";
        }
        if (userService.registerNewUserAccount(email, password) == null) {
            model.put("message", "Account with that email already exists. Please try again.");
            return "/error";
        }
        return "/login";
    }
}