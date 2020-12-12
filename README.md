# VS "Connected Cars" 
This Project shows an example of "Connected Cars"  
We simulate a car that has an indefinite number of sensors ( 4 in the initial example) that communicate to a central system through an MoM (Message-oriented middleware): MQTT in particular.  
This data is then relayed to an HTTP Client that allows viewing the state of each sensor.  
The Data is first saved in the Central system and later persisted in cloud servers through a Service Provider (Apache Thrift)  
  
![DesignDoc](./VS_Praktikum_Design.png)  
## Dockerfiles

The files [sensorfile](sensorfile) and [centralfile](centralfile) are the docker files. In these files, the Java projects are compiled in the first step and in the second step the jar is copied into a new container which is used for the execution.  
We follow the [Multi-Stage Builds](https://docs.docker.com/develop/develop-images/multistage-build/) Practice   

## Installation

The Central Control has 4 sensors Fuel Level, Kilometers travelled, Traffic state and Avg. speed.  
The Sensor type, IP and port are each configured in the compose file [docker-compose](docker-compose.yml )  

### How To - local build and run
- `docker-compose build`  
- `docker-compose up`  

### How To access Sensors in Web Client  
- `localhost:8080/sensors` for sensor history  
- `localhost:8080/sensor/[1-5]` for each Sensors' current status  

## Gitlab CI
The Gitlab CI pipeline is in the file [.gitlab-ci.yml](.gitlab-ci.yml)  

## Tests

Test 1:  
Check if Sensor Values are recieved correctly  
Execution:  
Compare output in Sensor container with output in Central container  
  
Test 2:  
The roundabout time of each HTTP Request/Response is measured.  
Execution:  
Mesure how long the Central takes to answer Get requests  
  
Test 3:  
Check if Sensor Values are persisted correctly in Service Provider Servers  
Execution:  
Compare saved sensor Values with the Service Provider's  
  
Test 4:  
Availability Test  
Execution:  
Continually send pings to the Service Provider Server and count the number of responses   
  
Test 5:  
Check if Sensor Values are still recieved correctly with MQTT  
Execution:  
Compare output in Sensor container with output in Central container  
  
Test 6:  
Performance Test  
Execution:  
Sensors are created periodically to measure how many sensors the MQTT broker can handle  
  
Test 7:  
Check if the sensor Values were correctly copied  
Execution:  
Compare first server data with Back-up data  
  
Test 8:  
Usual System functionality in case of Server-failure (Servers are in Primary-Secondary Architecture Hot-Standby)  
Execution:  
Simulate server failure on Primary Server and see if data is still persisted   