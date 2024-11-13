package HW.libraries.service;

import HW.libraries.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getWholeDepartment(Integer department);

    int calculateDepartmentCollectiveSalary(Integer department);

    Employee findHighestSalary(Integer department);

    Employee findLowestSalary(Integer department);

    Map<Integer, ArrayList<Employee>> getAllConsideringDepartments();
}
