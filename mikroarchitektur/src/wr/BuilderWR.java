package wr;

public class BuilderWR extends WR{

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

        public WR build(){
            BuilderWR wr = new BuilderWR();
            wr.faktor = this.faktor;
            wr.variante = this.variante;
            wr.setNextWR(this.nextChain);
            return wr;
        }
    }

    private double faktor;
    private String variante;


    private BuilderWR() {}

    @Override
    public double getFaktor() {
        return this.faktor;
    }

    @Override
    public boolean zustaendig(String variante) {
        if(variante.equals(this.variante)){
            return true;
        }
        return false;
    }




}
