package wr;

/**
 * Dieses Interface ist für Sammelumrechnungen zuständig.
 */

//SOLID erfüllt
public interface ISammelumrechnung {
    /**
     * Die Methode ist für die Sammelumrechnung zuständig.
     * @param betraege Array mit Beiträgen die umgerechnet werden sollen
     * @param variante die Umrechnungsvariante für die Beträge
     * @return die umgerechnete Gesamtsumme
     */
    public double sammelumrechnen(double[] betraege, String variante);
}
