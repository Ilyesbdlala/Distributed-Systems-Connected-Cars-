import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import rpc_generated.SensorService;

public class Main {

    public static RpcController handler;

    public static SensorService.Processor processor;


    public static void main(String [] args) throws InterruptedException {
        MongoClient mongoClient = new MongoClient(args[0], Integer.parseInt(args[1]));
        MongoDatabase database = mongoClient.getDatabase("vsdb");

        String portString = args[2];
        int port = Integer.parseInt(portString.replaceAll("\\D+",""));

        handler = new RpcController(database);
        processor = new SensorService.Processor(handler);

        Runnable simple = new Runnable() {
            public void run() {
                connect(processor, port);
            }
        };

        new Thread(simple).start();
    }

    public static void connect(SensorService.Processor processor, int port) {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            System.out.println("Starting RPC Server");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}