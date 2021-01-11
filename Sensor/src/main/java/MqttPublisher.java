import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {

    // MQTT
    String broker = "tcp://test.mosquitto.org:1883";
    String topic = "hda/group_e_9/VS";

    public void sendMessage(String messageString){
    // Create some MQTT connection options.
    MqttConnectOptions mqttConnectOpts = new MqttConnectOptions();
        mqttConnectOpts.setCleanSession(true);
        try {

        MqttClient client = new MqttClient(broker, MqttClient.generateClientId());

        // Connect to the MQTT broker using the connection options.
        try {
            client.connect(mqttConnectOpts);
            System.out.println("Connected to " + broker);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        // Create the message and set a quality-of-service parameter.
        MqttMessage message = new MqttMessage(messageString.getBytes());
        message.setQos(2); //  2 : Exactly once

        // Publish the message.
        client.publish(topic, message);

        // Disconnect from the MQTT broker.
        client.disconnect();

        // Exit the app explicitly.
        // System.exit(0);


    } catch (MqttException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
    }
}
