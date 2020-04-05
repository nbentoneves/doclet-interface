### Status
[![Coverage Status](https://coveralls.io/repos/github/nbentoneves/sesame-java/badge.svg?branch=master)](https://coveralls.io/github/nbentoneves/sesame-java?branch=FirstPhase)
[![Build Status](https://travis-ci.org/nbentoneves/sesame-java.svg?branch=master)](https://travis-ci.org/nbentoneves/sesame-java)

### Sesame Java
The Sesame Java allows you to test a class inside a java library without need to create code. It's useful when you need to 
create a java library for your project/service but you want to execute some manual validations.

### Limitations
This project has some limitations. Some of this limitations can be implemented in the future:

- You only can use primitive values, isn't allowed Object. E.g: Int, Boolean, String...
- It's only available for one class/method. You can't test more then one method.

### Use Sesame Java

**WARNING:** Already isn't available. We need to submit the project at nexus. Sorry :|


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


java -Dconfig.file.path=config/config.txt -cp sesame-java-0.0.1-SNAPSHOT.jar:config:/Users/nunobento/.m2/repository/com/sesame/helper/sesame-java-test/1.0-SNAPSHOT/sesame-java-test-1.0-SNAPSHOT.jar -Dloader.main=com.sesame.ui.SesameJavaApplicationKt org.springframework.boot.loader.PropertiesLauncher
java -Dconfig.file.path=config/config.txt -cp sesame-java-0.0.1-SNAPSHOT.jar:config:/Users/nunobento/.m2/repository/com/sesame/helper/sesame-java-test/1.0-SNAPSHOT/sesame-java-test-1.0-SNAPSHOT.jar org.springframework.boot.loader.PropertiesLauncher