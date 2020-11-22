package com.mypackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    int port = 50001;
    UdpUnicastServer server = new UdpUnicastServer(port);

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    executorService.submit(server);
  }
}