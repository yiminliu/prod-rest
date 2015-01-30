BedLogic
=============

Bedrosians Business Logic Service written in Java


## Dev Environment

* Mac OS X v10.7.5 or greater

* VirtualBox with Ubuntu 11.10 x64 Server

* Git


## Setup

* Install and setup VirtualBox with Ubuntu 11.10 x64 Server

https://github.com/beachsidecoders/beachside-dev-central/blob/master/virtualbox/vbx-osx-ubuntu.md

* Install and setup Git on Ubuntu with ssh key access 

http://vcs.bedrosians.com/bedrosians/bedrosians-dev-central/blob/master/vcs/git.md

* Install maven, jdk 7, and curl

```sh
sudo apt-get install -y maven2 openjdk-7-jdk curl

# Choose java-7 jre if it's not already selected
sudo update-alternatives --config java
sudo update-alternatives --config javac
sudo update-alternatives --config javaws
```

* Project Files

```sh
# clone repo
git clone ssh://git@vcs.bedrosians.com:2222/bedrosians/bedlogic.git

cd bedims-uitest
```

* Installation - Build, Install, and Run. Installation directory is /opt/bedlogic

```sh
cd bedlogic
setup/installbedlogic.sh
```

* Upgrade

```sh
cd bedlogic
git pull
setup/installbedlogic.sh
```

* Start/Stop/Status

```sh
# Start
sudo start bedlogic

# Stop
sudo stop bedlogic

# Status
sudo status bedlogic
```

## Development

* Building

```sh
mvn clean package
```

* Running/Stopping

```sh
mvn jetty:run
# To stop, type Ctrl+c on the Jetty shell console
```

## Verify

See test/v2/README.md

##Application WADL
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<application xmlns="http://wadl.dev.java.net/2009/02">
    <doc xmlns:jersey="http://jersey.java.net/" jersey:generatedBy="Jersey: 1.18.3 12/01/2014 08:23 AM"/>
    <grammars/>
    <resources base="http://localhost:8080/bedlogic/v2/">
        <resource path="/ims">
            <method id="get" name="GET">
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <method id="create" name="POST">
                <request>
                    <representation mediaType="application/json"/>
                </request>
                <response>
                    <representation mediaType="*/*"/>
                </response>
            </method>
            <method id="update" name="PUT">
                <request>
                    <representation mediaType="application/json"/>
                </request>
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <resource path="{itemcode}">
                <param xmlns:xs="http://www.w3.org/2001/XMLSchema" name="itemcode" style="template" type="xs:string"/>
                <method id="delete" name="DELETE">
                    <response>
                        <representation mediaType="*/*"/>
                    </response>
                </method>
                <method id="getByItemCode" name="GET">
                    <response>
                        <representation mediaType="application/json"/>
                    </response>
                </method>
            </resource>
        </resource>
        <resource path="/hello">
            <method id="getHello" name="GET">
                <response>
                    <representation mediaType="application/json"/>
                </response>
            </method>
            <resource path="/index">
                <method id="createInitialLuceneIndex" name="GET">
                    <response>
                        <representation mediaType="*/*"/>
                    </response>
                </method>
            </resource>
        </resource>
    </resources>
</application>

## Acknowledgements
BedLogic makes use of the following third-party open source libraries:

* Jetty (http://www.eclipse.org/jetty) - HTTP server
* Jersey (https://jersey.java.net) - JAX-RS REST implementation
* Spring Framework (http://projects.spring.io/spring-framework) - Application features: Dependency Injection, IOC, Data Access via ODBC, and spring-test module for integration testing
* JUnit (http://junit.org) - Testing framework
* Mockito (http://code.google.com/p/mockito/) - Mocking framework
* SLF4J (http://www.slf4j.org) and Logback (http://logback.qos.ch) - Logging api via SLF4J, and implementation via Logback.
