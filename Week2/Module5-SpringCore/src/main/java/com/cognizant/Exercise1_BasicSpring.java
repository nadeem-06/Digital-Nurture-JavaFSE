package com.cognizant;

import com.cognizant.bean.Employee;
import com.cognizant.bean.Department;
import com.cognizant.bean.Address;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support
    .ClassPathXmlApplicationContext;

public class Exercise1_BasicSpring {

    public static void main(String[] args) {

        System.out.println("=== Exercise 1: Basic Spring Application ===\n");

        // Step 1: Load the Spring IoC container
        // Spring reads applicationContext.xml
        // Creates and wires all beans automatically
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Spring IoC Container loaded!\n");

        // Step 2: Get beans from container
        Employee emp = context.getBean("employee", Employee.class);
        System.out.println("Employee bean retrieved:");
        System.out.println(emp);

        // Step 3: Get other beans
        Address addr = context.getBean("address", Address.class);
        System.out.println("\nAddress bean:");
        System.out.println(addr);

        Department dept = context.getBean("department", Department.class);
        System.out.println("\nDepartment bean:");
        System.out.println(dept);

        // Step 4: Prove singleton — same instance
        Employee emp1 = context.getBean("employee", Employee.class);
        Employee emp2 = context.getBean("employee", Employee.class);
        System.out.println("\nSingleton proof:");
        System.out.println("emp1 == emp2 ? " + (emp1 == emp2));

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring context closed.");
    }
}