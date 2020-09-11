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
import ru.nsu.truckcomp.model.Area;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AreasController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/areas")
    public String getAreas(Map<String, Object> model,
                           @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        long count = areaRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROWS_PER_PAGE) < count;
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("areaId").ascending());
        model.put("areas", areaRepository.findAll(sorted));
        model.put("employees", employeeRepository.findAll());
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);
        return "tables/areas";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addArea")
    public String addArea(@RequestParam Integer chief,
                          Map<String, Object> model) {
        Area area = new Area(employeeRepository.findByEmpId(chief));
        areaRepository.save(area);
        getAreas(model,1);
        return "tables/areas";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteArea")
    public String deleteArea(@RequestParam Integer areaId,
                          Map<String, Object> model) {
        areaRepository.delete(areaRepository.findByAreaId(areaId));
        getAreas(model, 1);
        return "tables/areas";
    }

}
