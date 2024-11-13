package HW.libraries.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int departmentID;
    private int wage;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = -1;
        this.wage = -1;
    }

    public Employee(String firstName, String lastName, int departmentID, int wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentID = departmentID;
        this.wage = wage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.firstName.equals(employee.firstName) && this.lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lastName);
    }

    @Override
    public String toString() {
        return String.join(" ", lastName, firstName)
                + "\nОтдел:\t" + departmentID
                + "\nЗарплата: " + wage;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
