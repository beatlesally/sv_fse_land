package wr;

/**
 * Diese Klasse ist für die Umrechnung von € zu Dollar verantwortlich.
 */
public class EUR2DOLLAR extends WR {

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


}
