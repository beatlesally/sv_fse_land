package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestVorstellung {

    private KinoSaal kinosaalOriginal;
    private Vorstellung vorstellung;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("KS2", map);

        //Vorstellung anlegen
        vorstellung = new Vorstellung(kinosaalOriginal, Zeitfenster.ABEND, LocalDate.parse("2023-06-05"), "Avatar 2", 23.50f);
        Ticket t = vorstellung.kaufeTicket('A', 10,60.98f);
    }

    /*Schreiben Sie einen Test, der validiert,
    dass das Anlegen einer Vorstellung korrekt funktioniert.
    Der Test sollte eine fachliche Bezeichnung haben und die Assertions
    sollten bei Validierungsfehler eine Hinweistext liefern.*/
    @Test
    void testVorstellungAnlegen()
    {
        assertSame(new Vorstellung(kinosaalOriginal, Zeitfenster.NACHT, LocalDate.parse("2023-06-05"), "Avatar 2", 23.50f).getClass(), vorstellung.getClass(), "Vorstellung konnte nicht erstellt werden!");
    }

    /*
    Schreiben Sie einen parametrisierten Test,
    der mehrere Ticketkäufe mit unterschiedlichen Parametern überprüft.
     */
    @ParameterizedTest
    @MethodSource("ticketKaufParams")
    void testParametrizedTicketKauf(char reihe, int platz, float geld) {
        Ticket t = vorstellung.kaufeTicket('A', 9,60.98f);
        assertTrue(vorstellung.kaufeTicket(reihe, platz, geld).getClass() == t.getClass());
    }

    private static Stream<Arguments> ticketKaufParams() {
        return Stream.of(
                arguments('A', 5, 40.00f),
                arguments('B', 10, 30.00f),
                arguments('C', 15, 29.00f)
        );
    }

    /*

     */



    @Test
    void testKaufeTicket_ExceptionGeld()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            vorstellung.kaufeTicket('A', 5, 10.00f);
        });
        assertTrue(exception.getMessage().contains("Nicht ausreichend Geld."));
    }

    @Test
    void testKaufeTicket_ExceptionExistiertNicht()
    {
        char reihe = 'A';
        int platz = 0;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            vorstellung.kaufeTicket('A', 0, 50.00f);
        });
        assertTrue(exception.getMessage().contains("Der Platz " + reihe + platz + " existiert nicht."));
    }

    @Test
    void testKaufeTicket_ExceptionBelegt()
    {
        char reihe = 'A';
        int platz = 10;
        Exception exception = assertThrows(IllegalStateException.class, () ->
        {
            vorstellung.kaufeTicket('A', 10, 50.00f);
        });
        assertTrue(exception.getMessage().contains("Der Platz " + reihe + platz + " ist bereits belegt."));
    }




}
