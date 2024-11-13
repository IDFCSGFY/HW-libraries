package HW.libraries.service;

import HW.libraries.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getWholeDepartment(int department);

    int calculateDepartmentCollectiveSalary(int department);

    Employee findHighestSalary(int department);

    Employee findLowestSalary(int department);

    Map<Integer, ArrayList<Employee>> getAllConsideringDepartments();
}
