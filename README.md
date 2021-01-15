# VS "Connected Cars" 
This Project shows an example of "Connected Cars"  
We simulate a car that has an indefinite number of sensors ( 5 in the initial example) that communicate to a central system through an MoM (Message-oriented middleware): MQTT in particular. This data is then relayed to an HTTP Client that allows viewing the state of each sensor.  
The Data is first saved in the Central system and later persisted in cloud servers through a Service Provider

Any number of sensors can be implemented by adding their corresponding container images to the [docker-compose.yml](docker-compose.yml) file. 
### MQTT  
We use Mosquitto as our MQTT Broker. Mosquitto is both lightweight and offering the management features we need.         
- The Central Station takes the role of a Subscriber  
- Each Sensor is a Publisher 
 
### HTTP Client 
  Sensor history is saved to a file (sensors) in the home folder of the CentralStation container, and each current sensor state value in saved in  JSON format in /sensor folder. These values are updated with each publish request done to the Mosquitto broker.    
  A server socket in the port 8080 is able handle HTTP GET requests. GET requests can be done to the files mentioned above to allow viewing them in a web browser. 

### RPC & Service Provider
 A service allowing the retrieval of the sensor data from the CentralStation is generated through an Apache Thrift file [InterfaceDef.thrift](Thrift/InterfaceDef.thrift).  
To ensure the consistency of the data. Data is retrieved each time a Message is received to the Subscriber(CentralStation).   
MongoDB is used as our Database Management System (DBMS). It being a document-oriented database program that can handle JSON formats, makes the management of our sensor data easier.

![DesignDoc](./VS_Praktikum_Design.png)  
## Dockerfiles

The files [sensorfile](sensorfile), [centralfile](centralfile) and [serviceproviderfile](serviceproviderfile) are the docker files. In these files, the Java projects are compiled in the first step and in the second step the jar is copied into a new container which is used for the execution.  
We follow the [Multi-Stage Builds](https://docs.docker.com/develop/develop-images/multistage-build/) Practice   

## Installation
The Central Control has currently 5 sensors: Fuel Level, Kilometers travelled, Traffic state and 2 consecutive of type Avg. speed
(This is done to prove the system's ability to handle multiple sensors of the same type).  
The Sensor type, IP and port are each configured in the compose file [docker-compose](docker-compose.yml )  

### How To - local build and run
- `docker-compose build`  
- `docker-compose up`  

### How To access Sensors in Web Client  
- `localhost:8080/sensors` for sensor history  
- `localhost:8080/sensor/[1-5]` for each Sensors' current status  

### How To access the Remote MongoDB Database  
- `localhost:8081` to access the main MongoDB GUI
- `localhost:8081/db/vsdb/` to access the sensors database
  
## Gitlab CI
The Gitlab CI pipeline is in the file [.gitlab-ci.yml](.gitlab-ci.yml)  

## Tests

**Test 1:**
Check if Sensor Values are recieved correctly  
Execution:  
Compare output in Sensor container with output in Central container  
Result: **✓** Checked manually.
  
**Test 2:**
The roundabout time of each HTTP Request/Response is measured.  
Execution:  
Mesure how long the Central takes to answer Get requests   
Result:   
- Single Sensor HTTP GET Request : 4ms
- Complete Sensor history HTTP GET Request : 8ms

**Test 3:** 
Check if Sensor Values are persisted correctly in Service Provider Servers  
Execution:  
Compare saved sensor Values with the Service Provider's  
Result: **✓** Checked manually.

**Test 4:**  
Availability Test  
Execution:  
Continually send pings to the Service Provider Server and count the number of responses     
Result:  
```
--- serviceprovider ping statistics ---
1000 packets transmitted, 1000 received, 0% packet loss, time 1999ms
rtt min/avg/max/mdev = 0.031/0.072/0.222/0.018 ms
```
This result is to be taken with a grain of salt. That is since all containers are available locally (and not remote, which would have increased the unreliabiliy of the network).

**Test 5:**  
Check if Sensor Values are still recieved correctly with MQTT  
Execution:  
Compare output in Sensor container with output in Central container  
Result: **✓** Checked manually.

**Test 6:**  
Performance Test  
Execution:  
Sensors are created periodically to measure how many sensors the MQTT broker can handle  
Result: not yet implemented

**Test 7:**  
Check if the sensor Values were correctly copied  
Execution:  
Compare first server data with Back-up data  
Result: not yet implemented

**Test 8:** 
Usual System functionality in case of Server-failure (Servers are in Primary-Secondary Architecture Hot-Standby)  
Execution:  
Simulate server failure on Primary Server and see if data is still persisted  
Result: not yet implemented   
