package HW.libraries.service;

import HW.libraries.exception.BadParamException;
import HW.libraries.exception.EmployeeAlreadyAddedException;
import HW.libraries.exception.EmployeeNotFoundException;
import HW.libraries.exception.EmployeeStorageIsFullException;
import HW.libraries.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    private final int MAX_EMPLOYEE_COUNT = 1000;
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        return addEmployee(firstName, lastName, -1, -1);
    }

    public Employee addEmployee(String firstName, String lastName, int dID, int wage) {
        if (employees.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        checkParamValidity(firstName, lastName);

        firstName = prepareName(firstName);
        lastName = prepareName(lastName);

        Employee target = new Employee(firstName, lastName, dID, wage);
        String tempKey = String.join("_", firstName, lastName);
        if (employees.containsKey(tempKey)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(tempKey, target);
        return target;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        checkParamValidity(firstName, lastName);

        firstName = prepareName(firstName);
        lastName = prepareName(lastName);

        Employee target = new Employee(firstName, lastName);
        String tempKey = String.join("_", firstName, lastName);
        if (!employees.containsKey(tempKey)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(tempKey);
        return target;
    }

    public Employee findEmployee(String firstName, String lastName) {
        checkParamValidity(firstName, lastName);

        firstName = prepareName(firstName);
        lastName = prepareName(lastName);

        String tempKey = String.join("_", firstName, lastName);
        if (!employees.containsKey(tempKey)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(tempKey);
    }

    public Map<String, Employee> getAllEmployees() {
        return Map.copyOf(employees);
    }

    private void checkParamValidity(String firstName, String lastName) {
        if (isEmpty(firstName) || isEmpty(lastName)
                || !containsOnly(firstName, LETTERS) || !containsOnly(lastName, LETTERS)) {
            throw new BadParamException();
        }
    }

    private String prepareName(String name) {
        name = strip(name);
        name = lowerCase(name);
        name = capitalize(name);
        return name;
    }
}
