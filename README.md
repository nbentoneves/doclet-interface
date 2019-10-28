### Status
[![Coverage Status](https://coveralls.io/repos/github/nbentoneves/sesame-java/badge.svg?branch=FirstPhase)](https://coveralls.io/github/nbentoneves/sesame-java?branch=FirstPhase)
[![Build Status](https://travis-ci.org/nbentoneves/sesame-java.svg?branch=master)](https://travis-ci.org/nbentoneves/sesame-java)

### Sesame Java
The Sesame Java allows you to test a class inside a java library without need to create code. It's useful when you need to 
create a java library for your projector/service but you want to execute some manual validations.

### Limitations
This project has some limitations. Some of this limitations can be implemented in the future:

- You can use primitive values, isn't allowed Object. E.g: Int, boolean, String...
- It's only available for one class/method. You can't test more then one method.
- The class can't have dependencies from other classes, isn't available injection classes. 

### Use Sesame Java

**WARNING:** Already isn't available. We need to submit the project at nexus. Sorry :|

#### Java docs
With java docs you only need to comment you code with specific tags and copy the following profile for your pom.xml file and
set the correct parameters.

| Field           | Description                                                        | Possible Values       |
| -------------   |--------------------------------------------------------------------|:---------------------:|
| phase           | You can set with one of the following maven phases.                | test, verify, install |
| goal            | This is a goal for the plugin. Please don't change this value!     | javadocs              |
| sourceInterface | You need to set the java file where did you put the documentation  | any java file         |

```xml
<profiles>
    <profile>
        <id>run-doclet-interface</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>com.doclet-interface.helper</groupId>
                    <artifactId>doclet-interface-plugin</artifactId>
                    <version>1.0-SNAPSHOT</version>
                    <executions>
                        <execution>
                            <phase>verify</phase>
                            <goals>
                                <goal>genDoc</goal>
                            </goals>
                            <configuration>
                                <sourceInterface>src/main/java/xxxxxx/TestDoc.java</sourceInterface>
                            </configuration>
                        </execution>
                    </executions>
                    <dependencies>
                        <dependency>
                            <groupId>xyzt.xyzt</groupId>
                            <artifactId>tzyt</artifactId>
                            <version>1.0</version>
                        </dependency>
                    </dependencies>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
``` 

### Contributions
Unfortunately the project is not ready to have contributors but you can send suggestions or features do you
like to see working. Maybe we can talk and you can work on it ;) 

### Notes
