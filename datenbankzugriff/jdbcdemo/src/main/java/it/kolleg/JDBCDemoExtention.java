package it.kolleg;

import java.sql.*;

public class JDBCDemoExtention {
    /*
    Erstelle neben der Tabelle im Videotraining eine weitere Tabelle mit Primärschlüssel
    und mindestens 2 weiteren Spalten (eine Textspalte, eine Zahlenspalte) für eine weitere Entität nach Wahl
    (z.B. Kurs, Adresse, Hobbies, etc.).
    Implementiere zu dieser neuen Tabelle den Datenbankzugriff aus Java heraus, analog zu Aufgabe 1.

    Tabelle - kurs: kursnummer(int, pk, a_i), kursbezeichnung(varchar), raumnummer(int)
     */

    public static void main(String[] args) {

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            System.out.println("Verbindung zu DB erfolgreich");
            selectAll(conn);
            findByKursBezLike(conn, "s");
            update();
            insert();
            delete();
        } catch (SQLException e){
            System.out.println("Fehler bei DB Verbindung: "+e.getMessage());
        }

    }

    public static void selectAll(Connection conn){
        System.out.println("------ SELECT ALL -------------------------");
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `kurs`");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int knr = rs.getInt("kursnummer");
                String kbz = rs.getString("kursbezeichnung");
                int rnr= rs.getInt("raumnummer");

                System.out.println("[KursNummer] "+knr+" [KursBezeichnung] "+kbz+" [RaumNummer] "+rnr);
            }

        } catch (SQLException e) {
            System.out.println("Fehler bei SQL-SELECT-Statement: "+e.getMessage());;
        }
    }

    public static void findByKursBezLike(Connection conn, String kursbez){
        System.out.println("------ SELECT BY TERM: "+kursbez+" -------------------------");
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `kurs` WHERE LOWER(`kurs`.`kursbezeichnung`) LIKE ?");
            preparedStatement.setString(1,"%"+kursbez.toLowerCase()+"%");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int knr = rs.getInt("kursnummer");
                String kbz = rs.getString("kursbezeichnung");
                int rnr= rs.getInt("raumnummer");

                System.out.println("[KursNummer] "+knr+" [KursBezeichnung] "+kbz+" [RaumNummer] "+rnr);
            }

        } catch (SQLException e) {
            System.out.println("Fehler bei SQL-SELECT-Statement: "+e.getMessage());;
        }
    }

    public static void update(){

    }

    public static void insert(){
    //INSERT INTO `kurs` (`kursnummer`, `kursbezeichnung`, `raumnummer`) VALUES (NULL, 'FSE', '25636'), (NULL, 'POS', '66954');
    }

    public static void delete(){

    }
}
