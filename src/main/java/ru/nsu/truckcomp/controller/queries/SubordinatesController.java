package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.util.Map;

@Controller
public class SubordinatesController {
    @Autowired
    private TransportRepository transportRepository;

    @GetMapping("/subordinates")
    public String getSubordinates(@RequestParam Integer employee,
                                  Map<String, Object> model) {

        return "query/subordinates";
    }
}