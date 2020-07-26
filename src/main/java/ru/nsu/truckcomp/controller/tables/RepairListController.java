package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.truckcomp.repository.RepairListRepository;

import java.util.Map;

@Controller
public class RepairListController {
    @Autowired
    private RepairListRepository repairListRepository;

    @GetMapping("/repairList")
    public String getAreas(Map<String, Object> model) {
        model.put("repairList", repairListRepository.findAll());
        return "tables/repairList";
    }
}
