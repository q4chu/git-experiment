#!/bin/bash -l
cd "${0%/*}"
cd ../../seleniumCL
mvn test -P regressionTest
xdg-open ./extent.html
rm ./extent.html
