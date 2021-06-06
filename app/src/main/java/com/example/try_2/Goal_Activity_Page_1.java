package com.example.try_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Goal_Activity_Page_1 extends AppCompatActivity implements DialogCloseListener{
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mGoalAttempted;
    private String goalStat="";
    private ArrayList<Goal_Item> mygoals;
    private RecyclerView mRecylerview;
    private Goal_Item_Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mGoalCompleted;
    private FloatingActionButton addGoal;
    private FloatingActionButton statGoal;
    private Button statCloseBtn;
    private TextView mNoGoalView;
    private DatabaseHandlerForGoals db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
       View bottomSheet = findViewById(R.id.bs_goal);
        Toolbar goal_ab = findViewById(R.id.ab_goals);
        setSupportActionBar(goal_ab);
        getSupportActionBar().setTitle("Goals");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mGoalAttempted = findViewById(R.id.goal_tv_stat_attempt);
        mGoalCompleted = findViewById(R.id.goal_tv_stat_completed);
        addGoal = findViewById(R.id.button_add_goal);
        statGoal = findViewById(R.id.button_stat_goal);
        statCloseBtn =findViewById(R.id.btn_stat_close);
        db = new DatabaseHandlerForGoals(this);
        db.openDatabase();
        mNoGoalView=findViewById(R.id.tv_no_goals);

        mygoals = new ArrayList<>();
        if(mygoals.size()!=0){
            mNoGoalView.setVisibility(View.INVISIBLE);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Goal_Item_Adapter(db,Goal_Activity_Page_1.this);
        mRecylerview = findViewById(R.id.rv_goals);

        mRecylerview.setLayoutManager(mLayoutManager);
        mRecylerview.setAdapter(mAdapter);



        mygoals= db.getAllTasks();

        Collections.reverse(mygoals);
        mAdapter.setTasks(mygoals);
        goalStat+=mAdapter.getItemCount();
        //mGoalAttempted.setText(goalStat);
       // mGoalCompleted.setText(mAdapter.getCompleted_task());

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(new RecyclerItemTouchHelper(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecylerview);




        statGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGoals.newInstance().show(getSupportFragmentManager(),AddGoals.TAG);
            }
        });

        statCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged( View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Toast.makeText(getApplicationContext(),"Collapsed",Toast.LENGTH_LONG).show();
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Toast.makeText(getApplicationContext(),"Dragged",Toast.LENGTH_LONG).show();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Toast.makeText(getApplicationContext(),"expanded",Toast.LENGTH_LONG).show();
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Toast.makeText(getApplicationContext(),"Hidden",Toast.LENGTH_LONG).show();
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Toast.makeText(getApplicationContext(),"settling",Toast.LENGTH_LONG).show();
                        break;
                }

            }

            @Override
            public void onSlide( View view, float v) {
                Toast.makeText(getApplicationContext(),"sliding",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        mygoals= db.getAllTasks();
        Collections.reverse(mygoals);
        mAdapter.setTasks(mygoals);
        mAdapter.notifyDataSetChanged();
    }
}
