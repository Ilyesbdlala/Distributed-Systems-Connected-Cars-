import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {

    // MQTT
    String broker = "tcp://mosquitto:1883";
    String topic = "hda/group_e_9/VS";
    MqttClient client;


    public void init() throws MqttException {
        // Create some MQTT connection options.
        MqttConnectOptions mqttConnectOpts = new MqttConnectOptions();
        mqttConnectOpts.setCleanSession(true);
        try {

            client = new MqttClient(broker, MqttClient.generateClientId());

            // Connect to the MQTT broker using the connection options.
            try {
                client.connect(mqttConnectOpts);
                System.out.println("Connected to " + broker);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        } catch (MqttException e) {
            System.out.println("An error occurred: " + e.getMessage());
            // Disconnect from the MQTT broker.
            client.disconnect();
        }
    }

        public void sendMessage(String messageString) throws MqttException {


        // Create the message and set a quality-of-service parameter.
        MqttMessage message = new MqttMessage(messageString.getBytes());
        message.setQos(2); //  2 : Exactly once

        // Publish the message.
        client.publish(topic, message);
        // Exit the app explicitly.
        // System.exit(0);

    }
}
