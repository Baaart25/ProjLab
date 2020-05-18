@echo off
rd /S /Q out
dir /S /B "src\*.java" > sources.txt
javac -d out @sources.txt
cp src\META-INF\MANIFEST.MF out
xcopy /E /I img out\