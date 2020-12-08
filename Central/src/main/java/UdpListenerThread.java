import java.io.*;
import java.net.SocketException;

public class UdpListenerThread extends UdpUnicastClient implements Runnable {
  private final String stationName;

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
      File fi = new File("sensorValues.csv");
      FileWriter writer = new FileWriter(fi);

      writer.append(msg + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

