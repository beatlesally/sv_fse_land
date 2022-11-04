package wr;

public interface IUmrechnen{
    /**
     * In dieser Methode wird die Umrechnung sowie die Zuständigkeitsweitergabe behandelt.
     * @param variante wie soll umgerechnet werden (z.B. von Euro zu Dollar -> EUR2DOLLAR)
     * @param betrag Geldbetrag der umgerechnet werden soll
     * @return umgerechneter Wert
     * @throws ENoNextChainElement wenn das letzte Element in der Kette erreicht wurde, jedoch die Zuständigkeit an ein
     * weiteres Element weitergegeben werden möchte. Dieser Fall tritt auf, wenn für {@code variante}
     * eine Umrechnungsmethode angeben wurde, die nicht in der Kette existiert.
     *
     */
    double umrechnen(String variante, double betrag) throws ENoNextChainElement;

    /**
     * Diese Methode implementiert den Umrechnungsfaktor für eine spezifische Umrechnungsmethode.
     * @return gibt den Faktor für die Umrechnungsmethode zurück
     */
    double getFaktor();

    /**
     * Diese Methode prüft, ob das Kettenelements zuständig ist.
     * @param variante die Variante, die vom Benutzer gewählt wurde
     * @return gibt {@code true} zurück, wenn die Variante mit der Zuständigkeit des Kettenelements
     * übereinstimmt. Sonst wird {@code false} zurückgegeben.
     */
    boolean zustaendig(String variante);
}
