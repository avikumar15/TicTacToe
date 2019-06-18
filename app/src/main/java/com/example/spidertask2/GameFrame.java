            package com.example.spidertask2;

            import android.animation.ValueAnimator;
            import android.app.Activity;
            import android.app.Application;
            import android.content.Context;
            import android.content.Intent;
            import android.content.SharedPreferences;
            import android.content.pm.ActivityInfo;
            import android.content.pm.ApplicationInfo;
            import android.graphics.Canvas;
            import android.graphics.Color;
            import android.graphics.Paint;
            import android.media.MediaPlayer;
            import android.os.Handler;
            import android.os.Message;
            import android.preference.PreferenceManager;
            import android.view.MotionEvent;
            import android.view.View;
            import android.view.animation.LinearInterpolator;
            import android.widget.Button;
            import android.widget.FrameLayout;
            import android.widget.TextView;

            import java.util.Random;

            import static android.content.Context.MODE_PRIVATE;

            public class GameFrame extends View {
                Paint p1, p2;
                Context con;

                static int victory=0;

                public static final String min = "khana";
                public static final String sec = "peena";

                public static final String MyPrefs = "pena";

                int mm,ss;

                public int gamewon=0;

                public Circle c;
                public Cross cross;
                int ff=0;
                int mode=0;
                int matja=2;
                int[][] scorecard;
                int flagoutermost=0;
                int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
                int flagoutermost2=0,flag12=0,flag22=0,flag32=0,flag42=0;
                int flagoutermost3=0, flag13=0,flag23=0, flag33=0,flag43=0;
                int flagoutermost4=0, flag14=0,flag24=0, flag34=0,flag44=0;
                int flagoutermost5=0, flag15=0,flag25=0, flag35=0,flag45=0;
                int flagoutermost6=0, flag16=0,flag26=0, flag36=0,flag46=0, flag56=0, flag66=0;
                int flagoutermost7=0, flag17=0,flag27=0, flag37=0,flag47=0, flag57=0, flag67=0;
                int flagoutermost8=0, flag18=0,flag28=0, flag38=0,flag48=0, flag58=0, flag68=0;
                int flagoutermost9=0, flag19=0,flag29=0, flag39=0,flag49=0, flag59=0, flag69=0;

                int flagtime=1;
                int elapsedSeconds = 0;
                public int minutes = 0;
                public int seconds = 0;
                ValueAnimator timer;

                long time;

                public static float cx = 1f;
                public static float cy = 1f;

                TextView tend;
                TextView winner;
                TextView timeee;
                public FrameLayout f;
                public Button again;

                public GameFrame(Context context, TextView t, FrameLayout fl, TextView w, int m, int[][] skd, TextView chaipeekarjana,Context hell) {
                    super(context);
                    p1 = new Paint();
                    p2 = new Paint();

                    p1.setStrokeWidth(5);
                    p2.setStrokeWidth(2);

                    p1.setColor(Color.parseColor("#fffaf0"));
                    p2.setColor(Color.parseColor("#fffaf0"));

                    p1.setStyle(Paint.Style.STROKE);

                    tend=t;
                    winner=w;
                    f=fl;
                    mode = m;
                    scorecard=skd;

                    int secondsToRun = 9999;

                    timeee = chaipeekarjana;

                    if(flagtime==1) {
                        timer = ValueAnimator.ofInt(secondsToRun);
                        timer.setDuration(secondsToRun * 1000).setInterpolator(new LinearInterpolator());
                        timer.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                elapsedSeconds = (int) animation.getAnimatedValue();
                                minutes = elapsedSeconds / 60;
                                seconds = elapsedSeconds % 60;

                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                            }
                        });
                        timer.start();
                    }
                    MainActivity.count=0;

                    con=hell;
                }

                @Override
                public void onDraw(Canvas canvas) {
                    super.onDraw(canvas);

                    canvas.drawRect(0 + 10, (getHeight()) / 4f, getWidth() - 10, getHeight() / 4f + getWidth() - 20, p1);

                    canvas.drawLine(10, (getHeight()) / 4f + (getWidth() - 20) / 3f, getWidth() - 10, (getHeight()) / 4f + (getWidth() - 20) / 3f, p2);
                    canvas.drawLine(10, (getHeight()) / 4f + 2 * (getWidth() - 20) / 3f, getWidth() - 10, ((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f), p2);

                    canvas.drawLine(10 + (getWidth() - 20) / 3f, (getHeight()) / 4f, 10 + (getWidth() - 20) / 3f, (getHeight()) / 4f + getWidth() - 20, p2);
                    canvas.drawLine(10 + 2 * (getWidth() - 20) / 3f, (getHeight()) / 4f, 10 + 2 * (getWidth() - 20) / 3f, (getHeight()) / 4f + getWidth() - 20, p2);

                }

                @Override
                public boolean onTouchEvent(MotionEvent event) {

                    if ((event.getAction() == MotionEvent.ACTION_DOWN) && (ff==0) && (mode==4)) {
                        cx = event.getX();
                        cy = event.getY();


                        if((cx>=10f&&cx<=getWidth()-10f)&&(cy>=getHeight()/4f&&cy<=(getHeight()/4f + getWidth() -20f)))
                        {
                            if(cx<(10 + (getWidth() - 20) / 3f) && cy<((getHeight()) / 4f + (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight()/4f + (getWidth()-20)/6f;

                                if(scorecard[2][2]==0)
                                {
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    MainActivity.count++;
                                    if(MainActivity.count%2==0) {
                                    scorecard[2][2]=1;
                                    c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                    f.addView(c);
                                }
                                else {
                                        mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                    scorecard[2][2]=2;
                                    cross = new Cross(getContext(), 10, getHeight() / 4);
                                    f.addView(cross);
                                }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                                }

                            else if(cx<(10 + 2 * (getWidth() - 20) / 3f) && cy<((getHeight()) / 4f + (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + 3*(getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight()/4f + (getWidth()-20)/6f;

                                if(scorecard[2][3]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[2][3] = 1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[2][3] = 2;
                                        cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if(cy<((getHeight()) / 4f + (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + 5*(getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight()/4f + (getWidth()-20)/6f;

                                if(scorecard[2][4]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[2][4]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        scorecard[2][4]=2;
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if(cx<(10 + (getWidth() - 20) / 3f) && cy<((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + (getWidth() - 20)/6f;
                                MainActivity.centery = getHeight()/4f + 3*(getWidth()-20)/6f;

                                if(scorecard[3][2]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][2]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][2]=2;
                                        cross = new Cross(getContext(), 10, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if(cx<(10 + 2 * (getWidth() - 20) / 3f) && cy<((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 3 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 3 * (getWidth() - 20) / 6f;
                                if (scorecard[3][3] == 0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][3]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][3]=2;
                                        cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if(cy<((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + 5 * (getWidth() - 20)/6f;
                                MainActivity.centery = getHeight()/4f + 3*(getWidth()-20)/6f;

                                if(scorecard[3][4]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][4] = 1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[3][4] = 2;
                                        cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }

                                    else
                                    {
                                        tend.setVisibility(VISIBLE);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                tend.setVisibility(INVISIBLE);
                                            }
                                        }, 1000);
                                    }
                            }

                            else if(cx<(10 + (getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + (getWidth() - 20)/6f;
                                MainActivity.centery = getHeight()/4f + 5*(getWidth()-20)/6f;

                                if(scorecard[4][2]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][2]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][2]=2;
                                        cross = new Cross(getContext(), 10, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if(cx<(10 + 2*(getWidth() - 20) / 3f))
                            {
                                MainActivity.centerx = 10 + 3*(getWidth() - 20)/6f;
                                MainActivity.centery = getHeight()/4f + 5*(getWidth()-20)/6f;

                                if(scorecard[4][3]==0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {

                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][3]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][3]=2;
                                        cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else {
                                MainActivity.centerx = 10 + 5 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 5 * (getWidth() - 20) / 6f;

                                if (scorecard[4][4] == 0) {
                                    MainActivity.count++;
                                    if (MainActivity.count % 2 == 0) {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][4]=1;
                                        c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                        f.addView(c);
                                    } else {
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        scorecard[4][4]=2;
                                        cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else
                                {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            int i=1;
                            int j=1;

                            for(i=2;i<5;i++)
                                for(j=2;j<5;j++)
                                {
                                    if((scorecard[i][j] == 1 || scorecard[i][j] == 2) && ((scorecard[i][j] == scorecard[i+1][j] && scorecard[i][j] == scorecard[i+2][j]) || (scorecard[i][j]==scorecard[i-1][j] && scorecard[i][j] == scorecard[i+1][j]) || (scorecard[i][j] == scorecard[i-1][j] && scorecard[i][j] == scorecard[i-2][j]) || (scorecard[i][j] == scorecard[i][j+1] && scorecard[i][j]==scorecard[i][j+2]) || (scorecard[i][j] == scorecard[i][j+1] && scorecard[i][j]==scorecard[i][j-1]) || (scorecard[i][j] == scorecard[i][j-1] && scorecard[i][j]==scorecard[i][j-2]) || (scorecard[i][j]==scorecard[i+1][j+1] && scorecard[i][j]==scorecard[i+2][j+2]) || (scorecard[i][j]==scorecard[i+1][j+1] && scorecard[i][j]==scorecard[i-1][j-1]) || (scorecard[i][j]==scorecard[i-1][j-1] && scorecard[i][j]==scorecard[i-2][j-2]) || (scorecard[i][j] == scorecard[i-1][j+1] && scorecard[i][j] == scorecard[i-2][j+2]) || (scorecard[i][j] == scorecard[i-1][j+1] && scorecard[i][j] == scorecard[i+1][j-1]) || (scorecard[i][j] == scorecard[i+1][j-1] && scorecard[i][j] == scorecard[i+2][j-2])))
                                    {
                                        again.setVisibility(VISIBLE);
                                        ff=1;
                                        if(scorecard[i][j]==1)
                                        {
                                            winner.setText("Player 2 Wins.");
                                            winner.setVisibility(VISIBLE);
                                            timer.cancel();
                                            System.out.println(minutes+":"+seconds);
                                            timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));

                                        }
                                        else
                                        {
                                            timer.cancel();
                                            winner.setText("Player 1 Wins.");
                                            winner.setVisibility(VISIBLE);
                                            System.out.println(minutes+":"+seconds);

                                            timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));

                                        }
                                    }
                                }

                            if(scorecard[2][2]!=0 && scorecard[2][3]!=0 && scorecard[2][4]!=0 && scorecard[3][2]!=0 && scorecard[3][3]!=0 && scorecard[3][4]!=0 && scorecard[4][2]!=0 && scorecard[4][3]!=0 && scorecard[4][4]!=0 )
                            {
                                again.setVisibility(VISIBLE);
                                ff=1;
                                timer.cancel();
                                winner.setText("Match Draws.");
                                winner.setVisibility(VISIBLE);
                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                winner.setTextColor(Color.parseColor("#9c0000"));
                                System.out.println(minutes+":"+seconds);
                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                            }


                        }
                        else
                        {
                            tend.setVisibility(VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tend.setVisibility(INVISIBLE);
                                }
                            }, 1000);
                        }
                    }

                    if ((event.getAction() == MotionEvent.ACTION_DOWN) && (ff==0) && (mode==1 || mode==2 || mode==3)) {
                        cx = event.getX();
                        cy = event.getY();


                        int i=1;
                        int j=1;

                        if ((cx >= 10f && cx <= getWidth() - 10f) && (cy >= getHeight() / 4f && cy <= (getHeight() / 4f + getWidth() - 20f)) && MainActivity.count % 2 == 0) {
                            if (cx < (10 + (getWidth() - 20) / 3f) && cy < ((getHeight()) / 4f + (getWidth() - 20) / 3f)) {
                                if (scorecard[2][2] == 0) {
                                    matja=1;
                                        i=2;
                                        j=2;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                        MainActivity.count++;
                                        scorecard[2][2] = 2;
                                        System.out.println("x=1,y=1");
                                        cross = new Cross(getContext(), 10, getHeight() / 4f);
                                        f.addView(cross);
                                    }
                                 else {
                                    matja=2;
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }
                            else if (cx < (10 + 2 * (getWidth() - 20) / 3f) && cy < ((getHeight()) / 4f + (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 3 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + (getWidth() - 20) / 6f;

                                if (scorecard[2][3] == 0) {
                                    MainActivity.count++;
                                    i=2;
                                    j=3;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    matja=1;
                                    System.out.println("x=2,y=1");
                                    scorecard[2][3] = 2;
                                        cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4);
                                        f.addView(cross);

                                }
                                else {
                                    matja=2;
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }
                            else if (cy < ((getHeight()) / 4f + (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 5 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + (getWidth() - 20) / 6f;

                                if (scorecard[2][4] == 0) {
                                    i=2;
                                    j=4;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    MainActivity.count++;
                                    System.out.println("x=3,y=1");

                                    matja=1;
                                        scorecard[2][4] = 2;
                                        cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4);
                                        f.addView(cross);

                                }
                                else {
                                    matja=2;
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }

                            else if (cx < (10 + (getWidth() - 20) / 3f) && cy < ((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 3 * (getWidth() - 20) / 6f;

                                if (scorecard[3][2] == 0) {
                                    i=3;
                                    j=2;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    MainActivity.count++;
                                    System.out.println("x=1,y=2");
                                        scorecard[3][2] = 2;
                                        cross = new Cross(getContext(), 10, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);

                                    matja=1;
                                }
                                else {
                                    matja=2;
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }
                            else if (cx < (10 + 2 * (getWidth() - 20) / 3f) && cy < ((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 3 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 3 * (getWidth() - 20) / 6f;
                                if (scorecard[3][3] == 0) {
                                    MainActivity.count++;

                                    i=3;
                                    j=3;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    System.out.println("x=2,y=2");
                                    matja=1;
                                        scorecard[3][3] = 2;
                                        cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                }
                                else {
                                    matja=2;
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                }
                            }
                            else if (cy < ((getHeight()) / 4f + 2 * (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 5 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 3 * (getWidth() - 20) / 6f;

                                if (scorecard[3][4] == 0) {
                                    MainActivity.count++;
                                    {
                                        i=3;
                                        j=4;
                                        MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                        mp.start();
                                        System.out.println("x=3,y=2");
                                        matja=1;
                                        scorecard[3][4] = 2;
                                        cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4f + (getWidth() - 20) / 3f);
                                        f.addView(cross);
                                    }
                                }
                                else {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                    matja=2;
                                }
                            }
                            else if (cx < (10 + (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 5 * (getWidth() - 20) / 6f;

                                if (scorecard[4][2] == 0) {
                                    MainActivity.count++;
                                    i=4;
                                    j=2;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();

                                    System.out.println("x=1,y=3");
                                    matja=1;
                                        scorecard[4][2] = 2;
                                        cross = new Cross(getContext(), 10, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                        f.addView(cross);

                                }
                                else {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                    matja=2;
                                }
                            }
                            else if (cx < (10 + 2 * (getWidth() - 20) / 3f)) {
                                MainActivity.centerx = 10 + 3 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 5 * (getWidth() - 20) / 6f;

                                if (scorecard[4][3] == 0) {
                                    MainActivity.count++;

                                    i=4;
                                    j=3;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    System.out.println("x=2,y=3");
                                    matja=1;
                                    scorecard[4][3] = 2;
                                    cross = new Cross(getContext(), 10 + (getWidth() - 20) / 3f, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                    f.addView(cross);

                                }
                                else {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                    matja=2;
                                }
                            }

                            else {
                                MainActivity.centerx = 10 + 5 * (getWidth() - 20) / 6f;
                                MainActivity.centery = getHeight() / 4f + 5 * (getWidth() - 20) / 6f;

                                if (scorecard[4][4] == 0) {
                                    i=4;
                                    j=4;
                                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                    mp.start();
                                    matja=1;
                                    System.out.println("x=3,y=3");
                                    MainActivity.count++;
                                    scorecard[4][4] = 2;
                                    cross = new Cross(getContext(), 10 + 2 * (getWidth() - 20) / 3f, getHeight() / 4f + 2 * (getWidth() - 20) / 3f);
                                    f.addView(cross);
                                }

                                else {
                                    tend.setVisibility(VISIBLE);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tend.setVisibility(INVISIBLE);
                                        }
                                    }, 1000);
                                    matja=2;
                                }
                            }
                            if((i>=2 && i<5 && j>=2 && j<5 && matja == 1 && scorecard[i][j] == 2)&&((scorecard[i][j] == scorecard[i + 1][j] && scorecard[i][j] == scorecard[i + 2][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i + 1][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i - 2][j]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j + 2]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j - 1]) || (scorecard[i][j] == scorecard[i][j - 1] && scorecard[i][j] == scorecard[i][j - 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i + 2][j + 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i - 1][j - 1]) || (scorecard[i][j] == scorecard[i - 1][j - 1] && scorecard[i][j] == scorecard[i - 2][j - 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i - 2][j + 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i + 1][j - 1]) || (scorecard[i][j] == scorecard[i + 1][j - 1] && scorecard[i][j] == scorecard[i + 2][j - 2]))) {
                                winner.setText("Player Wins.");
                                winner.setVisibility(VISIBLE);
                                victory++;
                                savedata();
                                timer.cancel();
                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                                gamewon=1;
                                System.out.println(minutes+":"+seconds);
                                again.setVisibility(VISIBLE);
                                ff=1;

                                for(i=0;i<8;i++)
                                    for(j=0;j<8;j++)
                                        scorecard[i][j]=0;

                                ff=1;

                                minutes=0;
                                seconds=0;

                            }
                            else if (scorecard[2][2] != 0 && scorecard[2][3] != 0 && scorecard[2][4] != 0 && scorecard[3][2] != 0 && scorecard[3][3] != 0 && scorecard[3][4] != 0 && scorecard[4][2] != 0 && scorecard[4][3] != 0 && scorecard[4][4] != 0) {

                                again.setVisibility(VISIBLE);
                                ff=1;
                                winner.setText("Match Draws.");
                                timer.cancel();
                                winner.setVisibility(VISIBLE);
                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                winner.setTextColor(Color.parseColor("#9c0000"));
                                System.out.println(minutes+":"+seconds);
                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));

                                for(i=0;i<8;i++)
                                    for(j=0;j<8;j++)
                                        scorecard[i][j]=0;


                                minutes=0;
                                seconds=0;

                            }
                        }
                        else {
                            tend.setVisibility(VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tend.setVisibility(INVISIBLE);
                                }
                            }, 1000);
                            matja=2;
                        }

                        if(ff==0 && matja==1)
                            switch(mode)
                            {
                                case 1 :
                                    {
                                    Handler handlermode1 = new Handler();
                                    handlermode1.postDelayed(new Runnable() {
                                        @Override

                                        public void run() {
                                            int move1 = 0;
                                            int move2 = 0;
                                            MainActivity.count++;
                                            matja=3;

                                            Random rand1 = new Random();
                                            move1 = 2 + rand1.nextInt(3);

                                            Random rand2 = new Random();
                                            move2 = 2 + rand2.nextInt(3);

                                            if(scorecard[move1][move2]!=0) {
                                                while (scorecard[move1][move2] != 0) {
                                                    Random rand3 = new Random();
                                                    move1 = 2 + rand3.nextInt(3);

                                                    Random rand4 = new Random();
                                                    move2 = 2 + rand4.nextInt(3);
                                                }
                                            }

                                            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                            mp.start();

                                            System.out.println("x= " + (move2-1) + ",y= " + (move1-1));

                                            MainActivity.centerx = 10 + (2 * (move2 - 1) - 1) * (getWidth() - 20) / 6f;
                                            MainActivity.centery = getHeight() / 4f + (2 * (move1 - 1) - 1) * (getWidth() - 20) / 6f;

                                            int i=move1;
                                            int j=move2;

                                            scorecard[move1][move2] = 1;
                                            c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                            f.addView(c);

                                            if(((scorecard[i][j] == scorecard[i + 1][j] && scorecard[i][j] == scorecard[i + 2][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i + 1][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i - 2][j]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j + 2]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j - 1]) || (scorecard[i][j] == scorecard[i][j - 1] && scorecard[i][j] == scorecard[i][j - 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i + 2][j + 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i - 1][j - 1]) || (scorecard[i][j] == scorecard[i - 1][j - 1] && scorecard[i][j] == scorecard[i - 2][j - 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i - 2][j + 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i + 1][j - 1]) || (scorecard[i][j] == scorecard[i + 1][j - 1] && scorecard[i][j] == scorecard[i + 2][j - 2])))
                                            {
                                                again.setVisibility(VISIBLE);
                                                winner.setText("Computer Wins.");
                                                timer.cancel();
                                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                                                winner.setVisibility(VISIBLE);
                                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                                winner.setTextColor(Color.parseColor("#9c0000"));
                                                ff=1;
                                                System.out.println(minutes+":"+seconds);


                                                for(i=0;i<8;i++)
                                                    for(j=0;j<8;j++)
                                                        scorecard[i][j]=0;
                                            }
                                        }
                                    }, 200);
                                }
                                    break;
                                case 2 :
                                    {
                                    Handler handlermode2 = new Handler();
                                    handlermode2.postDelayed(new Runnable() {
                                        @Override

                                        public void run() {
                                            int move1 = 0;
                                            int move2 = 0;
                                            MainActivity.count++;
                                            matja=3;

                                            move1=teenmatbana1();
                                            move2=teenmatbana2();

                                            System.out.println("MainActivity.count = "+MainActivity.count);

                                            if(!((move1>=2 && move1<5)&&(move2>=2 && move2<5)))
                                            {
                                                System.out.println("Hello again in calc");
                                                Random rand1 = new Random();
                                                move1 = 2 + rand1.nextInt(3);

                                                Random rand2 = new Random();
                                                move2 = 2 + rand2.nextInt(3);
                                            }

                                            if(scorecard[move1][move2]!=0) {
                                                System.out.println("Hello again2 in calc");
                                                while (scorecard[move1][move2] != 0) {
                                                    Random rand3 = new Random();
                                                    move1 = 2 + rand3.nextInt(3);

                                                    Random rand4 = new Random();
                                                    move2 = 2 + rand4.nextInt(3);
                                                }
                                            }

                                            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                            mp.start();

                                            System.out.println("x= " + (move2-1) + ",y= " + (move1-1));

                                            MainActivity.centerx = 10 + (2 * (move2 - 1) - 1) * (getWidth() - 20) / 6f;
                                            MainActivity.centery = getHeight() / 4f + (2 * (move1 - 1) - 1) * (getWidth() - 20) / 6f;

                                            int i=move1;
                                            int j=move2;

                                            scorecard[move1][move2] = 1;
                                            c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                            f.addView(c);

                                            if(((i>=2 && i<5 && j>=2 && j<5 && scorecard[i][j] == scorecard[i + 1][j] && scorecard[i][j] == scorecard[i + 2][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i + 1][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i - 2][j]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j + 2]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j - 1]) || (scorecard[i][j] == scorecard[i][j - 1] && scorecard[i][j] == scorecard[i][j - 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i + 2][j + 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i - 1][j - 1]) || (scorecard[i][j] == scorecard[i - 1][j - 1] && scorecard[i][j] == scorecard[i - 2][j - 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i - 2][j + 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i + 1][j - 1]) || (scorecard[i][j] == scorecard[i + 1][j - 1] && scorecard[i][j] == scorecard[i + 2][j - 2])))
                                            {
                                                again.setVisibility(VISIBLE);
                                                winner.setText("Computer Wins.");
                                                winner.setVisibility(VISIBLE);
                                                timer.cancel();
                                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                                winner.setTextColor(Color.parseColor("#9c0000"));
                                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                                                ff=1;


                                                for(i=0;i<8;i++)
                                                    for(j=0;j<8;j++)
                                                        scorecard[i][j]=0;
                                            }
                                        }
                                    }, 200);
                                }
                                    break;
                                case 3 :
                                    {
                                    Handler handlermode2 = new Handler();
                                    handlermode2.postDelayed(new Runnable() {
                                        @Override

                                        public void run() {
                                            int move1 = 0;
                                            int move2 = 0;
                                            MainActivity.count++;
                                            matja=3;

                                            if(flagoutermost==1 || (scorecard[3][3]==2 && MainActivity.count==2))
                                            {
                                                move1=4;
                                                move2=2;
                                                System.out.println("HEYO!");
                                                flagoutermost=1;

                                                if(flag1==1 || (scorecard[2][4]==2 && MainActivity.count==4))
                                                {
                                                    flag1=1;
                                                    move1=4;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag2==1 || MainActivity.count==4)
                                                {
                                                    flag2=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();

                                                    if(flag3==1 || (MainActivity.count == 6 && scorecard[2][3] == 1))
                                                    {
                                                        flag3=1;
                                                        if((flag4==1) || (scorecard[2][4]==2 && MainActivity.count==6))
                                                        {
                                                            flag4=1;
                                                            move1=2;
                                                            move2=2;

                                                            if(MainActivity.count>6)
                                                            {
                                                                move1=teenmatbana1();
                                                                move2=teenmatbana2();
                                                            }
                                                            else
                                                            {
                                                                move1=teenmatbana1();
                                                                move2=teenmatbana2();
                                                            }
                                                        }
                                                    }

                                                    else if((flag5==1) || (MainActivity.count == 6 && scorecard[2][4] ==1))
                                                    {
                                                        flag5=1;
                                                        move1=4;
                                                        move2=4;

                                                        if(MainActivity.count>6)
                                                        {
                                                            move1=teenmatbana1();
                                                            move2=teenmatbana2();
                                                        }
                                                    }

                                                    else if(MainActivity.count >= 6)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                            }

                                            else if(flagoutermost2==1 || (scorecard[4][2] == 2 && MainActivity.count==2)) {
                                                flagoutermost2 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if (flag12 == 1 || (scorecard[2][3] == 2 && MainActivity.count == 4))
                                                {
                                                    flag12=1;
                                                    move1=2;
                                                    move2=2;
                                                    System.out.println("THIS IS WHERE I BELONG");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag22==1 || (scorecard[2][4] == 2 && MainActivity.count==4))
                                                {
                                                    flag22=1;
                                                    move1=2;
                                                    move2=3;

                                                    System.out.println("ENTERED THE HELL : " + move1 +" "+move2);

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag32==1 || (scorecard[3][4] == 2 && MainActivity.count==4))
                                                {
                                                    flag32=1;
                                                    move1=4;
                                                    move2=4;

                                                    System.out.println("EEEE");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag42==1 || MainActivity.count>=4)
                                                {
                                                    flag42=1;
                                                    System.out.println("THE TIME HAS COME");
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                    System.out.println(" moves inside fn are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                                                }
                                            }

                                            else if(flagoutermost4==1 || (scorecard[4][4] == 2 && MainActivity.count==2)) {
                                                flagoutermost4 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if (flag14 == 1 || (scorecard[3][2] == 2 && MainActivity.count == 4))
                                                {
                                                    flag14=1;
                                                    move1=4;
                                                    move2=2;
                                                    System.out.println("THIS IS WHERE I BELONG");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag24==1 || (scorecard[2][2] == 2 && MainActivity.count==4))
                                                {
                                                    flag24=1;
                                                    move1=2;
                                                    move2=3;

                                                    System.out.println("ENTERED THE HELL : " + move1 +" "+move2);

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag34==1 || (scorecard[2][4] == 2 && MainActivity.count==4))
                                                {
                                                    flag34=1;
                                                    move1=2;
                                                    move2=4;

                                                    System.out.println("EEEE");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag44==1 || MainActivity.count>=4)
                                                {
                                                    flag44=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                }
                                            }


                                            else if(flagoutermost3==1 || (scorecard[2][4] == 2 && MainActivity.count==2)) {
                                                flagoutermost3 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if (flag13 == 1 || (scorecard[4][3] == 2 && MainActivity.count == 4))
                                                {
                                                    flag13=1;
                                                    move1=4;
                                                    move2=4;
                                                    System.out.println("THIS IS WHERE I BELONG");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag23==1 || (scorecard[4][2] == 2 && MainActivity.count==4))
                                                {
                                                    flag23=1;
                                                    move1=2;
                                                    move2=3;

                                                    System.out.println("ENTERED THE HELL : " + move1 +" "+move2);

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag33==1 || (scorecard[3][2] == 2 && MainActivity.count==4))
                                                {
                                                    flag33=1;
                                                    move1=2;
                                                    move2=2;

                                                    System.out.println("EEEE");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag43==1 || MainActivity.count>=4)
                                                {
                                                    flag43=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                }
                                            }


                                            else if(flagoutermost5==1 || (scorecard[2][2] == 2 && MainActivity.count==2)) {
                                                flagoutermost5 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if (flag15 == 1 || (scorecard[3][4] == 2 && MainActivity.count == 4))
                                                {
                                                    flag15=1;
                                                    move1=2;
                                                    move2=4;
                                                    System.out.println("THIS IS WHERE I BELONG");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag25==1 || (scorecard[4][4] == 2 && MainActivity.count==4))
                                                {
                                                    flag25=1;
                                                    move1=2;
                                                    move2=3;

                                                    System.out.println("ENTERED THE HELL : " + move1 +" "+move2);

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag35==1 || (scorecard[4][3] == 2 && MainActivity.count==4))
                                                {
                                                    flag35=1;
                                                    move1=4;
                                                    move2=2;

                                                    System.out.println("EEEE");

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag45==1 || MainActivity.count>=4)
                                                {
                                                    flag45=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                }
                                            }


                                            else if(flagoutermost6==1 || (scorecard[3][2] == 2 && MainActivity.count==2)) {
                                                flagoutermost6 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if(flag16==1 || (scorecard[4][3]==2 && MainActivity.count==4))
                                                {
                                                    flag16=1;
                                                    move1=4;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag26==1 || (scorecard[4][4]==2 && MainActivity.count==4))
                                                {
                                                    flag26=1;
                                                    move1=3;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag36==1 || (scorecard[2][4]==2 && MainActivity.count==4))
                                                {
                                                    flag36=1;
                                                    move1=3;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag46==1 || (scorecard[2][3]==2 && MainActivity.count==4))
                                                {
                                                    flag46=1;
                                                    move1=2;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag66==1 || (scorecard[3][4]==2 && MainActivity.count==4))
                                                {
                                                    flag66=1;
                                                    move1=4;
                                                    move2=4;
                                                    if(scorecard[2][2]==2 && MainActivity.count==6)
                                                    {
                                                        move1=4;
                                                        move2=2;
                                                    }
                                                    if(MainActivity.count>6)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag56==1 || (MainActivity.count==4))
                                                {
                                                        flag56=1;
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    System.out.println(" moves inside fn are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                                                }
                                            }

                                            else if(flagoutermost7==1 || (scorecard[4][3] == 2 && MainActivity.count==2)) {
                                                flagoutermost7 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if(flag17==1 || (scorecard[3][2]==2 && MainActivity.count==4))
                                                {
                                                    flag17=1;
                                                    move1=4;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag27==1 || (scorecard[2][2]==2 && MainActivity.count==4))
                                                {
                                                    flag27=1;
                                                    move1=2;
                                                    move2=3;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag37==1 || (scorecard[2][4]==2 && MainActivity.count==4))
                                                {
                                                    flag37=1;
                                                    move1=2;
                                                    move2=3;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag47==1 || (scorecard[3][4]==2 && MainActivity.count==4))
                                                {
                                                    flag47=1;
                                                    move1=4;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag67==1 || (scorecard[2][3]==2 && MainActivity.count==4))
                                                {
                                                    flag67=1;
                                                    move1=2;
                                                    move2=4;
                                                    if(scorecard[4][2]==2 && MainActivity.count==6)
                                                    {
                                                        move1=4;
                                                        move2=4;
                                                    }
                                                    if(MainActivity.count>6)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag57==1 || (MainActivity.count==4))
                                                {
                                                    flag57=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                    System.out.println(" moves inside fn are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                                                }
                                            }

                                            else if(flagoutermost8==1 || (scorecard[3][4] == 2 && MainActivity.count==2)) {
                                                flagoutermost8 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if(flag18==1 || (scorecard[4][3]==2 && MainActivity.count==4))
                                                {
                                                    flag18=1;
                                                    move1=4;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag28==1 || (scorecard[2][2]==2 && MainActivity.count==4))
                                                {
                                                    flag28=1;
                                                    move1=3;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag38==1 || (scorecard[4][2]==2 && MainActivity.count==4))
                                                {
                                                    flag38=1;
                                                    move1=3;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag48==1 || (scorecard[2][3]==2 && MainActivity.count==4))
                                                {
                                                    flag48=1;
                                                    move1=2;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag68==1 || (scorecard[3][2]==2 && MainActivity.count==4))
                                                {
                                                    flag68=1;
                                                    move1=2;
                                                    move2=4;
                                                    if(scorecard[4][2]==2 && MainActivity.count==6)
                                                    {
                                                        move1=2;
                                                        move2=2;
                                                    }
                                                    if(MainActivity.count>6)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag58==1 || (MainActivity.count==4))
                                                {
                                                    flag58=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                    System.out.println(" moves inside fn are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                                                }
                                            }

                                            else if(flagoutermost9==1 || (scorecard[2][3] == 2 && MainActivity.count==2)) {
                                                flagoutermost9 = 1;
                                                move1 = 3;
                                                move2 = 3;
                                                System.out.println("Sasura "+scorecard[2][3]+"   "+MainActivity.count+"   "+flag1);

                                                if(flag19==1 || (scorecard[3][4]==2 && MainActivity.count==4))
                                                {
                                                    flag19=1;
                                                    move1=2;
                                                    move2=4;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag29==1 || (scorecard[4][2]==2 && MainActivity.count==4))
                                                {
                                                    flag29=1;
                                                    move1=4;
                                                    move2=3;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag39==1 || (scorecard[4][4]==2 && MainActivity.count==4))
                                                {
                                                    flag39=1;
                                                    move1=4;
                                                    move2=3;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag49==1 || (scorecard[3][2]==2 && MainActivity.count==4))
                                                {
                                                    flag49=1;
                                                    move1=2;
                                                    move2=2;

                                                    if(MainActivity.count>4)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }

                                                else if(flag69==1 || (scorecard[2][3]==2 && MainActivity.count==4))
                                                {
                                                    flag69=1;
                                                    move1=2;
                                                    move2=4;
                                                    if(scorecard[4][2]==2 && MainActivity.count==6)
                                                    {
                                                        move1=4;
                                                        move2=4;
                                                    }
                                                    if(MainActivity.count>6)
                                                    {
                                                        move1=teenmatbana1();
                                                        move2=teenmatbana2();
                                                    }
                                                }
                                                else if(flag59==1 || (MainActivity.count==4))
                                                {
                                                    flag59=1;
                                                    move1=teenmatbana1();
                                                    move2=teenmatbana2();
                                                    System.out.println(" moves inside fn are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                                                }
                                            }

                                            if(move1==0 || move2==0 || scorecard[move1][move2]!=0)
                                            {
                                                move1=teenmatbana1();
                                                move2=teenmatbana2();
                                                System.out.println("WE ARE NOT IN THE SAFE ZONE.");
                                            }

                                            if(!((move1>=2 && move1<5)&&(move2>=2 && move2<5)))
                                            {
                                                System.out.println("Hello again in calc");
                                                Random rand1 = new Random();
                                                move1 = 2 + rand1.nextInt(3);

                                                Random rand2 = new Random();
                                                move2 = 2 + rand2.nextInt(3);
                                            }

                                            if(scorecard[move1][move2]!=0) {
                                                System.out.println("Hello again2 in calc");
                                                while (scorecard[move1][move2] != 0) {
                                                    Random rand3 = new Random();
                                                    move1 = 2 + rand3.nextInt(3);

                                                    Random rand4 = new Random();
                                                    move2 = 2 + rand4.nextInt(3);
                                                }
                                            }

                                            System.out.println(" moves here are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);



                                            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                            mp.start();

                                            System.out.println(" moves here are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);

                                            System.out.println("x= " + (move2-1) + ",y= " + (move1-1));

                                            MainActivity.centerx = 10 + (2 * (move2 - 1) - 1) * (getWidth() - 20) / 6f;
                                            MainActivity.centery = getHeight() / 4f + (2 * (move1 - 1) - 1) * (getWidth() - 20) / 6f;

                                            int i=move1;
                                            int j=move2;

                                            scorecard[move1][move2] = 1;
                                            c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                            f.addView(c);

                                            if(((i>=2 && i<5 && j>=2 && j<5 && scorecard[i][j] == scorecard[i + 1][j] && scorecard[i][j] == scorecard[i + 2][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i + 1][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i - 2][j]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j + 2]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j - 1]) || (scorecard[i][j] == scorecard[i][j - 1] && scorecard[i][j] == scorecard[i][j - 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i + 2][j + 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i - 1][j - 1]) || (scorecard[i][j] == scorecard[i - 1][j - 1] && scorecard[i][j] == scorecard[i - 2][j - 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i - 2][j + 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i + 1][j - 1]) || (scorecard[i][j] == scorecard[i + 1][j - 1] && scorecard[i][j] == scorecard[i + 2][j - 2])))
                                            {
                                                again.setVisibility(VISIBLE);
                                                winner.setText("Computer Wins.");
                                                winner.setVisibility(VISIBLE);
                                                timer.cancel();
                                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                                winner.setTextColor(Color.parseColor("#9c0000"));
                                                ff=1;
                                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));

                                                for(i=0;i<8;i++)
                                                    for(j=0;j<8;j++)
                                                        scorecard[i][j]=0;
                                            }
                                        }
                                    }, 200);
                                }
                                    break;
                                default :
                                    {
                                    Handler handlermode1 = new Handler();
                                    handlermode1.postDelayed(new Runnable() {
                                        @Override

                                        public void run() {
                                            int move1 = 0;
                                            int move2 = 0;
                                            MainActivity.count++;
                                            matja = 3;

                                            Random rand1 = new Random();
                                            move1 = 2 + rand1.nextInt(3);

                                            Random rand2 = new Random();
                                            move2 = 2 + rand2.nextInt(3);

                                            if (scorecard[move1][move2] != 0) {
                                                while (scorecard[move1][move2] != 0) {
                                                    Random rand3 = new Random();
                                                    move1 = 2 + rand3.nextInt(3);

                                                    Random rand4 = new Random();
                                                    move2 = 2 + rand4.nextInt(3);
                                                }
                                            }


                                            MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.blip);
                                            mp.start();

                                            System.out.println("x= " + (move2-1) + ",y= " + (move1-1));

                                            MainActivity.centerx = 10 + (2 * (move2 - 1) - 1) * (getWidth() - 20) / 6f;
                                            MainActivity.centery = getHeight() / 4f + (2 * (move1 - 1) - 1) * (getWidth() - 20) / 6f;

                                            int i=move1;
                                            int j=move2;

                                            scorecard[move1][move2] = 1;
                                            c = new Circle(getContext(), MainActivity.centerx, MainActivity.centery);
                                            f.addView(c);

                                            if(((scorecard[i][j] == scorecard[i + 1][j] && scorecard[i][j] == scorecard[i + 2][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i + 1][j]) || (scorecard[i][j] == scorecard[i - 1][j] && scorecard[i][j] == scorecard[i - 2][j]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j + 2]) || (scorecard[i][j] == scorecard[i][j + 1] && scorecard[i][j] == scorecard[i][j - 1]) || (scorecard[i][j] == scorecard[i][j - 1] && scorecard[i][j] == scorecard[i][j - 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i + 2][j + 2]) || (scorecard[i][j] == scorecard[i + 1][j + 1] && scorecard[i][j] == scorecard[i - 1][j - 1]) || (scorecard[i][j] == scorecard[i - 1][j - 1] && scorecard[i][j] == scorecard[i - 2][j - 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i - 2][j + 2]) || (scorecard[i][j] == scorecard[i - 1][j + 1] && scorecard[i][j] == scorecard[i + 1][j - 1]) || (scorecard[i][j] == scorecard[i + 1][j - 1] && scorecard[i][j] == scorecard[i + 2][j - 2])))
                                            {
                                                again.setVisibility(VISIBLE);
                                                timer.cancel();
                                                winner.setText("Computer Wins.");
                                                winner.setVisibility(VISIBLE);
                                                winner.setBackgroundColor(Color.parseColor("#f03f3f"));
                                                winner.setTextColor(Color.parseColor("#9c0000"));
                                                ff=1;
                                                timeee.setText(String.format("Time lapsed - %02d:%02d", minutes, seconds));
                                                savedata();


                                                minutes=0;
                                                seconds=0;

                                                for(i=0;i<8;i++)
                                                    for(j=0;j<8;j++)
                                                        scorecard[i][j]=0;
                                            }
                                        }
                                    }, 200);
                                }
                                    break;
                            }
                        matja=2;
                    }

                    return true;
                }

                public int teenmatbana1()
                {
                    int move1=0,move2=0;

                    if(scorecard[2][2]==2 && scorecard[2][3]==2 && scorecard[2][4]==0)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][2]==2 && scorecard[2][4]==2 && scorecard[2][3]==0)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][3]==2 && scorecard[2][4]==2 && scorecard[2][2]==0)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[3][2]==2 && scorecard[3][3]==2 && scorecard[3][4]==0)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[3][2]==2 && scorecard[3][4]==2 && scorecard[3][3]==0)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[3][3]==2 && scorecard[3][4]==2 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[4][2]==2 && scorecard[4][4]==2 && scorecard[4][3]==0)
                    {
                        System.out.println("HELLO WORLD");
                        move1=4;
                        move2=3;
                        System.out.println(" moves inside are "+move1 +"  "+move2+"  "+ scorecard[move1][move2]);
                    }
                    else if(scorecard[4][2]==2 && scorecard[4][3]==2 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[4][2]==0 && scorecard[4][4]==2 && scorecard[4][3]==2)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][2]==2 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[4][2]==2 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[2][2]==0 && scorecard[4][2]==2 && scorecard[3][2]==2)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][3]==2 && scorecard[3][3]==2 && scorecard[4][3] == 0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[2][3]==2 && scorecard[3][3]==0 && scorecard[4][3] == 2)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][3]==0 && scorecard[3][3]==2 && scorecard[4][3] == 2)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][4]==2 && scorecard[4][4] == 0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][4]==2 && scorecard[4][4] == 2)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][4]==0 && scorecard[4][4] == 2)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][3]==2 & scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][2]==0 && scorecard[3][3]==2 & scorecard[4][4]==2)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][3]==0 & scorecard[4][4]==2)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][3]==2 & scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][3]==2 & scorecard[4][2]==2)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][3]==0 & scorecard[4][2]==2)
                    {
                        move1=3;
                        move2=3;
                    }

                    System.out.println("Value 0f 3,3 is "+scorecard[3][3]);
                    //Second one

                    if(scorecard[2][2]==1 && scorecard[2][3]==1 && scorecard[2][4]==0)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][2]==1 && scorecard[2][4]==1 && scorecard[2][3]==0)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][3]==1 && scorecard[2][4]==1 && scorecard[2][2]==0)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[3][2]==1 && scorecard[3][3]==1 && scorecard[3][4]==0)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[3][2]==1 && scorecard[3][4]==1 && scorecard[3][3]==0)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[3][3]==1 && scorecard[3][4]==1 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[4][2]==1 && scorecard[4][4]==1 && scorecard[4][3]==0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[4][2]==1 && scorecard[4][3]==1 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[4][2]==0 && scorecard[4][4]==1 && scorecard[4][3]==1)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][2]==1 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[4][2]==1 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[2][2]==0 && scorecard[4][2]==1 && scorecard[3][2]==1)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][3]==1 && scorecard[3][3]==1 && scorecard[4][3] == 0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[2][3]==1 && scorecard[3][3]==0 && scorecard[4][3] == 1)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][3]==0 && scorecard[3][3]==1 && scorecard[4][3] == 1)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][4]==1 && scorecard[4][4] == 0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][4]==1 && scorecard[4][4] == 1)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][4]==0 && scorecard[4][4] == 1)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][3]==1 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][2]==0 && scorecard[3][3]==1 && scorecard[4][4]==1)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][3]==0 && scorecard[4][4]==2)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][3]==1 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][3]==1 && scorecard[4][2]==1)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][3]==0 && scorecard[4][2]==1)
                    {
                        move1=3;
                        move2=3;
                    }

                    return move1;
                }

                public int teenmatbana2()
                {
                    int move1=0,move2=0;

                    if(scorecard[2][2]==2 && scorecard[2][3]==2 && scorecard[2][4]==0)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][2]==2 && scorecard[2][4]==2 && scorecard[2][3]==0)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][3]==2 && scorecard[2][4]==2 && scorecard[2][2]==0)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[3][2]==2 && scorecard[3][3]==2 && scorecard[3][4]==0)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[3][2]==2 && scorecard[3][4]==2 && scorecard[3][3]==0)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[3][3]==2 && scorecard[3][4]==2 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[4][2]==2 && scorecard[4][4]==2 && scorecard[4][3]==0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[4][2]==2 && scorecard[4][3]==2 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[4][2]==0 && scorecard[4][4]==2 && scorecard[4][3]==2)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][2]==2 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[4][2]==2 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[2][2]==0 && scorecard[4][2]==2 && scorecard[3][2]==2)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][3]==2 && scorecard[3][3]==2 && scorecard[4][3] == 0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[2][3]==2 && scorecard[3][3]==0 && scorecard[4][3] == 2)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][3]==0 && scorecard[3][3]==2 && scorecard[4][3] == 2)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][4]==2 && scorecard[4][4] == 0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][4]==2 && scorecard[4][4] == 2)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][4]==0 && scorecard[4][4] == 2)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][3]==2 & scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][2]==0 && scorecard[3][3]==2 & scorecard[4][4]==2)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][2]==2 && scorecard[3][3]==0 & scorecard[4][4]==2)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][3]==2 & scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][3]==2 & scorecard[4][2]==2)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==2 && scorecard[3][3]==0 & scorecard[4][2]==2)
                    {
                        move1=3;
                        move2=3;
                    }

                    //Second one

                    if(scorecard[2][2]==1 && scorecard[2][3]==1 && scorecard[2][4]==0)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][2]==1 && scorecard[2][4]==1 && scorecard[2][3]==0)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][3]==1 && scorecard[2][4]==1 && scorecard[2][2]==0)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[3][2]==1 && scorecard[3][3]==1 && scorecard[3][4]==0)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[3][2]==1 && scorecard[3][4]==1 && scorecard[3][3]==0)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[3][3]==1 && scorecard[3][4]==1 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[4][2]==1 && scorecard[4][4]==1 && scorecard[4][3]==0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[4][2]==1 && scorecard[4][3]==1 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[4][2]==0 && scorecard[4][4]==1 && scorecard[4][3]==1)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][2]==1 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[4][2]==1 && scorecard[3][2]==0)
                    {
                        move1=3;
                        move2=2;
                    }
                    else if(scorecard[2][2]==0 && scorecard[4][2]==1 && scorecard[3][2]==1)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][3]==1 && scorecard[3][3]==1 && scorecard[4][3] == 0)
                    {
                        move1=4;
                        move2=3;
                    }
                    else if(scorecard[2][3]==1 && scorecard[3][3]==0 && scorecard[4][3] == 1)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][3]==0 && scorecard[3][3]==1 && scorecard[4][3] == 1)
                    {
                        move1=2;
                        move2=3;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][4]==1 && scorecard[4][4] == 0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][4]==1 && scorecard[4][4] == 1)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][4]==0 && scorecard[4][4] == 1)
                    {
                        move1=3;
                        move2=4;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][3]==1 && scorecard[4][4]==0)
                    {
                        move1=4;
                        move2=4;
                    }
                    else if(scorecard[2][2]==0 && scorecard[3][3]==1 && scorecard[4][4]==1)
                    {
                        move1=2;
                        move2=2;
                    }
                    else if(scorecard[2][2]==1 && scorecard[3][3]==0 && scorecard[4][4]==1)
                    {
                        move1=3;
                        move2=3;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][3]==1 && scorecard[4][2]==0)
                    {
                        move1=4;
                        move2=2;
                    }
                    else if(scorecard[2][4]==0 && scorecard[3][3]==1 && scorecard[4][2]==1)
                    {
                        move1=2;
                        move2=4;
                    }
                    else if(scorecard[2][4]==1 && scorecard[3][3]==0 && scorecard[4][2]==1)
                    {
                        move1=3;
                        move2=3;
                    }

                    return move2;
                }

                public void savedata()
                {
                    SharedPreferences sharedPreferences = con.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);     //con is getApplicationContext()
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.clear();
                    editor.putInt("count",victory);

                    editor.putInt("min",minutes);
                    editor.putInt("sec",seconds);
                    editor.apply();

                }

            }
