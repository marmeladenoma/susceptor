# susceptor
Library and utilities supporting rapid development and centralized management of common development practices.

## Getting Started

This section desribed how to integrate susceptor in your development workflow and where and
how it can help you developing plugins.

## Features

This brievly describes the core features of this library. You can find further information
in the correspondign sub directories.

### Dependency Injection

Implemented in [susceptor-dependency-injection](susceptor-dependency-injection).

Susceptor defines [Google Guice](https://github.com/google/guice) as a dependency injection framework.

### Persistence

Implemented in [susceptor-persistence](susceptor-persistence).

The persistence module introduces MongoDB as a database and uses [Morphia](https://morphia.dev/)
as orm for MongoDB.

For getting started you should take a look at the [PersistenceModule](susceptor-persistence/src/main/java/de/marmeladenoma/susceptor/persistence/inject/PersistenceModule.java).
This module contains several bindings for bootstrapping your persistence layer with MongoDB. The usage is pretty straight forward:

```java
var config = PersistenceConfig.newBuilder()
  .withMongoConnectionString("")
  .withMongoUser("")
  .withMongoPassword("")
  .withMongoSource("")
  .build();
var injector = Guice.createInjector(PersistenceModule.create(config));
```

The injector prepares a MongoDB Client that can be easily used via the
[DatastoreFactory](susceptor-persistence/src/main/java/de/marmeladenoma/susceptor/persistence/DatastoreFactory.java):

```java
var datastoreFactory = injector.getInstance(DatastoreFactory.class);
datastoreFactory.createDatastore("test_database");
```

Ready, Steady, Go!