package com.oppahansi.nis.ss18.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Toolbox;

/**
 * <p>This class represents the implementation of xor algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Xor {
    /**
     * Performs a XOR operation on two hexadecimal strings.
     *
     * @param arg1 Hexadecimal string
     * @param arg2 Hexadecimal string
     * @return
     */
    public static String xorHexStrings(String arg1, String arg2) {
        arg1 = Toolbox.HexToBin(arg1);
        arg2 = Toolbox.HexToBin(arg2);

        return Toolbox.removeZeros(xorBinaryStrings(arg1, arg2));
    }

    /**
     * Performs a XOR operation on two binary strings of the same length.
     *
     * @param arg1 Binary string
     * @param arg2 Binary string
     * @return
     */
    public static String xorBinaryStrings(String arg1, String arg2) {
        StringBuilder result = new StringBuilder();

        if (arg1.length() < arg2.length()) {
            arg1 = Toolbox.getZeros(arg2.length() - arg1.length()) + arg1;
        }
        if (arg2.length() < arg1.length()) {
            arg2 = Toolbox.getZeros(arg1.length() - arg2.length()) + arg2;
        }

        for (int i = 0; i < arg1.length(); i++) {
            result.append(arg1.charAt(i) == arg2.charAt(i) ? "0" : "1");
        }

        return result.toString();
    }
}
