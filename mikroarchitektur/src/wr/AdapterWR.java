package wr;

public class AdapterWR implements ISammelumrechnung{

    private WR umrechner;

    public AdapterWR(WR umrechner){
        this.umrechner = umrechner;
    }

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
