package HW.libraries.service;

import HW.libraries.exception.BadParamException;
import HW.libraries.exception.DepartmentNotFoundException;
import HW.libraries.exception.EmployeeNotFoundException;
import HW.libraries.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static HW.libraries.constants.DepartmentServiceTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        when(employeeServiceMock.getAllEmployees())
                .thenReturn(EMPLOYEE_HASH_MAP);
    }

    @Test
    public void shouldCollectCorrectListOfEmployeesWhenGetWholeDepartment() {
        List<Employee> actual = out.getWholeDepartment(1);
        assertTrue(actual.contains(D1_EMPLOYEE_POOR));
        assertTrue(actual.contains(D1_EMPLOYEE_RICH));
        assertEquals(2, actual.size());

        actual = out.getWholeDepartment(2);
        assertTrue(actual.contains(D2_EMPLOYEE_POOR));
        assertTrue(actual.contains(D2_EMPLOYEE_RICH));
        assertEquals(2, actual.size());

        actual = out.getWholeDepartment(3);
        assertTrue(actual.contains(D3_EMPLOYEE_SINGLE));
        assertEquals(1, actual.size());
    }

    @Test
    public void shouldCalculateCorrectSumWhenCalculateDepartmentCollectiveSalary() {
        int actual = out.calculateDepartmentCollectiveSalary(1);
        int expected = D1_EMPLOYEE_POOR.getWage() + D1_EMPLOYEE_RICH.getWage();
        assertEquals(expected, actual);

        actual = out.calculateDepartmentCollectiveSalary(2);
        expected = D2_EMPLOYEE_POOR.getWage() + D2_EMPLOYEE_RICH.getWage();
        assertEquals(expected, actual);

        actual = out.calculateDepartmentCollectiveSalary(3);
        expected = D3_EMPLOYEE_SINGLE.getWage();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindHighestSalary() {
        assertEquals(D1_EMPLOYEE_RICH, out.findHighestSalary(1));
        assertEquals(D2_EMPLOYEE_RICH, out.findHighestSalary(2));
        assertEquals(D3_EMPLOYEE_SINGLE, out.findHighestSalary(3));
    }

    @Test
    public void shouldFindLowestSalary() {
        assertEquals(D1_EMPLOYEE_POOR, out.findLowestSalary(1));
        assertEquals(D2_EMPLOYEE_POOR, out.findLowestSalary(2));
        assertEquals(D3_EMPLOYEE_SINGLE, out.findLowestSalary(3));
    }

    @Test
    public void shouldCollectCorrectMapWhenGetAllConsideringDepartments() {
        Map<Integer, ArrayList<Employee>> actual = out.getAllConsideringDepartments();

        assertTrue(actual.containsKey(1));
        assertTrue(actual.get(1).contains(D1_EMPLOYEE_POOR));
        assertTrue(actual.get(1).contains(D1_EMPLOYEE_RICH));
        assertEquals(2, actual.get(1).size());

        assertTrue(actual.containsKey(2));
        assertTrue(actual.get(2).contains(D2_EMPLOYEE_POOR));
        assertTrue(actual.get(2).contains(D2_EMPLOYEE_RICH));
        assertEquals(2, actual.get(2).size());

        assertTrue(actual.containsKey(3));
        assertTrue(actual.get(3).contains(D3_EMPLOYEE_SINGLE));
        assertEquals(1, actual.get(3).size());
    }

    @Test
    public void shouldThrowDepartmentNotFoundException() {
        //by private checkIfValid() method
        assertThrows(DepartmentNotFoundException.class, () -> out.getWholeDepartment(0));
        assertThrows(DepartmentNotFoundException.class, () -> out.calculateDepartmentCollectiveSalary(0));
        assertThrows(DepartmentNotFoundException.class, () -> out.findHighestSalary(0));
        assertThrows(DepartmentNotFoundException.class, () -> out.findLowestSalary(0));
        assertThrows(DepartmentNotFoundException.class, () -> out.getWholeDepartment(4));
        assertThrows(DepartmentNotFoundException.class, () -> out.calculateDepartmentCollectiveSalary(4));
        assertThrows(DepartmentNotFoundException.class, () -> out.findHighestSalary(4));
        assertThrows(DepartmentNotFoundException.class, () -> out.findLowestSalary(4));
        assertThrows(DepartmentNotFoundException.class, () -> out.getWholeDepartment(-1));
        assertThrows(DepartmentNotFoundException.class, () -> out.calculateDepartmentCollectiveSalary(-1));
        assertThrows(DepartmentNotFoundException.class, () -> out.findHighestSalary(-1));
        assertThrows(DepartmentNotFoundException.class, () -> out.findLowestSalary(-1));
    }
}