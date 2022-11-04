package wr;

/**
 * Diese Klasse ist für die Umrechnung von Euro zu Japanische Yen verantwortlich.
 */
public class EUR2JPY extends WR{

    @Override
    public double getFaktor() {
        return 144.589; //1 EUR = 144,589 JPY
    }

    @Override
    public boolean zustaendig(String variante){
        if(variante.equals("EUR2JPY")){
            return true;
        }
        return false;
    }

}
