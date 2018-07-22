package com.example.nitinwithin.home_automation;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public  class HTTP{

    private String url;
    private RequestQueue mRequest;
    private StringRequest stringRequest;
    private String responsemsg;


    protected void sendrequestandresponse(final String data, final Context act) {

        url = "http://8dcccbaa.ngrok.io/server.php";
       // url = "http://f553db7e.ngrok.io";
        stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    /*SUCCESS LISTENER*/
                    responsemsg = response;
                    Toast.makeText(act, response, Toast.LENGTH_LONG).show();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                /*ERROE LISTENER*/
                responsemsg = error.toString();
                Toast.makeText(act, error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(data, data);
                return params;
            }
        };
        mRequest = Volley.newRequestQueue(act);
        mRequest.add(stringRequest);
        //return responsemsg;
    }



  /*  public String sendhttp(String uri) {
        try {

            url = new URL(uri);
            urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            return  reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
        return null;


    }*/
}
