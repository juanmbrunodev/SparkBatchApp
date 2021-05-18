# Spark Batch Application

Project serves as a template for Spark (Java API) based batch applications and it has been developed as part of the course *"Apache Spark For Java Developers"* on [www.educative.io](www.educative.io) learning platform.

The structure of the project reflects common Batch application practices and patterns, if only customised for the educational purposes of the course and due to the creator's personal choice.


### Compilation and Execution

This is a *Maven 3.X* project, so the following commands are relevant:

- To compile:

    `mvn clean package spring-boot:repackage`


- Once the application *jar* file is created, the program can be executed by running:

    `java -jar batch-app-0.0.1-SNAPSHOT.jar jobName=[jobName] [parameters]`

    Where the jar name can be configured or changed in the `pom.xml` file.

- There is a Dummy Job that runs the application beginning to end, to do so, compile as described above and run:

  `java -jar batch-app-0.0.1-SNAPSHOT.jar` jobName=sparkJob


### Documentation

Please refer to the [educative.io](www.educative.io) respective course for an in-depth documentation.




