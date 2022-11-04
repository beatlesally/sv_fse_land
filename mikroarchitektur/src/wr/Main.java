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

        //Builder
        WR jpykwn = new BuilderWR.Builder("JPY2KWN").withFaktor(9.6).withNextChain(eurjpy).build();

        //Adapter
        //AdapterWR adapterWR = new AdapterWR(eurdollar); //with Chain of Responsibility
        //AdapterWR adapterWR = new AdapterWR(jpykwn); //with Builder
        AdapterWR adapterWR = new AdapterWR(dcfix); //with Decorator
        double[] betraege = new double[] {20.36,50.00,125.30,95.00};
        System.out.println(adapterWR.sammelumrechnen(betraege, "EUR2KWN"));

        try{
            //Chain of Responsibility & Template Method
            //System.out.println(eurdollar.umrechnen("EUR2WN", 10.0)); //throws Exception
            System.out.println(eurdollar.umrechnen("EUR2KWN", 10.0));

            //Decorator
            System.out.println(eurdollar.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcfix.umrechnen("EUR2DOLLAR", 25.36));
            System.out.println(dcproz.umrechnen("EUR2DOLLAR", 25.36));
            //System.out.println(mixed.umrechnen("EUR2DOLLAR", 25.36)); //zur Darbietung dass Decorator auch verschachtelt werden könnte

            //Builder
            System.out.println(jpykwn.umrechnen("EUR2JPY", 20.10));
            System.out.println(jpykwn.umrechnen("JPY2KWN", 1));
            //eurdollar.setNextWR(eurkwn); //gives sout
            //eurdollar.removeNextWR(); //gives sout
        } catch (ENoNextChainElement noNextChainElement) {
            System.out.println(noNextChainElement.getMessage());
        }



    }
}
