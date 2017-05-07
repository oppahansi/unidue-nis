package com.oppahansi.nis.ss15.tasks.markk;

/**
 * Programmierprojekt 2015
 * Aufgaben implementiert von Mark Kunze
 */

import java.util.Random;

/**
 * Diese Klasse ermï¿½glicht das Abrufen von Aufgaben vom Server und die
 * Implementierung der dazugehï¿½rigen Lï¿½sungen.
 * <p>
 * Nï¿½here Informationen zu den anderen Klassen und den einzelnen Aufgabentypen
 * entnehmen Sie bitte der entsprechenden Dokumentation im TMT und den Javadocs
 * zu den anderen Klassen.
 *
 * @see Connection
 * @see TaskObject
 */

public class Client implements TaskDefs {
    /* hier bitte die Matrikelnummer eintragen */
    private final int matrikelnr = 1234567;
    /* hier bitte das TMT-Passwort eintragen */
    private final String password = "password";
    /* Aufgaben, die bearbeitet werden sollen */
    private final int[] tasks = {TASK_CLEARTEXT, TASK_XOR, TASK_MODULO,
            TASK_FACTORIZATION, TASK_VIGENERE, TASK_DES_KEYSCHEDULE,
            TASK_DES_RBLOCK, TASK_DES_FEISTEL, TASK_DES_ROUND, TASK_AES_GF8,
            TASK_AES_KEYEXPANSION, TASK_AES_MIXCOLUMNS,
            TASK_AES_TRANSFORMATION, TASK_AES_3ROUNDS, TASK_RC4_LOOP,
            TASK_RC4_KEYSCHEDULE, TASK_RC4_ENCRYPTION, TASK_DIFFIEHELLMAN,
            TASK_RSA_ENCRYPTION, TASK_RSA_DECRYPTION, TASK_ELGAMAL_ENCRYPTION,
            TASK_ELGAMAL_DECRYPTION};
    String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private Connection con;
    private TaskObject currentTask;

    /**
     * Klassenkonstruktor. Baut die Verbindung zum Server auf.
     */
    public Client() {
        con = new Connection();
        if (con.auth(matrikelnr, password))
            System.out.println("Anmeldung erfolgreich.");
        else
            System.out.println("Anmeldung nicht erfolgreich.");
    }

    public static void main(String[] args) {
        Client c = new Client();
        if (c.isReady()) {
            c.taskLoop();
        }
        c.close();
    }

    /**
     * Besteht die Verbindung zum Server?
     *
     * @return true, falls Verbindung bereit, andernfalls false
     */
    public boolean isReady() {
        return con.isReady();
    }

    /**
     * Beendet die Verbindungs zum Server.
     */
    public void close() {
        con.close();
    }

    /**
     * Durchlï¿½uft eine Liste von Aufgaben und fordert diese vom Server an.
     */
    private int getPos(char a) {
        String[][] alphabetsg = {{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"},
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}};
        for (int i = 0; i < 26; i++)
            if (alphabetsg[0][i].charAt(0) == a || alphabetsg[1][i].charAt(0) == a) return i;
        return -1;
    }

    private int getASCII(char a) {
        String[][] alphabetsg = {{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"},
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}};
        for (int i = 0; i < 26; i++) {
            if (alphabetsg[0][i].charAt(0) == a) return 97 + i;
            if (alphabetsg[1][i].charAt(0) == a) return 65 + i;
        }
        return -1;
    }

    private char getCharfromASCII(int a) {
        char[] alphabetsg = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (int i = 0; i < alphabetsg.length; i++)
            if (this.getASCII(alphabetsg[i]) == a) return alphabetsg[i];
        return '*';
    }

    private String decToHex(int dec) {
        int max = 0;
        String solution = "";
        while (this.pow(16, max) < dec) max++;
        max--;
        for (int i = max; i >= 0; i--) {
            switch (dec / this.pow(16, i)) {
                case 0:
                    solution += "0";
                    break;
                case 1:
                    solution += "1";
                    break;
                case 2:
                    solution += "2";
                    break;
                case 3:
                    solution += "3";
                    break;
                case 4:
                    solution += "4";
                    break;
                case 5:
                    solution += "5";
                    break;
                case 6:
                    solution += "6";
                    break;
                case 7:
                    solution += "7";
                    break;
                case 8:
                    solution += "8";
                    break;
                case 9:
                    solution += "9";
                    break;
                case 10:
                    solution += "a";
                    break;
                case 11:
                    solution += "b";
                    break;
                case 12:
                    solution += "c";
                    break;
                case 13:
                    solution += "d";
                    break;
                case 14:
                    solution += "e";
                    break;
                case 15:
                    solution += "f";
                    break;
                default:
            }
            dec = this.modulo(dec, this.pow(16, i));
        }
        return solution;
    }

    private int pow(int base, int exponent) {
        int solution = 1;
        for (int i = 0; i < exponent; i++)
            solution *= base;
        return solution;
    }

    private int strToInt(String str) {
        int j = 1;
        int solution = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            switch (str.charAt(i)) {
                case '0':
                    solution += 0 * 10 * j;
                    break;
                case '1':
                    solution += j == 1 ? 1 : 1 * j;
                    break;
                case '2':
                    solution += j == 1 ? 2 : 2 * j;
                    break;
                case '3':
                    solution += j == 1 ? 3 : 3 * j;
                    break;
                case '4':
                    solution += j == 1 ? 4 : 4 * j;
                    break;
                case '5':
                    solution += j == 1 ? 5 : 5 * j;
                    break;
                case '6':
                    solution += j == 1 ? 6 : 6 * j;
                    break;
                case '7':
                    solution += j == 1 ? 7 : 7 * j;
                    break;
                case '8':
                    solution += j == 1 ? 8 : 8 * j;
                    break;
                case '9':
                    solution += j == 1 ? 9 : 9 * j;
                    break;
                default:
                    solution = -1;
            }
            j *= 10;
        }
        return solution;
    }

    private int hexToDec(String hex) {
        int solution = 0;
        for (int j = 0; j < hex.length(); j++)
            switch (hex.charAt(j)) {
                case '0':
                    solution += 0 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '1':
                    solution += 1 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '2':
                    solution += 2 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '3':
                    solution += 3 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '4':
                    solution += 4 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '5':
                    solution += 5 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '6':
                    solution += 6 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '7':
                    solution += 7 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '8':
                    solution += 8 * this.pow(16, hex.length() - 1 - j);
                    break;
                case '9':
                    solution += 9 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'a':
                    solution += 10 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'b':
                    solution += 11 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'c':
                    solution += 12 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'd':
                    solution += 13 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'e':
                    solution += 14 * this.pow(16, hex.length() - 1 - j);
                    break;
                case 'f':
                    solution += 15 * this.pow(16, hex.length() - 1 - j);
                    break;
                default:
            }
        return solution;
    }

    private String hexToBin(String hex) {
        String solution = "";
        for (int j = 0; j < hex.length(); j++)
            switch (hex.charAt(j)) {
                case '0':
                    solution += "0000";
                    break;
                case '1':
                    solution += "0001";
                    break;
                case '2':
                    solution += "0010";
                    break;
                case '3':
                    solution += "0011";
                    break;
                case '4':
                    solution += "0100";
                    break;
                case '5':
                    solution += "0101";
                    break;
                case '6':
                    solution += "0110";
                    break;
                case '7':
                    solution += "0111";
                    break;
                case '8':
                    solution += "1000";
                    break;
                case '9':
                    solution += "1001";
                    break;
                case 'a':
                    solution += "1010";
                    break;
                case 'b':
                    solution += "1011";
                    break;
                case 'c':
                    solution += "1100";
                    break;
                case 'd':
                    solution += "1101";
                    break;
                case 'e':
                    solution += "1110";
                    break;
                case 'f':
                    solution += "1111";
                    break;
                default:
            }
        return solution;
    }

    private String binToHex(String bin) {
        while (this.modulo(bin.length(), 4) != 0) {
            bin = "0" + bin;
        }
        String solution = "";
        String save = "";
        for (int j = 0; j < bin.length() / 4; j++) {
            save = bin.substring(j * 4, j * 4 + 4);
            switch (save) {
                case "0000":
                    solution += "0";
                    break;
                case "0001":
                    solution += "1";
                    break;
                case "0010":
                    solution += "2";
                    break;
                case "0011":
                    solution += "3";
                    break;
                case "0100":
                    solution += "4";
                    break;
                case "0101":
                    solution += "5";
                    break;
                case "0110":
                    solution += "6";
                    break;
                case "0111":
                    solution += "7";
                    break;
                case "1000":
                    solution += "8";
                    break;
                case "1001":
                    solution += "9";
                    break;
                case "1010":
                    solution += "a";
                    break;
                case "1011":
                    solution += "b";
                    break;
                case "1100":
                    solution += "c";
                    break;
                case "1101":
                    solution += "d";
                    break;
                case "1110":
                    solution += "e";
                    break;
                case "1111":
                    solution += "f";
                    break;
                default:
            }
        }
        return solution;
    }

    private int fourBitToInt(String bits) {
        int result = 0;
        for (int i = 0; i < 4; i++)
            switch (i) {
                case 0:
                    result += bits.charAt(i) == '1' ? 8 : 0;
                    break;
                case 1:
                    result += bits.charAt(i) == '1' ? 4 : 0;
                    break;
                case 2:
                    result += bits.charAt(i) == '1' ? 2 : 0;
                    break;
                case 3:
                    result += bits.charAt(i) == '1' ? 1 : 0;
                    break;
            }
        return result;
    }

    private int byteToDec(String bits) {
        while (bits.length() < 8) {
            bits = "0" + bits;
        }
        int result = 0;
        for (int i = 0; i < 8; i++)
            switch (i) {
                case 0:
                    result += bits.charAt(i) == '1' ? 128 : 0;
                    break;
                case 1:
                    result += bits.charAt(i) == '1' ? 64 : 0;
                    break;
                case 2:
                    result += bits.charAt(i) == '1' ? 32 : 0;
                    break;
                case 3:
                    result += bits.charAt(i) == '1' ? 16 : 0;
                    break;
                case 4:
                    result += bits.charAt(i) == '1' ? 8 : 0;
                    break;
                case 5:
                    result += bits.charAt(i) == '1' ? 4 : 0;
                    break;
                case 6:
                    result += bits.charAt(i) == '1' ? 2 : 0;
                    break;
                case 7:
                    result += bits.charAt(i) == '1' ? 1 : 0;
                    break;
            }
        return result;
    }

    private String decToFourBit(int number) {
        String solution = "";
        switch (number) {
            case 0:
                solution += "0000";
                break;
            case 1:
                solution += "0001";
                break;
            case 2:
                solution += "0010";
                break;
            case 3:
                solution += "0011";
                break;
            case 4:
                solution += "0100";
                break;
            case 5:
                solution += "0101";
                break;
            case 6:
                solution += "0110";
                break;
            case 7:
                solution += "0111";
                break;
            case 8:
                solution += "1000";
                break;
            case 9:
                solution += "1001";
                break;
            case 10:
                solution += "1010";
                break;
            case 11:
                solution += "1011";
                break;
            case 12:
                solution += "1100";
                break;
            case 13:
                solution += "1101";
                break;
            case 14:
                solution += "1110";
                break;
            case 15:
                solution += "1111";
                break;
            default:
        }
        return solution;
    }

    private String decToByte(int number) {
        String solution = "";
        solution += number / 128 == 1 ? 1 : 0;
        number = this.modulo(number, 128);
        solution += number / 64 == 1 ? 1 : 0;
        number = this.modulo(number, 64);
        solution += number / 32 == 1 ? 1 : 0;
        number = this.modulo(number, 32);
        solution += number / 16 == 1 ? 1 : 0;
        number = this.modulo(number, 16);
        solution += number / 8 == 1 ? 1 : 0;
        number = this.modulo(number, 8);
        solution += number / 4 == 1 ? 1 : 0;
        number = this.modulo(number, 4);
        solution += number / 2 == 1 ? 1 : 0;
        number = this.modulo(number, 2);
        solution += number == 1 ? 1 : 0;
        return solution;
    }

    private String xor(String bina, String binb) {
        while (bina.length() != binb.length())
            if (bina.length() < binb.length())
                bina = 0 + bina;
            else
                binb = 0 + binb;
        String solution = "";
        for (int j = 0; j < bina.length(); j++)
            if (bina.charAt(j) == binb.charAt(j))
                solution += "0";
            else solution += "1";
        return solution;
    }

    private int modulo(int a, int b) {
        while (a < 0) a += b;
        while ((a - b) >= 0) a -= b;
        return a;
    }

    private boolean isPrime(int a) {
        for (int i = 2; i < a / 2; i++) {
            if (this.modulo(a, i) == 0) return false;
        }
        return true;
    }

    private String primfakt(int a) {
        if (a > 1) {
            String solution = "";
            while (a > 1)
                for (int i = 2; i <= a; i++)
                    if (this.modulo(a, i) == 0 && this.isPrime(i)) {
                        if (a != i) solution += i + "*";
                        else solution += i;
                        a /= i;
                        i = a + 1;
                    }
            return solution;
        }
        return "";
    }

    private String getRoundKey(String key, int round) {
        String keyTotal = "";
        int shiftsForRound = 0;
        int[] shifts = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        int[] codeCblock = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36};
        int[] codeDblock = {63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
        int[] permutatePC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
        char[] Cblock = new char[28];
        char[] Dblock = new char[28];
        char[] shiftedTotal = new char[56];
        for (int i = 0; i < codeCblock.length; i++) {
            Cblock[i] = key.charAt(codeCblock[i] - 1);
            Dblock[i] = key.charAt(codeDblock[i] - 1);
        }
        for (int i = 0; i < shifts.length && i < round; i++)
            shiftsForRound += shifts[i];
        char[] shiftedKeyC = this.shiftKey(Cblock, shiftsForRound);
        char[] shiftedKeyD = this.shiftKey(Dblock, shiftsForRound);
        for (int i = 0; i < shiftedKeyC.length; i++) {
            shiftedTotal[i] = shiftedKeyC[i];
            shiftedTotal[i + Cblock.length] = shiftedKeyD[i];
        }
        for (int i = 0; i < permutatePC2.length; i++) keyTotal += shiftedTotal[permutatePC2[i] - 1];
        return keyTotal;
    }

    private char[] shiftKey(char[] key, int number) {
        if (number == 1) {
            char carry = key[0];
            for (int i = 0; i < key.length - 1; i++) key[i] = key[i + 1];
            key[key.length - 1] = carry;
        } else
            for (int i = 0; i < number; i++) key = this.shiftKey(key, 1);
        return key;
    }

    private char[] rblockberechnung(char[] block, int rounds, String key) {
        int[] expandcodec = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
        int[] permutation = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
        char[] total = block;
        char[] rblock = new char[total.length / 2];
        char[] rblockxorkey = new char[48];
        char[] rblocknew;
        String lblock = "";
        String rblockblowed = "";
        String rblockpermu = "";
        String roundkey = key;
        for (int j = 0; j < total.length / 2; j++) {
            rblock[j] = total[j + total.length / 2];
            lblock += total[j];
        }
        for (int i = 0; i < rounds; i++) {
            rblockblowed = "";
            rblockpermu = "";
            for (int j = 0; j < 48; j++) rblockblowed += rblock[expandcodec[j] - 1];
            rblockblowed = this.xor(rblockblowed, roundkey);
            for (int j = 0; j < 48; j++) rblockxorkey[j] = rblockblowed.charAt(j);
            rblocknew = this.sboxes(rblockxorkey);
            for (int j = 0; j < rblocknew.length; j++) rblockpermu += rblocknew[permutation[j] - 1];
            rblockpermu = this.xor(rblockpermu, lblock);
            lblock = "";
            for (int j = 0; j < rblock.length; j++) {
                lblock += rblock[j];
                rblock[j] = rblockpermu.charAt(j);
            }
        }
        return rblock;
    }

    private char[] sboxes(char[] block) {
        int[][] s1table = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
        int[][] s2table = {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
        int[][] s3table = {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
        int[][] s4table = {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
        int[][] s5table = {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
        int[][] s6table = {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
        int[][] s7table = {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
        int[][] s8table = {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
        char[][] inputblock = new char[8][6];
        char[] result = new char[32];
        String output = "";
        int counter = 0;
        for (int i = 0; i < inputblock.length; i++)
            for (int j = 0; j < inputblock[0].length; j++) {
                inputblock[i][j] = block[counter];
                counter++;
            }
        output += this.decToFourBit(s1table[this.fourBitToInt("00" + inputblock[0][0] + inputblock[0][5])][this.fourBitToInt("" + inputblock[0][1] + inputblock[0][2] + inputblock[0][3] + inputblock[0][4])]);
        output += this.decToFourBit(s2table[this.fourBitToInt("00" + inputblock[1][0] + inputblock[1][5])][this.fourBitToInt("" + inputblock[1][1] + inputblock[1][2] + inputblock[1][3] + inputblock[1][4])]);
        output += this.decToFourBit(s3table[this.fourBitToInt("00" + inputblock[2][0] + inputblock[2][5])][this.fourBitToInt("" + inputblock[2][1] + inputblock[2][2] + inputblock[2][3] + inputblock[2][4])]);
        output += this.decToFourBit(s4table[this.fourBitToInt("00" + inputblock[3][0] + inputblock[3][5])][this.fourBitToInt("" + inputblock[3][1] + inputblock[3][2] + inputblock[3][3] + inputblock[3][4])]);
        output += this.decToFourBit(s5table[this.fourBitToInt("00" + inputblock[4][0] + inputblock[4][5])][this.fourBitToInt("" + inputblock[4][1] + inputblock[4][2] + inputblock[4][3] + inputblock[4][4])]);
        output += this.decToFourBit(s6table[this.fourBitToInt("00" + inputblock[5][0] + inputblock[5][5])][this.fourBitToInt("" + inputblock[5][1] + inputblock[5][2] + inputblock[5][3] + inputblock[5][4])]);
        output += this.decToFourBit(s7table[this.fourBitToInt("00" + inputblock[6][0] + inputblock[6][5])][this.fourBitToInt("" + inputblock[6][1] + inputblock[6][2] + inputblock[6][3] + inputblock[6][4])]);
        output += this.decToFourBit(s8table[this.fourBitToInt("00" + inputblock[7][0] + inputblock[7][5])][this.fourBitToInt("" + inputblock[7][1] + inputblock[7][2] + inputblock[7][3] + inputblock[7][4])]);
        for (int i = 0; i < 32; i++) result[i] = output.charAt(i);
        return result;
    }

    private char[] permutadeIP(String block) {
        int[] permutatecodecIP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
        char[] permutation = new char[permutatecodecIP.length];
        for (int i = 0; i < permutatecodecIP.length; i++) permutation[i] = block.charAt(permutatecodecIP[i] - 1);
        return permutation;
    }

    private String multiplication(String a, String b) {
        String[] solutions = new String[b.length()];
        for (int i = 0; i < solutions.length; i++)
            if (b.charAt(i) == '0') solutions[i] = "0";
            else {
                solutions[i] = a;
                for (int j = solutions.length - 1 - i; j > 0; j--)
                    solutions[i] += "0";
            }
        for (int i = 1; i < solutions.length; i++)
            solutions[0] = this.xor(solutions[0], solutions[i]);
        return solutions[0];
    }

    private String multiplicationGF8(String a, String b) {
        String bina = this.hexToBin(a);
        String binb = this.hexToBin(b);
        String solution = "";
        solution = this.multiplication(bina, binb);
        solution = this.modulomx(solution);
        while (solution.length() < 8)
            solution = "0" + solution;
        return this.binToHex(solution);
    }

    private String modulomx(String a) {
        String mx = "100011011";
        while (a.length() > 0 && a.charAt(0) == '0')
            a = a.substring(1);
        if (a.length() <= 8) return a;
        else {
            String solution = "1";
            for (int i = 1; i < a.length() - 8; i++)
                solution += "0";
            solution = this.multiplication(solution, mx);
            solution = this.xor(solution, a);
            return this.modulomx(solution);
        }
    }

    private String rijndaelSBox(String a) {
        String[][] sBox = {
                {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
                {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"},
        };
        return sBox[this.fourBitToInt(this.hexToBin("" + a.charAt(0)))][this.fourBitToInt(this.hexToBin("" + a.charAt(1)))];
    }

    private String[] get3Keys128(String key) {
        String[][] rcon = {
                {"01", "02", "04", "08", "10", "20", "40", "80", "1b", "36"},
                {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
                {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"},
                {"00", "00", "00", "00", "00", "00", "00", "00", "00", "00"}
        };
        String[] keys = {"", "", ""};
        String[][] allkeys = new String[16][4];
        for (int i = 0; i < 4; i++) {
            allkeys[0][i] = key.substring(i * 2, i * 2 + 2);
            allkeys[1][i] = key.substring(i * 2 + 8, i * 2 + 10);
            allkeys[2][i] = key.substring(i * 2 + 16, i * 2 + 18);
            allkeys[3][i] = key.substring(i * 2 + 24, i * 2 + 26);
        }
        for (int i = 4; i < allkeys.length; i++)
            for (int j = 0; j < 4; j++)
                if (this.modulo(i, 4) != 0) {
                    allkeys[i][j] = this.binToHex(this.xor(this.hexToBin(allkeys[i - 4][j]), this.hexToBin(allkeys[i - 1][j])));

                } else {
                    String[] subword = {allkeys[i - 1][1], allkeys[i - 1][2], allkeys[i - 1][3], allkeys[i - 1][0]};
                    allkeys[i][j] = this.binToHex(this.xor(this.xor(this.hexToBin(allkeys[i - 4][j]), this.hexToBin(this.rijndaelSBox(subword[j]))), this.hexToBin(rcon[j][i / 4 - 1])));
                }
        for (int i = 4; i < 8; i++)
            for (int j = 0; j < 4; j++) {
                keys[0] += allkeys[i][j];
                keys[1] += allkeys[i + 4][j];
                keys[2] += allkeys[i + 8][j];
            }
        return keys;
    }

    private String mixColumns(String text) {
        String[][] cx = {
                {"02", "03", "01", "01"},
                {"01", "02", "03", "01"},
                {"01", "01", "02", "03"},
                {"03", "01", "01", "02"}
        };
        String[][] input = new String[4][4];
        String[][] output = new String[4][4];
        String result = "";
        for (int i = 0; i < 4; i++) {
            input[0][i] = text.substring(i * 2, i * 2 + 2);
            input[1][i] = text.substring(i * 2 + 8, i * 2 + 10);
            input[2][i] = text.substring(i * 2 + 16, i * 2 + 18);
            input[3][i] = text.substring(i * 2 + 24, i * 2 + 26);
        }
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[0].length; j++)
                output[i][j] = this.binToHex(
                        this.xor(
                                this.hexToBin(
                                        this.multiplicationGF8(cx[j][3], input[i][3])),
                                this.xor(
                                        this.hexToBin(
                                                this.multiplicationGF8(cx[j][2], input[i][2]))
                                        , this.xor(
                                                this.hexToBin(
                                                        this.multiplicationGF8(cx[j][0], input[i][0])),
                                                this.hexToBin(
                                                        this.multiplicationGF8(cx[j][1], input[i][1]))
                                        )
                                )
                        )
                );
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[0].length; j++)
                result += output[i][j];
        return result;
    }

    private String[][] subBytes(String[][] input) {
        String[][] result = new String[input.length][input[0].length];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[0].length; j++)
                result[i][j] = this.rijndaelSBox(input[i][j]);
        return result;
    }

    private String[][] shiftRows(String[][] input) {
        String[][] result = new String[input.length][input[0].length];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[0].length; j++)
                result[j][i] = input[this.modulo(i + j, 4)][i];
        return result;
    }

    private String sbSrMc(String text) {
        String[][] input = new String[4][4];
        String[][] output = new String[4][4];
        String result = "";
        for (int i = 0; i < 4; i++) {
            input[0][i] = text.substring(i * 2, i * 2 + 2);
            input[1][i] = text.substring(i * 2 + 8, i * 2 + 10);
            input[2][i] = text.substring(i * 2 + 16, i * 2 + 18);
            input[3][i] = text.substring(i * 2 + 24, i * 2 + 26);
        }
        output = this.subBytes(input);
        output = this.shiftRows(output);
        for (int i = 0; i < output.length; i++)
            for (int j = 0; j < output[0].length; j++)
                result += output[i][j];
        result = this.mixColumns(result);
        return result;
    }

    private String aesThreeRounds(String text, String key) {
        String result = "";
        String allover = "";
        String[] keys = this.get3Keys128(key);
        result = this.binToHex(this.xor(this.hexToBin(key), this.hexToBin(text)));
        allover += result;
        for (int i = 0; i < 2; i++) {
            result = this.sbSrMc(result);
            result = this.binToHex(this.xor(this.hexToBin(keys[i]), this.hexToBin(result)));
            allover += "_" + result;
        }
        return allover;
    }

    private int[] splitstring(String table) {
        int tablesize = 1;
        for (int i = 0; i < table.length(); i++)
            if (table.charAt(i) == '_') tablesize++;
        String[] stablestring = new String[tablesize];
        int[] stable = new int[tablesize];
        int next = 0;
        for (int i = 0; i < stablestring.length; i++)
            stablestring[i] = "";
        for (int i = 0; i < table.length(); i++)
            if (table.charAt(i) == '_') next++;
            else
                stablestring[next] += table.charAt(i);
        for (int i = 0; i < stablestring.length; i++)
            stable[i] = this.strToInt(stablestring[i]);
        return stable;
    }

    private String[] splittoStringarray(String table) {
        int tablesize = 1;
        for (int i = 0; i < table.length(); i++)
            if (table.charAt(i) == '_') tablesize++;
        String[] stablestring = new String[tablesize];
        int[] stable = new int[tablesize];
        int next = 0;
        for (int i = 0; i < stablestring.length; i++)
            stablestring[i] = "";
        for (int i = 0; i < table.length(); i++)
            if (table.charAt(i) == '_') next++;
            else
                stablestring[next] += table.charAt(i);
        return stablestring;
    }

    private int[] generationLoop(int numbers, int[] stable) {
        int[] randoms = new int[numbers];
        int i = 0;
        int j = 0;
        int l = 0;
        int swap = 0;
        for (int k = 0; k < numbers; k++) {
            i = this.modulo(i + 1, stable.length);
            j = this.modulo(j + stable[i], stable.length);
            swap = stable[i];
            stable[i] = stable[j];
            stable[j] = swap;
            randoms[l] = stable[this.modulo(stable[i] + stable[j], stable.length)];
            l++;
        }
        return randoms;
    }

    private int[] rc4keyschedule(String key) {
        int[] keytable = this.splitstring(key);
        int[] stable = new int[keytable.length];
        for (int i = 0; i < stable.length; i++) stable[i] = i;
        int j = 0;
        int swap = 0;
        for (int i = 0; i < stable.length; i++) {
            j = this.modulo(j + keytable[i] + stable[i], stable.length);
            swap = stable[i];
            stable[i] = stable[j];
            stable[j] = swap;
        }
        return stable;
    }

    private String rc4algo(String key, String text) {
        String chiffre = "";
        int[] randoms = this.generationLoop(text.length(), this.rc4keyschedule(key));
        for (int i = 0; i < text.length(); i++)
            chiffre += this.xor(this.decToByte(this.getASCII(text.charAt(i))), this.decToByte(randoms[i]));
        return chiffre;
    }

    private int powmodulo(int base, int exponent, int modulo) {
        int solution = 1;
        for (int i = 0; i < exponent; i++) {
            solution *= base;
            solution = this.modulo(solution, modulo);
        }
        return solution;
    }

    private boolean isPrimitiveRoot(int base, int modulo) {
        boolean[] check = new boolean[modulo - 1];
        for (int i = 0; i < check.length; i++)
            check[i] = false;
        int save = 0;
        for (int i = 0; i < modulo - 1; i++) {
            save = this.powmodulo(base, i, modulo);
            if (check[save - 1] == true) return false;
            check[save - 1] = true;
        }
        return true;
    }

    public void taskLoop() {
        String solution;
        Random rand = new Random();
        for (int i = 0; i < tasks.length; i++) {
            switch (tasks[i]) {
                case TASK_CLEARTEXT:
                    currentTask = con.getTask(tasks[i]);
                    solution = currentTask.getStringArray(0);
                    break;
                case TASK_XOR:
                    currentTask = con.getTask(tasks[i]);
                    String[] hexs = {currentTask.getStringArray(0), currentTask.getStringArray(1)};
                    while (hexs[0].length() != hexs[1].length())
                        if (hexs[0].length() < hexs[1].length())
                            hexs[0] = 0 + hexs[0];
                        else hexs[0] = 0 + hexs[0];

                    String[] bins = {this.hexToBin(hexs[0]), this.hexToBin(hexs[1])};
                    solution = this.xor(bins[0], bins[1]);
                    break;
                case TASK_MODULO:
                    currentTask = con.getTask(tasks[i]);
                    Integer mod = new Integer(this.modulo(currentTask.getIntArray(0), currentTask.getIntArray(1)));
                    solution = mod.toString();
                    break;

                case TASK_FACTORIZATION:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.primfakt(currentTask.getIntArray(0));
                    break;
                case TASK_VIGENERE:
                    currentTask = con.getTask(tasks[i]);
                    solution = "";
                    String vs = currentTask.getStringArray(0);
                    String vks = currentTask.getStringArray(1);
                    for (int v = 0; v < vs.length(); v++) {
                        solution += alphabet[this.modulo(this.getPos(vs.charAt(v)) - this.getPos(vks.charAt(this.modulo(v, vks.length()))) + 26, 26)];
                    }
                    break;
                case TASK_DES_KEYSCHEDULE:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.getRoundKey(currentTask.getStringArray(0), currentTask.getIntArray(0));
                    break;
                case TASK_DES_RBLOCK:
                    currentTask = con.getTask(tasks[i]);
                    char[] permutated = this.permutadeIP(currentTask.getStringArray(0));
                    char[] total = this.rblockberechnung(permutated, currentTask.getIntArray(0), "000000000000000000000000000000000000000000000000");
                    solution = "";
                    for (int i1 = 0; i1 < total.length; i1++) {
                        solution += total[i1];
                    }
                    break;
                case TASK_DES_FEISTEL:
                    currentTask = con.getTask(tasks[i]);
                    String block = currentTask.getStringArray(0);
                    char[] splitted = new char[64];
                    for (int i1 = 0; i1 < splitted.length; i1++) {
                        splitted[i1] = block.charAt(i1);
                    }
                    char[] totalfeistel = this.rblockberechnung(splitted, 1, currentTask.getStringArray(1));
                    solution = "";
                    for (int i1 = 0; i1 < totalfeistel.length; i1++) {
                        solution += totalfeistel[i1];
                    }
                    break;
                case TASK_DES_ROUND:
                    currentTask = con.getTask(tasks[i]);
                    String block2 = currentTask.getStringArray(0) + currentTask.getStringArray(1);
                    char[] splitted2 = new char[64];
                    for (int i1 = 0; i1 < splitted2.length; i1++) {
                        splitted2[i1] = block2.charAt(i1);
                    }
                    char[] totalround = this.rblockberechnung(splitted2, 1, this.getRoundKey(currentTask.getStringArray(2), currentTask.getIntArray(0)));
                    solution = "";
                    for (int i1 = 0; i1 < totalround.length; i1++) {
                        solution += totalround[i1];
                    }
                    solution = currentTask.getStringArray(1) + solution;
                    break;
                case TASK_AES_GF8:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.multiplicationGF8(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                case TASK_AES_KEYEXPANSION:
                    currentTask = con.getTask(tasks[i]);
                    String[] keys = this.get3Keys128(currentTask.getStringArray(0));
                    String key = currentTask.getStringArray(0) + "_" + keys[0] + "_" + keys[1];
                    solution = key;
                    break;
                case TASK_AES_MIXCOLUMNS:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.mixColumns(currentTask.getStringArray(0));
                    ;
                    break;
                case TASK_AES_TRANSFORMATION:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.sbSrMc(currentTask.getStringArray(0));
                    break;
                case TASK_AES_3ROUNDS:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.aesThreeRounds(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                case TASK_RC4_LOOP:
                    currentTask = con.getTask(tasks[i]);
                    int[] randoms = this.generationLoop(currentTask.getStringArray(1).length(), this.splitstring(currentTask.getStringArray(0)));
                    solution = "";
                    for (int j = 0; j < randoms.length; j++)
                        solution += randoms[j];
                    break;
                case TASK_RC4_KEYSCHEDULE:
                    currentTask = con.getTask(tasks[i]);
                    int[] stable = this.rc4keyschedule(currentTask.getStringArray(0));
                    solution = "";
                    for (int j = 0; j < stable.length; j++)
                        solution += solution == "" ? stable[j] : "_" + stable[j];
                    break;
                case TASK_RC4_ENCRYPTION:
                    currentTask = con.getTask(tasks[i]);
                    solution = this.rc4algo(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                case TASK_DIFFIEHELLMAN:
                    currentTask = con.getTask(tasks[i]);
                    solution = "";
                    int p = currentTask.getIntArray(0);
                    int g = currentTask.getIntArray(1);
                    double b = currentTask.getDoubleArray(0);
                    int a = rand.nextInt(p - 1);
                    String[] largea = {"" + this.powmodulo(g, a, p)};
                    con.sendMoreParams(currentTask, largea);
                    int k = this.powmodulo((int) b, a, p);
                    int[] code = this.splitstring(currentTask.getStringArray(0));
                    for (int j = 0; j < code.length; j++) {
                        code[j] = this.byteToDec(this.xor(this.decToByte(k), this.decToByte(code[j])));
                        solution += this.getCharfromASCII(code[j]);
                    }
                    break;
                case TASK_RSA_ENCRYPTION:
                    solution = "";
                    currentTask = con.getTask(tasks[i]);
                    String text = currentTask.getStringArray(0);
                    int e = currentTask.getIntArray(1);
                    int n = currentTask.getIntArray(0);
                    int[] textAscii = new int[text.length()];
                    for (int j = 0; j < text.length(); j++) {
                        textAscii[j] = this.powmodulo(this.getASCII(text.charAt(j)), e, n);
                        if (text.length() == j + 1) solution += textAscii[j];
                        else solution += textAscii[j] + "_";
                    }
                    break;
                case TASK_RSA_DECRYPTION:
                    solution = "";
                    int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71};
                    int[] pq = {0, 0};
                    int d = 1;
                    int[] ne = {1, 1};
                    while (d == 1 || this.modulo(d * ne[1], (pq[0] - 1) * (pq[1] - 1)) != 1) {
                        while (pq[0] * pq[1] < 130 || pq[0] == pq[1]) {
                            pq[0] = primes[rand.nextInt(20)];
                            pq[1] = primes[rand.nextInt(20)];
                        }
                        ne[0] = pq[0] * pq[1];
                        ne[1] = pq[0];
                        while (this.modulo(ne[0], ne[1]) == 0 || (pq[0] - 1) * (pq[1] - 1) < ne[1])
                            ne[1] = primes[rand.nextInt(20)];
                        d = 1;
                        while (this.modulo(d * ne[1], (pq[0] - 1) * (pq[1] - 1)) != 1 && d < (pq[0] - 1) * (pq[1] - 1))
                            d++;
                    }
                    String[] taskne = {"" + ne[0], "" + ne[1]};
                    currentTask = con.getTask(tasks[i], taskne);
                    int[] cipher = this.splitstring(currentTask.getStringArray(0));
                    for (int j = 0; j < cipher.length; j++) {
                        cipher[j] = this.powmodulo(cipher[j], d, ne[0]);
                        solution += this.getCharfromASCII(cipher[j]);
                    }
                    break;
                case TASK_ELGAMAL_ENCRYPTION:
                    currentTask = con.getTask(tasks[i]);
                    int elg_p = currentTask.getIntArray(0);
                    int alpha = currentTask.getIntArray(1);
                    int beta = currentTask.getIntArray(2);
                    int elg_k = rand.nextInt(elg_p - 2) + 1;
                    int elg_encK = this.powmodulo(beta, elg_k, elg_p);
                    int y1 = this.powmodulo(alpha, elg_k, elg_p);
                    String elg_text = currentTask.getStringArray(0);
                    solution = this.decToHex(y1) + "_";
                    int[] elg_textAscii = new int[elg_text.length()];
                    for (int j = 0; j < elg_text.length(); j++) {
                        elg_textAscii[j] = this.modulo(this.getASCII(elg_text.charAt(j)) * elg_encK, elg_p);
                        if (elg_text.length() == j + 1) solution += this.decToHex(elg_textAscii[j]);
                        else solution += this.decToHex(elg_textAscii[j]) + "_";
                    }
                    break;
                case TASK_ELGAMAL_DECRYPTION:
                    solution = "";
                    int elg_dec_p = 8;
                    while (!this.isPrime(elg_dec_p) || !this.isPrime((elg_dec_p - 1) / 2))
                        elg_dec_p = rand.nextInt(2999) + 130;
                    String[] elg_publicKey = new String[3];
                    int alpha2 = 2;
                    for (int j = alpha2; j < elg_dec_p - 2; j++) {
                        if (this.isPrimitiveRoot(alpha2, elg_dec_p)) break;
                        alpha2++;
                    }
                    int privatekey = rand.nextInt(elg_dec_p - 2) + 1;
                    elg_publicKey[0] = "" + elg_dec_p;
                    elg_publicKey[1] = "" + alpha2;
                    elg_publicKey[2] = "" + this.powmodulo(alpha2, privatekey, elg_dec_p);
                    currentTask = con.getTask(tasks[i], elg_publicKey);
                    String[] ciphertext = this.splittoStringarray(currentTask.getStringArray(0));
                    int[] ciphertextDEC = new int[ciphertext.length];
                    for (int j = 0; j < ciphertext.length; j++)
                        ciphertextDEC[j] = this.hexToDec(ciphertext[j]);
                    int invers = this.powmodulo(ciphertextDEC[0], elg_dec_p - 1 - privatekey, elg_dec_p);
                    for (int j = 1; j < ciphertextDEC.length; j++) {
                        ciphertextDEC[j] = this.modulo(ciphertextDEC[j] * invers, elg_dec_p);
                        solution += this.getCharfromASCII(ciphertextDEC[j]);
                    }
                    break;
                default:
                    currentTask = con.getTask(tasks[i]);
                    solution = "Nicht implementiert!";
            }

            if (con.sendSolution(solution))
                System.out.println("Aufgabe " + tasks[i] + ": Lï¿½sung korrekt");
            else
                System.out.println("Aufgabe " + tasks[i] + ": Lï¿½sung falsch");
        }
    }
}
