package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.Employee;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String getEmployees(Map<String, Object> model) {
        model.put("employees", employeeRepository.findAll());
        return "tables/employees";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String position,
                              @RequestParam(required = false) Integer bossId,
                              Map<String, Object> model) {
        Employee boss = null;
        if (bossId != null) {
            boss = employeeRepository.findByEmpId(bossId);
        }
        Employee employee = new Employee(name, position, boss);
        employeeRepository.save(employee);
        getEmployees(model);
        return "tables/employees";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Integer empId,
                                 Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getEmployees(model);
        return "tables/employees";
    }

}
