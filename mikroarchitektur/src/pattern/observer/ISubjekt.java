package pattern.observer;

/**
 * Das Interface stellt die Abstraktion des Subjekts dar, das Beobachter registrieren, entfernen und benachrichten kann.
 * Es kann auch den Wert für sich selbst verändern.
 */
public interface ISubjekt {
    public void registrieren(IBeobachter b);
    public void entfernen(int place);
    public void benachrichtigen();
    public void setzeWert(int wert);
}
