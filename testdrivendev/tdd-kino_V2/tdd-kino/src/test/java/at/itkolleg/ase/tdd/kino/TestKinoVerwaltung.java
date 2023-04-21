package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestKinoVerwaltung {

    @Mock
    private Ticket ticketMock = Mockito.mock(Ticket.class);

    private KinoSaal kinosaalOriginal;
    private Vorstellung vorstellung;
    private KinoVerwaltung kv;
    private Ticket ticket;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("KS2", map);

        vorstellung = new Vorstellung(kinosaalOriginal, Zeitfenster.ABEND, LocalDate.parse("2023-06-05"), "Avatar 2", 23.50f);
        kv = new KinoVerwaltung();

        ticket = kv.kaufeTicket(vorstellung,'A', 10,50.00f);
    }

    /*Mock Test*/
    @Test
    void testTicketErstellen()
    {
        Mockito.when(ticketMock.getPlatz()).thenReturn(10);
        Mockito.when(ticketMock.getSaal()).thenReturn("KS2");
        Mockito.when(ticketMock.getReihe()).thenReturn('A');

        assertEquals(ticket.getSaal(), ticketMock.getSaal());
        assertSame(ticket.getReihe(), ticketMock.getReihe());
        assertSame(ticket.getPlatz(), ticketMock.getPlatz());
    }

    /*
    Schreiben Sie einen Test, der validiert, dass das Einplanen mehrerer
    Vorstellungen korrekt funktioniert. Stellen Sie zudem sicher, dass beim
    möglichen Auftreten eines Fehlers trotzdem alle Validierungen
    ausgeführt werden.
     */
    @Test
    void testVorstellungenEinplanen()
    {
        Vorstellung v1 = new Vorstellung(kinosaalOriginal, Zeitfenster.NACHMITTAG, LocalDate.parse("2023-06-05"), "Captain America 3", 20.50f);
        Vorstellung v2 = new Vorstellung(kinosaalOriginal, Zeitfenster.ABEND, LocalDate.parse("2023-06-05"), "Captain America 3", 20.50f);
        Vorstellung v3 = new Vorstellung(kinosaalOriginal, Zeitfenster.NACHT, LocalDate.parse("2023-06-05"), "Attack on Titan Liveaction", 10.99f);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            kv.einplanenVorstellung(v1);
            kv.einplanenVorstellung(v1);
        });

        kv.einplanenVorstellung(v3);
        kv.einplanenVorstellung(v2);

        assertTrue(kv.getVorstellungen().contains(v1));
        assertTrue(kv.getVorstellungen().contains(v2));
        assertTrue(kv.getVorstellungen().contains(v3));
    }


    /*
    Schreiben Sie einen Test, der sicherstellt, dass ein Fehler geworfen wird,
    wenn eine Veranstaltung doppelt eingeplant wird.
     */
    @Test
    void testVorstellungDoppeltEinplanen()
    {
        kv.einplanenVorstellung(vorstellung);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            kv.einplanenVorstellung(vorstellung);
        });

        assertTrue(exception.getMessage().contains("Die Vorstellung ist bereits eingeplant"));
    }


    /*
    Schreiben Sie eine dynamische TestFactory die den Ticketkauf mit zufälligen Werten
    bombardiert. Der Test soll sicherstellen, dass der Ticketkauf entweder funktioniert
    oder nur einen der definierten Fehlermeldungen
    (z.B. new IllegalArgumentException("Nicht ausreichend Geld.")) ausgibt.
    Die Tests müssen reproduzierbar sein.
    *
    https://junit.org/junit5/docs/current/user-guide/#writing-tests-dynamic-tests
    */
    @TestFactory
    Stream<DynamicTest> dynamicTestsTicketKauf() {
        return IntStream.of(10, 5, 0, 2)
                .mapToObj(platz -> dynamicTest("Ticket Kauf mit Platz "+platz, () -> assertSame(kv.kaufeTicket(vorstellung, 'A', platz, 50.00f).getPlatz(), platz)));
    }


    @Test
    void testKinoVeinplanenVorstellung()
    {
        kv.einplanenVorstellung(vorstellung);
        assertTrue(kv.getVorstellungen().contains(vorstellung));
    }

    @Test
    void testKinoVkaufeTicket()
    {
        assertEquals(ticketMock.getClass(),kv.kaufeTicket(vorstellung, 'A', 5, 70.89f).getClass());
    }


}
