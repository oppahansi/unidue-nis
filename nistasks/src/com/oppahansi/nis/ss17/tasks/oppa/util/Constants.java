package com.oppahansi.nis.ss17.tasks.oppa.util;

/**
 * <p>This class contains constants / hardcoded values for reuse</p>
 *
 * @author Alexander Schellenberg
 */
public class Constants {
    static String HEX_ALPHABET = "0123456789ABCDEF";

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
}
