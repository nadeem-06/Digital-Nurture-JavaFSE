package com.cognizant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support
        .ClassPathXmlApplicationContext;

import com.cognizant.bean.Employee;
import com.cognizant.service.EmployeeService;

public class Exercise4_MavenProject {

    private static final Logger logger =
        LoggerFactory.getLogger(Exercise4_MavenProject.class);

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("Exercise 4: Maven Project Setup");
        System.out.println("================================\n");

        // Log Maven dependencies loaded
        logger.info("Starting Spring application...");
        logger.info("Maven dependencies loaded:");
        logger.info("  - spring-core:6.0.11");
        logger.info("  - spring-context:6.0.11");
        logger.info("  - spring-beans:6.0.11");
        logger.info("  - logback-classic:1.4.11");

        // Load Spring context
        logger.info("Loading Spring IoC container...");
        ApplicationContext context =
            new ClassPathXmlApplicationContext(
                "applicationContext.xml"
            );

        // List all beans in context
        logger.info("Beans registered in Spring context:");
        String[] beanNames =
            context.getBeanDefinitionNames();
        for (String name : beanNames) {
            logger.info("  Bean: {}", name);
        }
        logger.info("Total beans: {}", beanNames.length);

        // Get and use employee bean
        Employee emp =
            context.getBean("employee", Employee.class);
        logger.info("Employee loaded: {}", emp.getName());

        // Get and use employeeService bean
        EmployeeService service =
            context.getBean(
                "employeeServiceEmail",
                EmployeeService.class
            );

        System.out.println();
        service.onboardEmployee();

        // Close context
        ((ClassPathXmlApplicationContext) context).close();
        logger.info("Spring context closed successfully.");
        System.out.println("================================");
    }
}