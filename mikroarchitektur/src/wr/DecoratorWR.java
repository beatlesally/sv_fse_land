package wr;

/**
 * Diese Klasse implementiert den abstrakten Decorator für WR.
 * Es wird die Methode {@code umrechnen} dekoriert.
 */
public abstract class DecoratorWR extends WR{

    private WR umrechner;

    public DecoratorWR(WR umrechner){
        this.umrechner = umrechner;
    }

    @Override
    public double umrechnen(String variante, double betrag) throws ENoNextChainElement {
        return umrechner.umrechnen(variante, betrag);
    }

    @Override
    public double getFaktor() {
        return umrechner.getFaktor();
    }

    @Override
    public boolean zustaendig(String variante) {
        return umrechner.zustaendig(variante);
    }
}
