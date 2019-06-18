package com.example.spidertask2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static float cx = 1f;
    public static float cy = 1f;

    public static float centerx=1f;
    public static float centery=1f;
    public static int count = 0;

    public static final String gamewona="afsd";

    FrameLayout lay;
    GameFrame struc;
    TextView tend;
    TextView winner;
    Button again;
    TextView timer;

    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        margaya();


    }


    public void margaya()
    {
        int ans[][]= {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};

        lay = (FrameLayout)findViewById(R.id.rel);
        tend = (TextView)findViewById(R.id.tp);
        winner = (TextView)findViewById(R.id.winner);
        again = (Button)findViewById(R.id.again);
        timer = (TextView)findViewById(R.id.timer);

        Intent intent = getIntent();
        k = intent.getIntExtra(StartActivity.EXTRA,0);

        struc = new GameFrame(this,tend,lay,winner,k,ans,timer,getApplicationContext());
        struc.again=again;

        tend.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);
        again.setVisibility(View.INVISIBLE);
        FrameLayout.LayoutParams vp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        struc.setLayoutParams(vp);

        System.out.println("Time is "+struc.minutes+":"+struc.seconds);

        lay.addView(struc);
    }

    public void again(View ww)
    {

        Intent inn = new Intent(this,StartActivity.class);
        inn.putExtra(gamewona,struc.gamewon);
        startActivity(inn);
        finish();
    }
}