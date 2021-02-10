typedef i32 int

exception InvalidOperation {
	1: i32 whatOp,
	2: string why
}

struct SensorData{                            
    1: required string timeStamp;             
    2: required string sensorType;             
    3: required string value;
    4: required string sationName;
}

service SensorService
{
        bool sendValues(1:SensorData value),
}