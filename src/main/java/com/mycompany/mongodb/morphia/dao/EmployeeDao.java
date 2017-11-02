/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.morphia.dao;

import com.mycompany.mongodb.morphia.entities.Employee;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;

public class EmployeeDao {

    private Datastore datastore = EntityManager.getInstance().getDatastore();

    public void addEmployee() {
        Employee elmer = new Employee("Elmer Foud", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepe Le Pew", 50000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);
    }

    public void findEmployees() {
        final Query<Employee> query = datastore.createQuery(Employee.class);
        final List<Employee> employees = query.asList();
        System.out.println("All employees");
        employees.stream().forEach(System.out::println);

        List<Employee> underPaidEmployees = datastore.createQuery(Employee.class)
            .field("salary")
            .lessThanOrEq(30000)
            .asList();

//        List<Employee> underPaidEmployees = datastore.createQuery(Employee.class)
//            .filter("salary <=", 30000)
//            .asList();

        System.out.println("Underpaid employees");
        underPaidEmployees.stream().forEach(System.out::println);
    }

    public void updateEmployee() {
        Query<Employee> updateQuery = datastore.createQuery(Employee.class)
            .field("salary")
            .lessThanOrEq(30000);

        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
            .inc("salary", 10000);

        final UpdateResults updateResults = datastore.update(updateQuery, updateOperations);
        System.out.println(updateResults.getUpdatedCount());
    }

    public void removeEmployee() {
        Query<Employee> removeQuery = datastore.createQuery(Employee.class)
            .field("salary")
            .lessThanOrEq(30000);
        datastore.delete(removeQuery);
    }
}
