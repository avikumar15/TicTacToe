package com.example.spidertask2;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.spidertask2.DatabaseActivity.tim;
import static com.example.spidertask2.GameFrame.MyPrefs;

public class StartActivity extends AppCompatActivity {
    public static final String EXTRA="hellogaiz";
    public static final String EXTRATIME="Timeismoney";
    public static final String EXTRAMODE="Modeis";

    static int i=100;
    int z;

    int minute,second;

    LinearLayout buttongroup;
    TextView timeis;
    public static int min=99999;
    public static int sec=99999;
    int count;
    int gamewonb;
    int least;
    int l=0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("min", 999);
        savedInstanceState.putInt("sec", 999);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_start);


        Intent i = getIntent();
        gamewonb=i.getIntExtra(MainActivity.gamewona,0);

        buttongroup = (LinearLayout)findViewById(R.id.buttongroup);

        timeis = (TextView)findViewById(R.id.best);
        buttongroup.setVisibility(View.INVISIBLE);


        SharedPreferences sharedPreferences2 = getSharedPreferences("MyPrefs2",MODE_PRIVATE);

        int ansans=sharedPreferences2.getInt("Best",-1);

        if(ansans==-1)
        timeis.setVisibility(View.INVISIBLE);
        else
        {
            timeis.setVisibility(View.VISIBLE);
            timeis.setText("Best Time is "+ansans+" secs");
        }
    }

    public void Single (View single)
    {
        buttongroup.setVisibility(View.VISIBLE);
    }


    public void Easy (View easy)
    {
        i=1;
        Intent intent = new Intent(StartActivity.this,MainActivity.class);                //intent
        intent.putExtra(EXTRA,i);
        startActivity(intent);
        finish();

    }

    public void Med (View med)
    {
        i=2;
        Intent intent = new Intent(StartActivity.this,MainActivity.class);               //intent
        intent.putExtra(EXTRA,i);
        startActivity(intent);
    }

    public void Hard (View hard)
    {
        i=3;
        Intent intent = new Intent(StartActivity.this,MainActivity.class);               //intent
        intent.putExtra(EXTRA,i);
        startActivity(intent);
    }

    public void Double (View doubble)
    {
        i=4;
        Intent intent = new Intent(StartActivity.this,MainActivity.class);               //intent
        intent.putExtra(EXTRA,i);
        startActivity(intent);
    }

    public void DATA (View dataaa)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        int total=-1;

        if(!(sharedPreferences.getInt("min",0)==minute && sharedPreferences.getInt("sec",0)==second))
        total = (sharedPreferences.getInt("min",0)*60 + sharedPreferences.getInt("sec",-1));

        minute=sharedPreferences.getInt("min",0);
        second=sharedPreferences.getInt("sec",-1);

        {
            Intent intentt = new Intent(StartActivity.this, DatabaseActivity.class);
            if(gamewonb==1) {
                intentt.putExtra(EXTRATIME, total);
                intentt.putExtra(EXTRAMODE, i);
            }
            startActivity(intentt);
        }
    }
}
