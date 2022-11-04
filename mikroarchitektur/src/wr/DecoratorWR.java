package wr;

public abstract class DecoratorWR extends WR{

    private WR umrechner;

    public DecoratorWR(WR umrechner){
        this.umrechner = umrechner;
    }

    @Override
    public double umrechnen(String variante, double betrag) throws ENoNextChainElement {
        return umrechner.umrechnen(variante, betrag);
    }
}
