package pattern.observer;

/**
 * Eine spezielle Implementierung des Interface IBeobachter.
 */
public class Berta implements IBeobachter {
    @Override
    public void aktualisieren(int i) {
        System.out.println("Das ist Beobachter Berta mit dem Wert "+i);
    }
}
