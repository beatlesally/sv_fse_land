package wr;

public class EUR2DOLLAR extends WR{
    @Override
    public double getFaktor() {
        return 0;
    }

    @Override
    public boolean zustaendig(String variante) {
        return false;
    }
}
