package com.jorgesys.drawrectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

public class CustomRectangle extends View {

    private Rect rectangle;
    private Paint paint;

    public CustomRectangle(Context context) {
        super(context);
        int x = 50;
        int y = 50;
        int rectWidth = 400;
        int rectHeight = 200;

        // create a rectangle that we'll draw later
        rectangle = new Rect(x, y, rectWidth, rectHeight);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(getRandomColor());
        paint.setStrokeWidth(0);
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // canvas.drawColor(Color.BLUE);
        canvas.drawRect(rectangle, paint);
    }

}