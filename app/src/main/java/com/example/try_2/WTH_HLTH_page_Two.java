package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WTH_HLTH_page_Two extends AppCompatActivity {

    Button btn_nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wth__hlth_page__two);

        Toolbar ab_tsp_three = findViewById(R.id.wth_hlth_two);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("Health Tips");

        btn_nxt = findViewById(R.id.button_hlth_2);

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WTH_HLTH_page_Two.this,WTH_HLTH_page_Three.class);
                startActivity(intent);
            }
        });
    }
}
