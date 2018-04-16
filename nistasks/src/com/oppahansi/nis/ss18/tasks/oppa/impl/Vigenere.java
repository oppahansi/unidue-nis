package com.oppahansi.nis.ss18.tasks.oppa.impl;

import com.oppahansi.nis.ss17.tasks.oppa.util.Constants;

/**
 * <p>This class represents the implementation of vigenere algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Vigenere {

    /**
     * Decrytps a vigenere chiffre text using the given key.
     *
     * @param chiffre Enrypted message
     * @param key     Encryption key
     * @return
     */
    public static String decryptChiffreWithKey(String chiffre, String key) {
        String expandedKey = expandKey(chiffre, key);
        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < chiffre.length(); i++) {
            int chiffreTextLetterPosition = Constants.ALPHABET.indexOf(chiffre.toUpperCase().charAt(i));
            int keyLetterPosition = Constants.ALPHABET.indexOf(expandedKey.toUpperCase().charAt(i));
            int plainTextLetterPosition = (chiffreTextLetterPosition - keyLetterPosition) < 0 ?
                    26 + (chiffreTextLetterPosition - keyLetterPosition) :
                    (chiffreTextLetterPosition - keyLetterPosition) % 26;
            plain.append(Constants.ALPHABET.charAt(Math.abs(plainTextLetterPosition)));
        }

        return plain.toString();
    }

    /**
     * Encrypts plain text using the given key.
     *
     * @param plain Text to encrypt
     * @param key   Encryption key
     * @return
     */
    public static String enryptPlainWithKey(String plain, String key) {
        String expandedKey = expandKey(plain, key);
        StringBuilder chiffre = new StringBuilder();

        for (int i = 0; i < plain.length(); i++) {
            int plainTextLetterPosition = Constants.ALPHABET.indexOf(plain.toUpperCase().charAt(i));
            int keyLetterPosition = Constants.ALPHABET.indexOf(expandedKey.toUpperCase().charAt(i));
            int chiffreTextLetterPosition = (plainTextLetterPosition + keyLetterPosition) % 26;
            chiffre.append(Constants.ALPHABET.charAt(Math.abs(chiffreTextLetterPosition)));
        }

        return chiffre.toString();
    }

    /**
     * Helper method to expand the key to given input string.
     *
     * @param input Message
     * @param key   Encryption key
     * @return
     */
    private static String expandKey(String input, String key) {
        StringBuilder result = new StringBuilder();

        if (input.length() % key.length() == 0) {
            for (int i = 0; i < input.length() / key.length(); i++) {
                result.append(key);
            }

            return result.toString();
        } else {
            for (int i = 0; i < input.length() / key.length(); i++) {
                result.append(key);
            }

            for (int i = 0; i < input.length() - key.length() * (input.length() / key.length()); i++) {
                result.append(key.charAt(i));
            }

            return result.toString();
        }

    }
}
