package de.marmeladenoma.susceptor.persistence;

import com.google.common.base.Preconditions;
import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DatastoreFactory {
  private final MongoClient mongoClient;
  private final Map<String, Datastore> datastores = new ConcurrentHashMap<>();

  @Inject
  private DatastoreFactory(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Datastore createDatastore(String databaseName) {
    Preconditions.checkNotNull(databaseName);
    Preconditions.checkArgument(!databaseName.isEmpty());
    Preconditions.checkArgument(!databaseName.isBlank());
    return datastores.computeIfAbsent(databaseName, this::instantiateDatastore);
  }

  private Datastore instantiateDatastore(String databaseName) {
    return Morphia.createDatastore(mongoClient, databaseName);
  }
}
