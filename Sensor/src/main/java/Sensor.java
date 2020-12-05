import java.io.IOException;
import java.util.Random;

class Sensor {

  // params
  public String ip;
  public int port;
  public String sensortype;

  // udp
  public UdpUnicastServer udp;

  public void run() {
    Random rand = new Random();
    double index = 0;
    int rain = 0;
    while(true) {
      try {
        index++;
        index = index % 100; //max 500
        Thread.sleep(1000); //wait for one second
        int sensorValue = 0;
        System.out.println("My Type is "+ this.sensortype);
        System.out.println(this.sensortype.equals("Fuel_Level"));

        String switchstr = this.sensortype.substring(0,this.sensortype.length()-1);

        switch(switchstr) {
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

        udp.sendMessage("[" + this.sensortype + "] " + Integer.toString(sensorValue));
        System.out.println(index + " " + sensorValue);

      } catch (InterruptedException ex) {
        return;
      } catch (IOException ex) {
        return;
      }
    }
  }

}