#!/bin/bash
rm -rf out
find src -name "*.java" > sources.txt
javac -d out @sources.txt
