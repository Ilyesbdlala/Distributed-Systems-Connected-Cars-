import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import com.google.gson.Gson;

public class UdpListenerThread extends UdpUnicastClient implements Runnable {
  private final String stationName;


   class SensorData {
    String timestamp;
    String type;
    String value;


     public SensorData(String timestamp, String type, String value) {
       this.timestamp = timestamp;
       this.type = type;
       this.value = value;
     }
   }

  public UdpListenerThread(int port, String stationname) throws SocketException{
    super(port);
    this.stationName = stationname;
  }

  @Override
  public void run() {
    try {
      this.listen();
    } catch (IOException ex) {
      return;
    }
  }

  @Override
  public void receivedMessage(String msg, int port, String ip) {
    //log to console
    System.out.print(msg + "\nPort: " + port + "\nIp: " + ip + "\n");
    writeToFile(msg);
  }

  private void writeToFile(String msg) {
    try {
      // Sensor Data History
      FileWriter fw = new FileWriter("sensors", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(msg);
      bw.newLine();
      bw.close();


      //Current Sensor Data
      String[] dataElements = msg.split(",");
      String timestamp = dataElements[0];
      String sensorType = dataElements[1];
      String value = dataElements[2];


      String json = "{\"Timestamp\":\""+timestamp+"\",\"Sensor_Type\":\"" + sensorType + "\",\"Value\":" + value + "}";


     /*
      SensorData s = new SensorData(timestamp,sensorType,value);
      Gson g = new Gson();
      String json = g.toJson(s);
      */

        FileWriter ww = new FileWriter("sensor/" + sensorType);
        BufferedWriter cw = new BufferedWriter(ww);
        cw.write(json);
        cw.newLine();
        cw.close();


    } catch (IOException e) {
      e.printStackTrace();
    }
  }






}

