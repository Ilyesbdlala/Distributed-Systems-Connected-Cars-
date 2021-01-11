public class Station {

  //params
  public String stationname;
  public int sensorcount;
  public int sensorstartport;
  public int apiport;
  //public String rpcip;
  public int rpcport;

  public MqttSubscriber mqttSub;

  public void init() {
    mqttSub = new MqttSubscriber();
    mqttSub.run();

  }
}
