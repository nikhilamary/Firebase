package com.example.firebase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class datepicker extends AppCompatActivity  {

    private static final String TAG="datepicker";
    ArrayAdapter<CharSequence> adapter;

    Spinner spinner;
    Button set;

     TextView textView;
     DatePickerDialog.OnDateSetListener mdatesetlistner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        textView = (TextView) findViewById(R.id.textView);
        set=findViewById(R.id.button5);
        spinner=findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(this,R.array.Spinner,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Spinner= spinner.getSelectedItem().toString().trim();

                if(Spinner.equals("Wedding")){
                    Intent i=new Intent(getApplicationContext(),Wedding.class);

                    startActivity(i);
                }
                else if(Spinner.equals("Birthday")){
                    Intent ii=new Intent(getApplicationContext(),Birthday.class);
                    startActivity(ii);
                }
                else
                {
                    Intent iii=new Intent(getApplicationContext(),Party.class);
                    startActivity(iii);
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int year= c.get(Calendar.YEAR);
                int month= c.get(Calendar.MONTH);
                int day= c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        datepicker.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdatesetlistner,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdatesetlistner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                textView.setText(date);
            }
        };
    }
}
