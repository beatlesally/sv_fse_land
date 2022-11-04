package wr;

public class DecoratorWRprozentual extends DecoratorWR{

    public DecoratorWRprozentual(WR umrechner) {
        super(umrechner);
    }

    @Override
    public double umrechnen(String variante, double betrag) throws ENoNextChainElement {
        return super.umrechnen(variante, betrag-(betrag*0.005));
    }

}
