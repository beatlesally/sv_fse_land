package observer;

/**
 * Eine spezielle Implementierung des Interface IBeobachter.
 */
public class Anton implements IBeobachter {
    @Override
    public void aktualisieren(int i) {
        System.out.println("Das ist Beobachter Anton mit dem Wert "+i);
    }
}
