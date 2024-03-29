Mars Explorer Simulator Implementation
===================
This implementation responds the requirement stated in [REQUIREMENT.md](./REQUIREMENT.md).



# Build

## Install
```
mvn clean install
```

## Unit tests
```
mvn clean test
```

## Integration tests
```
mvn clean verify
```

## Java doc
```
mvn javadoc:javadoc
```

# Run Application

## Run Demo
```
java -jar ./target/xexplorer-<<version-number>>.jar
// e.g. java -jar ./target/xexplorer-1.0-SNAPSHOT.jar
```

## Run with specified command file
```
java -jar ./target/xexplorer-<<version-number>>.jar <<file-name>>
// e.g. java -jar ./target/xexplorer-1.0-SNAPSHOT.jar c:\hello-bot.txt
```

# API Document

API document is generated by java-doc. The document is available after running maven command:
```
mvn javadoc:javadoc
```

The location of the document is:
```
./target/site/apidocs/index.html
```

# Test Coverage Report
Test coverage report is automatically generated by jacoco plugin during running tests.

There are two types of reports, one includes unit tests coverage, and which is generated during maven test phase.

The other includes all (unit tests and integration tests), and which is generated during maven verify phase.

## Unit test coverage report

Report is only available after running unit tests successfully.

```
./target/site/jacoco-ut/index.html
```

## All test coverage report

Report is only available after running interagation tests successfully. This report covers both unit tests and integration tests.

```
./target/site/jacoco-all/index.html
```
