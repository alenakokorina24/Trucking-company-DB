package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
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

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/repairList")
    public String getRepairList(Map<String, Object> model,
                                @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = repairListRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("repId").ascending());
        model.put("repairList", repairListRepository.findAll(sorted));
        model.put("brigades", brigadeRepository.findAll());
        model.put("transport", transportRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/repairList";
    }

    @Secured("ROLE_ADMIN")
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
        getRepairList(model, 1);
        return "tables/repairList";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteRepairList")
    public String deleteRepairList(@RequestParam Integer repId,
                                   Map<String, Object> model) {
        repairListRepository.delete(repairListRepository.findByRepId(repId));
        getRepairList(model, 1);
        return "tables/repairList";
    }
}
