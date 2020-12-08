import java.io.IOException;

public class Station {

  //params
  public String stationname;
  public int sensorcount;
  public int sensorstartport;
  public int apiport;
  //public String rpcip;
  public int rpcport;

  // udp
  public UdpListenerThread[] udplts;

  public void init() {

    this.udplts = new UdpListenerThread[this.sensorcount];

    int i = 0;
    for(UdpListenerThread udp : this.udplts) {
      try {
        udp = new UdpListenerThread(this.sensorstartport + i, this.stationname);
        Thread t = new Thread(udp);
        t.setName("Sensor:" + this.sensorstartport + i);
        t.start();
      } catch (IOException ex) {
        return;
      }
      i++;
    }

  }
}
