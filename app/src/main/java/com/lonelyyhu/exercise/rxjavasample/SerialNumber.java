package com.lonelyyhu.exercise.rxjavasample;

import com.google.gson.annotations.SerializedName;

public class SerialNumber {

    private String snHex;

    public SerialNumber(String serialNumberHexStr) {
        snHex = serialNumberHexStr;
    }

    public String getSerialNumber() {
        return parseSNHex();
    }

    public String getSnHex() {
        return snHex;
    }

    private String parseSNHex() {

        String snString = snHex;

        try {

            if (snHex.matches("0+")) {
                snString = "0";
            } else {
                StringBuilder sb = new StringBuilder();

                String ascStr = hexToAscii(snHex.substring(0, 22));
                sb.append(ascStr);

                Byte byte11 = Byte.valueOf(snHex.substring(22, 24), 16);
                String unsignedStr = Integer.valueOf(unsignedByteToInt(byte11)).toString();
                sb.append(unsignedStr);

                ascStr = hexToAscii(snHex.substring(24, 26));
                sb.append(ascStr);

                ascStr = hexToAscii(snHex.substring(26, 28));
                sb.append(ascStr);

                Byte byte14 = Byte.valueOf(snHex.substring(28, 30), 16);
                Byte byte15 = Byte.valueOf(snHex.substring(30, 32), 16);
                long l = unsignedByteToInt(byte14) * 256 + unsignedByteToInt(byte15);
                String long36Str = Long.toString(l, 36).toUpperCase();
                String f36Str = String.format("%3s", long36Str).replace(" ", "0");
                sb.append(f36Str);

                snString = sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return snString;

    }

    private String parseSN(byte[] snBytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= 10; i++) {
            String ascStr = byteToAscii(snBytes[i]);

            sb.append(ascStr);
        }

        String unsignedStr = Integer.valueOf(unsignedByteToInt(snBytes[11])).toString();
        sb.append(unsignedStr);

        String ascStr = byteToAscii(snBytes[12]);
        sb.append(ascStr);

        ascStr = byteToAscii(snBytes[13]);
        sb.append(ascStr);

        long l = unsignedByteToInt(snBytes[14]) * 256 + unsignedByteToInt(snBytes[15]);
        String long36Str = Long.toString(l, 36).toUpperCase();
        String f36Str = String.format("%3s", long36Str).replace(" ", "0");
        sb.append(f36Str);

        return sb.toString();
    }

    private String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    private String byteToAscii(byte ascByte) {
        return String.valueOf((char) Byte.valueOf(ascByte).intValue());
    }

    private int unsignedByteToInt(byte b) {
        return (int) b & 0xFF;
    }
}