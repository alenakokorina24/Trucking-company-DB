package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.repository.EmployeeRepository;

import java.util.Map;

@Controller
public class SubordinatesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/subordinates")
    public String getSubordinates(@RequestParam Integer employee,
                                  Map<String, Object> model) {
        Employee boss = employeeRepository.findByEmpId(employee);
        model.put("subordinates", employeeRepository.findByBoss_EmpId(employee));
        model.put("employee", boss.getName());
        model.put("position", boss.getPosition());
        return "query/subordinates";
    }
}