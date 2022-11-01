package pattern.decorator;

/**
 * Dies ist der Decorator, der zwischen Spielfigur und Husten & Schnupfen zwischengeschalten wird.
 */
public class Decorator implements Spielfigur{
    private Spielfigur spielfigur;

    public Decorator(Spielfigur s){
        this.spielfigur = s;
    }

    @Override
    public void Drohe() {
        spielfigur.Drohe();
    }
}
