import http.HttpServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Main {
  public static RpcController handler;

  public static SensorService.Processor processor;

  public static void main(String[] args) throws Exception {
    System.out.println("Station started");
    //init args
    Station s = new Station();
    HttpServer hs = new HttpServer();
    try {
      s.stationname = args[0];
      s.sensorcount = Integer.parseInt(args[1]);
      s.sensorstartport = Integer.parseInt(args[2]);
      handler = new RpcController();
      processor = new SensorService.Processor(handler);

      Runnable simple = new Runnable() {
        public void run() {
          simple(processor);
        }
      };

      new Thread(simple).start();

    } catch (ArrayIndexOutOfBoundsException e) {
      s.stationname = "Generic CentralStation";
      s.sensorcount = 4;
      s.sensorstartport = 51020;
      s.apiport = 8080;
      //s.rpcip = "127.0.0.1";
      s.rpcport = 51030;
      System.out.println("Default to Generic Central Station with 1 Sensor at :51020 and REST API at :8080");
    }catch (Exception x) {
      x.printStackTrace();
    }
    s.init(handler);
    hs.launch();
  }

  public static void simple(SensorService.Processor processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(56565);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
      System.out.println("Starting RPC Server");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
