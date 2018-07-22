package com.example.nitinwithin.home_automation;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import static android.content.ContentValues.TAG;

public class MQTT
{
    String clientId = MqttClient.generateClientId();
    MqttAndroidClient client = null;
    MqttConnectOptions options;
    IMqttToken token,token2;
    byte[] payload;
    String topic;

    public MQTT()
    {
        options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setPassword("password".toCharArray());// TODO : SET USERNAME AND PASSWORD FOR ADDED SECURITY
        options.setUserName("username");
    }

    public void publishData(final Context context, String msg, String deviceID) {
        try {
            client = new MqttAndroidClient(context, "tcp://xxx.xxx.xxx.xxx:1883",clientId);
            //MqttConnectOptions options = new MqttConnectOptions();
            topic = deviceID;
            final String finalMsg = msg;
            payload = msg.getBytes();
            //options.setWill(topic, payload ,1,false);
            token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess : "+finalMsg.substring(0,2));
                    //Toast.makeText(context,"Connection Success",Toast.LENGTH_LONG).show();
                    try
                    {
                        token2 = client.publish(topic,payload,1,false);
                        payload = new byte[0];
                    }
                    catch (MqttException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");
                    Toast.makeText(context,"Connection FAILED",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}