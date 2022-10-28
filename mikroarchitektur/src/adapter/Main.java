package adapter;

//Example from https://www.baeldung.com/java-adapter-pattern
public class Main {
    public static void main(String[] args) {
        Movable car1 = new LuxuryCar();
        MovableAdapter car1adapter = new MovableAdapterImpl(car1);
        System.out.println(car1adapter.getSpeed());
    }
}
