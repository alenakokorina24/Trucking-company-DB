package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.repository.EmployeeRepository;
import ru.nsu.truckcomp.repository.RepairListRepository;

import java.sql.Date;
import java.util.Map;

@Controller
public class EmployeeWorkController {
    @Autowired
    private RepairListRepository repairListRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/work")
    public String getWorkInfo(@RequestParam Integer employee,
                              @RequestParam(required = false) Integer transport,
                              @RequestParam Date start,
                              @RequestParam Date end,
                              Map<String, Object> model) {
        if (transport == null) {
            model.put("work", repairListRepository.getWork(employee, start, end));
        } else {
            model.put("work", repairListRepository.getWork(employee, transport, start, end));
        }
        model.put("name", employeeRepository.findByEmpId(employee).getName());
        model.put("start", start);
        model.put("end", end);

        return "query/work";
    }
}
