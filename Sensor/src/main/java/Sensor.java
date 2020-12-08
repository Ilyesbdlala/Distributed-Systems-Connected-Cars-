import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

class Sensor {

  // params
  public String ip;
  public int port;
  public String sensortype;
  public String timeStamp;

  // udp
  public UdpUnicastServer udp;

  public void run() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    Random rand = new Random();
    double index = 0;
    int rain = 0;
    while(true) {
      try {
        index++;
        index = index % 100; //max 500
        Thread.sleep(1000); //wait for one second
        int sensorValue = 0;

        //String switchstr = this.sensortype.substring(0,this.sensortype.length()-1); //Remove

        switch(this.sensortype) {
          case "Fuel_Level": {
            sensorValue = rand.nextInt(100);
            break;
          }
          case "TravelDistance":{
            sensorValue = rand.nextInt(100000);
            break;
          }
          case "TrafficState": {
            sensorValue = rand.nextInt(3);
            break;
          }
          case "AvgSpeed": {
            sensorValue = rand.nextInt(200);
            break;
          }
          default:
            sensorValue = -1;
        }
        LocalDateTime now = LocalDateTime.now();
        timeStamp = dtf.format(now);

        udp.sendMessage(this.timeStamp + "," + this.sensortype + "," + sensorValue);
        //System.out.println(index + " " + sensorValue);

      } catch (InterruptedException ex) {
        return;
      } catch (IOException ex) {
        return;
      }
    }
  }

}