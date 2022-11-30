package it.kolleg;

import it.kolleg.dataaccess.MySQLDBConnection;
import it.kolleg.ui.CLI;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {

        CLI mycli = new CLI();
        mycli.start();

        try {
            Connection connection = MySQLDBConnection.getConn("jdbc:mysql://localhost:3306/kurssystem", "root", "");
            System.out.println("Verbindung check!");
        } catch (ClassNotFoundException e) {
            System.out.println("Klasse nicht gefunden: "+e.getMessage());;
        } catch (SQLException e) {
            System.out.println("Fehler beim Verbindungsaufbau: "+e.getMessage());
        }
    }
}
