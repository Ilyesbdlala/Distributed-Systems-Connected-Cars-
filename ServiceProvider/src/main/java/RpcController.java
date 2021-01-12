import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.apache.thrift.TException;
import rpc_generated.SensorService;

public class RpcController {
  public void perform(SensorService.Client client) throws TException, InterruptedException {

    System.out.println("ServiceClientStarted");
    while (true) {
      Thread.sleep(1000);
      Map<Integer, String> product = client.getValues();
      for (Map.Entry<Integer, String> entry : product.entrySet()) {
        writeMessageToCsv(entry.getValue());
      }
    }
  }

  private void writeMessageToCsv(String msg) {
    try {
      // Sensor Data History
      FileWriter fw = new FileWriter("sensors", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(msg);
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
