package com.example.spidertask2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;

public class Circle extends View

{
    public Paint paint;
    public float cxx;
    public float cyy;

    public Circle(Context context, float cx2, float cy2)
    {
        super(context);

        paint = new Paint();
        paint.setStrokeWidth(50);
        paint.setStyle(Paint.Style.STROKE);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        cxx=cx2;
        cyy=cy2;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float rad = (getWidth()-150)/6f - 45f;
        paint.setShader(new RadialGradient(cxx,cyy,rad,Color.parseColor("#0e0056"), Color.parseColor("#FFFAF0"), Shader.TileMode.MIRROR));

        canvas.drawCircle(cxx,cyy,rad,paint);


    }
}
