package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.PassTransportRepository;
import ru.nsu.truckcomp.repository.TransportRepository;
import ru.nsu.truckcomp.repository.TruckRepository;
import java.util.Map;

@Controller
public class AutoParkInfo {
    private TransportRepository transportRepository;

    @GetMapping("/autopark")
    public String autoParkInfo(Map<String, Object> model){
        model.put("transport", transportRepository.findAll());
        return "autopark.mustache";
    }
}
