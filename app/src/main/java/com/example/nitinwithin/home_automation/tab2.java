package com.example.nitinwithin.home_automation;


import android.graphics.Color;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;


public class tab2 extends Fragment {

    ImageButton sendbtn2;
    MQTT mqtt = new MQTT();
    boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        sendbtn2 = rootView.findViewById(R.id.sendmsg2);
        sendbtnclick();

        return rootView;
    }

    public void sendbtnclick()
    {

        sendbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag)
                {
                    mqtt.publishData(getActivity(),"ON2","1");
                    switchColor("ON");
                    flag = false;
                }
                else
                {
                    mqtt.publishData(getActivity(), "OFF2","1");
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
            sendbtn2.setImageResource(R.drawable.ic_switchoff);
            sendbtn2.setBackgroundColor(Color.TRANSPARENT);
        }
        else if(on.equals("ON"))
        {
            sendbtn2.setImageResource(R.drawable.ic_switchon);
            sendbtn2.setBackgroundColor(Color.TRANSPARENT);

        }
    }
}


