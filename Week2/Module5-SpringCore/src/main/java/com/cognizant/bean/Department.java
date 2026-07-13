package com.cognizant.bean;

public class Department {
    private String deptId;
    private String deptName;
    private String location;

    public Department() {}

    public Department(String deptId, String deptName,
                      String location) {
        this.deptId    = deptId;
        this.deptName  = deptName;
        this.location  = location;
    }

    public String getDeptId()   { return deptId;   }
    public String getDeptName() { return deptName; }
    public String getLocation() { return location; }

    public void setDeptId(String deptId)     { this.deptId   = deptId;   }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "Department{id=" + deptId +
               ", name=" + deptName +
               ", location=" + location + "}";
    }
}