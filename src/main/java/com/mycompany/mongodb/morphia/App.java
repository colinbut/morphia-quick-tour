package com.mycompany.mongodb.morphia;

import com.mongodb.MongoClient;
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

    }
}
