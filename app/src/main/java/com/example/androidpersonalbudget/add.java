package com.example.androidpersonalbudget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidpersonalbudget.database.models.Outgoing;
import com.example.androidpersonalbudget.util.DateConverter;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Date;

public class add extends AppCompatActivity {
    public static final String OUTGOING_KEY = "outgoingKey";
    private TextInputEditText tietSum;
    private TextInputEditText tietDescription;
    private CalendarView cvDate;
    private Spinner spnCategory;
    private Button btnSave;

    private Intent intent;
    private Outgoing outgoing=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle(getString(R.string.add_outgoing));

        intent=getIntent();
        if(intent.hasExtra(OUTGOING_KEY)){
            outgoing=(Outgoing) intent.getSerializableExtra(OUTGOING_KEY);
            outgoingToView(outgoing);
        }

        tietSum=findViewById(R.id.tiet_sum);
        tietDescription=findViewById(R.id.tiet_description);
        cvDate=findViewById(R.id.cv_date);
        spnCategory=findViewById(R.id.spn_category);
        addCategoryAdapter();

        btnSave=findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strErr="";
                Boolean boolErr=false;

                if(tietSum.getText().toString().trim().isEmpty()){
                    strErr+=" - "+getString(R.string.add_error_sum)+"\n";
                    boolErr=true;
                }else if(Integer.parseInt(tietSum.getText().toString().trim())<=0){
                    strErr+=" - "+getString(R.string.add_error_sum_invalid)+"\n";
                    boolErr=true;
                }

                if(tietDescription.getText().toString().trim().isEmpty()){
                    strErr+=" - "+getString(R.string.add_error_description)+"\n";
                    boolErr=true;
                }

                if(boolErr)
                    Toast.makeText(add.this, getString(R.string.add_error)+"\n\n"+strErr, Toast.LENGTH_LONG).show();
                else{
                    if(null==outgoing)outgoing=new Outgoing();

                    outgoing.setAmount(Double.parseDouble(tietSum.getText().toString()));
                    outgoing.setDate(DateConverter.toDate(cvDate.getDate()));
                    outgoing.setDescription(tietDescription.getText().toString());

                    Intent resultIntent=new Intent();
                    resultIntent.putExtra(OUTGOING_KEY,outgoing);

                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }

    private void addCategoryAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.outgoing_category_values,
                android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapter);
    }

    private void outgoingToView(Outgoing outgoing){
        if(null==outgoing)return;

        if(null!=outgoing.getDate())cvDate.setDate(DateConverter.toTimestamp(outgoing.getDate()));
        if(null != outgoing.getAmount())tietSum.setText(String.valueOf(outgoing.getAmount()));
        tietDescription.setText(outgoing.getDescription());

        ArrayAdapter adapter=(ArrayAdapter) spnCategory.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            String item = (String) adapter.getItem(i);
            if (null!=item && item.equals(outgoing.getCategory())) {
                spnCategory.setSelection(i);
                break;
            }
        }
    }
}