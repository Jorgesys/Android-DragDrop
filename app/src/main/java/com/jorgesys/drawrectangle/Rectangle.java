package com.jorgesys.drawrectangle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Rectangle extends View {
    Context context;
    Paint paint = new Paint();

    public Rectangle(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(0);
        //canvas.drawRect(0, 0, 500, 500, paint);


        int rectangleWidth = 300;
        int rectangleHeight = 200;

        Log.i("Rectangle", "Width : "  + getScreenWidth(getContext()));
        Log.i("Rectangle", "Width DP: "  + convertPxtoDP(getScreenWidth(getContext())));

        Log.i("Rectangle", "Height : "  + getScreenHeight(getContext()));
        Log.i("Rectangle", "Height DP: "  + convertPxtoDP(getScreenHeight(getContext())));
        Log.i("Rectangle", "Status and title bar  size: " + getStatusAndTitleBarSize());
         //LEFT , TOP, RIGHT, BOTTOM
        //canvas.drawRect(getScreenWidth(getContext())/2, getScreenHeight(getContext())/2, 500, 500, paint);
        //canvas.drawRect(0, getScreenHeight(getContext()) - rectangleHeight, rectangleWidth, getScreenHeight(getContext()), paint);

        Rect r = new Rect(0, 0, rectangleWidth, rectangleHeight);
        canvas.drawRect(r, paint);

       // Log.i("Rectangle", "LEFT:" + 0 + ", TOP:"+ (getScreenHeight(getContext()) - rectangleHeight) + " ,RIGHT: " + rectangleWidth + " ,BOTTOM: " + getScreenHeight(getContext()));
       /* paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, paint );
        paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint );*/

    }

    public int getStatusAndTitleBarSize(){
        Rect rectangle = new Rect();
        Window window = ((Activity)context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;
        return titleBarHeight + statusBarHeight;
    }



    public int convertPxtoDP(int value){
       return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics()));
    }

    /*public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }*/


    public static int getScreenHeight(Context mContext) {
        int height;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight();
        }
        //Log.i("Rectangle", "height : " + height);
        return height;
    }

    public static int getScreenWidth(Context mContext){
        int width = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }else{
            width = display.getWidth();
        }
        //Log.i("Rectangle", "width : " + width);
        return width;
    }



}