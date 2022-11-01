package pattern.chain_of_responsibility;

/**
 * Diese Klasse stellt die Währung dar.
 */
public class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
