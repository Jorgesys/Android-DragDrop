package com.jorgesys.drawrectangle;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity  implements View.OnTouchListener {

    private final static String TAG = "Drag & Drop";
    private RelativeLayout rootLayout;
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.root);
        Rectangle rectangle = new Rectangle(this);
        rootLayout.addView(rectangle);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(400, 200);
        rectangle.setLayoutParams(layoutParams);
        rectangle.setOnTouchListener(this);

    }

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                Snackbar.make(view, "Position: (x: " +  _xDelta +" , y:" + _yDelta +")", Snackbar.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

                if((X - _yDelta) > 0 && (X - _xDelta) < 1080 )
                    layoutParams.leftMargin = X - _xDelta;
                if((Y - _yDelta) > 0 && (Y - _xDelta) < 1920)
                    layoutParams.topMargin = Y - _yDelta;

                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                Log.i(TAG, "_xDelta : " + _xDelta + " ,_yDelta: " + _yDelta);
                break;
        }
        rootLayout.invalidate();
        return true;
    }


}