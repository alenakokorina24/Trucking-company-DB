package ru.nsu.truckcomp.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.usermanagement.entity.User;
import ru.nsu.truckcomp.usermanagement.repository.UserRepository;

import java.util.Map;

@Controller
public class PasswordRecoveryController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/passwordRecovery")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String matchPassword,
                                Map<String, Object> model) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (password.equals(matchPassword)) {
                user.setPassword(password);
                userRepository.save(user);
                return "/login";
            }
            model.put("message", "Passwords don't match.");
            return "/error";
        }
        model.put("message", "Email " + email + " is not registered.");
        return "/error";
    }
}
