public class Main {

  public static void main(String[] args) {

    Sensor s = new Sensor();
    try {

      s.ip = args[0];
      s.port = Integer.parseInt(args[1]);
      s.sensortype = args[2];
//        s.sensortype = s.sensortype.substring(0,s.sensortype.length()-1);


    } catch (ArrayIndexOutOfBoundsException e) {
      s.ip = "127.0.0.1";
      s.port = 51020;
      s.sensortype = "Generic Sensor";
      System.out.println("Defaulting to 127.0.0.1:51020 [Generic Sensor]");
    }

    System.out.println("Sensor started");
    s.mqttPub = new MqttPublisher();
    s.run();

  }

}
