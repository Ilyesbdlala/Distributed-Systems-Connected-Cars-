import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;

public class UdpListenerThread extends UdpUnicastClient implements Runnable {
  private SensorData _sensorData;
  private String _stationName;

  public UdpListenerThread(int port, String stationName) throws SocketException {
    super(port);
    this._stationName = stationName;
  }

  @Override
  public void run() {
    try {
      this.listen();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void receivedMessage(String msg, int port, String ip) {
    //log to console
    System.out.print(msg + "\nPort: " + port + "\nIp: " + ip + "\n");
    this._sensorData = new SensorData(msg);
    writeMessageToCsv(msg);
    writeValueToJson();
  }

  private void writeMessageToCsv(String msg) {
    try {
      // Sensor Data History
      FileWriter fw = new FileWriter("sensors", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(msg);
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeValueToJson() {
    try {
      String json = this._sensorData.toJsonString();

     /*
      SensorData s = new SensorData(timestamp,sensorType,value);
      Gson g = new Gson();
      String json = g.toJson(s);
      */

      FileWriter ww = new FileWriter("sensor/" + _sensorData.type);
      BufferedWriter cw = new BufferedWriter(ww);
      cw.write(json);
      cw.newLine();
      cw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

