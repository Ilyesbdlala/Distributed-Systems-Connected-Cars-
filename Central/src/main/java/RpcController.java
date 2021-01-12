import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TException;



public class RpcController implements SensorService.Iface {
  private Map<Integer,String> sensorValues;

  public RpcController() {
    sensorValues = new HashMap<>();
  }

  public void setValue(SensorData sd){
    sensorValues.put(sd.getId(),sd.toJsonString());
  }

  @Override
  public Map<Integer, String> getValues() throws TException {
    return this.sensorValues;
  }
}