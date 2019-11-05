package com.ma.toothapplication;

import java.util.Calendar;
import java.util.Date;

public class CardConfig {



    String mLundi;
    String mMardi;
    String mMercredi;
    public CardConfig(String lundi, String mardi, String mercredi){
        mLundi=lundi;
        mMardi=mardi;
        mMercredi=mercredi;
    }
    public void generateSchedule(Date date){

    }

}

class NormalWorkingSchedule{
    String Lundi;


}

class Schedule{

    String mJasonSchedule;
    public Schedule(String s){
        mJasonSchedule=s;
    }
    public String getScheduleString(Calendar date){
        /**code to create a schedule String like "8,12,14,16" based on projecting raw schedule into a date*/
        return "8,12,14,16";
    }


}