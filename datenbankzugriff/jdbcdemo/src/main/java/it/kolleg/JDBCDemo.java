package it.kolleg;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) {
        System.out.println( "JDBC Demo" );
        //INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Michi Vötter', 'mvoetter@tsn.at');

        selectAllDemo();
    }

    public static void selectAllDemo(){
        System.out.println("Select Demo with JDBC");
        String sqlSelectAllPersons = "SELECT * FROM `student`"; //SELECT Statement

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo"; //Über welche URL + DB soll zugegriffen werden
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
        {
            System.out.println("Verbindung zur DB erfolgreich");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`"); //SQL Statement vorbereiten
            ResultSet rs = preparedStatement.executeQuery(); //Abfrage ausführen und Ergebnis-Objekt speichern

            while(rs.next()) //solange ResultSet next hat, next ist 1 Datensatz!
            { //Datensatz: id, name, email

                //welche Spalten will man angreifen?
                int id = rs.getInt("id"); //von der Spalte id den Inhalt in int umwandeln und speichern
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("Student: [id] "+id+" [name] "+name+" [email] "+email);
            }

        } catch (SQLException e)
        {
            System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
        }
    }
}
