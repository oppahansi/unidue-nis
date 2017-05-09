package com.oppahansi.nis.ss17.tasks.oppa.util;

/**
 * <p>This class contains a collection of useful utilized and converter methods.</p>
 *
 * @author Alexander Schellenberg
 */
public class Toolbox {
    /**
     * Converts a Hexadecimal String to a Binary String
     *
     * @param hexValue
     * @return
     */
    public static String HexToBin(String hexValue) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < hexValue.length(); i++) {
            result.append(Constants.Binary.values()[Constants.HEX_ALPHABET.indexOf(hexValue.toUpperCase().charAt(i))]);
        }

        return result.toString();
    }

    /**
     * Left shifts the characters in a string by 1
     *
     * @param input
     * @return
     */
    public static String leftShift(String input) {
        return input.substring(1) + input.substring(0, 1);
    }

    /**
     * Left shifts the characters in a string by the amount given
     *
     * @param input
     * @return
     */
    public static String leftShift(String input, int amount) {
        if (amount == 1) {
            return leftShift(input);
        } else {
            return leftShift(leftShift(input), amount - 1);
        }
    }

    /**
     * Helper method to get a string containing 0s.
     *
     * @param amount
     * @return
     */
    public static String getZeros(int amount) {
        StringBuilder expansion = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            expansion.append("0");
        }

        return expansion.toString();
    }

    /**
     * Helper method to remove zeros from the beginning of the binary string.
     *
     * @param binaryString
     * @return
     */
    public static String removeZeros(String binaryString) {
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) != '0') return binaryString.substring(i);
        }

        return binaryString;
    }

    /**
     * Removes all special characters from the given text
     *
     * @param text Text to remove special characters from
     * @return
     */
    public static String cleanText(String text) {
        text = text.replaceAll("[-+.^:, ]", "");
        text = text.toUpperCase();

        return text;
    }

    /**
     * Method to determine whether or not the number given is a prime number.
     *
     * @param number Number to check
     * @return
     */
    public static boolean isPrim(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }

        return true;
    }
}
