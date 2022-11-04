package wr;

public interface IUmrechnen{
    double umrechnen(String variante, double betrag) throws ENoNextChainElement;
    double getFaktor();
    boolean zustaendig(String variante);
}
