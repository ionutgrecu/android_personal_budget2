package com.example.androidpersonalbudget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidpersonalbudget.asyncTask.Callback;
import com.example.androidpersonalbudget.database.models.Outgoing;
import com.example.androidpersonalbudget.database.service.OutgoingService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;

    private List<Outgoing> outgoings=new ArrayList<>();
    private OutgoingService outgoingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,add.class);
                startActivityForResult(intent,1);
            }
        });

        outgoingService.getAll(getAllDbCallback());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){

            }
        }
    }

    private Callback<List<Outgoing>> getAllDbCallback(){
        return new Callback<List<Outgoing>>() {
            @Override
            public void runResultOnUiThread(List<Outgoing> result) {
                if(result!=null){
                    outgoings.clear();
                    outgoings.addAll(result);
//                    notifyAdapter();
                }
            }
        };
    }
}