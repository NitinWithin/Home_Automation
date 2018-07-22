package com.example.nitinwithin.home_automation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class tab1 extends Fragment {

    ImageButton sendbtn;
    String data;
    MQTT mqtt = new MQTT();
    boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View tab1view = inflater.inflate(R.layout.tab1, container, false);
        sendbtn = tab1view.findViewById(R.id.sendmsg);

        sendbtnclick();
        return tab1view;
    }

    public void sendbtnclick()
    {
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag)
                {
                    mqtt.publishData(getActivity(),"ON1","1");
                    switchColor("ON");
                    flag = false;
                }
                else
                {
                    mqtt.publishData(getActivity(), "OFF1","1");
                    switchColor("OFF");
                    flag = true;
                }
            }
        });
    }

    private void switchColor(String on)
    {
        if(on.equals("OFF"))
        {
            sendbtn.setImageResource(R.drawable.ic_switchoff);
            sendbtn.setBackgroundColor(Color.TRANSPARENT);
        }
        else if(on.equals("ON"))
        {
            sendbtn.setImageResource(R.drawable.ic_switchon);
            sendbtn.setBackgroundColor(Color.TRANSPARENT);

        }
    }
}

