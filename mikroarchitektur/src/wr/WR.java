package wr;

public abstract class WR implements IUmrechnen, ChainWR{

    private WR firstChain = new EUR2DOLLAR();
    public double umrechnen(String variante, double betrag){
        return firstChain.umrechnen(variante, betrag); //Anstoßen der Chain; Berechnungsbeginn
    }

}
