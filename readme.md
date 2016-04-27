DB Migrations Starter
=====================

Simple Spring boot application to show off a few simple things: 

- Profiles referencing different datasources;
- Using an in-memory database for development;
- Using liquibase and migrating at bootstrap.

Usage
-----

Run the application:

`Application.main(final String ... args)`

By default the application.properties is loaded for further processing.
Inside the file a profile is set up (test by default) which means the
Spring Boot application will also automatically load the associated
properties files, being application-<profile>.properties.

Inside the properties file, properties are defined describing a
datasource. Spring boot (more specifically the jdbc part of boot) takes
these properties to initialize the datasource.

Datasources
-----------

This is the configuration for the 'pro' profile, which is a mysql 
datasource, using a mysql driver.

`spring.datasource.url=jdbc:mysql://localhost:3306/liquibase-demo`
`spring.datasource.username=root`
`spring.datasource.password=root`
`spring.datasource.driver-class-name=com.mysql.jdbc.Driver`

This is the configuration for the 'test' profile, which is a H2 database
running in MySql compatibility mode.

`spring.datasource.url=jdbc:h2:mem:liquibase-demo;Mode=MySql`
`spring.datasource.platform=h2`

Depending on the profile active, one of the two is initialized and the
application will be agnostic of which one.

To execute a query using jdbc, have the jdbcTemplate injected which will
go through this datasource.

`@Inject`
`private JdbcTemplate jdbcTemplate;`

Liquibase
---------

The application - on startup - runs a series of updates on the
database using liquibase database versioning. Liquibase keeps track of
changes and is able to incrementally update the database to the correct
state, or rebuild it from scratch. This is useful in local developement
and in production alike as it will make sure the database is in correct
shape for the application.

Spring boot will look for a file containing the changelog:

`db/changelog/db.changelog-master.yaml`

Based on this, and the current state of the data in the target database
liquibase will make sure the datasource is in the expected state for the
application to run against it.