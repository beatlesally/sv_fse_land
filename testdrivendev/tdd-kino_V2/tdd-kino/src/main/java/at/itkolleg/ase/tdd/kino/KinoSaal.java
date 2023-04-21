package at.itkolleg.ase.tdd.kino;

import java.util.Map;

public class KinoSaal {

    private final String name;

    private final Map<Character, Integer> reihen;

    public KinoSaal(String name, Map<Character, Integer> reihen) {
        this.name = name;
        this.reihen = reihen;
    }

    public String getName() {
        return name;
    }

    /**
     * Prüft, ob der Platz in einer bestimmten Reihe besteht
     * @param reihe Reihe vom Platz
     * @param platz Platznummer
     * @return
     */
    boolean pruefePlatz(char reihe, int platz) {
        Integer platze = reihen.get(reihe);
        System.out.println(platze);
        //wenn null zurückgegeben weil Reihe nicht existiert, mitgegebener Platz Platzanzahl überschreitet oder mitgegebener Platz = 0 ist
        if(platze == null || platz > platze || platz == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KinoSaal)) {
            return false;
        }
        return this.name.equals(((KinoSaal) obj).getName());
    }

}
