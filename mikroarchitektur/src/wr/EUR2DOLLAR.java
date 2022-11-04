package wr;

public class EUR2DOLLAR extends WR {

    private WR nextChain = new EUR2JPY();

    public double umrechnen(String variante, double betrag) throws ENoNextChainElement{
        if(zustaendig(variante)){
            return betrag*getFaktor(); //Betrag mit dem Umrechnungsfaktor berechnen
        } else {
            if(nextChain != null){
                return nextChain.umrechnen(variante,betrag); //an nächstes Element weitergeben
            } else {
                throw new ENoNextChainElement();
            }

        }

    }

    @Override
    public double getFaktor() {
        return 0.978; //1 EUR = 0,978 USD
    }

    @Override
    public boolean zustaendig(String variante) {
        if(variante.equals("EUR2DOLLAR")){
            return true;
        }
        return false;
    }

    @Override
    public void setNextWR(WR umrechner) {
        this.nextChain = umrechner;
    }

    @Override
    public void removeWR() {
        this.nextChain = null;

    }
}
