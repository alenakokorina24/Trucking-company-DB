package ru.nsu.truckcomp.controller.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.model.RepairList;
import ru.nsu.truckcomp.repository.EmployeeRepository;
import ru.nsu.truckcomp.repository.RepairListRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeWorkController {
    @Autowired
    private RepairListRepository repairListRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private final int ROWS_PER_PAGE = 12;

    @GetMapping("/work")
    public String getWorkInfo(@RequestParam Integer employee,
                              @RequestParam(required = false) Integer transport,
                              @RequestParam Date start,
                              @RequestParam Date end,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                              Map<String, Object> model) {
        Pageable sorted = PageRequest.of(pageNumber - 1, ROWS_PER_PAGE, Sort.by("empId").ascending());
        List<RepairList> work;
        if (transport == null) {
            work = repairListRepository.getWork(employee, start, end, sorted);
        } else {
            work = repairListRepository.getWork(employee, transport, start, end, sorted);
        }
        model.put("name", employeeRepository.findByEmpId(employee).getName());
        model.put("work", work);
        model.put("start", start);
        model.put("end", end);
        model.put("hasPrev", pageNumber > 1);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", (pageNumber * ROWS_PER_PAGE) < work.size());
        model.put("next", pageNumber + 1);
        return "query/work";
    }
}
