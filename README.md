# Exercise 2: Operating on Linked Data 

This repository contains a partial implementation of a [JaCaMo](https://github.com/jacamo-lang/jacamo) application, in which autonomous agents are able to operate on Linked Data in a distributed social network — and to manage personal data of a user.

## Table of Contents
- [Project structure](#project-structure)
- [Task 2](#task-2)
  - [Edit the Access Control List of your Solid pod](#edit-the-access-control-list-of-your-solid-pod)
  - [Interact with a Linked Data Platform Container](#interact-with-a-linked-data-platform-container)
    - [Creating an LDP container](#1-creating-an-ldp-container)
    - [Adding data to an LDP container](#2-adding-data-to-an-ldp-container)
    - [Reading data from an LDP container](#3-reading-data-from-an-ldp-container)
- [How to run the project](#how-to-run-the-project)


## Project structure
```

```

## Task 2 
### Edit the Access Control List of your Solid pod
- Use [PodPro](https://podpro.dev/) to navigate and edit the Access Control List (ACL) of your Solid pod (https://solid.interactions.ics.unisg.ch/your-pod-name/.acl). Update the <#public> authorization rule based on the [Web Access Control vocabulary](https://solid.github.io/web-access-control-spec/#authorization-rule) so that agents can interact with your pod to read and/or write data:
```
@prefix acl: <http://www.w3.org/ns/auth/acl#>.
@prefix foaf: <http://xmlns.com/foaf/0.1/>.

<#public>
    a acl:Authorization;
    acl:agentClass foaf:Agent;
    acl:accessTo <./>;
    acl:mode acl:Read, acl:Write, acl:Control;
    acl:default <./>.
```

### Interact with a Linked Data Platform Container
First, update the line 24 in the JaCaMo configuration file [task.jcm](task.jcm), so that the project uses your own Solid pod:
```
artifact pod: solid.Pod("https://solid.interactions.ics.unisg.ch/your-pod-name")
```

Second, complete the tasks that follow to enable autonomous agents to interact with [our group's Solid Community Server](https://solid.interactions.ics.unisg.ch/) that implements the [Solid protocol](https://solidproject.org/TR/protocol), based on the [W3C Linked Data Platform (LDP) specification](https://www.w3.org/TR/ldp/). The API of our group’s Solid Community Server is [documented here](https://communitysolidserver.github.io/CommunitySolidServer/5.x/).

#### 1. Creating an LDP container
Implement the method `createContainer()` of the Java class [`Pod`](src/env/solid/Pod.java) that enables agents to to create an LDP container in your pod.
- TIP: [Creating containers based on the Linked Data Platform 1.0 documentation](https://www.w3.org/TR/ldp-primer/#creating-containers-and-structural-hierarchy)

#### 2. Adding data to an LDP container
Implement the method `publishData()` of the Java class [`Pod`](src/env/solid/Pod.java) that enables agents to publish data (text/plain) to an LDP container in your pod.
- TIP: [Creating resources (e.g. publishing data to a container) based on the Solid Community Server documentation]

#### 3. Reading data from an LDP container
Implement the method `readData()` of the Java class [`Pod`](src/env/solid/Pod.java) that enables agents to read data (text/plain) from an LDP container in your pod.
- TIP: [Retrieving resources (e.g. reading data from a container) based on the Solid Community Server documentation]
   
## How to run the project
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
