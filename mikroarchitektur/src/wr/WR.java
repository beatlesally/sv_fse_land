package wr;

/**
 * Diese Klasse stellt das Grundgerüst für einen speziellen Umrechner dar.
 */
public abstract class WR implements IUmrechnen, ChainWR {

    private WR nextChain = null; //nächstes Kettenelement

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
    public void setNextWR(WR umrechner) {
        if(this.nextChain == null){
            this.nextChain = umrechner;
        } else {
            System.out.println("Next Element not on end");
        }

    }

    @Override
    public void removeNextWR() {
        if(this.nextChain.getNextChain() == null){
            this.nextChain = null;
        } else {
            System.out.println("Next Element is not on end");
        }
    }

    @Override
    public WR getNextChain(){
        return this.nextChain;
    }

}
