package com.oppahansi.nis.ss17.tasks.oppa.impl;

import java.util.ArrayList;

/**
 * <p>This class represents a simple Transposition Columnar Cipher algorithm.</p>
 *
 * @author Alexander Schellenberg
 */
public class TPositionCipher {

    private static ArrayList<String> splitText;
    private static StringBuilder result;
    private static String markedColumns;
    private static int rows;
    private static int columns;

    /**
     * Encrypts any given text with the given key.
     *
     * @param text Text to encrypt.
     * @param key  Key used for encryption
     * @return Encrypted text
     */
    public static String encryptColumnar(String text, String key) {
        text = prepareText(text);
        initVariables(text, key);

        for (int i = 0; i < rows; i++) {
            splitText.add(text.substring(i * key.length(), key.length() * (i + 1)));
        }

        if (text.length() % key.length() == 0) {
            for (Character column : key.toCharArray()) {
                for (int j = 0; j < rows; j++) {
                    result.append(splitText.get(j).charAt(key.indexOf(column + "")));
                }
            }
        } else {
            initAdditionalVariables(text, key);
            splitText.add(text.substring(rows * key.length()));

            for (int i = 1; i <= columns; i++) {
                for (int j = 0; j < rows + (markedColumns.contains(i + "") ? 1 : 0); j++) {
                    result.append(splitText.get(j).charAt(key.indexOf(i + "")));
                }
            }
        }

        return result.toString();
    }

    /**
     * Decrypts a text with the given text.
     *
     * @param text Encrypted text to decrypt
     * @param key  Key used for encryption
     * @return Plain text message
     */
    public static String decryptColumnar(String text, String key) {
        text = prepareText(text);
        initVariables(text, key);

        if (text.length() % key.length() == 0) {
            for (int i = 0; i < columns - 1; i++) {
                splitText.add(text.substring(0, rows));
                text = text.substring(rows);
            }

            splitText.add(text);

            for (int i = 0; i < rows; i++) {
                for (Character column : key.toCharArray()) {
                    result.append(splitText.get(Integer.parseInt(column.toString()) - 1).charAt(i));
                }
            }
        } else {
            initAdditionalVariables(text, key);

            for (int i = 0; i < columns - 1; i++) {
                if (markedColumns.contains(i + 1 + "")) {
                    splitText.add(text.substring(0, rows + 1));
                    text = text.substring(rows + 1);
                } else {
                    splitText.add(text.substring(0, rows));
                    text = text.substring(rows);
                }
            }

            splitText.add(text);

            for (int i = 0; i < rows + 1; i++) {
                if (i >= rows) {
                    for (Character markkedColumn : markedColumns.toCharArray()) {
                        result.append(splitText.get(Integer.parseInt(markkedColumn.toString()) - 1).charAt(i));
                    }
                } else {
                    for (Character column : key.toCharArray()) {
                        result.append(splitText.get(Integer.parseInt(column.toString()) - 1).charAt(i));
                    }
                }
            }
        }

        return result.toString();
    }

    /**
     * Removes all special characters from the given text
     *
     * @param text Text to remove special characters from
     * @return
     */
    private static String prepareText(String text) {
        text = text.replaceAll("[-+.^:, ]", "");
        text = text.toUpperCase();

        return text;
    }

    /**
     * Initializes needed variables for the algorithm
     *
     * @param text Text used for encryption or decryption
     * @param key  Key used for encryption or decryption. Usually the same.
     */
    private static void initVariables(String text, String key) {
        rows = text.length() / key.length();
        columns = key.length();
        splitText = new ArrayList<>();
        result = new StringBuilder();
    }

    /**
     * Initializes additional needed variables for the algorithm in case of irregular text lengths.
     *
     * @param text Text used for encryption or decryption
     * @param key  Key used for encryption or decryption. Usually the same.
     */
    private static void initAdditionalVariables(String text, String key) {
        int additionalColumns = text.length() % key.length();
        markedColumns = key.substring(0, additionalColumns);
    }
}
