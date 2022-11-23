package it.kolleg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemoExtention {
    /*
    Erstelle neben der Tabelle im Videotraining eine weitere Tabelle mit Primärschlüssel
    und mindestens 2 weiteren Spalten (eine Textspalte, eine Zahlenspalte) für eine weitere Entität nach Wahl
    (z.B. Kurs, Adresse, Hobbies, etc.).
    Implementiere zu dieser neuen Tabelle den Datenbankzugriff aus Java heraus, analog zu Aufgabe 1.
     */

    public static void main(String[] args) {

        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "";

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)){
            selectAll();
            findByTerm();
            update();
            insert();
            delete();
        } catch (SQLException e){
            System.out.println("Fehler bei DB Verbindung: "+e.getMessage());
        }

    }

    public static void selectAll(){

    }

    public static void findByTerm(){

    }

    public static void update(){

    }

    public static void insert(){

    }

    public static void delete(){

    }
}
