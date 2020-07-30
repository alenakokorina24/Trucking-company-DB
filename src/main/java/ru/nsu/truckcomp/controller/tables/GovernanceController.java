package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Governance;
import ru.nsu.truckcomp.repository.EmployeeRepository;
import ru.nsu.truckcomp.repository.GovernanceRepository;

import java.util.Map;

@Controller
public class GovernanceController {
    @Autowired
    private GovernanceRepository governanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/governance")
    public String getBosses(Map<String, Object> model) {
        model.put("bosses", governanceRepository.findAll());
        return "tables/governance";
    }

    @PostMapping("/addBoss")
    public String addBoss(@RequestParam String name,
                            @RequestParam String position,
                            Map<String, Object> model) {
        Governance governance = new Governance(name, position);
        governanceRepository.save(governance);
        getBosses(model);
        return "tables/governance";
    }

    @PostMapping("/deleteBoss")
    public String deleteBoss(@RequestParam Integer empId,
                             Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getBosses(model);
        return "tables/governance";
    }

}