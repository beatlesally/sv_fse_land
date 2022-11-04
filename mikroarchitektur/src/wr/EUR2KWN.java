package wr;

public class EUR2KWN extends WR{

    private WR nextChain;

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
        return 1387.48; //1 EUR = 1387,48 KWN
    }

    @Override
    public boolean zustaendig(String variante) {
        if(variante.equals("EUR2KWN")){
            return true;
        }
        return false;
    }

    @Override
    public void setNextWR(WR umrechner) {
        this.nextChain = umrechner;
    }

    @Override
    public void removeNextWR() {
        this.nextChain = null;
    }

    @Override
    public WR getNextChain() {
        return this.nextChain;
    }
}
