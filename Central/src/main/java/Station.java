import org.apache.thrift.transport.TTransportException;

public class Station {

  private String stationname;
  private String ngixIp;
  private int ngixPort;
  private String mqttConnection;
  private String mqttTopic;
  private MqttSubscriber mqttSub;


  public void init() throws TTransportException {
    SensorData.stationName = stationname;
    RpcController.connect(ngixIp, ngixPort);
    mqttSub = new MqttSubscriber(mqttConnection, mqttTopic);
    mqttSub.run();
  }

  public String getStationname() {
    return stationname;
  }

  public void setStationname(String stationname) {
    this.stationname = stationname;
  }

  public String getNgixIp() {
    return ngixIp;
  }

  public void setNgixIp(String ngixIp) {
    this.ngixIp = ngixIp;
  }

  public int getNgixPort() {
    return ngixPort;
  }

  public void setNgixPort(int ngixPort) {
    this.ngixPort = ngixPort;
  }

  public void setMqttTopic(String mqttTopic) {
    this.mqttTopic = mqttTopic;
  }

  public void setMqttConnection(String mqttConnection) {
    this.mqttConnection = mqttConnection;
  }
}
