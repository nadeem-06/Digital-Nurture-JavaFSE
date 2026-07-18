package com.cognizant;

import com.cognizant.entity.Employee;
import com.cognizant.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication
        implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(
            SpringDataJpaApplication.class, args
        );
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\n================================");
        System.out.println("Spring Data JPA - Quick Example");
        System.out.println("================================\n");

        // ─────────────────────────────────────
        // CREATE - Save employees
        // ─────────────────────────────────────
        System.out.println("--- CREATE Operations ---");

        Employee e1 = new Employee(
            "Nadeem", "Khan",
            "nadeem@cognizant.com",
            "IT", 75000
        );
        Employee e2 = new Employee(
            "Alice", "Smith",
            "alice@cognizant.com",
            "IT", 85000
        );
        Employee e3 = new Employee(
            "Bob", "Jones",
            "bob@cognizant.com",
            "HR", 65000
        );
        Employee e4 = new Employee(
            "Charlie", "Brown",
            "charlie@cognizant.com",
            "HR", 70000
        );
        Employee e5 = new Employee(
            "David", "Wilson",
            "david@cognizant.com",
            "Finance", 90000
        );

        employeeService.saveEmployee(e1);
        employeeService.saveEmployee(e2);
        employeeService.saveEmployee(e3);
        employeeService.saveEmployee(e4);
        employeeService.saveEmployee(e5);

        // ─────────────────────────────────────
        // READ - Get all employees
        // ─────────────────────────────────────
        System.out.println("\n--- READ All Employees ---");
        List<Employee> allEmployees =
            employeeService.getAllEmployees();
        allEmployees.forEach(System.out::println);

        // ─────────────────────────────────────
        // READ - Get by ID
        // ─────────────────────────────────────
        System.out.println("\n--- READ by ID ---");
        Employee emp =
            employeeService.getEmployeeById(1L);
        System.out.println("Found: " + emp);

        // ─────────────────────────────────────
        // UPDATE
        // ─────────────────────────────────────
        System.out.println("\n--- UPDATE Operation ---");
        Employee updated = new Employee(
            "Nadeem", "Khan",
            "nadeem.khan@cognizant.com",
            "IT", 90000
        );
        Employee result =
            employeeService.updateEmployee(1L, updated);
        System.out.println("Updated: " + result);

        // ─────────────────────────────────────
        // FIND by Department
        // ─────────────────────────────────────
        System.out.println("\n--- Find by Department: IT ---");
        List<Employee> itEmployees =
            employeeService.getByDepartment("IT");
        itEmployees.forEach(System.out::println);

        // ─────────────────────────────────────
        // FIND by Salary Range
        // ─────────────────────────────────────
        System.out.println(
            "\n--- Find by Salary Range: 70000-90000 ---"
        );
        List<Employee> salaryRange =
            employeeService.getBySalaryRange(70000, 90000);
        salaryRange.forEach(System.out::println);

        // ─────────────────────────────────────
        // Average Salary by Department
        // ─────────────────────────────────────
        System.out.println(
            "\n--- Average Salary by Department ---"
        );
        Double avgIT =
            employeeService.getAvgSalary("IT");
        Double avgHR =
            employeeService.getAvgSalary("HR");
        System.out.println("IT  avg salary: " + avgIT);
        System.out.println("HR  avg salary: " + avgHR);

        // ─────────────────────────────────────
        // DELETE
        // ─────────────────────────────────────
        System.out.println("\n--- DELETE Operation ---");
        employeeService.deleteEmployee(3L);
        System.out.println(
            "Total employees after delete: " +
            employeeService.getTotalCount()
        );

        System.out.println("\n================================");
        System.out.println("JPA vs Hibernate vs Spring Data JPA");
        System.out.println("================================");
        System.out.println(
            "JPA          : Specification/Interface only"
        );
        System.out.println(
            "Hibernate    : Implementation of JPA spec"
        );
        System.out.println(
            "Spring Data  : Abstraction on top of JPA"
        );
        System.out.println(
            "              (no SQL needed - just methods)"
        );
        System.out.println("================================\n");
    }
}