package it.kolleg;

import it.kolleg.dataaccess.*;
import it.kolleg.ui.CLI;

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
            mycli = new CLI(new MySqlStudentRepository(), new MySqlCourseRepository(), new MySqlBookingsRepository()); //Dependency Injection
            mycli.start();
        } catch (SQLException e) {
            System.out.println("DB-Fehler: "+e.getSQLState()+" \n"+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Sonstiger Fehler: "+e.getMessage());
        }

    }
}
