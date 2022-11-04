package wr;

public class Main {
    public static void main(String[] args) {

        WR eurdollar = new EUR2DOLLAR();
        WR eurjpy = new EUR2JPY();
        WR eurkwn = new EUR2KWN();
        eurdollar.setNextWR(eurjpy);
        eurjpy.setNextWR(eurkwn);
        try{
            System.out.println(eurdollar.umrechnen("EUR2KWN", 10.0));
        } catch (ENoNextChainElement noNextChainElement) {
            System.out.println(noNextChainElement.getMessage());
        }

    }
}
