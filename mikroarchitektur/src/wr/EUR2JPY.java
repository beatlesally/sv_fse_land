package wr;

public class EUR2JPY extends WR{

    private WR nextChain = null;

    @Override
    public double getFaktor() {
        return 0;
    }

    @Override
    public boolean zustaendig(String variante) {
        return false;
    }

    @Override
    public void setNextWR(WR umrechner) {
        this.nextChain = umrechner;
    }

    @Override
    public void removeWR() {
        this.nextChain = null;

    }
}
