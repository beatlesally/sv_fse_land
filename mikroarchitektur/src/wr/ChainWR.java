package wr;

/**
 * Implementiert die Funktionen einer Chain. Hinzufügen eines neuen Kettenelements und das Entfernen eines Kettenelement,
 * jeweils am Ende.
 */
public interface ChainWR {

    /**
     * Einen weiteren Umrechner an der Kette anfügen.
     * @param umrechner ist der neue Umrechner, der angefügt wird
     */
    void setNextWR(WR umrechner);

    /**
     * Umrechner am Ende der Kette wird entfernt
     */
    void removeNextWR();

    WR getNextChain();

}
