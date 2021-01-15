import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttException;

class Sensor {
  // params
  public String ip;
  public int port;
  public String sensortype;
  public String timeStamp;

  public MqttPublisher mqttPub ;

  public void run() throws MqttException, InterruptedException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    Random rand = new Random();

    double index = 0;

    int sensorValue = 0;
    while (true) {
        index++;
        index = index % 100; //max 500

      //String switchstr = this.sensortype.substring(0,this.sensortype.length()-1); //Remove

        switch (this.sensortype) {
          case "Fuel_Level": {
            sensorValue = rand.nextInt(100);
            Thread.sleep(5000);
            break;
          }
          case "TravelDistance": {
            sensorValue = rand.nextInt(100000);
            Thread.sleep(10000);
            break;
          }
          case "TrafficState": {
            sensorValue = rand.nextInt(3);
            Thread.sleep(30000);
            break;
          }
          case "AvgSpeed": {
            sensorValue = rand.nextInt(200);
            Thread.sleep(1000);
            break;
          }
          default:
            /*
             * On some Operating Systems, the sensorType gets
             * passed with '/n' as the last String char.
             *
             * If that happens to be the case, the Systems go into the default
             * Switch Case and the last char is then removed.
             * */
            this.sensortype = this.sensortype.substring(0, this.sensortype.length() - 1);
            continue;
            //sensorValue = -1;
        }

        LocalDateTime now = LocalDateTime.now();
        timeStamp = dtf.format(now);
        String messageString = this.port + "," + this.timeStamp + "," + this.sensortype + "," + sensorValue ;

        //Sending Message
        mqttPub.sendMessage(messageString);
    }
  }

}