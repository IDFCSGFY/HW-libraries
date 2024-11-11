package HW.libraries.service;

import HW.libraries.exception.BadParamException;
import HW.libraries.exception.EmployeeAlreadyAddedException;
import HW.libraries.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static HW.libraries.constants.EmployeeServiceTestConstants.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    public void shouldPutCorrectIDAndWageValuesIfNotStated() {
        Employee actual = out.addEmployee(VALID_NAME, VALID_NAME);
        assertEquals(-1, actual.getDepartmentID());
        assertEquals(-1, actual.getWage());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNameCombinations")
    public void shouldThrowBadParamException(String firstName, String lastName) {
        //by private checkForValidity() method
        assertThrows(BadParamException.class, () -> out.addEmployee(firstName, lastName));
        assertThrows(BadParamException.class, () -> out.findEmployee(firstName, lastName));
        assertThrows(BadParamException.class, () -> out.removeEmployee(firstName, lastName));
    }

    @ParameterizedTest
    @MethodSource("provideManageableVariationsOfTheSameName")
    public void shouldThrowEmployeeAlreadyAddedException(String firstName, String lastName) {
        out.addEmployee(VALID_NAME, VALID_NAME);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(firstName, lastName));
    }

    @Test
    public void shouldAddIntoHashMapCorrectly() {
        Map<String, Employee> expected = new HashMap<>();
        String key = NAME_VARIATION1 + "_" + NAME_VARIATION1;
        Employee value = new Employee(NAME_VARIATION1, NAME_VARIATION1);
        expected.put(key, value);
        key = NAME_VARIATION2 + "_" + NAME_VARIATION2;
        value = new Employee(NAME_VARIATION2, NAME_VARIATION2);
        expected.put(key, value);

        out.addEmployee(NAME_VARIATION1, NAME_VARIATION1);
        out.addEmployee(NAME_VARIATION2, NAME_VARIATION2);

        assertEquals(expected, out.getAllEmployees());
    }

    @Test
    public void shouldFindFromHashMapCorrectly() {
        out.addEmployee(NAME_VARIATION1, NAME_VARIATION1);
        out.addEmployee(NAME_VARIATION2, NAME_VARIATION2);

        assertEquals(EMPLOYEE_VARIATION1, out.findEmployee(NAME_VARIATION1, NAME_VARIATION1));
        assertEquals(EMPLOYEE_VARIATION2, out.findEmployee(NAME_VARIATION2, NAME_VARIATION2));
    }

    @Test
    public void shouldRemoveFromHashMapCorrectly() {
        out.addEmployee(VALID_NAME, VALID_NAME);
        assertEquals(CORRECT_EMPLOYEE, out.removeEmployee(VALID_NAME, VALID_NAME));
        assertEquals(new HashMap<>(), out.getAllEmployees());
    }

    @ParameterizedTest
    @MethodSource("provideManageableVariationsOfTheSameName")
    public void shouldManageNameCapitalizationThenReturnCorrectEmployee(String firstName, String lastName) {
        //by private prepareName() method
        assertEquals(CORRECT_EMPLOYEE, out.addEmployee(firstName, lastName));
        assertEquals(CORRECT_EMPLOYEE, out.findEmployee(firstName, lastName));
        assertEquals(CORRECT_EMPLOYEE, out.removeEmployee(firstName, lastName));
    }

    public static Stream<Arguments> provideInvalidNameCombinations() {
        return Stream.of(
                Arguments.of(VALID_NAME, NULL),
                Arguments.of(VALID_NAME, EMPTY),
                Arguments.of(VALID_NAME, BLANK),
                Arguments.of(VALID_NAME, INVALID_SYMBOLS_NAME),
                Arguments.of(VALID_NAME, NUMBERS_INCLUDED_NAME),
                Arguments.of(NULL, VALID_NAME),
                Arguments.of(EMPTY, VALID_NAME),
                Arguments.of(BLANK, VALID_NAME),
                Arguments.of(INVALID_SYMBOLS_NAME, VALID_NAME),
                Arguments.of(NUMBERS_INCLUDED_NAME, VALID_NAME),
                Arguments.of(NULL, NULL),
                Arguments.of(EMPTY, EMPTY),
                Arguments.of(BLANK, BLANK),
                Arguments.of(INVALID_SYMBOLS_NAME, INVALID_SYMBOLS_NAME),
                Arguments.of(NUMBERS_INCLUDED_NAME, NUMBERS_INCLUDED_NAME)
        );
    }

    public static Stream<Arguments> provideManageableVariationsOfTheSameName() {
        return Stream.of(
                Arguments.of(VALID_NAME, VALID_NAME),
                Arguments.of(VALID_NAME, ALL_CAPS_NAME),
                Arguments.of(VALID_NAME, NOT_CAPITALIZED_NAME),
                Arguments.of(ALL_CAPS_NAME, VALID_NAME),
                Arguments.of(NOT_CAPITALIZED_NAME, VALID_NAME),
                Arguments.of(ALL_CAPS_NAME, ALL_CAPS_NAME),
                Arguments.of(NOT_CAPITALIZED_NAME, NOT_CAPITALIZED_NAME)
        );
    }
}