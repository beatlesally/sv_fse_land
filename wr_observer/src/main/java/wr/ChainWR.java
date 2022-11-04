package wr;

/**
 * Implementiert die Zusatzfunktionen der Chain wie Hinzufügen eines neuen Kettenelements und das Entfernen eines Kettenelement,
 * jeweils am Ende der Chain.
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

    /**
     * Mit dieser Methode erhält man das nächste Kettenelement.
     * @return gibt das nächste Kettenelement vom Typ {@code WR} zurück
     */
    WR getNextChain();

}
