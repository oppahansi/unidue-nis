package com.oppahansi.nis.ss17.tasks.oppa.impl;

/**
 * <p>This class represents the implementation of factorization algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Factorization {
    public static String factorize(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < number; ) {
            if (isPrim(i) && number % i == 0 && !isPrim(number)) {
                result.append(i).append("*");
                number /= i;
                continue;
            } else {
                i++;
            }
        }
        result.append(number);
        return result.toString();
    }

    private static boolean isPrim(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
