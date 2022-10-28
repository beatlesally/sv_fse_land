package adapter;

/**
 * Diese Klasse konkretisiert Movable als ein bestimmtes Auto. Hier ist die Geschwindigkeit in MPH fix festgelegt.
 */
public class LuxuryCar implements Movable{
    @Override
    public double getSpeed() {
        return 269; //MPH
    }
}
