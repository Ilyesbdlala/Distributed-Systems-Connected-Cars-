package com.mypackage;

import java.io.IOException;
import java.net.*;

/**
 * Created by dan.geabunea on 6/3/2016.
 */
public class UdpUnicastServer implements Runnable {
  /**
   * The port where the client is listening.
   */
  private final int clientPort;

  public UdpUnicastServer(int clientPort) {
    this.clientPort = clientPort;
  }

  @Override
  public void run() {
    /**
     * Create a new server socket and bind it to a free port. I have chosen
     * one in the 49152 - 65535 range, which are allocated for internal applications
     */
    try (DatagramSocket serverSocket = new DatagramSocket(50000)) {
      // The server will generate 3 messages and send them to the client
      while(true) {
        String message = "Ping over Port " + Integer.toString(clientPort);
        DatagramPacket datagramPacket = new DatagramPacket(
          message.getBytes(),
          message.length(),
          InetAddress.getLocalHost(),
          clientPort
        );
        serverSocket.send(datagramPacket);
        System.out.print("Sent: " + message + "\n");
        Thread.sleep(1000);
      }
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}