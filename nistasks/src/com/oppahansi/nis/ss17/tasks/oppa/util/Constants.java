package com.oppahansi.nis.ss17.tasks.oppa.util;

/**
 * <p>This class contains constants / hardcoded values for reuse</p>
 *
 * @author Alexander Schellenberg
 */
public class Constants {
    public static String HEX_ALPHABET = "0123456789ABCDEF";
    public static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    enum Binary {
        ZERO("0000"),
        ONE("0001"),
        TWO("0010"),
        THREE("0011"),
        FOUR("0100"),
        FIVE("0101"),
        SIX("0110"),
        SEVEN("0111"),
        EIGHT("1000"),
        NINE("1001"),
        TEN("1010"),
        ELEVEN("1011"),
        TWELVE("1100"),
        THIRTEEN("1101"),
        FOURTEEN("1110"),
        FIFTEEN("1111");

        private String value;

        Binary(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    enum Hex {
        ZERO("0"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("A"),
        ELEVEN("B"),
        TWELVE("C"),
        THIRTEEN("D"),
        FOURTEEN("E"),
        FIFTEEN("F");

        private String value;

        Hex(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static final int[] PC1_LEFT = new int[]{
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36
    };

    public static final int[] PC1_RIGHT = new int[]{
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    public static final int[] PC2 = new int[]{
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    public static final int[] E_TABLE = new int[] {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };

}
