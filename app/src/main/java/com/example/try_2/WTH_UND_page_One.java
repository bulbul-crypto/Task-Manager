package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WTH_UND_page_One extends AppCompatActivity {

    Button btn_nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wth__und_page__one);

        Toolbar ab_tsp_three = findViewById(R.id.wth_und_one);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("Health Tips");

        btn_nxt = findViewById(R.id.button_und_1);

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WTH_UND_page_One.this,WTH_UND_page_Two.class);
                startActivity(intent);
            }
        });
    }
}
