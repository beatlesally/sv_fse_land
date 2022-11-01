package pattern.templatemethod;

/**
 * Unterklasse, die nur die Methode work() implementiert.
 */
public class Author extends Worker {
    @Override
    public void work() {
        System.out.println("I'm writing a book");
    }
}
