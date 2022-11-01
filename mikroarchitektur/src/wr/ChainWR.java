package wr;

import java.util.ArrayList;

public class ChainWR {

    private ArrayList<WR> wr = new ArrayList<>();

    /**
     * Einen weiteren Umrechner an der Kette anfügen.
     * @param umrechner ist der neue Umrechner, der angefügt wird
     */
    public void addWR(WR umrechner){
        wr.add(umrechner);
    }

    /**
     * Umrechner am Ende der Kette wird entfernt
     */
    public void removeWR(){
        wr.remove(wr.size()-1);
    }

    /**
     * Eine Kette mit fixen Umrechnern wird erstellt
     */
    public void makeStandardChain(){
        //Standardchain mit einer fixen Kettenfolge
        wr.add(new DOLLAR2POUND());
        wr.add(new EUR2YEN());
        wr.add(new EUR2DOLLAR());
    }
}
