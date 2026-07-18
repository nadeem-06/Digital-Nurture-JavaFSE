package com.cognizant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private double salary;

    // Default constructor - required by JPA
    public Employee() {}

    // Parameterized constructor
    public Employee(String firstName, String lastName,
                    String email, String department,
                    double salary) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.department = department;
        this.salary     = salary;
    }

    // Getters
    public Long   getId()         { return id;         }
    public String getFirstName()  { return firstName;  }
    public String getLastName()   { return lastName;   }
    public String getEmail()      { return email;      }
    public String getDepartment() { return department; }
    public double getSalary()     { return salary;     }

    // Setters
    public void setId(Long id)               { this.id         = id;         }
    public void setFirstName(String fn)      { this.firstName  = fn;         }
    public void setLastName(String ln)       { this.lastName   = ln;         }
    public void setEmail(String email)       { this.email      = email;      }
    public void setDepartment(String dept)   { this.department = dept;       }
    public void setSalary(double salary)     { this.salary     = salary;     }

    @Override
    public String toString() {
        return "Employee{" +
               "id="          + id         +
               ", firstName=" + firstName  +
               ", lastName="  + lastName   +
               ", email="     + email      +
               ", department="+ department +
               ", salary="    + salary     +
               "}";
    }
}