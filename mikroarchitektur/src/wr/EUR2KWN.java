package wr;

/**
 * Diese Klasse ist für die Umrechnung von € zu Südkoreanische Won verantwortlich.
 */
public class EUR2KWN extends WR{

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


}
