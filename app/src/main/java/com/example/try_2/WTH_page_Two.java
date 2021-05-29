
// This file is part of Health Tips Module

// Watch files with 'WTH' prefix for related infos


package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WTH_page_Two extends AppCompatActivity {

    TextView tv_bmi_fld,tv_bmi_lvl_fld;
    Button btn_shtp;
    float bmi;
    String sbmi,sbmi_lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wth_page__two);

        Toolbar ab_tsp_three = findViewById(R.id.ab_wth_two);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("BMI value");

        tv_bmi_fld = findViewById(R.id.bmi_fld);
        tv_bmi_lvl_fld = findViewById(R.id.bmi_lvl_fld);
        btn_shtp = findViewById(R.id.button_shtp);

        bmi = Constant.getBmi_value();
        //sbmi = Float.toString(bmi);
        sbmi = String.format("%.2f",bmi);
        tv_bmi_fld.setText(sbmi);

        if(bmi<18.5){

            sbmi_lvl = "You are UnderWeight";
            tv_bmi_lvl_fld.setText(sbmi_lvl);
        }
        else if(bmi>=18.5 && bmi<25.0){

            sbmi_lvl = "You are Healthy";
            tv_bmi_lvl_fld.setText(sbmi_lvl);
        }
        else if(bmi>=25.0 && bmi<30){

            sbmi_lvl = "You are OverWeight";
            tv_bmi_lvl_fld.setText(sbmi_lvl);
        }
        else{

            sbmi_lvl = "You are Obese";
            tv_bmi_lvl_fld.setText(sbmi_lvl);

        }

        btn_shtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bmi<18.5){

                    Intent intent = new Intent(WTH_page_Two.this,WTH_UND_page_One.class);
                    startActivity(intent);
                }
                else if(bmi>=18.5 && bmi<25.0){

                    Intent intent = new Intent(WTH_page_Two.this,WTH_HLTH_page_One.class);
                    startActivity(intent);

                }

            }
        });

    }
}
