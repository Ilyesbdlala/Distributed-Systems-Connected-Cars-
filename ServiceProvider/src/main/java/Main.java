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
        MongoClient mongoClient = new MongoClient("mongo", 27017);
        MongoDatabase database = mongoClient.getDatabase("vsdb");

        handler = new RpcController(database);
        processor = new SensorService.Processor(handler);

        Runnable simple = new Runnable() {
            public void run() {
                connect(processor);
            }
        };

        new Thread(simple).start();





            String ip = args[0];

            String portString = args[1];
            int port = Integer.parseInt(portString.replaceAll("\\D+",""));



    }

    public static void connect(SensorService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(56565);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            System.out.println("Starting RPC Server");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}