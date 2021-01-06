package com.example.androidpersonalbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class add extends AppCompatActivity {
    private TextInputEditText tietSum;
    private TextInputEditText tietDescription;
    private CalendarView cvDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle(getString(R.string.add_outgoing));
        Intent intent=getIntent();

        tietSum=findViewById(R.id.tiet_sum);
        tietDescription=findViewById(R.id.tiet_description);
        cvDate=findViewById(R.id.cv_date);

        btnSave=findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strErr="";
                Boolean boolErr=false;

                if(tietSum.getText().toString().trim()==""){
                    strErr+=" - "+getString(R.string.add_error_sum)+"\n";
                    boolErr=true;
                }

                if(Integer.parseInt(tietSum.getText().toString().trim())<=0){
                    strErr+=" - "+getString(R.string.add_error_sum_invalid)+"\n";
                    boolErr=true;
                }

                if(tietDescription.getText().toString().trim()==""){
                    strErr+=" - "+getString(R.string.add_error_description)+"\n";
                    boolErr=true;
                }

                if(boolErr)
                    Toast.makeText(add.this, "Some errors occurred:"+"\n\n"+strErr, Toast.LENGTH_SHORT).show();
            }
        });
    }
}