package com.ma.toothapplication;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class SingleCardData {
    String mDay;
    String mSchedule;
    Calendar mDate;



    public SingleCardData(String mDay, String schedule) {//this constructor is not relevant
        this.mDay = mDay;
        this.mSchedule =schedule;

    }

    public SingleCardData(Calendar date, String schedule) {

        mDate= (Calendar) date.clone();
        mSchedule=schedule;
        String[] days = new String[] {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        Log.d("aze", String.valueOf(mDate.get(Calendar.DAY_OF_WEEK)));
        mDay=days[mDate.get(Calendar.DAY_OF_WEEK)-1];

    }

    public String getDay(){
        return this.mDay;
    }

    public String getSchedule() {
        return this.mSchedule;
    }

    public String getDate() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRENCH);
        return sdf.format(mDate.getTime());
    }
}
