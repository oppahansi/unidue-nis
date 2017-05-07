package com.oppahansi.nis.ss17.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Constants;

/**
 * <p>This class represents the implementation of DES algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Des {

    private static String[][] SHIFTED_PC1_LEFT_RIGHT_HALVES = new String[2][16];
    private static String[] SHIFTED_PC1_LEFT_RIGHT = new String[16];
    private static String[] ROUND_KEYS = new String[16];

    /**
     * Returns the calculated key for the given round.
     *
     * @param key Initial key
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
     *
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

        SHIFTED_PC1_LEFT_RIGHT_HALVES[0][0] = leftShiftString(left.toString());
        SHIFTED_PC1_LEFT_RIGHT_HALVES[1][0] = leftShiftString(right.toString());
    }

    /**
     * Genereting left shifted key halves pairs and combining them for PC-2.
     */
    private static void prepateForPC2() {
        for (int i = 1; i < SHIFTED_PC1_LEFT_RIGHT_HALVES[0].length; i++) {
            if (i == 1 || i == 8 || i == 15) {
                SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i-1]);
                SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i-1]);
            }
            else if (i >= 2 && i <= 7) {
                SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i-1], 2);
                SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i-1], 2);
            }
            else if (i >= 9 && i <= 14) {
                SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i-1], 2);
                SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i] = leftShiftString(SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i-1], 2);
            }
        }

        for (int i = 0; i < SHIFTED_PC1_LEFT_RIGHT.length; i++) {
            SHIFTED_PC1_LEFT_RIGHT[i] = SHIFTED_PC1_LEFT_RIGHT_HALVES[0][i] + SHIFTED_PC1_LEFT_RIGHT_HALVES[1][i];
        }
    }

    /**
     * Left shifts the characters in a string by 1
     *
     * @param input
     * @return
     */
    private static String leftShiftString(String input) {
        return input.substring(1) + input.substring(0, 1);
    }

    /**
     * Left shifts the characters in a string by the amount given
     *
     * @param input
     * @return
     */
    private static String leftShiftString(String input, int amount) {
        if (amount == 1) {
            return leftShiftString(input);
        }
        else {
            return leftShiftString(leftShiftString(input), amount - 1);
        }
    }

    /**
     * Forming round key based on the table for PC-2
     */
    private static void permutedChoiceTwo() {
        for (int i = 0; i < ROUND_KEYS.length; i++) {
            StringBuilder roundKey = new StringBuilder();

            for (Integer k : Constants.PC2) {
                roundKey.append(SHIFTED_PC1_LEFT_RIGHT[i].charAt(k - 1));
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
