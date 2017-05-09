package com.oppahansi.nis.ss17.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Constants;
import com.oppahansi.nis.ss17.tasks.oppa.util.Toolbox;

/**
 * <p>This class represents the implementation of DES algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Des {

    /**
     * c_d_blocks contains the left shifted left and right halves of the permuted key
     */
    private static String[][] c_d_blocks = new String[2][16];

    /**
     * Combined c_d_blocks after left shifts have been done
     */
    private static String[] combined_c_d_blocks = new String[16];

    /**
     * Generated round_keys after the DES key schedule
     */
    private static String[] round_keys = new String[16];

    /**
     * Returns the calculated key for the given round.
     *
     * @param key   Initial key
     * @param round Required round for calculation
     * @return
     */
    public static String getKeyForRound(String key, int round) {
        permutedChoiceOne(key);
        leftShiftCdBlocks();
        combineCdBlocks();
        permutedChoiceTwo();

        return round_keys[round - 1];
    }

    /**
     * <p>The 64-bit key is permuted according to the PC-1 table.</p>
     * <p>
     * <p>Note only 56 bits of the original key appear in the permuted key.</p>
     *
     * @param key Initial key
     */
    private static void permutedChoiceOne(String key) {
        StringBuilder initial_c_block = new StringBuilder();
        StringBuilder initial_d_block = new StringBuilder();

        for (Integer l : Constants.PC1_LEFT) {
            initial_c_block.append(key.charAt(l - 1));
        }

        for (Integer r : Constants.PC1_RIGHT) {
            initial_d_block.append(key.charAt(r - 1));
        }

        initializeCdBlocks(initial_c_block.toString(), initial_d_block.toString());
    }

    /**
     * Initializes the first values for the c_d_blocks by performing the first left shift of the LS Cycle
     *
     * @param initial_c_block
     * @param initial_d_block
     */
    private static void initializeCdBlocks(String initial_c_block, String initial_d_block) {
        c_d_blocks[0][0] = Toolbox.leftShift(initial_c_block);
        c_d_blocks[1][0] = Toolbox.leftShift(initial_d_block);
    }

    /**
     * Genereting left shifted C D Blocks.
     */
    private static void leftShiftCdBlocks() {
        for (int i = 1; i < c_d_blocks[0].length; i++) {
            if (i == 1 || i == 8 || i == 15) {
                c_d_blocks[0][i] = Toolbox.leftShift(c_d_blocks[0][i - 1]);
                c_d_blocks[1][i] = Toolbox.leftShift(c_d_blocks[1][i - 1]);
            } else if (i >= 2 && i <= 7) {
                c_d_blocks[0][i] = Toolbox.leftShift(c_d_blocks[0][i - 1], 2);
                c_d_blocks[1][i] = Toolbox.leftShift(c_d_blocks[1][i - 1], 2);
            } else if (i >= 9 && i <= 14) {
                c_d_blocks[0][i] = Toolbox.leftShift(c_d_blocks[0][i - 1], 2);
                c_d_blocks[1][i] = Toolbox.leftShift(c_d_blocks[1][i - 1], 2);
            }
        }
    }

    /**
     * Combines C D Blocks for PC-2.
     */
    private static void combineCdBlocks() {
        for (int i = 0; i < combined_c_d_blocks.length; i++) {
            combined_c_d_blocks[i] = c_d_blocks[0][i] + c_d_blocks[1][i];
        }
    }

    /**
     * Forming round key based on the table for PC-2
     */
    private static void permutedChoiceTwo() {
        for (int i = 0; i < round_keys.length; i++) {
            StringBuilder roundKey = new StringBuilder();

            for (Integer k : Constants.PC2) {
                roundKey.append(combined_c_d_blocks[i].charAt(k - 1));
            }

            round_keys[i] = roundKey.toString();
        }
    }
}
