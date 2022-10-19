package templatemethod;

/**
 * Diese abstrakte Klasse implementiert die Vorlage des Worker. Dabei sind manche Methoden bereits ausimplementiert (default)
 * andere müssen noch ausimplementiert werden.
 */
public abstract class Worker {

    public void eatBreakfast(){
        System.out.println("Eat for Breakfast cereals und tea");
    }

    public void goToWork(){
        System.out.println("Go To Work by bus of course");
    }

    public void relax(){
        System.out.println("Relax with a book");
    }

    public abstract void work();

}
