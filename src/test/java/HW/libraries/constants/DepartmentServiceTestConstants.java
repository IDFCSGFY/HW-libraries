package HW.libraries.constants;

import HW.libraries.model.Employee;

import java.util.*;

public class DepartmentServiceTestConstants {
    public static final Employee D1_EMPLOYEE_POOR = new Employee("Dude", "Broke", 1, 111111);
    public static final Employee D1_EMPLOYEE_RICH = new Employee("Dood", "Rich", 1, 111222);
    public static final Employee D2_EMPLOYEE_POOR = new Employee("Miss", "Broke", 2, 222111);
    public static final Employee D2_EMPLOYEE_RICH = new Employee("Gal", "Rich", 2, 222222);
    public static final Employee D3_EMPLOYEE_SINGLE = new Employee("Thatis", "Whoever", 3, 333000);

    public static final Map<String, Employee> EMPLOYEE_HASH_MAP = new HashMap<>(Map.of(
            "Dude_Broke", D1_EMPLOYEE_POOR,
            "Dood_Rich", D1_EMPLOYEE_RICH,
            "Miss_Broke", D2_EMPLOYEE_POOR,
            "Gal_Rich", D2_EMPLOYEE_RICH,
            "Thatis_Whoever", D3_EMPLOYEE_SINGLE
    ));
}
