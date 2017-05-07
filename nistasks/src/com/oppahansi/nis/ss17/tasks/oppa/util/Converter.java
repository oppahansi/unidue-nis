package com.oppahansi.nis.ss17.tasks.oppa.util;

/**
 * <p>This class contains a collection of useful converter methods.</p>
 *
 * @author Alexander Schellenberg
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
