package pattern.templatemethod;

/**
 * Unterklasse, die nur die Methode work() implementiert.
 */
public class Lumberjill extends Worker{

    @Override
    public void work() {
        System.out.println("I'm working in the woods");
    }
}
