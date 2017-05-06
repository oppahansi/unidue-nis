package com.oppahansi.nis.ss17.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Converter;

/**
 * <p>This class represents the implementation of xor algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Xor {
    public static String xorHexStrings(String arg1, String arg2) {
        System.out.println(arg1);
        System.out.println(arg2);
        arg1 = Converter.HexToBin(arg1);
        arg2 = Converter.HexToBin(arg2);
        if (arg1.length() < arg2.length()) {
            for (int i = 0; i < arg2.length() - arg1.length(); i++) {
                arg1 = "0000" + arg1;
            }

        }
        if (arg2.length() < arg1.length()) {
            for (int i = 0; i < arg1.length() - arg2.length(); i++) {
                arg2 = "0000" + arg2;
            }
        }
        return removeZeros(xorBinaryStrings(arg1, arg2));
    }

    public static String xorBinaryStrings(String arg1, String arg2) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < arg1.length(); i++) {
            result.append(arg1.charAt(i) == arg2.charAt(i) ? "0" : "1");
        }

        return result.toString();
    }

    private static String removeZeros(String binaryString) {
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) != '0') return binaryString.substring(i);
        }
        return binaryString;
    }
}
