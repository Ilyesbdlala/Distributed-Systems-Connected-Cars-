import java.io.File;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("Station started");
    //init args
    Station s = new Station();
    HttpServer hs = new HttpServer();
    try {
      s.stationname = args[0];
      s.sensorcount = Integer.parseInt(args[1]);
      s.sensorstartport = Integer.parseInt(args[2]);
    } catch (ArrayIndexOutOfBoundsException e) {
      s.stationname = "Generic CentralStation";
      s.sensorcount = 4;
      s.sensorstartport = 51020;
      s.apiport = 8080;
      //s.rpcip = "127.0.0.1";
      s.rpcport = 51030;
      System.out.println("Default to Generic Wheaterstation with 1 Sensor at :51020 and REST API at :8080");
    }
    s.init();
    hs.launch();
  }
}
