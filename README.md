# Exercise 2: Operating on Linked Data 

This repository contains a partial implementation of a [JaCaMo](https://github.com/jacamo-lang/jacamo) application, in which autonomous agents are able to operate on Linked Data in a social graph — and to manage personal data of a user.

## Table of Contents
- [Project structure](#project-structure)
- [Task 2](#task-2)
  - [Modify the Access Control resource of a Solid pod](#modify-the-access-control-resource-of-a-solid-pod)
  - [Interact with a Linked Data Platform Container](#interact-with-a-linked-data-platform-container)
- [How to run the project](#how-to-run-the-project)


## Project structure
```

```

## Task 2 
### Modify the Access Control resource of a Solid pod
- Use [PodPro](https://podpro.dev/) to navigate and edit the ACL resource of your pod (https://solid.interactions.ics.unisg.ch/your-pod-name/.acl). Update the <#public> authorization rule based on the [ACL vocabulary](https://solid.github.io/web-access-control-spec/#authorization-rule) so that agents can interact with your pod without being explicitly authorized:
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
