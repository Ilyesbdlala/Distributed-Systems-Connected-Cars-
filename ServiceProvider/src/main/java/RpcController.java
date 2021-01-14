import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.thrift.TException;
import org.bson.Document;
import rpc_generated.SensorService;

public class RpcController implements SensorService.Iface {
  private SensorData sensorData;
  private MongoDatabase db;

  public RpcController(MongoDatabase database) {
    db = database;
  }

  @Override
  public boolean getValues(String value) throws TException {
    Gson g = new Gson();
    System.out.println(value);

    writeMessageToCsv(value);

    sensorData = g.fromJson(value, SensorData.class);

    MongoCollection<Document> collection = db.getCollection(sensorData.getSensortype());

    collection.insertOne(sensorData.createMongoDocument());

    return true;
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
