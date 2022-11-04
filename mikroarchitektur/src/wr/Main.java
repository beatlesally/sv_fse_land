package wr;

public class Main {
    public static void main(String[] args) {

        WR umrechner = new EUR2DOLLAR();
        try{
            System.out.println(umrechner.umrechnen("EUR2", 10.0));
        } catch (ENoNextChainElement noNextChainElement) {
            System.out.println(noNextChainElement.getMessage());
        }

    }
}
