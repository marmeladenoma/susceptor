package de.marmeladenoma.susceptor.persistence.inject;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Objects;

public final class PersistenceConfig {
  private final String mongoConnectionString;
  private final String mongoUser;
  private final String mongoSource;
  private final String mongoPassword;

  PersistenceConfig(
    String mongoConnectionString,
    String mongoUser,
    String mongoSource,
    String mongoPassword
  ) {
    this.mongoConnectionString = mongoConnectionString;
    this.mongoUser = mongoUser;
    this.mongoSource = mongoSource;
    this.mongoPassword = mongoPassword;
  }

  public String mongoHost() {
    return mongoConnectionString;
  }

  public String mongoUser() {
    return mongoUser;
  }

  public String mongoSource() {
    return mongoSource;
  }

  public String mongoPassword() {
    return mongoPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersistenceConfig that = (PersistenceConfig) o;
    return mongoConnectionString.equals(that.mongoConnectionString)
      && mongoUser.equals(that.mongoUser)
      && mongoSource.equals(that.mongoSource)
      && mongoPassword.equals(that.mongoPassword);
  }

  @Override
  public int hashCode() {
    return Objects
      .hash(mongoConnectionString, mongoUser, mongoSource, mongoPassword);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("mongoHost", mongoConnectionString)
      .add("mongoUser", mongoUser)
      .add("mongoSource", mongoSource)
      .add("mongoPassword", mongoPassword)
      .toString();
  }

  public static PersistenceConfigBuilder newBuilder() {
    return new PersistenceConfigBuilder();
  }

  public static final class PersistenceConfigBuilder {
    private String mongoConnectionString;
    private String mongoUser;
    private String mongoSource;
    private String mongoPassword;

    private PersistenceConfigBuilder() {
    }

    @Deprecated
    public PersistenceConfigBuilder withMongoConnectionStrong(
      String mongoConnectionString
    ) {
      Preconditions.checkNotNull(mongoConnectionString);
      this.mongoConnectionString = mongoConnectionString;
      return this;
    }

    public PersistenceConfigBuilder withMongoConnectionString(
      String mongoConnectionString
    ) {
      Preconditions.checkNotNull(mongoConnectionString);
      this.mongoConnectionString = mongoConnectionString;
      return this;
    }

    public PersistenceConfigBuilder withMongoUser(String mongoUser) {
      Preconditions.checkNotNull(mongoUser);
      this.mongoUser = mongoUser;
      return this;
    }

    public PersistenceConfigBuilder withMongoSource(String mongoSource) {
      Preconditions.checkNotNull(mongoSource);
      this.mongoSource = mongoSource;
      return this;
    }

    public PersistenceConfigBuilder withMongoPassword(String mongoPassword) {
      Preconditions.checkNotNull(mongoPassword);
      this.mongoPassword = mongoPassword;
      return this;
    }

    public PersistenceConfig build() {
      Preconditions.checkNotNull(mongoConnectionString);
      Preconditions.checkNotNull(mongoUser);
      Preconditions.checkNotNull(mongoSource);
      Preconditions.checkNotNull(mongoPassword);
      return new PersistenceConfig(
        mongoConnectionString,
        mongoUser,
        mongoSource,
        mongoPassword
      );
    }
  }
}
