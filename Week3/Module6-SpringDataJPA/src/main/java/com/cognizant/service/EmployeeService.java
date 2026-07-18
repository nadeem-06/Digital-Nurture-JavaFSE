package com.cognizant.service;

import com.cognizant.entity.Employee;
import com.cognizant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ─────────────────────────────────────────
    // CREATE
    // ─────────────────────────────────────────
    public Employee saveEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        System.out.println("Saved: " + saved);
        return saved;
    }

    // ─────────────────────────────────────────
    // READ ALL
    // ─────────────────────────────────────────
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ─────────────────────────────────────────
    // READ BY ID
    // ─────────────────────────────────────────
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp =
            employeeRepository.findById(id);
        if (emp.isPresent()) {
            return emp.get();
        }
        throw new RuntimeException(
            "Employee not found with id: " + id
        );
    }

    // ─────────────────────────────────────────
    // UPDATE
    // ─────────────────────────────────────────
    public Employee updateEmployee(Long id,
                                   Employee updatedEmp) {
        Employee existing = getEmployeeById(id);
        existing.setFirstName(updatedEmp.getFirstName());
        existing.setLastName(updatedEmp.getLastName());
        existing.setEmail(updatedEmp.getEmail());
        existing.setDepartment(updatedEmp.getDepartment());
        existing.setSalary(updatedEmp.getSalary());
        return employeeRepository.save(existing);
    }

    // ─────────────────────────────────────────
    // DELETE
    // ─────────────────────────────────────────
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        System.out.println("Deleted employee id: " + id);
    }

    // ─────────────────────────────────────────
    // CUSTOM QUERIES
    // ─────────────────────────────────────────
    public List<Employee> getByDepartment(String dept) {
        return employeeRepository.findByDepartment(dept);
    }

    public List<Employee> getByDepartmentOrderedBySalary(
            String dept) {
        return employeeRepository
            .findByDepartmentOrderBySalaryDesc(dept);
    }

    public List<Employee> getBySalaryRange(
            double min, double max) {
        return employeeRepository
            .findBySalaryRange(min, max);
    }

    public Double getAvgSalary(String dept) {
        return employeeRepository
            .getAvgSalaryByDepartment(dept);
    }

    public long getTotalCount() {
        return employeeRepository.count();
    }
}