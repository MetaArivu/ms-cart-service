#!/bin/sh
# -------------------------------------------------------------------------------------------
# @author: Araf Karsh Hamid
# -------------------------------------------------------------------------------------------
mvn clean package

cp -rf target/*.jar Docker/

