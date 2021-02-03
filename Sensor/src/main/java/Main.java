import java.lang.reflect.Array;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
  public static void main(String[] args) throws MqttException, InterruptedException {

    Sensor s = new Sensor();

    try {
//      String[] q = args;
//        for (String sd : args){
//        System.out.println(sd);
//      }
      s.ip = args[0];
      s.port = Integer.parseInt(args[1]);
      s.sensortype = args[2];
      String topic = args[4].replaceAll("[^A-Za-z0-9_:/-]*$","");
      s.mqttPub = new MqttPublisher(args[3], topic);
//      s.sensortype = s.sensortype.substring(0,s.sensortype.length()-1);


    } catch (ArrayIndexOutOfBoundsException e) {
      s.ip = "localhost";
      s.port = 51020;
      s.sensortype = "Fuel_Level";
      System.out.println("Defaulting to localhost:51020 [Fuel_Level]");
    }

    System.out.println("Sensor started");

    s.mqttPub.init();
    s.run();

  }

}
