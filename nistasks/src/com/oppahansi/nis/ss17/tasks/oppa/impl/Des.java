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
     *  l_r_blocks contains the calculated L- and R-Blocks
     */
    private static String[][] l_r_blocks = new String[2][17];

    /**
     * Encrypts the given binary message with the given binary key.
     *
     * @param binMessage Message to encrypt as binary String
     * @param binKey Key used for encryption as binary String
     * @return Binary String - encrypted message
     */
    public static String encryptBinMessage(String binMessage, String binKey) {
        createRoundKeys(binKey);
        initialPermutation(binMessage);
        initializeLRBlocks();

        return applyFinalPermutation();
    }

    /**
     * Encrypts the given hex message with the given hex key
     *
     * @param hexMessage Message to encrypt as hex String
     * @param hexKey Key used for encryption as hex String
     * @return Hex String - encrypted Message
     */
    public static String encryptHexMessage(String hexMessage, String hexKey) {
        return Toolbox.BinToHex(encryptBinMessage(Toolbox.HexToBin(hexMessage), Toolbox.HexToBin(hexKey)));
    }

    /**
     * Decrypts a binary message with the given key.
     *
     * @param binMessage Message to decrytp as binary String
     * @param binKey Key used to encrypt
     * @return Binary String - decrypted Message
     */
    public static String decryptBinMessage(String binMessage, String binKey) {
        createRoundKeys(binKey);
        initialPermutation(binMessage);
        initializeLRBlocksDecryption();

        return applyFinalPermutation();
    }

    /**
     * Decrypts a hex message with the given key.
     *
     * @param hexMessage Message to decrytp as hex String
     * @param hexKey Key used to encrypt
     * @return Hex String - decrypted Message
     */
    public static String decryptHexMessage(String hexMessage, String hexKey) {
        return Toolbox.BinToHex(decryptBinMessage(Toolbox.HexToBin(hexMessage), Toolbox.HexToBin(hexKey)));
    }

    /**
     * Returns the calculated key for the given round.
     *
     * @param key   Initial key
     * @param round Required round for calculation
     * @return
     */
    public static String getKeyForRound(String key, int round) {
        createRoundKeys(key);

        return round_keys[round - 1];
    }

    /**
     * <p>Gets an R-Block for the given input and the given round.</p>
     * <p>Kinda only used for the University task 7.</p>
     *
     * @param input 64bit BINARY String
     * @param round Round nuber
     * @return 32bit String
     */
    public static String getRBlockForRound(String input, int round) {
        createRoundKeys("0000000000000000000000000000000000000000000000000000000000000000");

        initialPermutation(input);
        initializeLRBlocks();

        return l_r_blocks[1][round];
    }

    /**
     * <p>Applying the F-Function once with given input 64bit binary String.</p>
     * <p>First 32 bits are the L-Block, last 32bits are the R-Block</p>
     *
     * @param input 64bit binary String, L-R-Block
     * @param roundKey 48bit binary String, round key
     * @return L xor R : 32bit binary String
     */
    public static String applyFeistel(String input, String roundKey) {
        return Xor.xorBinaryStrings(input.substring(0, 32), rBlockFunction(input.substring(32, input.length()), roundKey));
    }

    /**
     * Calculating one round
     *
     * @param l_block 32bit binary String - L-Block
     * @param r_block 32bit binary String - R-Block
     * @param key 48bit binary String - Key
     * @param round round number
     * @return 64bit binary String
     */
    public static String calculateOneRound(String l_block, String r_block, String key, int round) {
        createRoundKeys(key);
        String l_block_next = r_block;
        r_block = Xor.xorBinaryStrings(l_block, rBlockFunction(r_block, round_keys[round - 1]));

        return l_block_next+ r_block;
    }

    /**
     * Creates the Round Keys for the given key.
     *
     * @param key 64bit BINARY String
     */
    private static void createRoundKeys(String key) {
        permutedChoiceOne(key);
        leftShiftCdBlocks();
        combineCdBlocks();
        permutedChoiceTwo();
    }

    /**
     * <p>The 64-bit key is permuted according to the PC-1 table.</p>
     * <p>
     * <p><strong>Note</strong> only 56 bits of the original key appear in the permuted key.</p>
     *
     * @param key Initial key
     */
    private static void permutedChoiceOne(String key) {
        StringBuilder initial_c_block = new StringBuilder();
        StringBuilder initial_d_block = new StringBuilder();

        for (Integer l : Constants.PC1_LEFT_TABLE) {
            initial_c_block.append(key.charAt(l - 1));
        }

        for (Integer r : Constants.PC1_RIGHT_TABLE) {
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

            for (Integer k : Constants.PC2_TABLE) {
                roundKey.append(combined_c_d_blocks[i].charAt(k - 1));
            }

            round_keys[i] = roundKey.toString();
        }
    }

    /**
     * Applies the Initial Permutation using the IP-Table on the given message and initializes the first L- and R-Block values
     *
     * @param message 64bit BINARY String
     */
    private static void initialPermutation(String message) {
        StringBuilder result = new StringBuilder();

        for (Integer ip : Constants.IP_TABLE) {
            result.append(message.charAt(ip - 1));
        }

        l_r_blocks[0][0] = result.toString().substring(0, 32);
        l_r_blocks[1][0] = result.toString().substring(32, result.toString().length());
    }

    /**
     * Calculates and initializes the L-R-Blocks
     */
    private static void initializeLRBlocks() {
        for (int i = 1; i < 17; i++) {
            l_r_blocks[0][i] = l_r_blocks[1][i - 1];
            l_r_blocks[1][i] = Xor.xorBinaryStrings(l_r_blocks[0][i - 1], rBlockFunction(l_r_blocks[1][i - 1], round_keys[i - 1]));
        }
    }

    /**
     * Calculates and initializes the L-R-Blocks for decryption
     */
    private static void initializeLRBlocksDecryption() {
        for (int i = 1; i < 17; i++) {
            l_r_blocks[0][i] = l_r_blocks[1][i - 1];
            l_r_blocks[1][i] = Xor.xorBinaryStrings(l_r_blocks[0][i - 1], rBlockFunction(l_r_blocks[1][i - 1], round_keys[round_keys.length - i]));
        }
    }

    /**
     * Performs all needed R-Block operations.
     *
     * @param r_Block 32bit BINARY String
     * @param key 48bit String Round key
     * @return 32bit BINARY String
     */
    private static String rBlockFunction(String r_Block, String key) {
        String sBoxResult = sBoxFunction(Xor.xorBinaryStrings(expandRWithETable(r_Block), key));

        return applyPermutation(sBoxResult);
    }

    /**
     * Expands the R-Block using the E-Table
     *
     * @param r_Block 32bit BINARY String
     * @return 48bit BINARY String
     */
    private static String expandRWithETable(String r_Block) {
        StringBuilder result = new StringBuilder();

        for (Integer e : Constants.E_TABLE) {
            result.append(r_Block.charAt(e - 1));
        }

        return result.toString();
    }

    /**
     * Performs the S-Box calculation.
     *
     * @param input 48bit BINARY String
     * @return 48bit BINARY String
     */
    private static String sBoxFunction(String input) {
        String[] s_boxes = Toolbox.splitStringIntoChunks(input, 6);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s_boxes.length; i++) {
            int row = Toolbox.BinToDec(s_boxes[i].charAt(0) + "" + s_boxes[i].charAt(5));
            int column = Toolbox.BinToDec(s_boxes[i].substring(1, 5));

            result.append(Constants.BINARY[Toolbox.GET_S_BOX(i + 1)[row][column]]);
        }

        return result.toString();
    }

    /**
     * Reduces the calculated 48bit R-Block to 32bit BINARY String using the P-Table
     *
     * @param input 48bit BINARY String
     * @return 32bit binry String
     */
    private static String applyPermutation(String input) {
        StringBuilder result = new StringBuilder();

        for (Integer p : Constants.P_TABLE) {
            result.append(input.charAt(p - 1));
        }

        return result.toString();
    }

    private static String applyFinalPermutation() {
        String r_l_last = l_r_blocks[1][16] + l_r_blocks[0][16];
        StringBuilder result = new StringBuilder();

        for (Integer fp : Constants.FINAL_PERMUTATION_TABLE) {
            result.append(r_l_last.charAt(fp - 1));
        }

        return result.toString();
    }
}
