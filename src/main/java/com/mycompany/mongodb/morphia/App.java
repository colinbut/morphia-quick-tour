package com.mycompany.mongodb.morphia;

import com.mongodb.MongoClient;
import com.mycompany.mongodb.morphia.entities.Employee;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class App {

    public static void main( String[] args ) {

        final Morphia morphia = new Morphia();

        // tell Morphia where to locate the entities
        // can be called multiple times with different packages
        morphia.mapPackage("com.mycompany.mongodb.morphia.entities");

        // Connecting to database default port of 27017 on localhost
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia-quick-tour");

        datastore.ensureIndexes();

        Employee elmer = new Employee("Elmer Foud", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepe Le Pew", 50000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

    }
}
