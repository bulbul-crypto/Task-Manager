package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TS_Page_Three extends AppCompatActivity {

    LinearLayout layoutList;
    FloatingActionButton task_add_btn,task_schedule_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts__page__three);

        Toolbar ab_tsp_three = findViewById(R.id.ab_ts_three);
        setSupportActionBar(ab_tsp_three);
        getSupportActionBar().setTitle("Task Scheduler");

        task_add_btn = findViewById(R.id.task_add_btn);
        task_schedule_btn = findViewById(R.id.task_schedule_btn);
        layoutList = findViewById(R.id.lin_lay_in_hsv);

        task_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addView();
            }
        });

        task_schedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfValid(view)){

                    Intent intent = new Intent(TS_Page_Three.this,TS_Page_Two.class);
                            /*Bundle bundle = new Bundle();
                            bundle.putSerializable("list",Constant.getArraylist());
                            intent.putExtras(bundle);*/
                    startActivity(intent);
                }
            }
        });
    }

    //-->>
    private boolean checkIfValid(View view){

        Constant.getArraylist().clear();

        boolean res = true;

        for(int i=0;i<layoutList.getChildCount();i+=2){

            View taskView = layoutList.getChildAt(i);

            EditText task_name_et = (EditText)taskView.findViewById(R.id.et_tn);
            EditText start_time_et = (EditText)taskView.findViewById(R.id.et_strt);
            EditText finish_time_et = (EditText)taskView.findViewById(R.id.et_fnsh);
            Button imageClose = (Button)taskView.findViewById(R.id.image_remove);

            Task taskElement = new Task();

            String tn,st,fn;

            tn=task_name_et.getText().toString();
            st=start_time_et.getText().toString();
            fn=finish_time_et.getText().toString();

            if(!tn.equals("")){

                taskElement.setTaskName(task_name_et.getText().toString());

            }else {
                res = false;
                break;
            }

            if(!st.equals("")){

                taskElement.setStartTime(Integer.parseInt(start_time_et.getText().toString()));

            }else {
                res = false;
                break;
            }

            if(!fn.equals("")){

                taskElement.setFinishTime(Integer.parseInt(finish_time_et.getText().toString()));

            }else {
                res = false;
                break;
            }

            Constant.getArraylist().add(taskElement);

        }

        if(Constant.getArraylist().size()==0){
            res = false;
            //Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "The task list is empty. Add tasks First!", Snackbar.LENGTH_LONG)
                    .setAction("Empty list", null).show();
        }
        else if(!res){

            //Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "Enter All Details Correctly!", Snackbar.LENGTH_LONG)
                    .setAction("Wrong details", null).show();
        }


        return res;
    }

    private void addView() {

        final View taskView = getLayoutInflater().inflate(R.layout.row_add_task_2,null,false);

        final View spaceView = getLayoutInflater().inflate(R.layout.space_horizontal_rat,null,false);

        //LinearLayout ll_rat = (LinearLayout)taskView.findViewById(R.id.rat_lin_layout);
        EditText task_name_et = (EditText)taskView.findViewById(R.id.et_tn);
        EditText start_time_et = (EditText)taskView.findViewById(R.id.et_strt);
        EditText finish_time_et = (EditText)taskView.findViewById(R.id.et_fnsh);
        Button imageClose = (Button)taskView.findViewById(R.id.image_remove);
        ConstraintLayout cns1 = (ConstraintLayout)taskView.findViewById(R.id.rat_main);



        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeView(taskView);
                removeView(spaceView);
            }
        });

        View space = (View)spaceView.findViewById(R.id.space_rat);

        layoutList.addView(taskView);
        layoutList.addView(spaceView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }


}
