package com.lonelyyhu.exercise.rxjavasample;

import android.widget.Toast;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by lonelyyhu on 2018/4/2.
 */

public class TimeZoneTest {

    public static void main(String[] args) {

//        TimeZone timeZone = TimeZone.getTimeZone("UTC");
//        Calendar calendar = Calendar.getInstance(timeZone);
//        SimpleDateFormat simpleDateFormat =
//                new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
//        simpleDateFormat.setTimeZone(timeZone);
//
//        System.out.println("Time zone: " + timeZone.getID());
//        System.out.println("default time zone: " + TimeZone.getDefault().getID());
//        System.out.println("offset: " + TimeUnit.HOURS.convert(Integer.valueOf(TimeZone.getDefault().getRawOffset()).longValue(), TimeUnit.MILLISECONDS));
//        System.out.println();
//
//        System.out.println("UTC:     " + simpleDateFormat.format(calendar.getTime()));
//        System.out.println("Default: " + calendar.getTime());
//
//
//        System.out.println("Mills:  " + calendar.getTimeInMillis());
//        System.out.println("Mills:  " + Calendar.getInstance().getTimeInMillis());



//        String INVALID_VCU_NUMBER = "00000000000000";
//
//        String pattern = "0+";
//
//        System.out.printf("matches: " + Pattern.matches(pattern, INVALID_VCU_NUMBER));



        byte[] snBytes = {0x00, 0x01, 0x02, 0x03};

//        ByteBuffer bf = ByteBuffer.wrap(snBytes);
//        bf.order(ByteOrder.BIG_ENDIAN);
//        bf.put(snBytes);
//        bf.putInt(20);
//        bf.flip();

//        byte[] newBytes = ArrayUtils
//
//        StringBuilder sb = new StringBuilder(newBytes.length * 2);
//        Formatter formatter = new Formatter(sb);
//        for (byte b : newBytes) {
//            formatter.format("%02x", b);
//        }
//
//        System.out.printf(sb.toString());

    }

}
