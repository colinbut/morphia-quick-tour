/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.morphia.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Entity("employees")
@Indexes(
    @Index(value = "salary", fields = @Field("salary"))
)
public class Employee {

    @Id
    private ObjectId id;

    private String name;

    @Reference
    private Employee manager;

    @Reference
    private List<Employee> directReports;

    @Property("wage")
    private Double salary;
}
