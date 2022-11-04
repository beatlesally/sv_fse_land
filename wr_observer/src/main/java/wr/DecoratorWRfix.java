package wr;

/**
 * Diese Klasse ist ein konkreter Decorator. Hier wird eine fixe Gebühr verrechnet.
 */
public class DecoratorWRfix extends DecoratorWR{

    public DecoratorWRfix(WR umrechner) {
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
        if(betrag > 5){
            return super.umrechnen(variante, betrag-5);
        }
        return super.umrechnen(variante,betrag);
    }


}
