package com.cognizant.bean;

public class Department {

    private String deptId;
    private String deptName;
    private String location;

    // Default constructor - required by Spring
    public Department() {}

    // Getters
    public String getDeptId()   { return deptId;   }
    public String getDeptName() { return deptName; }
    public String getLocation() { return location; }

    // Setters
    public void setDeptId(String deptId)     { this.deptId   = deptId;   }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Department{" +
               "id="       + deptId   +
               ", name="   + deptName +
               ", location=" + location +
               "}";
    }
}