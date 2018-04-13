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
            result.append(Constants.BINARY[Constants.HEX_ALPHABET.indexOf(hexValue.toUpperCase().charAt(i))]);
        }

        return result.toString();
    }

    public static String BinToHex(String binValue) {
        StringBuilder result = new StringBuilder();
        String[] binValueChunks = Toolbox.splitStringIntoChunks(binValue, 4);

        for (String chunk : binValueChunks) {
            result.append(Constants.HEX[BinToDec(chunk)]);
        }

        return result.toString();
    }

    /**
     * Converts a decimal number to a BINARY String
     *
     * @param decimalValue
     * @return
     */
    public static String DecToBin(int decimalValue) {
        StringBuilder result = new StringBuilder();

        while(decimalValue > 1) {
            result.insert(0, decimalValue % 2);
            decimalValue /= 2;
        }

        result.insert(0, decimalValue);

        return result.toString();
    }

    /**
     * Converts a BINARY String to a decimal Number
     *
     * @param binaryValue
     * @return
     */
    public static int BinToDec(String binaryValue) {
        int result = 0;

        for (int i = binaryValue.length() - 1; i >= 0; i--) {
            result += (int) (Integer.parseInt(binaryValue.charAt(i) + "") * Math.pow(2, binaryValue.length() - 1 - i));
        }

        return result;
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
     * Helper method to remove zeros from the beginning of the BINARY string.
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

    /**
     * Method to splti a String into several chunks
     *
     * @param input String to split
     * @param chunkSize Size of a chunk
     * @return String[]
     */
    public static String[] splitStringIntoChunks(String input, int chunkSize) {
        String[] result = new String[input.length() % chunkSize == 0 ? input.length() / chunkSize : input.length() / chunkSize + 1];

        for (int i = 0; i < result.length - 1; i++) {
            result[i] = input.substring(0, chunkSize);
            input = input.substring(chunkSize, input.length());
        }

        result[result.length - 1] = input;

        return result;
    }

    /**
     * Returns the required S-Box
     *
     * @param sBoxNumber S-Box number
     * @return S-Box int-Array[][]
     */
    public static int[][] GET_S_BOX(int sBoxNumber) {
        if (sBoxNumber == 1) return Constants.S1_BOX;
        else if (sBoxNumber == 2) return  Constants.S2_BOX;
        else if (sBoxNumber == 3) return  Constants.S3_BOX;
        else if (sBoxNumber == 4) return  Constants.S4_BOX;
        else if (sBoxNumber == 5) return  Constants.S5_BOX;
        else if (sBoxNumber == 6) return  Constants.S6_BOX;
        else if (sBoxNumber == 7) return  Constants.S7_BOX;
        else return  Constants.S8_BOX;
    }
}
