package it.kolleg;

import it.kolleg.dataaccess.MySQLDBConnection;
import it.kolleg.dataaccess.MySQLDBException;
import it.kolleg.dataaccess.MySqlCourseRepository;
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

        CLI mycli = null;
        try {
            mycli = new CLI(new MySqlCourseRepository()); //Dependency Injection
            mycli.start();
        } catch (SQLException e) {
            System.out.println("DB-Fehler: "+e.getSQLState()+" \n"+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Sonstiger Fehler: "+e.getMessage());
        }

    }
}
