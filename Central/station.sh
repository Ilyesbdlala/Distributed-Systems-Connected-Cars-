#!/bin/sh

java -jar --illegal-access=permit /usr/app/Central-1.0-SNAPSHOT.jar   $stationname $sensorcount $sensorstartport 8080 $rpcip 51030
