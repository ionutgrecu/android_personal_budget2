package com.example.androidpersonalbudget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidpersonalbudget.asyncTask.Callback;
import com.example.androidpersonalbudget.database.models.Outgoing;
import com.example.androidpersonalbudget.database.service.OutgoingService;
import com.example.androidpersonalbudget.util.OutgoingAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 1;
    public static final int REQUEST_CODE_EDIT = 2;
    private FloatingActionButton btnAdd;
    private ListView lvOutgoings;

    private List<Outgoing> outgoings = new ArrayList<>();
    private OutgoingService outgoingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        lvOutgoings=findViewById(R.id.lv_main_outgoings);
        lvOutgoings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), add.class);
                intent.putExtra(add.OUTGOING_KEY, outgoings.get(i));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        addAdapter();

        outgoingService = new OutgoingService(getApplicationContext());
        outgoingService.getAll(getAllDbCallback());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch(requestCode){
                case REQUEST_CODE_ADD:
                    Outgoing outgoing = (Outgoing) data.getSerializableExtra(add.OUTGOING_KEY);
                    outgoingService.insert(outgoing,insertDbCallback());
                    break;
            }
        }
    }

    private Callback<Outgoing> insertDbCallback(){
        return new Callback<Outgoing>() {
            @Override
            public void runResultOnUiThread(Outgoing result) {
                if(result!=null){
                    outgoings.add(result);
//                    notifyAdapter();
                }
            }
        };
    }

    private Callback<List<Outgoing>> getAllDbCallback() {
        return new Callback<List<Outgoing>>() {
            @Override
            public void runResultOnUiThread(List<Outgoing> result) {
                if (result != null) {
                    outgoings.clear();
                    outgoings.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private void addAdapter() {
        OutgoingAdapter adapter = new OutgoingAdapter(getApplicationContext(), R.layout.lv_expense_row,
                outgoings, getLayoutInflater());
        lvOutgoings.setAdapter(adapter);
    }

    private void notifyAdapter() {
        OutgoingAdapter adapter = (OutgoingAdapter) lvOutgoings.getAdapter();
        adapter.notifyDataSetChanged();
    }
}