import org.apache.thrift.transport.TTransportException;

public class Station {

  //params
  public String stationname;
  public int sensorcount;
  public int sensorstartport;
  public int apiport;
  //public String rpcip;
  public int rpcport;

  public MqttSubscriber mqttSub;


  public void init() throws TTransportException {
    RpcController.connect();
    mqttSub = new MqttSubscriber();
    mqttSub.run();


  }
}
