package it.kolleg;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) {
        System.out.println( "JDBC Demo" );


        selectAllDemo();
        updateStudentDemo("2","Neuer Name", "new@email.com");
        insertStudentDemo("Neuer Name", "new@email.com");
        deleteStudentDemo(8);
        selectAllDemo();
        findAllByNameLike("neu");
    }

    public static void findAllByNameLike(String student_name){
        System.out.println("Find All by Name Demo with JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo"; //Über welche URL + DB soll zugegriffen werden
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
        {
            System.out.println("Verbindung zur DB erfolgreich");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student` WHERE `student`.`name` LIKE ?"); //SQL Statement vorbereiten
            preparedStatement.setString(1,"%"+student_name.toLowerCase()+"%");
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

    public static void deleteStudentDemo(int student_id){
        System.out.println("delete Demo with JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo"; //Über welche URL + DB soll zugegriffen werden
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
        {
            System.out.println("Verbindung zur DB erfolgreich");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `student` WHERE `student`.`id` = ?"); //SQL Statement vorbereiten

            try
            {
                preparedStatement.setInt(1,student_id);
                int rowAffected= preparedStatement.executeUpdate();
                System.out.println("Rows affected: "+rowAffected);
            } catch (SQLException e)
            {
                System.out.println("Fehler beim SQL-DELETE-Statement: "+e.getMessage());
            }

        } catch (SQLException e)
        {
            System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
        }
    }

    public static void updateStudentDemo(String student_id, String student_name, String student_email){
        System.out.println("update Demo with JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo"; //Über welche URL + DB soll zugegriffen werden
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
        {
            System.out.println("Verbindung zur DB erfolgreich");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email` = ? WHERE `student`.`id`=?"); //SQL Statement vorbereiten

            try
            {
                preparedStatement.setString(1,student_name);
                preparedStatement.setString(2,student_email);
                preparedStatement.setString(3,student_id);
                int rowAffected= preparedStatement.executeUpdate();
                System.out.println("Rows affected: "+rowAffected);
            } catch (SQLException e)
            {
                System.out.println("Fehler beim SQL-UPDATE-Statement: "+e.getMessage());
            }

        } catch (SQLException e)
        {
            System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
        }
    }

    public static void insertStudentDemo(String student_name, String student_email){
        System.out.println("insert Demo with JDBC");

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo"; //Über welche URL + DB soll zugegriffen werden
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
        {
            System.out.println("Verbindung zur DB erfolgreich");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)"); //SQL Statement vorbereiten

            try
            {
                preparedStatement.setString(1, student_name); //Datenwerte eintragen bei ?
                preparedStatement.setString(2, student_email);
                int rowAffected= preparedStatement.executeUpdate();
                System.out.println("Rows affected: "+rowAffected);
            } catch (SQLException e)
            {
                System.out.println("Fehler beim SQL-INSERT-Statement: "+e.getMessage());
            }

        } catch (SQLException e)
        {
            System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
        }
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
