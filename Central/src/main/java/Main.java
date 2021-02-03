import http.HttpServer;


public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("Station started");
    Station s = new Station();
    HttpServer hs = new HttpServer();
    try {
      s.setStationname(args[0]);
      s.setNgixIp(args[1]);
      s.setNgixPort(Integer.parseInt(args[2]));
      s.setMqttConnection(args[3]);
      String topic = args[4];
      topic = topic.replaceAll("[^A-Za-z0-9_:/-]*$","");
      s.setMqttTopic(topic);
    } catch (ArrayIndexOutOfBoundsException e) {
      s.setStationname("Generic CentralStation");
      System.out.println("Default to Generic Central Station with 1 Sensor at :51020 and REST API at :8080");
    }catch (Exception x) {
      x.printStackTrace();
    }
    s.init();
    hs.launch();
  }

  }

