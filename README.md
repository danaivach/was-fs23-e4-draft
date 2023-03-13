# Exercise 2: Operating on Linked Data 

This repository contains a partial implementation of a [JaCaMo](https://github.com/jacamo-lang/jacamo) application, in which autonomous agents are able to operate on Linked Data in a social graph — and to manage personal data of a user.

## Table of Contents
- [Project structure](#project-structure)
- [Task 2](#task-2)
  - [Task 2.1](#task-21)
  - [Task 2.2](#task-22)
  - [Task 2.3](#task-23)
- [How to run the project](#how-to-run-the-project)
  1. [Run the mockserver that mocks the tractors' HTTP APIs](#1-run-the-mockserver-that-mocks-the-tractors-http-apis)
  2. [Run the JaCaMo application](#2-run-the-jacamo-application)

## Project structure
```
├── SAMOD // the only directory required for Tasks 2.1 and 2.2
│   └── agriculture-domain 
│       ├── README.md // the description of the Motivating Scenario, including the Competency Questions and the preliminary Glossary of terms
│       ├── tbox.ttl // the preliminary TBox that implements the description of the terms in the glossary
│       ├── abox.ttl // the preliminary ABox that implements the description of the first 3 competency questions
│       └── queries  
│           ├── insert // queries for populating a knowledge graph based on the ABox
│           └── select // queries for testing the knowledge graph based on the competency questions
│ // the rest are required for Task 2.3
├── mockserver
│   └── tractors.json // the configuration file for the mockserver that mocks the HTTP APIs of farm tractors 
├── src
│   ├── agt
│   │   ├── irrigator.asl // agent program of the agent that irrigates the soil
│   │   └── moisture_detector.asl // agent program of the agent that detects the moisture level of soil
│   └── env
│       ├── farm
│       │   └── FarmKG.java // artifact that can be used for querying a GraphDB repository
│       └── wot
│           └── ThingArtifact.java // artifact that can be used for interacting with W3C Web of Things (WoT) Things
├── task.jcm // the configuration file of the JaCaMo application for Task 2.3
└── wot-td-java // library for the W3C Web of Things (WoT) Thing Description (TD)
```

## Task 2 
### Modify the Access Control resource of a Solid pod
### Interact with a Linked Data Platform Container
- Update line 24 in the JaCaMo configuration file [task.jcm](task.jcm), so that the project uses your own Solid pod:
```
artifact pod: solid.Pod("https://solid.interactions.ics.unisg.ch/your-pod-name")
```

- Complete the implementation of the Java class [`Pod`](src/env/solid/Pod.java) that enable autonomous agents to handle a Linked Data Platform Container in your pod:
    - Implement the method `createContainer()` which enables agents to create a container in your pod 
    - Implement the method `publishData()` which enables agents to publish data (text/plain) in a container of your pod
    - Implement the method `readData()` which enables agents to read data (text/plain) from a container of your pod
    - TIPS:
        - [Creating containers based on the Linked Data Platform 1.0 documentation](https://www.w3.org/TR/ldp-primer/#creating-containers-and-structural-hierarchy)
        - [Creating resources (e.g. publishing data to a container) based on the Solid Community Server documentation](https://communitysolidserver.github.io/CommunitySolidServer/5.x/usage/example-requests/#put-creating-resources-for-a-given-url)
        - [Retrieving resources (e.g. reading data from a container) based on the Solid Community Server documentation](https://communitysolidserver.github.io/CommunitySolidServer/5.x/usage/example-requests/#get-retrieving-resources)

## How to run the project
### 1. Run the mockserver that mocks the tractors' HTTP APIs

Run [MockServer](https://www.mock-server.com/) with [Docker](https://www.docker.com/):
   ```
   docker run -v "$(pwd)"/mockserver/tractors.json:/tmp/mockserver/tractors.json -e MOCKSERVER_INITIALIZATION_JSON_PATH=/tmp/mockserver/tractors.json -d --rm --name      mockserver -p 1080:1080 mockserver/mockserver
   ```
 The above command will run a Docker container in the background and will print the container ID. To stop a container run: `docker stop <CONTAINER_ID>`.
You can use this [Postman collection](https://api.postman.com/collections/2431802-7df760e4-7bd6-430a-a15f-f189b66ad619?access_key=PMAT-01GTWY6C8F4EZ44DV7T7Y7XWAF) to inspect the behavior of the tractors' mockserver.


### 2. Run the JaCaMo application

You can run the project directly in Visual Studio Code or from the command line with Gradle 7.4.
- In VSCode:  Click on the Gradle Side Bar elephant icon, and navigate through `GRADLE PROJECTS` > `exercise-3` > `Tasks` > `jacamo` > `task`.
- On MacOS and Linux run the following command:
```shell
./gradlew task
```
- On Windows run the following command:
```shell
gradle.bat task
```
