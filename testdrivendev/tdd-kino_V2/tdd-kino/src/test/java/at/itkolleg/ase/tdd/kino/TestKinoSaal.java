package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestKinoSaal {

    private KinoSaal kinosaalOriginal;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinosaalOriginal = new KinoSaal("KS2", map);

    }

    @Test
    void testKinosaalGetName()
    {
        assertEquals("KS2",kinosaalOriginal.getName());
    }

    @Test
    void testKinosaalCheckPlatz()
    {
        assertFalse(kinosaalOriginal.pruefePlatz('D', 8 ));  //Reihe existiert nicht
        assertFalse(kinosaalOriginal.pruefePlatz('A', 12));  //Platznummer größer als Reihe
        assertTrue(kinosaalOriginal.pruefePlatz('B', 2));   //Platz & Reihe existieren
    }
}
