package com.example.androidpersonalbudget;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpersonalbudget.asyncTask.AsyncTaskRunner;
import com.example.androidpersonalbudget.asyncTask.Callback;
import com.example.androidpersonalbudget.network.HttpManager;

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

        html+=appendRows(jsonData);

        html+="</ul>";

        webview.loadData("<html><body>"+html+"</body></html>","text/html; charset=utf-8","UTF-8");
    }

    private String appendRows(JSONObject json){
        String html="";

        Iterator<String> keys=json.keys();
        while(keys.hasNext()){
            String key=keys.next();

            html+="<li>";
                html+="<span style='font-weight:bold;'>"+key+":</span>";

                try {
                    if (json.get(key) instanceof JSONObject)
                        html += "<ul>" + appendRows((JSONObject) json.get(key)) + "</ul>";
                    else html+="<span>"+json.get(key).toString()+"LEI</span>";
                }catch (Exception e){
                    e.printStackTrace();
                }

            html+="</li>";
        }

        return html;
    }
}
