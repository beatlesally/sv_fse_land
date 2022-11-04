package wr;

public class EUR2TZS extends WR {
    @Override
    public double getFaktor() {
        return 2306.76; //1EUR = 2306,76 Tansania-Schilling
    }

    @Override
    public boolean zustaendig(String variante) {
        if(variante.equals("EUR2TZS")){
            return true;
        }
        return false;
    }
}
