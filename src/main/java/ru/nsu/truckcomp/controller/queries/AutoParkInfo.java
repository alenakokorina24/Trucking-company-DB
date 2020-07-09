package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.util.Map;

@Controller
public class AutoParkInfo {
    @Autowired
    private TransportRepository transportRepository;

    @GetMapping("/autoPark")
    public String getAutoParkInfo(Map<String, Object> model){
        model.put("transport", transportRepository.findAll());
        return "query/autoPark";
    }
}
