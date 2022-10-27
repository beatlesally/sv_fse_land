package decorator;


//Example: https://de.wikipedia.org/wiki/Decorator#Ein_Beispiel_in_C#
//from C# to Java
public class Main {

    public static void main(String[] args) {

        Spielfigur monster = new Monster();
        monster.Drohe();

        Spielfigur verschnupft = new SchnupfenDecorator(monster);
        verschnupft.Drohe();

        Spielfigur hustend = new HustenDecorator(monster);
        hustend.Drohe();

        /*
        Deklaration & Initialisierung:
        Im SchnupfenDecorator-Decorator befindet sich im Datenfeld ein Objekt HustenDecorator
            im Datenfeld von HustenDecorator-Decorator befindet sich das Objekt Monster
        Methodenaufruf Drohe():
        SchnupfenDecorator ruft Drohe auf (Schniff wird ausgegeben), springt weiter zu super.Drohe;
        im Datenfeld spielfigur ist HustenDecorator hinterlegt; im Decorator wird spielfigur.Drohe aufgerufen;
        HustenDecorator ruft Drohe auf (Hust hust wird ausgegeben), springt weiter zu super.Drohe;
        im Datenfeld spielfigur ist Monster hinterlegt; im Decorator wird spielfigur.Drohe aufgerufen;
        Monster ruft Drohe auf (Grrrrrrr) wird ausgegeben
         */
        Spielfigur verschnupfthustend = new SchnupfenDecorator(new HustenDecorator(monster));
        verschnupfthustend.Drohe();

        Spielfigur hustendverschnupft = new HustenDecorator(new SchnupfenDecorator(monster));
        hustendverschnupft.Drohe();

    }
}
