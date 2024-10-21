package HW.libraries.controller;

import HW.libraries.model.Employee;
import HW.libraries.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    Employee findMaxSalary(@RequestParam(value = "departmentID", required = false) int departmentID) {
        return departmentService.findHighestSalary(departmentID);
    }

    @GetMapping(path = "/min-salary")
    Employee findMinSalary(@RequestParam(value = "departmentID", required = false) int departmentID) {
        return departmentService.findLowestSalary(departmentID);
    }

    @GetMapping(value = "/all", params = {"departmentID"})
    Collection<Employee> showDepartment(@RequestParam(value = "departmentID", required = false) int departmentID) {
        return departmentService.getWholeDepartment(departmentID);
    }

    @GetMapping("/all")
    Map<Integer, ArrayList<Employee>> showWithDepartments() {
        return departmentService.getAllConsideringDepartments();
    }
}
