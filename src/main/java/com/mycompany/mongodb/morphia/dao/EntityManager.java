/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.morphia.dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class EntityManager {

    private static final EntityManager INSTANCE = new EntityManager();

    public static final EntityManager getInstance() {
        return INSTANCE;
    }

    private Datastore datastore;

    private void initialize() {
        final Morphia morphia = new Morphia();

        // tell Morphia where to locate the entities
        // can be called multiple times with different packages
        morphia.mapPackage("com.mycompany.mongodb.morphia.entities");

        // Connecting to database default port of 27017 on localhost
        Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia-quick-tour");

        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
