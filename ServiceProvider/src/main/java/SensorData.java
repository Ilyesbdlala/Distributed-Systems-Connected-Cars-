import org.bson.Document;
import rpc_generated.SensorValues;

public class SensorData {
  private String timestamp;
  private String sensortype;
  private String value;
  private String station;


  public SensorData(String timestamp, String type, String value, String stationName) {
    this.timestamp = timestamp;
    this.sensortype = type;
    this.value = value;
    this.station = stationName;
  }

  public SensorData(SensorValues values){
    timestamp = values.getTimeStamp();
    sensortype = values.getSensorType();
    value = values.getValue();
    station = values.getSationName();
  }

  public Document createMongoDocument(){
    Document doc = new Document("timestamp", timestamp)
        .append("type", sensortype)
        .append("value", value)
        .append("stationName", station);
    return doc;
  }

  @Override
  public String toString() {
    return "SensorData{" +
        "timestamp='" + timestamp + '\'' +
        ", type='" + sensortype + '\'' +
        ", value='" + value + '\'' +
        ", stationName='" + station + '\'' +
        '}';
  }

  public String getSensortype() {
    return sensortype;
  }

}