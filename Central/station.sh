#!/bin/sh

java -jar --illegal-access=permit /usr/app/Central-1.0-SNAPSHOT.jar   $stationname $nginxip $nginxport $mqttbroker $mqtttopic