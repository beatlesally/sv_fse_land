package wr;

import java.util.Observable;

/**
 * Diese Klasse stellt das Grundgerüst für einen speziellen Umrechner dar.
 * Hier sind die Methoden, die die Unterklassen benötigen, bereits ausprogrammiert.
 */
public abstract class WR extends Observable implements IUmrechnen, ChainWR {

    private WR nextChain = null; //nächstes Kettenelement

    public double umrechnen(String variante, double betrag) throws ENoNextChainElement{
        if(zustaendig(variante)){
            double umrechnung = betrag*getFaktor();

            //settings die für den Observer übergeben werden
            String[] settings = new String[] {""+variante, ""+betrag, ""+umrechnung};

            setChanged(); //es tritt eine Veränderung auf, "Flag" wird gesetzt
            notifyObservers(settings); //darauf werden die Observer verständigt, die das Objekt beobachten
            clearChanged(); //"Flag" wird gelöscht

            return umrechnung; //Betrag mit dem Umrechnungsfaktor berechnen
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
