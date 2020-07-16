package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.RideRepository;

import java.util.Map;

@Controller
public class Routes {
    @Autowired
    private RideRepository rideRepository;

    @GetMapping("/routes")
    public String getDriversInfo(Map<String, Object> model) {
        model.put("routes", rideRepository.getRoutesDistribution());
        return "query/routes";
    }
}
