package com.oppahansi.nis.ss17.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Constants;

/**
 * <p>This class represents the implementation of DES algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Des {

    /**
     * LEFT_RIGHT_KEY_HALVES contains the left shifted left and right halves of the permuted key
     */
    private static String[][] LEFT_RIGHT_KEY_HALVES = new String[2][16];

    /**
     * Combined LEFT_RIGHT_KEY_HALVES after left shifts have been done
     */
    private static String[] COMBINED_LEFT_RIGHT_HALVES = new String[16];

    /**
     * Generated ROUND_KEYS after the DES key schedule
     */
    private static String[] ROUND_KEYS = new String[16];

    /**
     * Returns the calculated key for the given round.
     *
     * @param key   Initial key
     * @param round Required round for calculation
     * @return
     */
    public static String getKeyForRound(String key, int round) {
        permutedChoiceOne(key);
        prepateForPC2();
        permutedChoiceTwo();

        return ROUND_KEYS[round - 1];
    }

    /**
     * <p>The 64-bit key is permuted according to the PC-1 table.</p>
     * <p>
     * <p>Note only 56 bits of the original key appear in the permuted key.</p>
     *
     * @param key Initial key
     */
    private static void permutedChoiceOne(String key) {
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        for (Integer l : Constants.PC1_LEFT) {
            left.append(key.charAt(l - 1));
        }

        for (Integer r : Constants.PC1_RIGHT) {
            right.append(key.charAt(r - 1));
        }

        LEFT_RIGHT_KEY_HALVES[0][0] = leftShift(left.toString());
        LEFT_RIGHT_KEY_HALVES[1][0] = leftShift(right.toString());
    }

    /**
     * Genereting left shifted key halves pairs and combining them for PC-2.
     */
    private static void prepateForPC2() {
        for (int i = 1; i < LEFT_RIGHT_KEY_HALVES[0].length; i++) {
            if (i == 1 || i == 8 || i == 15) {
                LEFT_RIGHT_KEY_HALVES[0][i] = leftShift(LEFT_RIGHT_KEY_HALVES[0][i - 1]);
                LEFT_RIGHT_KEY_HALVES[1][i] = leftShift(LEFT_RIGHT_KEY_HALVES[1][i - 1]);
            } else if (i >= 2 && i <= 7) {
                LEFT_RIGHT_KEY_HALVES[0][i] = leftShift(LEFT_RIGHT_KEY_HALVES[0][i - 1], 2);
                LEFT_RIGHT_KEY_HALVES[1][i] = leftShift(LEFT_RIGHT_KEY_HALVES[1][i - 1], 2);
            } else if (i >= 9 && i <= 14) {
                LEFT_RIGHT_KEY_HALVES[0][i] = leftShift(LEFT_RIGHT_KEY_HALVES[0][i - 1], 2);
                LEFT_RIGHT_KEY_HALVES[1][i] = leftShift(LEFT_RIGHT_KEY_HALVES[1][i - 1], 2);
            }
        }

        for (int i = 0; i < COMBINED_LEFT_RIGHT_HALVES.length; i++) {
            COMBINED_LEFT_RIGHT_HALVES[i] = LEFT_RIGHT_KEY_HALVES[0][i] + LEFT_RIGHT_KEY_HALVES[1][i];
        }
    }

    /**
     * Left shifts the characters in a string by 1
     *
     * @param input
     * @return
     */
    private static String leftShift(String input) {
        return input.substring(1) + input.substring(0, 1);
    }

    /**
     * Left shifts the characters in a string by the amount given
     *
     * @param input
     * @return
     */
    private static String leftShift(String input, int amount) {
        if (amount == 1) {
            return leftShift(input);
        } else {
            return leftShift(leftShift(input), amount - 1);
        }
    }

    /**
     * Forming round key based on the table for PC-2
     */
    private static void permutedChoiceTwo() {
        for (int i = 0; i < ROUND_KEYS.length; i++) {
            StringBuilder roundKey = new StringBuilder();

            for (Integer k : Constants.PC2) {
                roundKey.append(COMBINED_LEFT_RIGHT_HALVES[i].charAt(k - 1));
            }

            ROUND_KEYS[i] = roundKey.toString();
        }
    }

    public static String getRBlockForRound(String input, int round) {
        return "";
    }

    public static String feistel(String input, String key) {
        return "";
    }

    public static String calculateRound(String lBlock, String rBlock, String key, int round) {
        return "";
    }
}
