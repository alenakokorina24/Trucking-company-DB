package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Area;
import ru.nsu.truckcomp.model.Brigade;
import ru.nsu.truckcomp.repository.AreaRepository;
import ru.nsu.truckcomp.repository.BrigadeRepository;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@Controller
public class BrigadesController {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @GetMapping("/brigades")
    public String getBrigades(Map<String, Object> model) {
        model.put("employees", employeeRepository.findAll());
        model.put("areas", areaRepository.findAll());
        model.put("brigades", brigadeRepository.findAll());
        return "tables/brigades";
    }

//    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/addBrigade")
    public String addBrigade(@RequestParam Integer area,
                          @RequestParam Integer brigadier,
                          Map<String, Object> model) {
        Brigade brigade = new Brigade(areaRepository.findByAreaId(area), employeeRepository.findByEmpId(brigadier));
        brigadeRepository.save(brigade);
        getBrigades(model);
        return "tables/brigades";
    }

//    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/deleteBrigade")
    public String deleteBrigade(@RequestParam Integer brigadeId,
                             Map<String, Object> model) {
        brigadeRepository.delete(brigadeRepository.findByBrigadeId(brigadeId));
        getBrigades(model);
        return "tables/brigades";
    }

}
