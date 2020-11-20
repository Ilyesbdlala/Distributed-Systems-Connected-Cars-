# VS "Connected Cars" 
This Project shows an example of "Connected Cars"  
We simulate a car that has an indefinite number of sensors ( 4 in the initial example) that communicate to a central system through an MoM (Message-oriented middleware): MQTT in particular.  
This data is then relayed to an HTTP Client that allows viewing the state of each sensor.  
The Data is first saved in the Central system and later persisted in cloud servers through a Service Provider (Apache Thrift)  
## Dockerfiles

The files [sensor](sensor) and [centralcontrol](centralcontrol) are the docker files. In these files, the Java projects are compiled in the first step and in the second step the jar is copied into a new container which is used for the execution.  
We follow the [Multi-Stage Builds](https://docs.docker.com/develop/develop-images/multistage-build/) Practice   

## Docker-Compose

The Central Control has 4 sensors Fuel Level, Kilometers travelled, Traffic state and Avg. speed.  
The Sensor type, IP and port are each configured in the compose file [docker-compose](docker-compose)  

### How To - local build and run
- `docker-compose -f docker-compose.yml build`  
- `docker-compose -f docker-compose.yml up`  

## Gitlab CI
The Gitlab CI pipeline is in the file [.gitlab-ci.yml](.gitlab-ci.yml)  

## Tests

Average Response Time: The roundabout time of each HTTP Request/Response is measured.  
Sensor Redundancy: System continues to work in the case of a sensor failure.  
Cloud Server Redundancy: Usual System functionality in case of Server-failure (Servers are in Primary-Secondary Architecture Hot-Standby)  
Performance Test: Sensors are created periodically to measure how many sensors the system can handle.  
Race-Condition Test: Two sensors send Data Simultaneously. Data should arrive for both sensors at the central control  


