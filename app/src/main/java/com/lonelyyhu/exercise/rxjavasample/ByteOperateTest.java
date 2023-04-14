package com.lonelyyhu.exercise.rxjavasample;

public class ByteOperateTest {

    public static void main(String[] args) {

//        byte[] snBytes = {0x33, 0x31, 0x35, 0x30, 0x41, 0x41, 0x47, 0x44, 0x37, 0x39, 0x41, 0x12, 0x41, 0x56, 0x01, 0x00};
//
//        String snHex = "33313530414147443739411241560100";
//
////        SerialNumber sn = new SerialNumber(snBytes);
//        SerialNumber sn = new SerialNumber(snHex);
//
//        System.out.println(sn.getSerialNumber());


//        long lct = 891164999;
//
//        System.out.println(Long.toString(lct, 16));
//
//        System.out.println(Long.toBinaryString(lct));
//
//        LctTime lctTime = new LctTime(lct);
//        System.out.println(lctTime.getLctTime().getTime());


//        long t = 1295;
//
//        String s = String.format("%3s", Long.toString(t, 36));
////        String s = String.format("%04x", 20);
//
//        System.out.println(s);

        byte[] snBytes = {0x03, 0x16, 0x08, 0x1e};

        String bmsStr = String.format("%d.%d.%d.%d",
                snBytes[0],
                snBytes[1],
                snBytes[2],
                snBytes[3]);

        System.out.println(bmsStr);


    }

}
