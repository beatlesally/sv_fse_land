package chain_of_responsibility;

/**
 * Diese Klasse definiert die Reihefolge der Chain.
 */
public class ATMDispenseChain {

    private DispenseChain c1; //erste Kettenglied

    public ATMDispenseChain(){
        //Kettenglieder festlegen
        this.c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        //Kette zusammenhängen
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        //Dollar50Dispenser -> Dollar20Dispenser -> Dollar10Dispenser

    }

    public DispenseChain getC1() {
        return c1;
    }
}
