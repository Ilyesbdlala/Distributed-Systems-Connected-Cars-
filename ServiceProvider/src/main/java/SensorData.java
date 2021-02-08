import org.bson.Document;

public class SensorData {
  private String timestamp;
  private String sensortype;
  private String value;
  private String stationName;


  public SensorData(String timestamp, String type, String value, String stationName) {
    this.timestamp = timestamp;
    this.sensortype = type;
    this.value = value;
    this.stationName = stationName;
  }

  public Document createMongoDocument(){
    System.out.print(stationName);
    Document doc = new Document("timestamp", timestamp)
        .append("type", sensortype)
        .append("value", value)
        .append("stationName", stationName);
    return doc;
  }

  @Override
  public String toString() {
    return "SensorData{" +
        "timestamp='" + timestamp + '\'' +
        ", type='" + sensortype + '\'' +
        ", value='" + value + '\'' +
        ", stationName='" + stationName + '\'' +
        '}';
  }

  public String getSensortype() {
    return sensortype;
  }
}