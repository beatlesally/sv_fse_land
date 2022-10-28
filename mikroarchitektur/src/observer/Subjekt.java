package observer;

import java.util.ArrayList;

/**
 * Diese Klasse ist eine Konkretisierung von ISubjekt. In einer Arraylist werden die Beobachter gespeichert und
 * bei Änderungen informiert.
 */
public class Subjekt implements ISubjekt{

    private ArrayList<IBeobachter> beobachter = new ArrayList<>();
    private int wert = 0;

    @Override
    public void registrieren(IBeobachter b) {
        beobachter.add(b);
    }

    @Override
    public void entfernen(int place) {
       beobachter.remove(place);
    }

    @Override
    public void benachrichtigen() {
        for(IBeobachter b : beobachter){
            b.aktualisieren(wert);
        }
    }

    @Override
    public void setzeWert(int wert) {
        this.wert = wert;
        benachrichtigen();
    }
}
