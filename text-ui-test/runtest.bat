@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp ../src/main/java;../build/install/ip/lib/* -Xlint:none -d ../bin ../src/main/java/duke/*.java ../src/main/java/parser/*.java ../src/main/java/task/*.java ../src/main/java/ui/*.java ../src/main/java/serialiser/*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp ../bin/;../build/install/ip/lib/* duke/Duke < input.txt > ACTUAL.TXT

REM removes the data and file directory to ensure consistent testing
rmdir /s /q data

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
