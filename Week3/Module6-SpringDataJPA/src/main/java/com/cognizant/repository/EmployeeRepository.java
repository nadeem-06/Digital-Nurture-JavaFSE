package com.cognizant.repository;

import com.cognizant.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    // ─────────────────────────────────────────
    // Derived Query Methods
    // Spring auto-generates SQL from method name
    // ─────────────────────────────────────────

    // Find by department
    List<Employee> findByDepartment(String department);

    // Find by first name
    List<Employee> findByFirstName(String firstName);

    // Find by email
    Optional<Employee> findByEmail(String email);

    // Find employees with salary greater than
    List<Employee> findBySalaryGreaterThan(double salary);

    // Find by department ordered by salary
    List<Employee> findByDepartmentOrderBySalaryDesc(
            String department);

    // ─────────────────────────────────────────
    // Custom JPQL Queries
    // ─────────────────────────────────────────

    // Find by department using JPQL
    @Query("SELECT e FROM Employee e " +
           "WHERE e.department = :dept")
    List<Employee> findEmployeesByDept(
            @Param("dept") String department);

    // Find employees with salary in range
    @Query("SELECT e FROM Employee e " +
           "WHERE e.salary BETWEEN :min AND :max")
    List<Employee> findBySalaryRange(
            @Param("min") double minSalary,
            @Param("max") double maxSalary);

    // Get average salary by department
    @Query("SELECT AVG(e.salary) FROM Employee e " +
           "WHERE e.department = :dept")
    Double getAvgSalaryByDepartment(
            @Param("dept") String department);

    // Native SQL query
    @Query(value = "SELECT * FROM employees " +
                   "WHERE department = :dept",
           nativeQuery = true)
    List<Employee> findByDeptNative(
            @Param("dept") String department);
}