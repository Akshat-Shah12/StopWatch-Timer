package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    ProgressBar hour;
    TextView hours,minutes,seconds,timer;
    Button b1,b2,b3,b4,b5,b6,dismiss,snooze,start,pause,returnTo;
    Long Seconds =0L;
    MediaPlayer themeSong;
    Boolean running2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        b1=(Button)findViewById(R.id.hourMinus);
        b2=(Button)findViewById(R.id.hourPlus);
        b3=(Button)findViewById(R.id.minutesMinus);
        b4=(Button)findViewById(R.id.minutesPlus);
        b5=(Button)findViewById(R.id.secondsMinus);
        b6=(Button)findViewById(R.id.secondsPlus);
        returnTo= (Button)findViewById(R.id.StopWatch);
        start=(Button)findViewById(R.id.button8);
        pause=(Button)findViewById(R.id.button9);
        dismiss=(Button)findViewById(R.id.dismissBtn);
        snooze=(Button)findViewById(R.id.snooze);
        hours=(TextView)findViewById(R.id.textHours);
        minutes=(TextView)findViewById(R.id.textMinutes);
        seconds=(TextView)findViewById(R.id.textSeconds);
        timer = (TextView)findViewById(R.id.timer2);
        themeSong = MediaPlayer.create(this,R.raw.theme);
        countSeconds();
        runTimer();
    }
    public void switchToStopWatch(View view){
        Intent i = new Intent();
        i.setClass(this,MainActivity.class);
        startActivity(i);
    }
    public void hourPlus(View view){
        int num = Integer.parseInt(hours.getText().toString());
        num++;
        if(num==100){
            num=0;
        }
        hours.setText(num+"");
        changed();
    }
    public void hourMinus(View view){
        int num = Integer.parseInt(hours.getText().toString());
        num--;
        if(num==-1){
            num=99;
        }
        hours.setText(num+"");
        changed();
    }
    public void minutesPlus(View view){
        int num = Integer.parseInt(minutes.getText().toString());
        num++;
        if(num==60){
            num=0;
        }
        if(num<10){
            minutes.setText("0"+num);
        }
        else {
            minutes.setText(num+"");
        }
        changed();
    }
    public void minutesMinus(View view){
        int num = Integer.parseInt(minutes.getText().toString());
        num--;
        if(num==-1){
            num=59;
        }
        if(num<10){
            minutes.setText("0"+num);
        }
        else{
            minutes.setText(num+"");}
        changed();
    }
    public void secondsPlus(View view){
        int num = Integer.parseInt(seconds.getText().toString());
        num++;
        if(num==60){
            num=0;
        }
        if(num<10){
            seconds.setText("0"+num);
        }
        else{
            seconds.setText(num+"");}
        changed();
    }
    public void secondsMinus(View view){
        int num = Integer.parseInt(seconds.getText().toString());
        num--;
        if(num==-1){
            num=59;
        }
        if(num<10){
            seconds.setText("0"+num);
        }
        else{
            hours.setText(num+"");
        }
        changed();
    }
    public void changed(){
        countSeconds();
        running2=false;
        int hoursInt = (int)(Seconds/3600);
        int minutesInt = (int)(Seconds%3600)/60;
        int secsInt = (int)(Seconds%60);
        String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hoursInt,minutesInt,secsInt);
        timer.setText(time);
    }
    public void dismissClicked(View view){
        showEverything();
    }
    public void showEverything(){
        hours.setVisibility(View.VISIBLE);
        seconds.setVisibility(View.VISIBLE);
        minutes.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        pause.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        returnTo.setVisibility(View.VISIBLE);
        dismiss.setVisibility(View.GONE);
        snooze.setVisibility(View.GONE);
        themeSong.pause();
    }
    public void offVisibility(){
        hours.setVisibility(View.GONE);
        seconds.setVisibility(View.GONE);
        minutes.setVisibility(View.GONE);
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        b4.setVisibility(View.GONE);
        b5.setVisibility(View.GONE);
        b6.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        returnTo.setVisibility(View.GONE);
        dismiss.setVisibility(View.VISIBLE);
        snooze.setVisibility(View.VISIBLE);
    }
    public void snoozeClicked(View view){
        running2=false;
        hours.setText("0");
        minutes.setText("05");
        seconds.setText("00");
        countSeconds();
        showEverything();
        running2=true;

    }
    public void runTimer(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hoursInt = (int)(Seconds/3600);
                int minutesInt = (int)(Seconds%3600)/60;
                int secsInt = (int)(Seconds%60);
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hoursInt,minutesInt,secsInt);
                timer.setText(time);
                if(running2){
                    Seconds--;
                }
                if(Seconds==0 && running2){
                    themeSong.start();
                    running2=false;
                    offVisibility();
                }
                handler.postDelayed(this,1000);
            }
        });
    }
    public void countSeconds(){
        Seconds=0L;
        Seconds+=Integer.parseInt(hours.getText().toString())*3600;
        Seconds+=Integer.parseInt(minutes.getText().toString())*60;
        Seconds+=Integer.parseInt(seconds.getText().toString());
    }
    public void startTimer(View view){
        running2=true;
    }
    public void pauseTimer(View view){
        running2=false;
    }


}