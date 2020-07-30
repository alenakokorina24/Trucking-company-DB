package ru.nsu.truckcomp.controller.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.truckcomp.model.ServiceStaff;
import ru.nsu.truckcomp.repository.*;

import java.util.Map;

@Controller
public class ServiceStaffController {
    @Autowired
    private ServiceStaffRepository serviceStaffRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BrigadeRepository brigadeRepository;

    @GetMapping("/serviceStaff")
    public String getServiceStaff(Map<String, Object> model) {
        model.put("serviceStaff", serviceStaffRepository.findAll());
        model.put("brigades", brigadeRepository.findAll());
        return "tables/serviceStaff";
    }

    @PostMapping("/addServiceStaff")
    public String addServiceStaff(@RequestParam String name,
                                  @RequestParam String position,
                                  @RequestParam Integer brigade,
                                  Map<String, Object> model) {
        ServiceStaff serviceStaff = new ServiceStaff(name, position, brigadeRepository.findByBrigadeId(brigade));
        serviceStaffRepository.save(serviceStaff);
        getServiceStaff(model);
        return "tables/serviceStaff";
    }

    @PostMapping("/deleteServiceStaff")
    public String deleteServiceStaff(@RequestParam Integer empId,
                                     Map<String, Object> model) {
        employeeRepository.delete(employeeRepository.findByEmpId(empId));
        getServiceStaff(model);
        return "tables/serviceStaff";
    }

}
