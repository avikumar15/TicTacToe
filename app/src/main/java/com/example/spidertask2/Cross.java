package com.example.spidertask2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

public class Cross extends View
{
    public Paint paint,paint2;
    float x,y;

    public Cross(Context context, float x1,float y1)
    {
        super(context);

        x=x1;
        y=y1;
        paint = new Paint();
        paint2= new Paint();

        paint.setStrokeWidth(50);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        paint2.setStrokeWidth(50);
        paint2.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float angleInRadians = (float) Math.toRadians(45);
        float length = (float) 1.414 * ((10+x+(getWidth()-20)/3f-50)-x-50);

        float endX = (float) Math.cos(angleInRadians) * length;
        float endY = (float) Math.sin(angleInRadians) * length;

        paint.setShader(new LinearGradient((int)x+50,(int)y+50,(int)(x+50+endX),(int)(y+50+endY), Color.parseColor("#8b0000"),Color.parseColor("#FFFAF0"), Shader.TileMode.CLAMP));
        paint2.setShader(new LinearGradient((int)x+50,(int)y+50+endY,(int)x+50+endX,(int)y+50, Color.parseColor("#8b0000"),Color.parseColor("#FFFAF0"), Shader.TileMode.CLAMP));

        canvas.drawLine(x+50,(y+(getWidth()-20)/3f - 50),(10+x+(getWidth()-20)/3f-50),y+50,paint);
        canvas.drawLine(x+50,y+50,(10+x+(getWidth()-20)/3f-50),(y+(getWidth()-20)/3f - 50) ,paint2);

    }

}

