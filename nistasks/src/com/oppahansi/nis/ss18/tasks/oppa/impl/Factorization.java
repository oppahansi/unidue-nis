package com.oppahansi.nis.ss18.tasks.oppa.impl;

import com.oppahansi.nis.ss18.tasks.oppa.util.Toolbox;

/**
 * <p>This class represents the implementation of factorization algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Factorization {

    /**
     * Method to factorize the given number into prime factors.
     *
     * @param number Number to factorize
     * @return
     */
    public static String factorize(int number) {
        StringBuilder result = new StringBuilder();

        for (int i = 2; i < number; ) {
            if (Toolbox.isPrim(i) && number % i == 0 && !Toolbox.isPrim(number)) {
                result.append(i).append("*");
                number /= i;
            } else {
                i++;
            }
        }

        result.append(number);

        return result.toString();
    }
}
