import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.thrift.TException;

public class PayloadHandler {
    SensorData _sensorData;

    PayloadHandler(SensorData s){
        _sensorData = s;
    }

    public void onReceiveMessage() throws TException {
        writeMessageToCsv(_sensorData.toString());
        writeValueToJson();
        if (!RpcController.perform(_sensorData.toJsonString()))System.out.println("Lost Connection to Rpc Server");
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

    private void writeValueToJson() {
        try {
            String json = this._sensorData.toJsonString();

            FileWriter ww = new FileWriter("sensor/" + _sensorData.getId());
            BufferedWriter cw = new BufferedWriter(ww);
            cw.write(json);
            cw.newLine();
            cw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
