package it.kolleg;

import it.kolleg.dataaccess.MySQLDBConnection;

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
