import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpUnicastClient{
  private final int port;
  private DatagramSocket socket;

  public UdpUnicastClient (int port) throws SocketException {
    this.port = port;
    this.socket = new DatagramSocket(this.port); //Bind socket to port
    socket.setSoTimeout(10000); //10 Second Timeout
  }

  public void listen() throws IOException {
    byte[] buffer = new byte[65507];
    while(true) {
      DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
      this.socket.receive(datagramPacket);

      this.receivedMessage(new String(datagramPacket.getData(), 0, datagramPacket.getLength()), this.port, datagramPacket.getAddress().toString());
    }
  }

  public void receivedMessage(String msg, int port, String ip) {
    System.out.println(msg);
  }
}