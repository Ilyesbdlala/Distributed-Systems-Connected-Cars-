import java.io.IOException;
import java.net.*;

public class UdpUnicastServer{
  private final int port;
  private DatagramSocket socket;
  private InetAddress address;
  private String ip;

  public UdpUnicastServer(int port, String ip) {
    this.port = port;
    this.ip = ip;
  }

  public void sendMessage(String msg) throws IOException {
    this.socket = new DatagramSocket(this.port); //Bind socket to port
    this.address = InetAddress.getByName(ip);
    byte[] buffer = new byte[65507];
    buffer = msg.getBytes();
    DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, this.address, this.port);
    this.socket.send(datagramPacket);
    this.socket.close();
  }

}