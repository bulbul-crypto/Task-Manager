package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TS_Page_One extends AppCompatActivity {

    String task_name;
    int start_time,finish_time;

    EditText task_name_et,start_time_et,finish_time_et;

    //ArrayList<Task> arraylist = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts__page__one);


        task_name_et = findViewById(R.id.et_task_name);
        start_time_et = findViewById(R.id.et_start_time);
        finish_time_et = findViewById(R.id.et_finishing_time);

        FloatingActionButton add_task_button = findViewById(R.id.task_add_btn);
        FloatingActionButton schedule_task_button = findViewById(R.id.task_schedule_btn);


        add_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                task_name = task_name_et.getText().toString();
                start_time = Integer.parseInt((start_time_et.getText().toString()));
                finish_time = Integer.parseInt((finish_time_et.getText().toString()));

                Constant.getArraylist().add(new Task(start_time, task_name, finish_time));

                Snackbar.make(view, "This task has been successfully added.", Snackbar.LENGTH_LONG)
                        .setAction("Added", null).show();

                task_name_et.setHint("Task Name");
                start_time_et.setHint("Start Time");
                finish_time_et.setHint("Finishing Time");

            }
        });

        schedule_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TS_Page_One.this,TS_Page_Two.class);
                startActivity(intent);
            }
        });


    }
}
