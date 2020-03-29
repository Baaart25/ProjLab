dir /S /B "src\*.java" > sources.txt
javac -d out @sources.txt