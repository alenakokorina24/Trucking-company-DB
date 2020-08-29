package ru.nsu.truckcomp.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.usermanagement.entity.User;
import ru.nsu.truckcomp.usermanagement.repository.UserRepository;

import java.util.Map;

@Controller
public class AuthorizationController {
    @Autowired
    private UserRepository userRepository;

    //@GetMapping("/authorization")
    public String registerUserAccount(@RequestParam String email,
                                      @RequestParam String password,
                                      Map<String, Object> model) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                model.put("message", "Incorrect password. Please try again.");
                return "/error";
            }
            return "/menu";
        }

        model.put("message", "Email " + email + " is not registered.");
        return "/error";
    }
}
