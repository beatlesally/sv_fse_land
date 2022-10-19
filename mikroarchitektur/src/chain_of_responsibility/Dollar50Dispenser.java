package chain_of_responsibility;

/**
 * Diese Klasse wechselt in 50$ Scheinen.
 * Diese Klasse beinhaltet auch Codekommentare, die die Logik
 * genauer beschreibt (kann auch auf die anderen beiden Dispenser-Klassen
 * angewendet werden).
 */
public class Dollar50Dispenser implements DispenseChain{

    private DispenseChain nextChain; //das nächste Element in der Kette

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain; //nächstes Kettenelement wird gesetzt
    }

    @Override
    public void dispense(Currency cur) { //Berechnung für dieses Kettenglied
        if(cur.getAmount() >=50){
            int num = cur.getAmount()/50; //wie viele 50$ Scheine
            int remainder = cur.getAmount()%50; //Restberechnung
            System.out.println("Dispensing "+num+" 50$ note");
            //wenn nicht durch 50 teilbar, dann gib den Rest an das nächste Kettenglied weiter
            if(remainder != 0) this.nextChain.dispense(new Currency(remainder));
        } else {
            //wenn der Wert bereits kleiner 50 ist, gib ihn an das nächste Kettenglied weiter
            this.nextChain.dispense(cur);
        }
    }
}
