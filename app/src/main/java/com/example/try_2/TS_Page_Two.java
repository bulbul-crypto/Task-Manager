package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TS_Page_Two extends AppCompatActivity {

    TextView tn1_tv,tn2_tv;
    ArrayList<Task> resultedArraylist;
    RecyclerView recyclerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts__page__two);

        Toolbar ab_tsp_two = findViewById(R.id.ab_ts_two);
        setSupportActionBar(ab_tsp_two);
        getSupportActionBar().setTitle("Task List");

        Collections.sort(Constant.getArraylist());

        int flag,rem,lft;

        flag=1;

        rem=0;

        resultedArraylist = new ArrayList<Task>();

        resultedArraylist.clear();

        for(Task str: Constant.getArraylist()){

            //System.out.println(str);

            if(flag==1){

                System.out.println(str);

                //tn1_tv.setText(str.getTaskName());

                resultedArraylist.add(str);

                rem=str.getFinishTime();

                flag=0;

            }
            else{

                lft=str.getStartTime();

                if(lft>=rem){

                    System.out.println(str);

                    //tn2_tv.setText(str.getTaskName());

                    resultedArraylist.add(str);

                    rem=str.getFinishTime();

                }
            }
        }

        /*RecyclerView myrv = (RecyclerView) findViewById(R.id.recycler_task);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);*/

        recyclerTask = findViewById(R.id.recycler_task);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerTask.setLayoutManager(layoutManager);

        recyclerTask.setAdapter(new Task_RV_Adapter(this,resultedArraylist));


    }

}
