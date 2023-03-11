#!/bin/bash

service spring stop
service mysql stop
cd ~/cars-r-us
git pull origin main
mvn install
service mysql start
service spring start
