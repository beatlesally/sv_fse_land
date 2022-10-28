package adapter;

public class MovableAdapterImpl implements MovableAdapter{
    private Movable luxuryCar;

    public MovableAdapterImpl(Movable car){
        this.luxuryCar = car;
    }


    @Override
    public double getSpeed() {
        return convertMPHtoKMH(luxuryCar.getSpeed());
    }

    private double convertMPHtoKMH(double mph){
        return mph * 1.60934;
    }
}
