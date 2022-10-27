package decorator;

/**
 * Dies ist die Klasse, die für das Verschnupfen einer Spielfigur zuständig ist.
 */
public class HustenDecorator extends Decorator {

    public HustenDecorator(Spielfigur s) {
        super(s);
    }

    @Override
    public void Drohe() {
        System.out.print("Hust hust. ");
        super.Drohe();
    }
}
