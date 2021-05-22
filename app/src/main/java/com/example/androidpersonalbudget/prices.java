package com.example.androidpersonalbudget;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpersonalbudget.network.HttpManagerV2;

import org.json.JSONArray;
import org.json.JSONException;

public class prices extends AppCompatActivity {
    private WebView webview;
    public static final String PRICES_URL = "https://jsonkeeper.com/b/XCZH";

    private JSONArray jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        setTitle(getString(R.string.retrieve_online_prices));

        webview=findViewById(R.id.webview);

        getPrices();
    }

    private void getPrices(){
        Thread thread=new Thread(){
            @Override
            public void run(){
                final String result=new HttpManagerV2(PRICES_URL).process();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            jsonData = new JSONArray(result);
                            displayData();
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        thread.start();
    }

    private void displayData(){
        String html="<html><body>";
        html+="<ul>";

        for(int i=0;i<jsonData.length();i++){
            html+="<li>";
            html+=jsonData.getJSONArray(i).getString();
            html+="</li>";
        }
        html+="</ul>";

        webview.loadData("<html><body>"+html+"</body></html>","text/html; charset=utf-8","UTF-8");
    }
}
