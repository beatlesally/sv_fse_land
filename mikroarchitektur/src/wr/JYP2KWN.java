package wr;

public class JYP2KWN extends WR{

    public static class Builder {

        private double faktor = 0.0;
        private String variante = null;
        private WR nextChain = null;

        public Builder(String variante){
            this.variante = variante;
        }

        public Builder withFaktor(double faktor){
            this.faktor = faktor;
            return this;
        }

        public Builder withNextChain(WR nextChain){
            this.nextChain = nextChain;
            return this;
        }

        public JYP2KWN build(){
            JYP2KWN jypkwn = new JYP2KWN();
            jypkwn.faktor = this.faktor;
            jypkwn.variante = this.variante;
            jypkwn.nextChain = this.nextChain;

            return jypkwn;
        }
    }

    private double faktor;
    private String variante;
    private WR nextChain;

    private JYP2KWN() {}

    @Override
    public double getFaktor() {
        return 0;
    }

    @Override
    public boolean zustaendig(String variante) {
        return false;
    }

}
