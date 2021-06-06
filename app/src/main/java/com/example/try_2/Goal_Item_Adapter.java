package com.example.try_2;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Goal_Item_Adapter extends RecyclerView.Adapter<Goal_Item_Adapter.ViewHolder> {
    private ArrayList<Goal_Item> mygoals;
    private DatabaseHandlerForGoals db;
    private Goal_Activity_Page_1 activity;
    private int completed_task=0,attempted_task=0;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goals_layout,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Goal_Item_Adapter.ViewHolder holder, int i) {
        db.openDatabase();
        final Goal_Item item = mygoals.get(i);

        holder.goals.setText(item.getTask());
        holder.goals.setChecked(toBoolean(item.getStatus()));
        holder.goals.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                    completed_task++;
                } else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }
    private boolean toBoolean(int n) {
        return n != 0;
    }


    public void setTasks(ArrayList<Goal_Item> mygoals) {
        this.mygoals = mygoals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mygoals.size();
    }

    public Goal_Item_Adapter(ArrayList<Goal_Item> mygoals,DatabaseHandlerForGoals db,Goal_Activity_Page_1 activity)
    {
        this.mygoals = mygoals;
        this.db = db;
        this.activity = activity;
    }

    public Goal_Item_Adapter (DatabaseHandlerForGoals db,Goal_Activity_Page_1 activity){
        this.db=db;
        this.activity = activity;
    }
    public void deleteItem(int position) {
        Goal_Item item = mygoals.get(position);
        db.deleteTask(item.getId());
        mygoals.remove(position);
        notifyItemRemoved(position);
        completed_task--;
    }
    public Context getContext() {
        return activity;
    }
    public void editItem(int position) {
        Goal_Item item = mygoals.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("goal", item.getTask());
        AddGoals fragment = new AddGoals();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddGoals.TAG);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CheckBox goals;
        public ViewHolder(View view)
        {
            super(view);
            goals = view.findViewById(R.id.checkbox_goals);
        }
    }
    public int getCompleted_task()
    {
        return this.completed_task;
    }
    public int getAttempted_task()
    {
        if(mygoals.size()==0)
        {
            return 0;
        }
        else {
            return this.attempted_task;
        }
    }
}
