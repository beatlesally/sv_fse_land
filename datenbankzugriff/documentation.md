# Datenbankzugriff mit JAVA, JDBC und DAO

In dieser Mitschrift werden die Schritte der Videos aufgelistet und beschrieben. 

Inhaltsverzeichnis
* [JDBC Intro 1](#jdbc-intro-1)
    * [Umgebung einrichten](#umgebung-einrichten)
    * [Verbindung herstellen](#datenbankverbindung-herstellen)
    * [Daten abfragen](#daten-abfragen)
    * [Daten einfügen, aktualisieren, löschen](#daten-einfügen-aktualisieren-und-löschen)


## JDBC Intro 1

### Umgebung einrichten

Es wird folgendes für eine funktionstüchtige Umgebung benötigt:
* Webserver
* Datenbankserver
* IDE (für JAVA)
* Maven Projekt Setup

Für den Web- und Datenbankserver wird XAMPP installiert. XAMPP kombiniert mehrere Server und ist sehr gut für Test- oder Entwicklungszwecke.

    Unter XAMPP den Download für das richtige Betriebssystem wählen:
    https://www.apachefriends.org/de/download.html
    ! Es müssen nur Apache und MySQL installiert werden !

Apache und MySQL über das XAMPP Control Panel starten.

Über die URL kann auf das MySQL Dashboard zugegrifffen werden. Hier können Datenbanken angelegt und verwaltet werden.

    http://localhost/phpmyadmin/
    bei abweichenden Apache Port (80,443) URL anpassen

Als nächstes wird mit IntelliJ ein neues Maven Projekt erstellt. Hierfür ist eine funktionierende Maven-Installation notwendig.

    Die Installation und den Download von MAVEN findet man hier:
    https://maven.apache.org/install.html

Ein neues Maven Projekt wird mit IntelliJ erstellt.

![Create Project](../../../../../../C:/school/5AAIF/FSE_LAND/sv_fse_land/datenbankzugriff/pics/Screenshot%202022-11-23%20084421.png)

In der `pom.xml` wird eine neue Dependency eingefügt, um den Datenbankzugriff mit Java erst realisieren zu können. (immer die neueste Version verwenden!)

    https://mvnrepository.com/artifact/com.mysql/mysql-connector-j

    oder

    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.31</version>
    </dependency>


### Datenbankverbindung herstellen

In `http://localhost/phpmyadmin/` eine neue Datenbank anlegen und in dieser Datenbank eine Tabelle (student: id, name, email) erzeugen. Es werden zwei Datensätze angelegt und das SQL Skript, das erzeugt wird, zwischengespeichert (als Kommentar in Main).

Im Java Projekt werden für einen Verbindungaufbau zu einer SQl DB folgenden Parameter benötigt:
* Benutzername
* Passwort
* Connection URL

        SQL Verbindungen und Abfragen müssen in einem try-catch sein, da es immer zu SQLExceptions kommen kann (z.B. falscher Benutzername, nicht existente DB/Tabelle, etc.)

        wenn Connection Objekt in try-Parameter erzeugt wird, wird die Verbindung automatisch geschlossen, sobald der try-Block zu Ende ist

Minimale Anforderungen für eine Datenbankverbindung mit Java (mit My-Sql-Connector-J Dependency)
```java
String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
String user = "root";
String pwd = "";

try(Connection conn = DriverManager.getConnection(connectionUrl, user, pwd))
{
    System.out.println("Verbindung zur DB erfolgreich");
} catch (SQLException e)
{
    System.out.println("Fehler beim Aufbau zur Verbindung zur DB: "+ e.getMessage());
}
```

### Daten abfragen
Nach dem Herstellen der Verbindung können Daten abgefragt werden. 
Nötig dafür:
* Variable vom Typ `PreparedStatement`: dort wird die Abfrage vorbereitet; am `Connection` Objekt wird mit `prepareStatement(sqlstatement)` die SQL Abfrage als String als Parameter übergeben
* Variable vom Typ `ResultSet`: dort wird das Ergebnis der Abfrage gespeichert; an der `PreparedStatement` Variable wird `executeQuery()` aufgerufen, um die Abfrage aufzuführen
* while-Schleife um `ResultSet` mit Zeiger mit `next()` zu durchlaufen: Zeiger zeigt auf einen Datensatz (z.B. id, name, email); in der Schleife wird auf die Spalten und deren Daten des Datensatzes zugegriffen

```java
PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`"); //SQL Statement vorbereiten
ResultSet rs = preparedStatement.executeQuery(); //Abfrage ausführen und Ergebnis-Objekt speichern

while(rs.next()) //Zeiger; solange ResultSet next hat, next ist 1 Datensatz!
{ //Datensatz: id, name, email

    int id = rs.getInt("id"); //von der Spalte id den Inhalt in int umwandeln und speichern
    String name = rs.getString("name");
    String email = rs.getString("email");

    System.out.println("Student: [id] "+id+" [name] "+name+" [email] "+email);
}
```

### Daten einfügen, aktualisieren und löschen
Nach Herstellung der Verbindung verändert Daten eingefügt werden. Nötig dafür:

* Variable vom Typ `PreparedStatement`: dort wird die Abfrage vorbereitet; am `Connection` Objekt wird mit `prepareStatement(sqlstatement)` die SQL Abfrage als String als Parameter übergeben; Datenwerte werden beim INSERT mit ? ersetzt
    * so wird das Statement bereits vorbereitet und kompiliert. Es werden damit SQL-Injections vermieden, weil SQL-Statements wo eigentlich Daten sein sollten, nicht ausgeführt werden, wenn hardcodiert. Mit `setString(stelle,wert)` werden an den ? die Daten nachträglich eingetragen.
* `executeUpdate()`: Statement wird ausgeführt; gibt die Anzahl der betroffenen Reihen zurück



```java
//separater try-catch-Block optional

//---- Insert Demo ----------------------------------------------

PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)"); //INSERT

try
{
    preparedStatement.setString(1, "Peter Zeck"); //Datenwerte eintragen bei ?
    preparedStatement.setString(2, "p.zeck@gmail.com");
    int rowAffected= preparedStatement.executeUpdate();
    System.out.println("Rows affected: "+rowAffected);
} catch (SQLException e)
{
    System.out.println("Fehler beim SQL-Statement: "+e.getMessage());
}



//---- Update Demo ----------------------------------------------

PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `student` SET `name` = ? WHERE `student`.`id`=7"); //UPDATE

try
{
    preparedStatement.setString(1,"Hans Zimmer");
    int rowAffected= preparedStatement.executeUpdate();
    System.out.println("Rows affected: "+rowAffected);
} catch (SQLException e)
{
    System.out.println("Fehler beim SQL-UPDATE-Statement: "+e.getMessage());
}


//---- Delete Demo ----------------------------------------------

PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `student` WHERE `student`.`id` = ?"); //SQL Statement vorbereiten

try
{
    preparedStatement.setInt(1,student_id);
    int rowAffected= preparedStatement.executeUpdate();
    System.out.println("Rows affected: "+rowAffected);
} catch (SQLException e)
{
    System.out.println("Fehler beim SQL-DELETE-Statement: "+e.getMessage());
}
```

