package wr;

public class Main {
    public static void main(String[] args) {

        //Chain of Responsibility & Template Method
        WR eurdollar = new EUR2DOLLAR();
        WR eurjpy = new EUR2JPY();
        WR eurkwn = new EUR2KWN();
        eurdollar.setNextWR(eurjpy);
        eurjpy.setNextWR(eurkwn);
        //eurdollar.setNextWR(eurkwn); //gives sout because next Chain is already set/not on chain end
        //eurdollar.removeNextWR(); //gives sout because not on chain end

        //Decorator
        DecoratorWR dcfix = new DecoratorWRfix(eurdollar);
        DecoratorWR dcproz = new DecoratorWRprozentual(eurdollar);
        //DecoratorWR mixed = new DecoratorWRprozentual(new DecoratorWRfix(eurdollar)); //zur Darbietung dass Decorator auch verschachtelt werden könnte

        try{
            //Chain of Responsibility & Template Method
            //System.out.println(eurdollar.umrechnen("EUR2WN", 10.0)); //throws Exception
            System.out.println(eurdollar.umrechnen("EUR2KWN", 10.0));

            //Decorator
            System.out.println(eurdollar.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcfix.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcproz.umrechnen("EUR2DOLLAR", 25.36));
            //System.out.println(mixed.umrechnen("EUR2DOLLAR", 25.36)); //zur Darbietung dass Decorator auch verschachtelt werden könnte
        } catch (ENoNextChainElement noNextChainElement) {
            System.out.println(noNextChainElement.getMessage());
        }

    }
}
