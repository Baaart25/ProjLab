#!/bin/bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
