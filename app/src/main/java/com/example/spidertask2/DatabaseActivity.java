package com.example.spidertask2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    LinearLayout layoutdata;
    LinearLayout table;
    LinearLayout[] column;
    TextView[][] t;
    TextView head;

    String[] srno={"HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE"};
    String[] diff = {"HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE","HELLO HOW ARE"};
    public static String[] tim={"9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999","9999"};
    int i,j;

    Button reset;
    Cursor res;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("count", 0);
    }

    Database myDb = new Database(this);

    int mode,sec;
    String modeis;

    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_database);

        Intent intentt = getIntent();
        mode = intentt.getIntExtra(StartActivity.EXTRAMODE,9);
        sec = intentt.getIntExtra(StartActivity.EXTRATIME,99999);


        switch(mode)
        {
            case 1 :
                modeis="EASY";
                break;

            case 2 :
                modeis="MEDIUM";
                break;

            case 3 :
                modeis="HARD";
                break;

                default:
                    modeis="EASY";
                            break;
        }

        head=(TextView)findViewById(R.id.head);
        head.setVisibility(View.VISIBLE);

        reset=(Button)findViewById(R.id.reset);
        reset.setVisibility(View.VISIBLE);

        System.out.println("mode is : "+mode);
        System.out.println("sec is : "+sec);

        if(!(mode==9 || sec==99999 || sec==-1))
        myDb.insertData(modeis,sec);

        {
            int minim;
            minim=Integer.parseInt(tim[0]);

            res = myDb.getAllData();

            if(res.getCount() == 0) {
                reset.setVisibility(View.INVISIBLE);
                head.setVisibility(View.INVISIBLE);
                showMessage("Error","No data to show. Win timing will be shown here once you win.");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                srno[count] = res.getString(0);
                diff[count] = res.getString(1);
                tim[count] = res.getString(2);

                if(Integer.parseInt(tim[count])<minim)
                    minim = Integer.parseInt(tim[count]);

                count++;
            }
            System.out.println(srno[0]+" "+diff[0]+" "+tim[0]);



            CreateTable();
            count=0;
        }
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void CreateTable()
    {

        layoutdata=findViewById(R.id.layoutdata);
        table = new LinearLayout(this);
        table.setOrientation(LinearLayout.HORIZONTAL);
        table.setVisibility(View.VISIBLE);
        column = new LinearLayout[3];
        for(i=0;i<3;i++)
        {
            column[i]=new LinearLayout(this);
            column[i].setOrientation(LinearLayout.VERTICAL);
            table.addView(column[i]);
        }
        t=new TextView[100][3];

        for(i=0;i<3;i++)
            for(j=0;j<100;j++)
                t[j][i]=new TextView(this);

        for(i=0;i<3;i++)
            for(j=0;j<count+1;j++)
            {
                t[j][i].setWidth(300);
                t[j][i].setHeight(80);
                t[j][i].setTextColor(Color.parseColor("#000000"));
                if(j==0)
                {
                    if(i==0)
                        t[j][i].setText("Sr.No.");
                    else if(i==1)
                        t[j][i].setText("Mode");
                    else if(i==2)
                        t[j][i].setText("Time");
                }
                else
                {
                    if(i==0)
                        t[j][i].setText(Integer.toString(j));
                    else if(i==1)
                        t[j][i].setText(diff[j-1]);
                    else if(i==2) {
                        t[j][i].setText(tim[j - 1] + " sec");
                    }
                    }
                column[i].addView(t[j][i]);
            }

        table.setGravity(Gravity.CENTER_HORIZONTAL);
        layoutdata.addView(table);
    }

    public void reset (View viewview)
    {
        myDb.DeleteTable(table);

    }
    }

