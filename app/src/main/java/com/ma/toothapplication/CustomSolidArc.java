package com.ma.toothapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomSolidArc extends RelativeLayout {

    Paint mPaint;
    RectF mOval;
    List<Float>  mStartingAngle;
    List<Float>  mSweepAngle;
    int mCustomSolidArcColor;
    String mCustomSolidArcSetSchedule;


    int mWidth;
    int mHeight;

    int mSet;
    ArrayList<TextView> mTxt;


    public CustomSolidArc(Context context) {
        super(context);
        init(null);
    }

    public CustomSolidArc(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomSolidArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }


    private void init(@Nullable AttributeSet attrs){


        mTxt =new ArrayList<>();
        mSet=1;
        mCustomSolidArcSetSchedule= "";
        for (int i=0 ; i<12; i++){
            mTxt.add(new TextView(getContext()));
            //mTxt.get(i).setBackgroundColor(Color.RED);
            if(mTxt.get(i).getParent()!= null){
                ((RelativeLayout) mTxt.get(i).getParent()).removeView(mTxt.get(i));
            }
            addView(mTxt.get(i));

        }

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mStartingAngle=new ArrayList<>();
        mSweepAngle=new ArrayList<>();
        mStartingAngle.add(0f) ;
        mSweepAngle.add(360f);
        mOval=new RectF();


        this.setWillNotDraw(false);

        if (attrs==null){
            return;
        }
        TypedArray ta=getContext().obtainStyledAttributes(attrs,R.styleable.CustomSolidArc);
        mCustomSolidArcColor =ta.getColor(R.styleable.CustomSolidArc_CustomSolidArc_color, Color.GREEN);
        mCustomSolidArcSetSchedule =ta.getString(R.styleable.CustomSolidArc_set_schedule);

        mPaint.setColor(mCustomSolidArcColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ta.recycle();

        setSolidArc(convertString2Couple(mCustomSolidArcSetSchedule));
    }

    public static ArrayList<Couple> convertString2Couple(String text) {
        ArrayList<String> stringList=new ArrayList<>();
        ArrayList<Couple> coupleList=new ArrayList<>();
        if (text!= null){
            while (text.indexOf(",")>0) {
                int comaPosition=text.indexOf(",");
                stringList.add(text.substring(0,text.indexOf(",")));
                text=text.substring(text.indexOf(",")+1);
            }
            if (!text.contains(",")) {
                stringList.add(text);
            }
        }
        for (int i = 0; i <stringList.size() ; i=i+2) {
            coupleList.add(new Couple(Integer.parseInt(stringList.get(i)),Integer.parseInt(stringList.get(i+1))));
        }
        return coupleList;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getWidth();
        mHeight=getHeight();

        mOval.top=dip2px(50);
        mOval.bottom=mWidth-dip2px(50);
        mOval.left =dip2px(50);
        mOval.right=mWidth-dip2px(50);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        displayFigures();
    }

    public void displayFigures() {
        assignFigures(mSet);
        for (int i=0 ; i<12; i++) {
            double a=2*3.14159*(i+5)/12;
            double R=mWidth/2f-dip2px(25f);
            RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) mTxt.get(i).getLayoutParams();
            param.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            param.height =RelativeLayout.LayoutParams.WRAP_CONTENT;
            mTxt.get(i).setBackgroundColor(0x00ff00);
            mTxt.get(i).setGravity(Gravity.CENTER);
            double Xoffset=R*Math.cos(a);
            double Yoffset=R*Math.sin(a);
            int l=(int) ( mWidth / 2f - mTxt.get(i).getWidth() / 2f+Xoffset);
            int t= (int) (mWidth / 2f - mTxt.get(i).getWidth() / 2f+Yoffset);
            int r= (int) (mWidth / 2f + mTxt.get(i).getWidth() / 2f+Xoffset);
            int b= (int) (mWidth / 2f + mTxt.get(i).getWidth() / 2f+Yoffset);
            mTxt.get(i).layout(l, t, r, b);
        }
    }
    private float dip2px(float dip) {
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return px;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<mStartingAngle.size();i++) {
            canvas.drawArc(mOval, mStartingAngle.get(i), mSweepAngle.get(i), true, mPaint);
        }
        displayFigures();
        Log.d("mset:", String.valueOf(mSet));
    }

    public void setSolidArc(ArrayList<Couple> couple) {
        mStartingAngle.clear();
        mSweepAngle.clear();
        for (int i=0; i<couple.size();i++) {
            mStartingAngle.add(Couple.toAngle(couple.get(i).getFromTime()));
            mSweepAngle.add(Couple.toSweepAngle(couple.get(i)));
            Log.d("setSolidArc: ", String.valueOf(mStartingAngle.get(i)));
        }
        invalidate();
    }
    public void assignFigures(int set) {
        mSet=set;
        if (set==1){
            displayHours();
        }else {
            displayMinutes();
        }
    }
    public void displayMinutes() {
        mTxt.get(0).setText("40");
        mTxt.get(1).setText("45");
        mTxt.get(2).setText("50");
        mTxt.get(3).setText("55");
        mTxt.get(4).setText("00");
        mTxt.get(5).setText("05");
        mTxt.get(6).setText("10");
        mTxt.get(7).setText("15");
        mTxt.get(8).setText("20");
        mTxt.get(9).setText("25");
        mTxt.get(10).setText("30");
        mTxt.get(11).setText("35");
    }
    public void displayHours() {
        mTxt.get(0).setText("08");
        mTxt.get(1).setText("09");
        mTxt.get(2).setText("10");
        mTxt.get(3).setText("11");
        mTxt.get(4).setText("12");
        mTxt.get(5).setText("13");
        mTxt.get(6).setText("14");
        mTxt.get(7).setText("15");
        mTxt.get(8).setText("16");
        mTxt.get(9).setText("17");
        mTxt.get(10).setText("18");
        mTxt.get(11).setText("19");
    }
}

