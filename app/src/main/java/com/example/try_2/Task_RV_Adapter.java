package com.example.try_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Task_RV_Adapter extends RecyclerView.Adapter<Task_RV_Adapter.TaskViewHolder> {

    private Context mContext ;
    private ArrayList<Task> mData ;

    public Task_RV_Adapter(Context mContext, ArrayList<Task> mData) {

        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.row_task_details,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {    ///TBA

        holder.tv_tn.setText(mData.get(position).getTaskName());
        final String tmp1 = Integer.toString((mData.get(position).getStartTime()));
        final String tmp2 = Integer.toString((mData.get(position).getFinishTime()));
        String tmp3 = " --- ";
        holder.tv_dur.setText(tmp1+tmp3+tmp2);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,TS_Page_Four.class);

                // passing data to the book activity
                intent.putExtra("Task Name",mData.get(position).getTaskName());
                intent.putExtra("Start",tmp1);
                intent.putExtra("End",tmp2);
                // start the activity
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() { ///TBA

        return mData.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView tv_tn,tv_dur;
        CardView cardView ;

        public TaskViewHolder (View itemView){

            super(itemView);

            tv_tn = (TextView) itemView.findViewById(R.id.rtd_tn);
            tv_dur = (TextView) itemView.findViewById(R.id.rtd_dur);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }

}
