import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import rpc_generated.SensorService;

public class Main {
    public static RpcController _controller = new RpcController();

    public static void main(String [] args) throws InterruptedException {
        Thread.sleep(1000);
        try {
            TTransport transport;

            //String ip = args[0];

            //String portString = args[1];

            //int port = Integer.parseInt(portString.substring(1,portString.length()-1));
            transport = new TSocket("centralstation", 56565);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SensorService.Client client = new SensorService.Client(protocol);

            _controller.perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    /*private static void perform(SensorService.Client client) throws TException, InterruptedException {

        System.out.println("ServiceClientStarted");
        while (true) {
            Thread.sleep(1000);
            Map<Integer, String> product = client.getValues();
            for (Map.Entry<Integer, String> entry : product.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }
        }
    }*/

    /*private void writeMessageToCsv(String msg) {
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
    }*/
}