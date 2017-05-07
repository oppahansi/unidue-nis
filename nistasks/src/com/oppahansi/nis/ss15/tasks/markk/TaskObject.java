package com.oppahansi.nis.ss15.tasks.markk;

/**
 * Diese Klasse beinhaltet alle relevanten Informationen, die der Client
 * ben�tigt, um eine Aufgabe zu l�sen.
 * <p>
 * Bitte ver�ndern Sie diese Klasse nicht.
 */
public class TaskObject {
    /**
     * Der Aufgabentyp als Int.
     */
    private int type = 0;

    /**
     * Drei Arrays die alle relevanten Daten der Aufgabenstellung beinhalten.
     */
    private String[] sa;
    private int[] ia;
    private double[] da;

    /**
     * Liefert den Aufgabentyp
     *
     * @return Aufgabentyp als Integer
     */
    public int getType() {
        return this.type;
    }

    /**
     * Setzt den Aufgabentyp
     *
     * @param t Aufgabentyp
     */
    public void setType(int t) {
        this.type = t;
    }

    /**
     * F�llt das String-Array
     *
     * @param s   einzuf�gender String
     * @param pos Position im Array
     */
    public void setStringArray(String s, int pos) {
        if (pos < this.sa.length)
            this.sa[pos] = s;
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
    }

    /**
     * Liefert einen spezifischen Wert aus dem String-Array
     *
     * @param pos Position im String-Array
     * @return String der Array-Position pos
     */
    public String getStringArray(int pos) {
        if (this.sa == null)
            return null;
        if (pos < this.sa.length)
            return this.sa[pos];
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
        return null;
    }

    /**
     * Liefert das komplette String-Array
     *
     * @return sa
     */
    public String[] getStringArray() {
        return sa;
    }

    protected void setStringArray(String[] sa) {
        this.sa = sa;
    }

    /**
     * F�llt das Int-Array
     *
     * @param i   einzuf�gende Zahl
     * @param pos Position im Array
     */
    public void setIntArray(int i, int pos) {
        if (pos < this.ia.length)
            this.ia[pos] = i;
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
    }

    /**
     * Liefert spezifisches Wert aus dem Int-Array
     *
     * @param pos Position im Array
     * @return Int der Position pos
     */
    public int getIntArray(int pos) {
        if (this.ia == null)
            return Integer.MIN_VALUE;
        if (pos < this.ia.length)
            return this.ia[pos];
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
        return Integer.MIN_VALUE;
    }

    /**
     * Liefert das komplette Int-Array
     *
     * @return Int-Array
     */
    public int[] getIntArray() {
        return ia;
    }

    protected void setIntArray(int[] ia) {
        this.ia = ia;
    }

    /**
     * F�llt das Double-Array
     *
     * @param d   einzuf�gende Zahl
     * @param pos Position im Array
     */
    public void setDoubleArray(double d, int pos) {
        if (pos < this.da.length)
            this.da[pos] = d;
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
    }

    /**
     * Liefert einen spezifischen Wert des Double-Arrays
     *
     * @param pos Position im Array
     * @return Double der Position pos
     */
    public double getDoubleArray(int pos) {
        if (this.da == null)
            return Double.MIN_VALUE;
        if (pos < this.da.length)
            return this.da[pos];
        else
            System.err.println("Position " + pos
                    + " liegt nicht im Bereich des Arrays.");
        return Double.MIN_VALUE;
    }

    /**
     * Liefert das komplette Double-Array
     *
     * @return Double-Array
     */
    public double[] getDoubleArray() {
        return da;
    }

    protected void setDoubleArray(double[] da) {
        this.da = da;
    }

    /**
     * Gibt das gesamte Task-Object aus
     */
    public void printTO() {
        System.out.println("Task-Type: " + this.type);
        if (sa != null)
            for (int i = 0; i < this.sa.length; i++)
                System.out.println("SA" + i + ": " + sa[i]);
        if (da != null)
            for (int i = 0; i < this.da.length; i++)
                System.out.println("DA" + i + ": " + da[i]);
        if (ia != null)
            for (int i = 0; i < this.ia.length; i++)
                System.out.println("IA" + i + ": " + ia[i]);
    }
}
