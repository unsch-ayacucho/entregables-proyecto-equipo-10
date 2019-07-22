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
git clone git@github.com:gedzeppelin/SI2.git
```

### Compile the project to a .war file
* If you need to build your changes for development making a clean install:

```
cd </path/to/project>
mvn clean install -U
```

* If you need to build your changes for development making without making a clean install:

```
cd </path/to/project>
mvn compile
```

### Start and configure Apache Tomcat
If you're working with linux you can link your target folder/compiled_file.war to a relative path for hot-reloads:

```
cd </path/to/tomcat/conf_folder> (in Arch Linux is usually at /etc/tomcat<your_tomcat_version>/Catalina/localhost)
```

Using vim (or your favorite text editor) create the file 'si2.xml' with the following content:
```
<Context path="/si2" docBase="/home/gedzeppelin/Projects/SI2/target/si2_app-0.0.1-SNAPSHOT.war" reloadable="true"/>
```

And restart the tomcat service
```
sudo systemctl restart tomcat <your_tomcat_version>
```

## Docker helpers (optional)
If you don't want to install maven or tomcat you can use they through docker:

### MySQL
Pull the mysql official image and start the container with that, we're using a docker volume to store the database data outside the container:
```
docker pull mysql
docker volume create mysql-data
docker run --name <container_name> -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=7449 -d -p 3306:3306 mysql:latest
```

### Tomcat
You should follow this [link](https://github.com/gedzeppelin/custom-tomcat-dockerfile).

### Maven
#### Installation
Pull the maven official image and compile the project with that:
```
docker pull maven
docker volume create --name maven-repo
docker run -it --rm --name si2-maven -v maven-repo:/root/.m2 -v </path/to/project>:/usr/src/si2-maven -w /usr/src/si2-maven maven:latest mvn [clean install -U|compile]
```

#### Maven antrun task
* To generate .java files:
```
docker run -it --rm --name si2-maven [--link <db-container>:<db-container>] -v maven-repo:/root/.m2 -v </path/to/project>:/usr/src/si2-maven -w /usr/src/si2-maven maven:latest mvn antrun:run@hbm2java
``````

* To generate .hbm.xml files:
```
docker run -it --rm --name si2-maven [--link <db-container>:<db-container>] -v maven-repo:/root/.m2 -v </path/to/project>:/usr/src/si2-maven -w /usr/src/si2-maven maven:latest mvn antrun:run@hbm2hbmxml
```

* To generate .cfg.xml files:
```
docker run -it --rm --name si2-maven [--link <db-container>:<db-container>] -v maven-repo:/root/.m2 -v </path/to/project>:/usr/src/si2-maven -w /usr/src/si2-maven maven:latest mvn antrun:run@hbm2cfgxml
```

#### Ownership of generated files
The target folder/files will be created with root ownership, you can give the ownership to your user this way:

* For maven compilation:
```
sudo chown -R "$USER":$(id -g -n "$USER") </path/to/project>/target/
``````

* For maven antrun hbm2java:
```
sudo chown -R "$USER":$(id -g -n "$USER") </path/to/project>/src/main/java/pe/edu/unsch/hibernate'
``````


* For maven antrun hbm2hbmxml:
```
sudo chown -R "$USER":$(id -g -n "$USER") </path/to/project>/src/main/resources/hibernate-beans'
``````

* For maven antrun hbm2cfgxml:
```
sudo chown "$USER":$(id -g -n "$USER") </path/to/project>/src/main/resources/hibernate.cfg.xml'
``````

## Developed with
* [Atom](https://atom.io/) -  A hackable text editor, built on Electron.
* [Terminator](https://gnometerminator.blogspot.com/p/introduction.html) - Useful tool for arranging multiple terminals.
