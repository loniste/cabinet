package com.ma.toothapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomBottomSheetView extends ConstraintLayout {

    Paint mPaint;
    Paint mGrayPaint;

    Point mP1;
    Point mP2;
    Point mP3;
    Point mP4;
    int mCustomViewColor;
    Path mPath;
    Path mPath2;

    int mWidth;
    int mHeight;

    public CustomBottomSheetView(Context context) {
        super(context);
        init(null);
    }

    public CustomBottomSheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomBottomSheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    /** for maybe other extension than ContraintLayout*/
    /*public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }*/
    private void init(@Nullable AttributeSet attrs){
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mGrayPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

        mGrayPaint.setColor(Color.rgb(229,229,229));

        mPath=new Path();
        mPath2=new Path();
        mP1=new Point();

        mP2=new Point();
        mP3=new Point();

        mP4=new Point();

        this.setWillNotDraw(false);

        if (attrs==null){
            return;
        }
        TypedArray ta=getContext().obtainStyledAttributes(attrs,R.styleable.CustomBottomSheetView);
        mCustomViewColor =ta.getColor(R.styleable.CustomBottomSheetView_view_color, Color.GREEN);
        mPaint.setColor(mCustomViewColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ta.recycle();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getWidth();
        mHeight=getHeight();
        mP1.set(0,mWidth/7);

        mP2.set(mWidth/7,0);
        mP3.set(mWidth-mWidth/7,0);

        mP4.set(mWidth,mWidth/7);

        mPath.reset();

        mPath.moveTo(mP1.x,mP1.y);
        mPath.cubicTo(mP2.x,mP2.y,mP3.x,mP3.y,mP4.x,mP4.y);
        mPath.lineTo(mWidth,mHeight);
        mPath.lineTo(0,mHeight);

        mPath.close();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawPath(mPath,mPaint);
        canvas.drawPath(mPath2,mGrayPaint);


    }

    public void setCorner(int gap) {
        mP1.set(0,gap);

        mP2.set(gap,0);
        mP3.set(mWidth-gap,0);

        mP4.set(mWidth,gap);

        mPath.reset();

        mPath.moveTo(mP1.x,mP1.y);
        mPath.cubicTo(mP2.x,mP2.y,mP3.x,mP3.y,mP4.x,mP4.y);
        mPath.lineTo(mWidth,mWidth/7*2);
        mPath.lineTo(0,mWidth/7*2);

        mPath.close();

        mPath2.reset();

        mPath2.moveTo(0,mWidth/7*2);
        mPath2.lineTo(mWidth,mWidth/7*2);
        mPath2.lineTo(mWidth,mHeight);
        mPath2.lineTo(0,mHeight);

        mPath2.close();



        invalidate();
        //Log.d("ddd", "setCorner: "+gap);

    }


}
