package de.marmeladenoma.susceptor.persistence;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceConfig;
import de.marmeladenoma.susceptor.persistence.inject.PersistenceModule;

class DatastoreFactoryTest {
  public static void main(String[] args) {
    var config = PersistenceConfig.newBuilder()
      .withMongoConnectionString("")
      .withMongoUser("")
      .withMongoPassword("")
      .withMongoSource("")
      .build();
    var injector = Guice.createInjector(PersistenceModule.create(config));
    var datastoreFactory = injector.getInstance(DatastoreFactory.class);
    datastoreFactory.createDatastore("test_database");
  }
}