package it.kolleg.dataaccess;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton Klasse für MySQL Verbindung
 */
public class MySQLDBConnection {
    private static Connection conn = null;

    private MySQLDBConnection() //private, damit mit new kein Objekt erstellt werden kann
    {

    }

    public static Connection getConn(String url, String user, String pwd) throws ClassNotFoundException, SQLException {
        if(conn != null){ //wenn im Datenfeld schon Objekt drinnen, wird Objekt zurückgegeben; somit nur eine Verbindung erzeugt
            return conn;
        } else {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pwd);
            return conn;
        }
    }
}
