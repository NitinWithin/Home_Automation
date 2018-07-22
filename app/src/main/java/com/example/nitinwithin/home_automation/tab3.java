package com.example.nitinwithin.home_automation;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

public class tab3 extends Fragment {

    ImageButton sendbtn2;
    MQTT mqtt = new MQTT();
    boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);
        sendbtn2 = rootView.findViewById(R.id.sendmsg3);
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
                    mqtt.publishData(getActivity(),"ON3","1");
                    switchColor("ON");
                    flag = false;
                }
                else
                {
                    mqtt.publishData(getActivity(), "OFF3","1");
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


