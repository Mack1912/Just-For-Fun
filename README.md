# Just-For-Fun
Playing with spring-boot

08/24/2017
Trying to build the Contacts application in the last chapter of book - "Spring in Action"

Faced a build error by maven
[ERROR] Failed to execute goal on project pk_contacts_app: Could not resolve dependencies for project com.pklabs:pk_contacts_app:jar:1.0-SNAPSHOT: Failure to find
 org.springframework.boot:spring-boot-starter-parent:jar:1.5.6.RELEASE in https://repo.maven.apache.org/maven2 was cached in the local repository, resolution will not be reattempted until the update interval of central has elapsed or updates are forced

Went through some blogs and found this:
1. try deleteing the folder c:/user/Mack/.m2/repository/org/springframework/boot/spring-boot-starter-parent and then using mvn with -U - This did not solve the issue

2. I thought, it was caused because I did not define the mvn plugin
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>

This plugin is the Maven counterpart to the Gradle plugin and enables
the build to produce an executable uber-JAR file.

I searched abt what are maven plugins after all:
https://www.tutorialspoint.com/maven/maven_plugins.htm

It did not work!!!!

3. if you look the error it says it could not find the jar file [spring-boot-starter-parent:jar:1.5.6.RELEASE]
similar problem:
https://stackoverflow.com/questions/27110014/could-not-find-artifact-in-maven-central-repo-when-search-returns-a-match
The problem is that on Maven Central(where all the pom files are pulled from) this kind of artifact does not exist. It only exist a pom file but not a jar file.

4. I searched more about what is present in spring-boot-starter-parent:
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-build-systems.html
Maven users can inherit from the spring-boot-starter-parent project to obtain sensible defaults. The parent project provides the following features:

Java 1.6 as the default compiler level.
UTF-8 source encoding.
A Dependency Management section, allowing you to omit <version> tags for common dependencies, inherited from the spring-boot-dependencies POM.
Sensible resource filtering.
Sensible plugin configuration (exec plugin, surefire, Git commit ID, shade).
Sensible resource filtering for application.properties and application.yml including profile-specific files (e.g. application-foo.properties and application-foo.yml)

I found that i have declared this starter-parent as a dependency instead of <parent></parent>
Hahaaa.. that was the mistake.. terrible


