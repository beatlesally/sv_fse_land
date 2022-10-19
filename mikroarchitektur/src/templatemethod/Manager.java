package templatemethod;

/**
 * Unterklasse, die die Methode work() implementiert und die Methode relax() überschreibt.
 */
public class Manager extends Worker{
    @Override
    public void work() {
        System.out.println("I'm working manager things");
    }

    @Override
    public void relax(){ //Überschreiben des Defaults
        //super.relax();
        System.out.println("Relaxing with a glass of wine!");
    }
}
