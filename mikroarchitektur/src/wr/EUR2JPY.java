package wr;

public class EUR2JPY extends WR{

    private WR nextChain = null;

    public double umrechnen(String variante, double betrag){
        if(zustaendig(variante)){
            return betrag*getFaktor(); //Betrag mit dem Umrechnungsfaktor berechnen
        } else {
            if(nextChain != null){
                return nextChain.umrechnen(variante,betrag); //an nächstes Element weitergeben
            } else {
                return -1.0; //TODO: mit Exception ersetzen
            }
        }

    }

    @Override
    public double getFaktor() {
        return 144.589; //1 EUR = 144,589 JPY
    }

    @Override
    public boolean zustaendig(String variante) {
        if(variante.equals("EUR2JPY")){
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
