package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class AcquiredAndDecommissioned {
    @Autowired
    private TransportRepository transportRepository;

    @GetMapping("/acqDec")
    public String getTransportInfo(@RequestParam(required = false) Date start,
                                   @RequestParam(required = false) Date end,
                                   Map<String, Object> model) {
        if (start != null && end != null) {
            model.put("transport", transportRepository.findByAcquirementDateAfterAndAcquirementDateBeforeOrDecommissionDateAfterAndDecommissionDateBefore(start, end, start, end));
        } else {
            model.put("transport", transportRepository.findAll());
        }
        return "query/acqDec";
    }
}
