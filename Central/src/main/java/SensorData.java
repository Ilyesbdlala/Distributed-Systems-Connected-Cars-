class SensorData {
  String timestamp;
  String type;
  String value;


  public SensorData(String timestamp, String type, String value) {
    this.timestamp = timestamp;
    this.type = type;
    this.value = value;
  }

  public SensorData(String message) {
    _parseMessage(message);
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