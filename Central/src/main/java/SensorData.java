public class SensorData {
  private String timestamp;
  private String type;
  private String value;
  private int id;
  public static String stationName;


  public SensorData(String timestamp, String type, String value) {
    this.timestamp = timestamp;
    this.type = type;
    this.value = value;
  }

  public SensorData(String message) {
    _parseMessage(message);
  }

  public void setId(int port){
    this.id = port - 51019;
  }

  public int getId(){
    return this.id;
  }

  private void _parseMessage(String msg) {
    String[] dataElements = msg.split(",");
    this.setId(Integer.parseInt(dataElements[0]));
    this.timestamp = dataElements[1];
    this.type = dataElements[2];
    this.value = dataElements[3];
  }

  @Override
  public String toString() {
    return "SensorData{" +
        "timestamp='" + timestamp + '\'' +
        ", type='" + type + '\'' +
        ", value='" + value + '\'' +
        ", station='" + stationName + '\'' +
        '}';
  }

  public String toJsonString() {
    return "{"
        + "\"timestamp\":\"" + this.timestamp
        + "\",\"sensortype\":\"" + this.type
        + "\",\"value\":" + this.value
        + ",\"station\":\"" + stationName + "\""
        + "}";
  }
}