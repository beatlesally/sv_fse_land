package pattern.chain_of_responsibility;

/**
 * Interface für die Wechselklassen.
 */
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain); //Setzen des nächsten Kettengliedes
    void dispense(Currency cur); //Berechnung für das jetzige Kettenglied
}
