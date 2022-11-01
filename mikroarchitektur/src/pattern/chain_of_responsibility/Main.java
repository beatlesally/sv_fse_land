package pattern.chain_of_responsibility;
import java.util.Scanner;
/**
 * Diese Klasse stellt den Klient dar, der mit seiner Anfrage
 * auf den Bearbeiter (ATMDispenseChain) zugreift. Von diesem Bearbeiter
 * aus wird die Kette ausgeführt.
 */
//Code von: https://www.digitalocean.com/community/tutorials/chain-of-responsibility-design-pattern-in-java
public class Main {
    public static void main(String[] args) {
        ATMDispenseChain atm = new ATMDispenseChain();

        while(true){
            int amount = 0;
            System.out.println("Enter amount of dispense");
            Scanner input = new Scanner(System.in);
            amount = Integer.parseInt(input.nextLine());
            if(amount % 10 != 0){
                System.out.println("Amount should be multiple of 10");
                return;
            }

            //Kette beginnen
            atm.getC1().dispense(new Currency(amount));
        }
    }
}
