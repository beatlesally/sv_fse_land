package wr;

/**
 * Diese Klasse ist der Adapter, der die Verwendung von einer Sammelumrechnung
 * in Verbindung von IUmrechnen ermöglicht.
 */
public class AdapterWR implements ISammelumrechnung{

    private WR umrechner;

    /**
     * Im Konstruktor wird der Umrechner festgelegt.
     * @param umrechner ein Objekt für die Umrechnung
     */
    public AdapterWR(WR umrechner){
        this.umrechner = umrechner;
    }

    /**
     * In dieser Methode erfolgt die Sammelumrechnung. Hier wird für jeden Betrag
     * eine Umrechnung einzeln durchgeführt und das Ergebnis zum Gesamtbetrag dazugezählt.
     * @param betraege Array mit Beiträgen die umgerechnet werden sollen
     * @param variante die Umrechnungsvariante für die Beträge
     * @return den umgerechneten Gesamtbetrag
     */
    @Override
    public double sammelumrechnen(double[] betraege, String variante) {
        double gesamtbetrag = 0;
        for (double betrag:betraege) {
            try {
                gesamtbetrag+=umrechner.umrechnen(variante,betrag);
            } catch (ENoNextChainElement e) {
                System.out.println(e.getMessage());
            }
        }
        return gesamtbetrag;
    }
}
