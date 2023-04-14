package com.lonelyyhu.exercise.rxjavasample;

import java.util.Calendar;
import java.util.TimeZone;

public class LctTime {

    private Calendar lctTime;

    public LctTime(long lctLong) {
        parse(lctLong);
    }

    public Calendar getLctTime() {
        return lctTime;
    }

    private void parse(long lctLong) {

        System.out.println(lctLong);

        lctTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        if (lctLong > 0) {
            String lctBinaryStr = String.format("%32s", Long.toBinaryString(lctLong)).replace(" ", "0");
            System.out.println("time hex:"+lctBinaryStr);

            String sStr = lctBinaryStr.substring(0, 6);
            int year = Integer.parseInt(sStr, 2);
            System.out.println("year hex:"+sStr);
            System.out.println("year:"+year);


            sStr = lctBinaryStr.substring(6, 10);
            int month =  Integer.parseInt(sStr, 2);
            System.out.println("month hex:"+sStr);
            System.out.println("month:"+month);


            sStr = lctBinaryStr.substring(10, 15);
            int day =  Integer.parseInt(sStr, 2);
            System.out.println("day hex:"+sStr);
            System.out.println("day:"+day);



            sStr = lctBinaryStr.substring(15, 20);
            int hour =  Integer.parseInt(sStr, 2);
            System.out.println("hour hex:"+sStr);
            System.out.println("hour:"+hour);

            sStr = lctBinaryStr.substring(20, 26);
            int minute =  Integer.parseInt(sStr, 2);
            System.out.println("minute hex:"+sStr);
            System.out.println("minute:"+minute);



            sStr = lctBinaryStr.substring(26, 32);
            int sec =  Integer.parseInt(sStr, 2);
            System.out.println("sec hex:"+sStr);
            System.out.println("sec:"+sec);



            lctTime.set(2000+year, month-1, day, hour, minute, sec);
            lctTime.set(Calendar.MILLISECOND, 0);
        } else {
            lctTime.setTimeInMillis(0);
        }


    }

    public static void main(String[] args) {

        LctTime lctTime = new LctTime(Long.valueOf("49b2b632", 16));

        System.out.println(lctTime.getLctTime().getTimeInMillis());

    }

}
