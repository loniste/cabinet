package com.ma.toothapplication;

class Couple {
    int fromTime;
    int toTime;
    public Couple(int fromTime, int toTime){
        this.fromTime=fromTime;
        this.toTime=toTime;
    }
    public static float toAngle(int t) {
        return ((t-15)>=0?(t-15)*30:(t-3)*30);
    }

    public static float toSweepAngle(Couple couple) {
        return (couple.getToTime()-couple.getFromTime())*30;
    }

    public void setFromTime(int fromTime) {
        this.fromTime = fromTime;
    }

    public int getFromTime() {
        return fromTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public int getToTime() {
        return toTime;
    }
}
