package wr;

public class DecoratorWRfix extends DecoratorWR{

    public DecoratorWRfix(WR umrechner) {
        super(umrechner);
    }

    @Override
    public double umrechnen(String variante, double betrag) throws ENoNextChainElement {
        if(betrag > 5){
            return super.umrechnen(variante, betrag-5);
        }
        return super.umrechnen(variante,betrag);
    }


}
