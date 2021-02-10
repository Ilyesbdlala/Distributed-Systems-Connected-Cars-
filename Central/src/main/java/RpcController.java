import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import rpc_generated.SensorService;
import rpc_generated.SensorService.Client;
import rpc_generated.SensorValues;


public abstract class RpcController {

  private static volatile TTransport transport;
  private static volatile SensorService.Client client;

  public static void connect(String nginxIp, int nginxPort) throws TTransportException {
    transport = new TSocket(nginxIp, nginxPort);
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);
    client = new SensorService.Client(protocol);
  }

  public static TTransport getTransport() {
    return transport;
  }

  public static Client getClient() {
    return client;
  }

  public static void close() {
    transport.close();
  }

  public static boolean perform(SensorValues values) throws TException {
    return client.sendValues(values);
  }

  public static void rebuildConnection() throws TTransportException {
    transport.open();
    TProtocol protocol = new TBinaryProtocol(transport);
    client = new SensorService.Client(protocol);
  }

}