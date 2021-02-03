import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import rpc_generated.SensorService;


public abstract class RpcController {

  private static TTransport transport;
  private static SensorService.Client client;

  public static void connect(String nginxIp, int nginxPort) throws TTransportException {
    transport = new TSocket(nginxIp, nginxPort);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);
    client = new SensorService.Client(protocol);
  }

  public void close() {
    transport.close();
  }

  public static boolean perform(String values) throws TException {
    return client.getValues(values);
  }

}