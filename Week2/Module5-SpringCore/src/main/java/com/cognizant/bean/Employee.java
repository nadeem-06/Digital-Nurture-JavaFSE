package com.cognizant.bean;

public class Employee {
    private int empId;
    private String name;
    private double salary;
    private Address address;       // object dependency
    private Department department; // object dependency

    public Employee() {}

    // Getters
    public int        getEmpId()      { return empId;      }
    public String     getName()       { return name;       }
    public double     getSalary()     { return salary;     }
    public Address    getAddress()    { return address;    }
    public Department getDepartment() { return department; }

    // Setters
    public void setEmpId(int empId)           { this.empId      = empId;      }
    public void setName(String name)          { this.name       = name;       }
    public void setSalary(double salary)      { this.salary     = salary;     }
    public void setAddress(Address address)   { this.address    = address;    }
    public void setDepartment(Department dep) { this.department = dep;        }

    @Override
    public String toString() {
        return "\nEmployee{" +
               "\n  id="         + empId +
               "\n  name="       + name +
               "\n  salary="     + salary +
               "\n  address="    + address +
               "\n  department=" + department +
               "\n}";
    }
}