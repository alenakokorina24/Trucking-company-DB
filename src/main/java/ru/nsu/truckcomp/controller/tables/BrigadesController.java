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
import ru.nsu.truckcomp.model.Brigade;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.BrigadeRepository;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.Map;

@Controller
public class BrigadesController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/brigades")
    public String getBrigades(Map<String, Object> model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = brigadeRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("brigadeId").ascending());
        model.put("brigades", brigadeRepository.findAll(sorted));
        model.put("employees", employeeRepository.findAll());
        model.put("areas", areaRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/brigades";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addBrigade")
    public String addBrigade(@RequestParam Integer area,
                          @RequestParam Integer brigadier,
                          Map<String, Object> model) {
        Brigade brigade = new Brigade(areaRepository.findByAreaId(area), employeeRepository.findByEmpId(brigadier));
        brigadeRepository.save(brigade);
        getBrigades(model, 1);
        return "tables/brigades";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteBrigade")
    public String deleteBrigade(@RequestParam Integer brigadeId,
                             Map<String, Object> model) {
        brigadeRepository.delete(brigadeRepository.findByBrigadeId(brigadeId));
        getBrigades(model, 1);
        return "tables/brigades";
    }

}
