package templatemethod;

//UML: https://sourcemaking.com/design_patterns/template_method
public class Main {
    public static void main(String[] args) {

        Worker w[] = new Worker[3];
        w[0] = new Manager();
        w[1] = new Lumberjill();
        w[2] = new Author();

        for (Worker worker: w) {
            System.out.println(worker.getClass().getSimpleName());
            worker.eatBreakfast();
            worker.goToWork();
            worker.work();
            worker.relax();
            System.out.println();
        }


    }
}
