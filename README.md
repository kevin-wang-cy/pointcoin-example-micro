# Git
Please check [Git](git.md) on git configration and operations.

# About this example

This example intends to provide a template for building micro-service project basing on spring boot + jax-rs chassis.

The build used in the project is [Gradle](https://gradle.org/), please go to [How to install Gradle](https://gradle.org/install) if you don't have it installed in your box.

Of course, you don't have to install Gradle to build this project. you can simply use the **gradlew** (or **gradlew.bat** on windows) commands in the project root for all command
where **gradle** is used below.

## Respository, a.k.a Artifactory
A local maven repository with mirror of maven-center is suggested.
Which repository used is defined in _gradle.properties_, and credentials used to publish artifacts into respository is defined there either.

**Notes:**
> Never put your credentials in source code, your can use either environment variable or gradle command line parameters to run gradle commands.
For example:  

```
gradle publish -PnexusUsername=mynexususername -PnexusPassword=mynexuspasswork
```

## Actions at init step

1. Change _rootProject.name_ in _settings.gradle_
1. Change _descripiton_ in all those _build.gradle_ files
1. Execute below build tasks to download dependices 
    ```
    gradle clean build
    gradle cleanIdea idea
    gradle cleanEclipse eclipse
    ```
1. Use either Eclipse or Intellij IDEA open the project and refactor the package _com.upbchain.pointcoin.examplemicro_ to a proper one for this project.
1. Reopen your project in IDE after re-generating IDE file as below:
    ```
    #!bash
    gradle cleanIdea idea
    gradle cleanEclipse eclipse
    ```
1. Then you can run below to start your micro-service:
    ```
    ./app$ gradle bootRun
    ```

   
## Sub Projects
We're suggesting to use jax-rs instead of spring restful for our micro-service implementation.
To make jerssey jax-rs api auto discovery work, there are two sub-projects defined for each micro-service, api and app.
API project is core of the micro-service, all domain, model, service and resource itself would be defined in this project.
App project, is about how the micro-service api is configured and exposed, configurations except jax-rs resource api will be defined here.

**Note**
> By default, the /api/ endpoint in api sub project doesn't have any security protectation, it means that you can test your api endpoints 
easily in api project. If you want to test security protection annotation, you should better run the app sub project which by default uses
the jwt token for authentication. Of course to run app project sucessfully you need start up the authentication & authorization server first
and then change the jwt token_key url to the correct one in app's _application.yml_.

## Packages
Each micro-service deserves a dedicated root package, the nanme of micro-service root package should be
_com.upbchain.pointcoin.<micro-service-name>_.

Spring boot application itself should be defined within root package in app project so as to auto discovery can work well with convention. 
1 sub package. If you look at the example skeleton code you would find there is a _DummyApplication_ defined in api project, this is
because that putting this dummary application in api project can easier our api development in development stage, it is excluded during package stage.

2 sub pacakges underneath _root pacakge_:

* **api**, this is the root package of the api sub project, most of the implementation and configuration about api itself should be here.
* **configuration**, all about spring boot configrations. API itself configuration should defined in api sub project. Application configurations like security etc should define in app sub-project.

6 sub packages are suggested under api's root package for the api of each micro-service:

 * **domain**, this is all about entities 
 * **respository**, JPA respository. JPA is suggested.
 * **service**, services defined here.
 * **model**, the java respsentation of the rest model which this micro-service exposes. POJOs with JSON annotation is suggested.
 * **resource**, JAX-RS resources defined here.
 * **util**, utilities used in this micro-service only.

## Tips for development
### Continous Development
For those you would like to continuous development without restart, and if you like commandline way to run application,
you can open two terminals, in the first terminal execute below command to lunch your application:
```bash
gradle bootRun
```
Of course you can use below_Debug & JVM Options_ to lunch the application in debug mode so as to you can debug on the fly.

And use below command to build the project in another terminal:
```bash
gradle build --continous
```
Above command will halt the build and execute build automatically each time after you change the source code, and thereafter the applicaiton will reload automactically
without breaking your debug breakpoints.

### Debug & JVM Options
```
gradle bootRun -PjvmArgs="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```


## Actions to publish to Artifcatory
With the right respository url configured, you can publish artifacts into maven repository as below:

1. Got the the app sub project
1. Execute below command if you want to publish the snapshot version
    ```bash
    gradle publish -PnexusUsername=yourusername -PnexusPassword=yourpassowrd
    ```
1. Or execute below command if you want to publish a release version
    ```
    gradle publish -PnexusUsername=yourusername -PnexusPassword=yourpassowrd -PreleaseVersion=1.0.0
    ```

## Integrations

### git.properties
This template has integrated git plugin which generates a git.proerties basing on your source code repository
and exposes git info through actuator's _/info_ endpoints as below:
```json
    "git": {
        "branch": "dev/CPC-5",
        "commit": {
            "id": "612f31b",
            "time": "2017-06-05T06:28:03.000+0000"
        }
    }
```