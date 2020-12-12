class SensorData {
  private String timestamp;
  private String type;
  private String value;
  private int id;


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
    this.timestamp = dataElements[0];
    this.type = dataElements[1];
    this.value = dataElements[2];
  }

  @Override
  public String toString() {
    return "SensorData{" +
        "timestamp='" + timestamp + '\'' +
        ", type='" + type + '\'' +
        ", value='" + value + '\'' +
        '}';
  }

  public String toJsonString() {
    return "{"
        + "\"Timestamp\":\"" + this.timestamp
        + "\",\"Sensor_Type\":\"" + this.type
        + "\",\"Value\":" + this.value
        + "}";
  }
}