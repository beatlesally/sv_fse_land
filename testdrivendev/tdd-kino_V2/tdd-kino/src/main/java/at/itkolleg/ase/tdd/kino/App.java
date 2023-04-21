package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Dieses Beispiel stammt aus https://training.cherriz.de/cherriz-training/1.0.0/testen/junit5.html
 */
public class App 
{
    public static void main( String[] args )
    {
        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        KinoSaal ks = new KinoSaal("LadyX",map);

        map.put('S', 20);
        map.put('V', 34);
        KinoSaal ks2 = new KinoSaal("Captain", map);

        //Platz prüfen
        //System.out.println(ks.pruefePlatz('A',11));
        //System.out.println(ks.pruefePlatz('A',10));
        //System.out.println(ks.pruefePlatz('B',10));
        //System.out.println(ks.pruefePlatz('C',14));

        //Vorstellung anlegen
        Vorstellung v1 = new Vorstellung(ks, Zeitfenster.ABEND, LocalDate.parse("2023-03-30"), "Super Mario", 12.50f );
        Vorstellung v2 = new Vorstellung(ks, Zeitfenster.NACHMITTAG, LocalDate.parse("2023-03-30"), "Spongebob", 15.35f );
        Vorstellung v3 = new Vorstellung(ks2, Zeitfenster.NACHMITTAG, LocalDate.parse("2023-03-29"), "Spongebob 3D", 20.99f );


        //Kinoverwaltung
        KinoVerwaltung kv1 = new KinoVerwaltung();
        kv1.einplanenVorstellung(v1);
        //kv1.einplanenVorstellung(v1); //Exception: Die Vorstellung ist bereits eingeplant
        kv1.einplanenVorstellung(v2);
        kv1.einplanenVorstellung(v3);

        Ticket t1 = kv1.kaufeTicket(v1, 'A', 5, 15.30f);
        Ticket t2 = kv1.kaufeTicket(v3, 'S', 5, 30.00f);
        //Ticket t1 = kv1.kaufeTicket(v1, 'A', 15, 15.30f); //Exception: Platz existiert nicht
        //Ticket t1 = kv1.kaufeTicket(v1, 'A', 5, 3.30f); //Exception: Nicht ausreichend Geld

        System.out.println("t1: "+t1.getSaal());
        System.out.println("t2: "+t2.getSaal());
    }
}
