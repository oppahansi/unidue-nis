package com.oppahansi.nis.ss17.tasks.oppa.util;

/**
 * Created by Oppa on 5/5/2017.
 */
public class Converter {
    public static String HexToBin(String hexValue) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hexValue.length(); i++) {
            result.append(Constants.Binary.values()[Constants.HEX_ALPHABET.indexOf(hexValue.toUpperCase().charAt(i))]);
        }
        return result.toString();
    }
}
