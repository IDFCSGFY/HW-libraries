package HW.libraries.constants;

import HW.libraries.model.Employee;

public class EmployeeServiceTestConstants {
    public static final String VALID_NAME = "Name";
    public static final String ALL_CAPS_NAME = "NAME";
    public static final String NOT_CAPITALIZED_NAME = "name";
    public static final String INVALID_SYMBOLS_NAME = "нейм";
    public static final String NUMBERS_INCLUDED_NAME = "n123e";
    public static final String NULL = null;
    public static final String EMPTY = "";
    public static final String BLANK = "   ";

    public static final Employee CORRECT_EMPLOYEE = new Employee(VALID_NAME, VALID_NAME, -1, -1);

    public static final String NAME_VARIATION1 = "Alex";
    public static final String NAME_VARIATION2 = "Sophie";
    public static final Employee EMPLOYEE_VARIATION1 = new Employee(NAME_VARIATION1, NAME_VARIATION1, -1, -1);
    public static final Employee EMPLOYEE_VARIATION2 = new Employee(NAME_VARIATION2, NAME_VARIATION2, -1, -1);
}
