package observer;

//Example from https://de.wikipedia.org/wiki/Beobachter_(Entwurfsmuster)
//from C++ to Java
/**
 * Es werden die Methoden ausprobiert und beobachtet, wie die Beobachter auf Änderungen reagieren.
 */
public class Main {
    public static void main(String[] args) {
        IBeobachter anton = new Anton();
        IBeobachter berta = new Berta();

        ISubjekt subjekt = new Subjekt();
        subjekt.registrieren(anton);
        subjekt.registrieren(berta);

        subjekt.setzeWert(1);
        subjekt.entfernen(0);
        subjekt.setzeWert(2);
        subjekt.entfernen(0);
    }



}
