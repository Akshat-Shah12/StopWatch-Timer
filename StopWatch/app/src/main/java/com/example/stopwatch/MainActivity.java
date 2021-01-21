package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Long seconds=0L;
    Boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView)findViewById(R.id.timer);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getLong("seconds");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("seconds",seconds);

    }

    public void btnStart(View view){
        running=true;
    }
    public void btnPause(View view){
        running=false;
    }
    public void btnRefresh(View view){
        seconds=0L;
        running=false;
    }
    public void switchToTimer(View view){
        Intent i = new Intent();
        i.setClass(this,Timer.class);
        startActivity(i);
    }
    public void runTimer(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = (int)(seconds/3600);
                int minutes = (int)(seconds%3600)/60;
                int secs = (int)(seconds%60);
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timer.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}