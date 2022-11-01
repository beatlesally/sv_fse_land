package pattern.decorator;

/**
 * Diese Klasse ist für das Verschnupfen einer Spielfigur zuständig.
 */
public class SchnupfenDecorator extends Decorator{
    public SchnupfenDecorator(Spielfigur s) {
        super(s);
    }

    @Override
    public void Drohe() {
        System.out.print("Schniff. ");
        super.Drohe();
    }
}
