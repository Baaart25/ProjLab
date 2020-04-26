#!/bin/python3

# Runs all tests from the input directory
# Usage python3 runall.py

import os
import subprocess


def runTest(name: str):
    subprocess.call(["runtest.bat",name])


path = 'input'
files = []
# r=root, d=directories, f = files
for r, d, f in os.walk(path):
    for file in f:
        if '.txt' in file:
            files.append(os.path.join(r, file))

names = []
for file in files:
    filep: str = file
    name = filep.split(os.path.sep)[1]
    name = name.replace(".txt","")
    names.append(name)

for testname in names:
    runTest(testname)