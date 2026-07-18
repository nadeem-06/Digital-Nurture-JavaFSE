package com.cognizant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.bean.Address;
import com.cognizant.bean.Department;
import com.cognizant.bean
        .Employee;

public class Exercise1_BasicSpring {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("Exercise 1: Basic Spring Application");
        System.out.println("================================\n");

        // Load Spring IoC container
        // Spring reads XML and creates all beans automatically
        ApplicationContext context =
            new ClassPathXmlApplicationContext(
                "applicationContext.xml"
            );

        System.out.println("Spring IoC Container started!\n");

        // Get Employee bean from container
        Employee emp =
            context.getBean("employee", Employee.class);
        System.out.println("Employee bean retrieved:");
        System.out.println(emp);

        // Get Address bean
        Address addr =
            context.getBean("address", Address.class);
        System.out.println("\nAddress bean:");
        System.out.println(addr);

        // Get Department bean
        Department dept =
            context.getBean("department", Department.class);
        System.out.println("\nDepartment bean:");
        System.out.println(dept);

        // Prove Singleton - same instance returned every time
        Employee emp1 =
            context.getBean("employee", Employee.class);
        Employee emp2 =
            context.getBean("employee", Employee.class);
        System.out.println("\nSingleton proof:");
        System.out.println("emp1 == emp2 : " + (emp1 == emp2));

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\nSpring context closed.");
        System.out.println("================================");
    }
}