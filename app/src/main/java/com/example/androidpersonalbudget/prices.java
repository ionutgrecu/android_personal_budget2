package com.example.androidpersonalbudget;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpersonalbudget.asyncTask.AsyncTaskRunner;
import com.example.androidpersonalbudget.asyncTask.Callback;
import com.example.androidpersonalbudget.network.HttpManager;
import com.example.androidpersonalbudget.network.HttpManagerV2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.Callable;

public class prices extends AppCompatActivity {
    private WebView webview;
    public static final String PRICES_URL = "https://jsonkeeper.com/b/XCZH";

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();
    private JSONObject jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        setTitle(getString(R.string.retrieve_online_prices));

        webview=findViewById(R.id.webview);

        getPrices();
    }

    private void getPrices(){
        Callable<String> asyncOperation = new HttpManager(PRICES_URL);
        Callback<String> mainThreadOperation = receivePricesFromHttp();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receivePricesFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                try {
                    jsonData = new JSONObject(result);
                    displayData();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
    }

    private void displayData(){
        String html="<html><body>";
        html+="<ul>";

        Iterator<String> keys=jsonData.keys();
        while(keys.hasNext()){
            String key=keys.next();

            html+="<li>";
            html+=key;

            html+="</li>";
        }
        html+="</ul>";

        webview.loadData("<html><body>"+html+"</body></html>","text/html; charset=utf-8","UTF-8");
    }
}
