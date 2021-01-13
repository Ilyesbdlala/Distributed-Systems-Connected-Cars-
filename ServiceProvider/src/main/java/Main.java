import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.bson.Document;
import rpc_generated.SensorService;

public class Main {
    public static RpcController _controller = new RpcController();


    public static void main(String [] args) throws InterruptedException {
        MongoClient mongoClient = new MongoClient("mongo", 27017);
        MongoDatabase database = mongoClient.getDatabase("vsdb");
        MongoCollection<Document> collection = database.getCollection("sensors");

        try {
            TTransport transport;

            String ip = args[0];

            String portString = args[1];
            int port = Integer.parseInt(portString.replaceAll("\\D+",""));

            transport = new TSocket(ip, port);//56565);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SensorService.Client client = new SensorService.Client(protocol);

            _controller.perform(client, collection);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}