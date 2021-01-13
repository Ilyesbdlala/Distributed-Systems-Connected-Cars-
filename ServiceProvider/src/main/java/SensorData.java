import org.bson.Document;

public class SensorData {
  private String timestamp;
  private String sensortype;
  private String value;


  public SensorData(String timestamp, String type, String value) {
    this.timestamp = timestamp;
    this.sensortype = type;
    this.value = value;
  }

  public Document createMongoDocument(){
    Document doc = new Document("timestamp", timestamp)
        .append("type", sensortype)
        .append("value", value);
    return doc;
  }

  @Override
  public String toString() {
    return "SensorData{" +
        "timestamp='" + timestamp + '\'' +
        ", type='" + sensortype + '\'' +
        ", value='" + value + '\'' +
        '}';
  }
}