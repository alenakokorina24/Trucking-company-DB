package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.BrigadeRepository;
import ru.nsu.truckcomp.repository.RepairListRepository;
import ru.nsu.truckcomp.repository.TransportRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class RepairListController {
    @Autowired
    private RepairListRepository repairListRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @Autowired
    private TransportRepository transportRepository;

    @GetMapping("/repairList")
    public String getRepairList(Map<String, Object> model) {
        model.put("brigades", brigadeRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("repairList", repairListRepository.findAll());
        return "tables/repairList";
    }

    @PostMapping("/addRepairList")
    public String addRepairList(@RequestParam Integer cost,
                                @RequestParam Date received,
                                @RequestParam Date returned,
                                @RequestParam String sparePart,
                                @RequestParam Integer brigade,
                                @RequestParam Integer transport,
                                Map<String, Object> model) {
        RepairList repairList = new RepairList(brigadeRepository.findByBrigadeId(brigade), transportRepository.findById(transport).get(), sparePart, cost, received, returned);
        repairListRepository.save(repairList);
        getRepairList(model);
        return "tables/repairList";
    }

    @PostMapping("/deleteRepairList")
    public String deleteRepairList(@RequestParam Integer repId,
                                   Map<String, Object> model) {
        repairListRepository.delete(repairListRepository.findByRepId(repId));
        getRepairList(model);
        return "tables/repairList";
    }
}
