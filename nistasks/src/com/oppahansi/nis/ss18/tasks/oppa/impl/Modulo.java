package com.oppahansi.nis.ss18.tasks.oppa.impl;

/**
 * <p>This class represents the implementation of modulo algorithm</p>
 *
 * @author Alexander Schellenberg
 */
public class Modulo {
    public static String mod(int devident, int devisor) {
        try {
            return (devident - (devisor * (devident / devisor))) + "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
