import org.apache.thrift.TException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscriber {

    String broker;// = "tcp://mosquitto:1883";
    String topic; //= "hda/group_e_9/VS";
    PayloadHandler payloadHandler;
    RpcController _rpcHandler;

    public MqttSubscriber(String broker, String topic) {
        System.out.println("SensorBroker = " + broker + "Sensor Topic =" + topic);
        this.broker = broker;
        this.topic = topic;
    }

    public void setRpcHandler(RpcController rpc){
        _rpcHandler = rpc;
    }

    public void run(){

        try {
            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws TException {
                    String message = new String(mqttMessage.getPayload()) ;
                    System.out.println("Message received: "+ message );
                    SensorData sd = new SensorData(message);
                    payloadHandler = new PayloadHandler(sd);
                    payloadHandler.onReceiveMessage();

                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });
            // Connect to the MQTT broker.
            client.connect();
            System.out.println("Connected to " + broker);
            // Subscribe to a topic.
            client.subscribe(topic);
        } catch (MqttException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
}
