package wr;

public class Main {
    public static void main(String[] args) {

        WR eurdollar = new EUR2DOLLAR();
        WR eurjpy = new EUR2JPY();
        WR eurkwn = new EUR2KWN();
        eurdollar.setNextWR(eurjpy);
        eurjpy.setNextWR(eurkwn);
        //eurdollar.setNextWR(eurkwn); //gives sout because next Chain is already set/not on chain end
        //eurdollar.removeNextWR(); //gives sout because not on chain end

        DecoratorWR dcfix = new DecoratorWRfix(eurdollar);
        DecoratorWR dcproz = new DecoratorWRprozentual(eurdollar);

        try{
            //System.out.println(eurdollar.umrechnen("EUR2WN", 10.0)); //throws Exception
            System.out.println(eurdollar.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcfix.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcproz.umrechnen("EUR2DOLLAR", 25.36));
        } catch (ENoNextChainElement noNextChainElement) {
            System.out.println(noNextChainElement.getMessage());
        }

    }
}
