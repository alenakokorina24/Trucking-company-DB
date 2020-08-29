package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Area;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.Map;

@Controller
public class AreasController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/areas")
    public String getAreas(Map<String, Object> model) {
        model.put("employees", employeeRepository.findAll());
        model.put("areas", areaRepository.findAll());
        return "tables/areas";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addArea")
    public String addArea(@RequestParam Integer chief,
                          Map<String, Object> model) {
        Area area = new Area(employeeRepository.findByEmpId(chief));
        areaRepository.save(area);
        getAreas(model);
        return "tables/areas";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteArea")
    public String deleteArea(@RequestParam Integer areaId,
                          Map<String, Object> model) {
        areaRepository.delete(areaRepository.findByAreaId(areaId));
        getAreas(model);
        return "tables/areas";
    }

}
