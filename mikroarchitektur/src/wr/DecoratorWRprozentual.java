package wr;

/**
 * Diese Klasse ist ein konkreter Decorator, der eine Gebühr prozentuell verrechnet.
 * Das sind 0,5% des Ausgangsbetrag.
 */
public class DecoratorWRprozentual extends DecoratorWR{

    public DecoratorWRprozentual(WR umrechner) {
        super(umrechner);
    }

    /**
     * In dieser Methode wird die Verrechnung der Gebühr vorgenommen. Es werden lediglich die Verrechnung der Gebühr hinzugefügt.
     * Der Umrechnungsvorgang wird vom Umrechner übernommen.
     * @param variante wie soll umgerechnet werden (z.B. von Euro zu Dollar -> EUR2DOLLAR)
     * @param betrag Geldbetrag der umgerechnet werden soll
     * @return gibt den Aufruf zurück an super und lässt dort die Umrechnung vornehmen mit dem verminderten Betrag
     * @throws ENoNextChainElement
     */
    @Override
    public double umrechnen(String variante, double betrag) throws ENoNextChainElement {
        return super.umrechnen(variante, betrag-(betrag*0.005));
    }

}
