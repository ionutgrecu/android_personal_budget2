package com.example.androidpersonalbudget;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpersonalbudget.database.models.Outgoing;
import com.example.androidpersonalbudget.util.ChartView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class chart extends AppCompatActivity {
    public static final String OUTGOINGS_KEY = "outgoingsKey";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.intent=getIntent();

        setTitle(getString(R.string.outgoing_status));

        List<Outgoing> outgoings=(List<Outgoing>) getIntent().getSerializableExtra(OUTGOINGS_KEY);
        setContentView(new ChartView(getApplicationContext(),getSource(outgoings)));
    }

    private Map<String, Integer> getSource(List<Outgoing> outgoings) {
        if (null==outgoings || outgoings.isEmpty()) return null;

        Map<String, Integer> source = new HashMap<>();
        for (Outgoing outgoing : outgoings) {
            if (source.containsKey(outgoing.getCategory())) {
                Integer value = source.get(outgoing.getCategory());
                Integer newValue = (value != null ? value : 0);
                source.put(outgoing.getCategory(), newValue);
            } else {
                source.put(outgoing.getCategory(), 1);
            }
        }
        return source;
    }
}
