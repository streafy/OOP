@echo off
:parse
IF "%~1"=="" GOTO endparse
IF "%~1"=="-c" javac src/main/java/ru/nsu/fit/Heapsort.java
IF "%~1"=="-d" javadoc -d javadoc src/main/java/ru/nsu/fit/Heapsort.java
IF "%~1"=="-r" java -cp src/main/java/ ru.nsu.fit.Heapsort
SHIFT
GOTO parse
:endparse
pause