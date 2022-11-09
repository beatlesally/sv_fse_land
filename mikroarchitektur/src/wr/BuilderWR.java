package wr;

/**
 * Diese Klasse ist der Builder für einen speziellen Umrechner.
 * Hierbei können beliebige Umrechner erstellt werden.
 */

public class BuilderWR extends WR{

    /**
     * In dieser Klasse wird das Objekt "zusammengestöpselt" und ein Objekt erzeugt.
     */
    public static class Builder {

        private double faktor = 0.0;
        private String variante = null;
        private WR nextChain = null;

        /**
         * Konstruktor
         * @param variante wird standardmäßig angegeben
         */
        public Builder(String variante){
            this.variante = variante;
        }

        /**
         * Hinzufügen des Faktors des Umrechners
         * @param faktor Umrechnungsfaktor
         * @return dieses Element
         */
        public Builder withFaktor(double faktor){
            this.faktor = faktor;
            return this;
        }

        /**
         * Hinzufügen der nächsten Chain des Umrechners
         * @param nextChain nächstes Kettenelement
         * @return dieses Element
         */
        public Builder withNextChain(WR nextChain){
            this.nextChain = nextChain;
            return this;
        }

        /**
         * Bauen des Objekts
         * @return ein Objekt von {@code WR}
         */
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


    /**
     * Privater Konstruktor, damit ein Objekt über den Builder erzeugt werden kann;
     * jedoch privat, dass man kein Objekt ohne den Builder erzeugen kann.
     */
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
