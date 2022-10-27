package decorator;

/**
 * Diese Klasse spezifiziert das Interface Spielfigur.
 */
public class Monster implements Spielfigur{
    @Override
    public void Drohe() {
        System.out.println("Grrrrrrrr.");
    }
}
