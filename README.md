# Git
Please check [Git](git.md) on git configration and operations.

# About this example

This example intends to provide a template for building micro-service project basing on spring boot + jax-rs chassis.

## Actions at init step

1. change _rootProject.name_ in _settings.gradle_
1. change _descripiton_ in all those _build.gradle_ files
1. Execute below build tasks to download dependices 
    ```
    gradle clean build
    gradle cleanIdea idea
    gradle cleanEclipse eclipse
    ```
1. Use either Eclipse or Intellij IDEA open the project and refactor the package _com.upbchain.pointcoin.examplemicro_ to a proper one for this project.
1. Reopen your project in IDE after re-generating IDE file as below:
    ````
    gradle cleanIdea idea
    gradle cleanEclipse eclipse

    ````
1. Then you can run below to start your micro-service:
    ```
    ./app$ gradle bootRun
    ```

   
## Sub Projects
We're suggesting to use jax-rs instead of spring restful for our micro-service implementation.
To make jerssey jax-rs api auto discovery work, there are two sub-projects defined for each micro-service, api and app.
API project is core of the micro-service, all domain, model, service and resource itself would be defined in this project.
App project how the micro-service api is configured and exposed, configurations except jax-rs resource api will be defined here.

## Packages
Each micro-service deserves a dedicated root package, the nanme of micro-service root package should be
_com.upbchain.pointcoin.<micro-service-name>_.

Spring boot application itself should be defined within root package in app project so as to auto discovery can work well with convention. 
1 sub package. If you look at the example skeleton code you would find there is a _DummyApplication_ defined in api project, this is
because that putting this dummary application in api project can easier our api development in development stage, it is excluded during package stage.

2 sub pacakges underneath _root pacakge_:
 * **api**, this is the root package of the api sub project, most of the implementation and configuration about api itself should be here.
 * **configuration**, all about spring boot configrations. API itself configuration should defined in api sub project. Application configurations like
 security etc should define in app sub-project.

6 sub packages are suggested under api's root package for the api of each micro-service:
 * **domain**, this is all about entities 
 * **respository**, JPA respository. JPA is suggested.
 * **service**, services defined here.
 * **model**, the java respsentation of the rest model which this micro-service exposes. POJOs with JSON annotation is suggested.
 * **resource**, JAX-RS resources defined here.
 * **util**, utilities used in this micro-service only.

