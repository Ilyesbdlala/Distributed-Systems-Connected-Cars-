import java.io.IOException;
import java.net.SocketException;

public class UdpListenerThread extends UdpUnicastClient implements Runnable {


  private String stationname;

  public UdpListenerThread(int port, String stationname) throws SocketException{
    super(port);
    this.stationname = stationname;
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
    try {
      //log to console
      System.out.println(msg + ";" + port + ";" + ip);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }
}
