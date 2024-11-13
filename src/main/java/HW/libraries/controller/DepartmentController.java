package HW.libraries.controller;

import HW.libraries.model.Employee;
import HW.libraries.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{id}/employees")
    Collection<Employee> showDepartment(@PathVariable(value = "id", required = false) int departmentID) {
        return departmentService.getWholeDepartment(departmentID);
    }

    @GetMapping(path = "/{id}/salary/sum")
    int sumDepartmentSalaries(@PathVariable(value = "id", required = false) int departmentID) {
        return departmentService.calculateDepartmentCollectiveSalary(departmentID);
    }

    @GetMapping(path = "/{id}/salary/max")
    Employee findMaxSalary(@PathVariable(value = "id", required = false) int departmentID) {
        return departmentService.findHighestSalary(departmentID);
    }

    @GetMapping(path = "/{id}/salary/min")
    Employee findMinSalary(@PathVariable(value = "id", required = false) int departmentID) {
        return departmentService.findLowestSalary(departmentID);
    }

    @GetMapping("/employees")
    Map<Integer, ArrayList<Employee>> showWithDepartments() {
        return departmentService.getAllConsideringDepartments();
    }
}
