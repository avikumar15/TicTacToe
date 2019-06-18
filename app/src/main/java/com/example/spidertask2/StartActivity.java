package com.example.spidertask2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

        while(Integer.parseInt(tim[l])!=9999)
        {
            l++;
            least=Integer.parseInt(tim[l]);
        }
        z=l;
        for(l=z;((l<(z+DatabaseActivity.count)));l++)
        {
            if(Integer.parseInt(tim[l])<least && Integer.parseInt(tim[l])!=9999)
                least=Integer.parseInt(tim[l]);
        }

        timeis.setVisibility(View.INVISIBLE);
        timeis.setText("Best Time is "+least);
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
