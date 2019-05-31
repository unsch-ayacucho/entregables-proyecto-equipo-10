# SI2 FINAL PROJECT
## Description
Final project for the university subject "Sistemás de Información II" - U.N.S.C.H.

## Requirements
* You must have [Maven](https://maven.apache.org/), wich is tipically bundled with [Java SDK 8+](https://openjdk.java.net/projects/jdk8/)
* You need to have [Apache Tomcat](https://tomcat.apache.org/) if you want to deploy the .war files compiled in the project.
* You also must have [GIT](https://git-scm.com/) if you want to contribute to the project.

## Instalation

First of all, clone the repository.

```
git@github.com:unsch-ayacucho/entregables-proyecto-equipo-10.git
```

### Compile the project to a .war file
* If you need to build your changes for development making a clean install:

```
cd <path_to_project>
mvn clean install -U
```

* If you need to build your changes for development making without making a clean install:

```
cd <path_to_project>
mvn compile
```

### Start and configure Apache Tomcat
If you're working with linux you can link your target folder/compiled_file.war to a relative path for hot-reloads:

```
cd <path_to_tomcat_conf> (in Arch Linux is usually at /etc/tomcat<your_tomcat_version>/Catalina/localhost)
sudo vim (or your favorite text editor) si2.xml
<Context path="/si2" docBase="/home/gedzeppelin/Projects/SI2/target/si2_app-0.0.1-SNAPSHOT.war" reloadable="true"/>
sudo systemctl restart tomcat <your_tomcat_version>
```

## Developed with
* [Atom](https://atom.io/) -  A hackable text editor, built on Electron.
* [Terminator](https://gnometerminator.blogspot.com/p/introduction.html) - Useful tool for arranging multiple terminals.
