package it.kolleg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {

    public static void main(String[] args) {
        System.out.println( "JDBC Demo" );
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Michi Vötter', 'mvoetter@tsn.at');
        //SELECT * FROM `student`
        selectAllDemo();
    }

    public static void selectAllDemo(){
        System.out.println("Select Demo with JDBC");
        String sqlSelectAllPersons = "SELECT * FROM `student`"; //SELECT Statement
        String connectionUrl = "jdbc:mysql://localhost:3306/jdcbdemo"; //Über welche URL + DB soll zugegriffen werden

        try(Connection conn = DriverManager.getConnection(connectionUrl, "root", ""))
        {
            System.out.println("Verbindung zur DB erfolgreich");
        } catch (SQLException e)
        {
            System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
        }
    }
}
