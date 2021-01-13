import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TException;
import org.bson.Document;
import rpc_generated.SensorService;

public class RpcController {
  public void perform(SensorService.Client client, MongoCollection<Document> collection) throws TException, InterruptedException {
    Map<Integer, String> sensorValues;

    System.out.println("ServiceClientStarted");
    String msg;
    Gson g = new Gson();
    while (true) {
      Thread.sleep(1000);
      sensorValues = client.getValues();
      for (Map.Entry<Integer, String> entry : sensorValues.entrySet()) {
        msg = entry.getValue();
        writeMessageToCsv(msg);

        SensorData sensorData;
        sensorData = g.fromJson(msg, SensorData.class);
        System.out.println(sensorData.toString());
        collection.insertOne(sensorData.createMongoDocument());
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
