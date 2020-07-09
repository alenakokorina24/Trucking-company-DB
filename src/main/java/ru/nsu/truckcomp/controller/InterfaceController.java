package ru.nsu.truckcomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.EmployeeRepository;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.util.Map;

@Controller
public class InterfaceController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String authorization() {
        return "login";
    }

    @GetMapping("/queries")
    public String queries(Map<String, Object> model) {
        model.put("areas", areaRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("employees", employeeRepository.findAll());
        return "queries";
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @GetMapping("/recovery")
    public String pwdRecovery(){
        return "recovery";
    }
}
