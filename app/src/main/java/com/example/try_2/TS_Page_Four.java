package com.example.try_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class TS_Page_Four extends AppCompatActivity {

    TextView tn,st,fn;
    Button rem_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts__page__four);
        createNotificationChannel();

        Toolbar ab_tsp_four = findViewById(R.id.ab_ts_four);
        setSupportActionBar(ab_tsp_four);
        getSupportActionBar().setTitle("Task Details");

        tn=findViewById(R.id.textView61);
        st=findViewById(R.id.textView62);
        fn=findViewById(R.id.textView63);

        rem_btn = findViewById(R.id.btn_set_rem);

        // Recieve data
        Intent intent = getIntent();
        final String task_name = intent.getExtras().getString("Task Name");
        final String srt_time = intent.getExtras().getString("Start");
        String fn_time = intent.getExtras().getString("End");

        tn.setText(task_name);
        st.setText(srt_time);
        fn.setText(fn_time);

        final long stn = Integer.parseInt(srt_time);
        final int stn2 = Integer.parseInt(srt_time);

        rem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TS_Page_Four.this,ReminderBroadcast.class);
                //intent.putExtra("Task",task_name);
                //intent.putExtra("Begin",srt_time);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(TS_Page_Four.this,0,intent,0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                Constant.staticTask.setTaskName(task_name);
                Constant.staticTask.setStartTime(stn2);

                long timeAtButtonClicked = System.currentTimeMillis();
                long hr = timeAtButtonClicked/1000;
                System.out.println("Current time -> "+hr);
                long diff = stn-hr;
                System.out.println("Time Difference -> "+diff);
                long disp = 1000*10;

                alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClicked + disp,pendingIntent);

                Snackbar.make(view, "Reminder on this task has been set on.", Snackbar.LENGTH_LONG)
                        .setAction("Empty list", null).show();

            }
        });

    }

    public void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Task Reminder Channel";
            String description = "Channel for reminding task";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyTask",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}
