package de.unidue.iem.tdr.nis.client;

import com.oppahansi.nis.ss17.tasks.oppa.impl.*;

/**
 * This Class is provided by by University Duisburg-Essen.
 * For learning purpose only.
 * <p>
 * Diese Klasse ermoeglicht das Abrufen von Aufgaben vom Server und die
 * Implementierung der dazugehoerigen Loesungen.
 * <p>
 * Naehere Informationen zu den anderen Klassen und den einzelnen Aufgabentypen
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
    private final String password = "MEIN_PASSWORT";
    /* Aufgaben, die bearbeitet werden sollen */
    private final int[] tasks = {
            TASK_CLEARTEXT, TASK_XOR, TASK_MODULO,
            TASK_FACTORIZATION, TASK_VIGENERE, TASK_DES_KEYSCHEDULE,
            TASK_DES_RBLOCK, TASK_DES_FEISTEL, TASK_DES_ROUND, TASK_AES_GF8,
            TASK_AES_KEYEXPANSION, TASK_AES_MIXCOLUMNS,
            TASK_AES_TRANSFORMATION, TASK_AES_3ROUNDS, TASK_RC4_LOOP,
            TASK_RC4_KEYSCHEDULE, TASK_RC4_ENCRYPTION, TASK_DIFFIEHELLMAN,
            TASK_RSA_ENCRYPTION, TASK_RSA_DECRYPTION, TASK_ELGAMAL_ENCRYPTION,
            TASK_ELGAMAL_DECRYPTION
    };
    private Connection con;
    private TaskObject currentTask;

    /**
     * Klassenkonstruktor. Baut die Verbindung zum Server auf.
     */
    public Client() {
        con = new Connection();
        if (con.auth(Credentials.MAT_NUM, Credentials.PWD)) { // use here your credentials: matrikelnr, password
            System.out.println("Anmeldung erfolgreich.");
        } else {
            System.out.println("Anmeldung nicht erfolgreich.");
        }
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
     * <p>Durchlaeuft eine Liste von Aufgaben und fordert diese vom Server an.</p>
     *
     * <p>Adjusted for my own Implementations - adjust here for your own classes / implementations.</p>
     */
    public void taskLoop() {
        String solution;
        for (int i = 7; i < 8; i++) {
            switch (tasks[i]) {
                case TASK_CLEARTEXT:
                    currentTask = con.getTask(tasks[i]);
                    solution = currentTask.getStringArray(0);
                    break;
                case TASK_XOR:
                    currentTask = con.getTask(tasks[i]);
                    solution = Xor.xorHexStrings(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                case TASK_MODULO:
                    currentTask = con.getTask(tasks[i]);
                    solution = Modulo.mod(currentTask.getIntArray(0), currentTask.getIntArray(1));
                    break;
                case TASK_FACTORIZATION:
                    currentTask = con.getTask(tasks[i]);
                    solution = Factorization.factorize(currentTask.getIntArray(0));
                    break;
                case TASK_VIGENERE:
                    currentTask = con.getTask(tasks[i]);
                    solution = Vigenere.decryptChiffreWithKey(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                case TASK_DES_KEYSCHEDULE:
                    currentTask = con.getTask(tasks[i]);
                    solution = Des.getKeyForRound(currentTask.getStringArray(0), currentTask.getIntArray(0));
                    break;
                case TASK_DES_RBLOCK:
                    currentTask = con.getTask(tasks[i]);
                    solution = Des.getRBlockForRound(currentTask.getStringArray(0), currentTask.getIntArray(0));
                    break;
                case TASK_DES_FEISTEL:
                    currentTask = con.getTask(tasks[i]);
                    solution = Des.applyFeistel(currentTask.getStringArray(0), currentTask.getStringArray(1));
                    break;
                default:
                    currentTask = con.getTask(tasks[i]);
                    solution = "Nicht implementiert!";
                    break;
            }

            if (con.sendSolution(solution)) {
                System.out.println("Aufgabe " + tasks[i] + ": L�sung korrekt");
            } else {
                System.out.println("Aufgabe " + tasks[i] + ": L�sung falsch");
            }
        }
    }
}