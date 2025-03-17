
import org.example.persistence.EmployeeData;
import org.example.model.Employee;

import java.util.List;

public class EmployeeDAOTest {
    public static void main(String[] args) {
        EmployeeData employeeData = new EmployeeData();

        // Add some employees
        Employee employee1 = new Employee("Alice");
        Employee employee2 = new Employee("Bob");
        employeeData.addEmployee(employee1);
        employeeData.addEmployee(employee2);

        // Retrieve employees from file
        List<Employee> employees = employeeData.loadEmployees();

        // Print employees to verify
        System.out.println("Retrieved employees:");
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName());
        }
    }
}