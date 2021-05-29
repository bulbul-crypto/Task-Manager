

// This file handles the module of calculating BMI and switch on to different activities according to that value to provide health tips


package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class WTH_Page_One extends AppCompatActivity {

    EditText weight_et,height_et;
    Button btn_cbmi;
    String wts,hts;
    float weight,height,bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wth__page__one);

        Toolbar ab_tsp_three = findViewById(R.id.ab_wth_one);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("BMI Calculator");

        weight_et = findViewById(R.id.et_weight);
        height_et = findViewById(R.id.et_height);
        btn_cbmi = findViewById(R.id.button_cbmi);

        btn_cbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wts = weight_et.getText().toString();
                hts = height_et.getText().toString();

                if(!(wts.equals("")) && !(hts.equals(""))){

                    weight = Float.parseFloat(wts);
                    height = Float.parseFloat(hts);

                    bmi = (weight/(height*height));

                    Constant.setBmi_value(bmi);

                    Intent intent = new Intent(WTH_Page_One.this,WTH_page_Two.class);
                    startActivity(intent);

                }
                else{

                    Snackbar.make(view, "Add height and weight values properly!", Snackbar.LENGTH_LONG)
                            .setAction("Empty list", null).show();
                }
            }
        });

    }
}
