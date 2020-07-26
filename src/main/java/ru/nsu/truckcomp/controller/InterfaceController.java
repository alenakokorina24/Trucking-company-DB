package ru.nsu.truckcomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class InterfaceController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RouteRepository routeRepository;

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

    @GetMapping("/menu")
    public String menu(Map<String, Object> model) {
        model.put("brigades", brigadeRepository.findAll());
        model.put("garages", garageRepository.findAll());
        model.put("rides", rideRepository.findAll());
        model.put("routes", routeRepository.findAll());
        model.put("areas", areaRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("employees", employeeRepository.findAll());
        return "menu";
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
