typedef i32 int

exception InvalidOperation {
	1: i32 whatOp,
	2: string why
}

service SensorService
{
        bool getValues(list<string> value),
}