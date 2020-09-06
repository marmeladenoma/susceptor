package de.marmeladenoma.susceptor.persistence.inject;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javax.inject.Named;
import javax.inject.Singleton;

public final class PersistenceModule extends AbstractModule {
  private final PersistenceConfig persistenceConfig;

  private PersistenceModule(PersistenceConfig persistenceConfig) {
    this.persistenceConfig = persistenceConfig;
  }

  @Override
  protected void configure() {
    bindConstant().annotatedWith(Names.named("mongo_connection_string"))
      .to(persistenceConfig.mongoHost());
    bindConstant().annotatedWith(Names.named("mongo_user"))
      .to(persistenceConfig.mongoUser());
    bindConstant().annotatedWith(Names.named("mongo_source"))
      .to(persistenceConfig.mongoSource());
    bindConstant().annotatedWith(Names.named("mongo_password"))
      .to(persistenceConfig.mongoPassword());
  }

  @Provides
  @Singleton
  MongoClient provideMongoClient(MongoClientSettings clientSettings) {
    return MongoClients.create(clientSettings);
  }

  private static final String APPLICATION_NAME = "Susceptor";

  @Provides
  @Singleton
  MongoClientSettings provideMongoClientSettings(
    ConnectionString connectionString,
    MongoCredential credential
  ) {
    return MongoClientSettings.builder()
      .applicationName(APPLICATION_NAME)
      .applyConnectionString(connectionString)
      .credential(credential)
      .build();
  }

  @Provides
  @Singleton
  ConnectionString provideConnectionString(
    @Named("mongo_connection_string") String mongoConnectionString
  ) {
    return new ConnectionString(mongoConnectionString);
  }

  @Provides
  @Singleton
  MongoCredential provideMongoCredential(
    @Named("mongo_user") String mongoUser,
    @Named("mongo_source") String mongoSource,
    @Named("mongo_password") String mongoPassword
  ) {
    return MongoCredential.createScramSha256Credential(
      mongoUser,
      mongoSource,
      mongoPassword.toCharArray()
    );
  }

  public static PersistenceModule create(PersistenceConfig persistenceConfig) {
    Preconditions.checkNotNull(persistenceConfig);
    return new PersistenceModule(persistenceConfig);
  }
}
