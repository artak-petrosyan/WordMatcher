#!/bin/sh
echo Cleaning build folder... 
rm -rf ./build/*
echo Generating Java doc
javadoc -d doc src/main/java/com/illumio/assessment/dictionary/*.java src/main/java/com/illumio/assessment/*.java
echo Building
javac -d ./build src/main/java/com/illumio/assessment/dictionary/*.java src/main/java/com/illumio/assessment/*.java
cd build
jar -cvfm WordMatcher-0.0.1-SNAPSHOT.jar ../manifest.txt *
echo Testing...
java -jar WordMatcher-0.0.1-SNAPSHOT.jar
echo For runing Word Matcher execute run.sh ...