# Datenbankzugriff mit JAVA, JDBC und DAO

In dieser Mitschrift werden die wichtigsten Inhalte aus den Videos dokumentiert, sowie einige Implementierungen festgehalten.

Inhaltsverzeichnis
* [JDBC Intro 1](#jdbc-intro-1)
    * [Umgebung einrichten](#umgebung-einrichten)
    * [Verbindung herstellen](#datenbankverbindung-herstellen)
    * [Daten abfragen](#daten-abfragen)
    * [Daten einfügen, aktualisieren, löschen](#daten-einfügen-aktualisieren-und-löschen)
* [JDBC Intro 2](#jdbc-intro-2)
    * [Singleton](#singleton)
    * [DAO](#dao-data-access-object)
    * [Objektrelationales Mapping](#objektrelationales-mapping)
    * [CLI](#cli)
    * [Exceptions](#exceptions)
    * [Erben von Interfaces](#erben-von-interfaces)
    * [Kurssystem grundlegend](#kurssystem-grundlegend-db-seitig)
        * [Aufbau der Repositories](#aufbau-der-repos-grundlegend)
        * [Aufbau der Repositories im Kurssystem](#aufbau-der-repos-für-kurssystem)
    * [CRUD-Operationen](#crud-operationen)
        * [Parametrisierung](#parametrisierung)
        * [Optional](#optional)
    * [Implementierung: Student und Booking + UML](#implementierung-mit-student-und-bookings-kurzbeschreibung--fertiges-uml)


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

Dort können auch SQL Statements auf ihre Richtigkeit geprüft werden.
![SQL](pics/Screenshot%202022-11-23%20105444.png)

Als nächstes wird mit IntelliJ ein neues Maven Projekt erstellt. Hierfür ist eine funktionierende Maven-Installation notwendig.

    Die Installation und den Download von MAVEN findet man hier:
    https://maven.apache.org/install.html

Ein neues Maven Projekt wird mit IntelliJ erstellt.

![Create Project](pics/Screenshot%202022-11-23%20084421.png)

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
* wird eine Select nach einem bestimmten Buchstaben ausgeführt, wird ein anderes SQL-Statement benötigt, sowie ein ? Platzhalter in diesem Statement; siehe [Daten einfügen, aktualisieren, löschen](#daten-einfügen-aktualisieren-und-löschen) für Erklärung

Nötig dafür:
* Variable vom Typ `PreparedStatement`: dort wird die Abfrage vorbereitet; am `Connection` Objekt wird mit `prepareStatement(sqlstatement)` die SQL Abfrage als String als Parameter übergeben
* Variable vom Typ `ResultSet`: dort wird das Ergebnis der Abfrage gespeichert; an der `PreparedStatement` Variable wird `executeQuery()` aufgerufen, um die Abfrage aufzuführen
* while-Schleife um `ResultSet` mit Zeiger mit `next()` zu durchlaufen: Zeiger zeigt auf einen Datensatz (z.B. id, name, email); in der Schleife wird auf die Spalten und deren Daten des Datensatzes zugegriffen


```java

//---- Select All Demo ----------------------------------------------

PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`"); //SQL Statement vorbereiten
ResultSet rs = preparedStatement.executeQuery(); //Abfrage ausführen und Ergebnis-Objekt speichern

while(rs.next()) //Zeiger; solange ResultSet next hat, next ist 1 Datensatz!
{ //Datensatz: id, name, email

    int id = rs.getInt("id"); //von der Spalte id den Inhalt in int umwandeln und speichern
    String name = rs.getString("name");
    String email = rs.getString("email");

    System.out.println("Student: [id] "+id+" [name] "+name+" [email] "+email);
}


//---- Select Like Demo ----------------------------------------------
 PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student` WHERE `student`.`name` LIKE ?");
    preparedStatement.setString(1,"%"+student_name.toLowerCase()+"%");
    ResultSet rs = preparedStatement.executeQuery(); 

    while(rs.next()) 
    { //Datensatz: id, name, email
        int id = rs.getInt("id"); 
        String name = rs.getString("name");
        String email = rs.getString("email");

```

### Daten einfügen, aktualisieren und löschen
Nach Herstellung der Verbindung können Daten eingefügt, verändert und gelöscht werden. 

Nötig dafür:

* Variable vom Typ `PreparedStatement`: dort wird die Abfrage vorbereitet; am `Connection` Objekt wird mit `prepareStatement(sqlstatement)` die SQL Abfrage als String als Parameter übergeben; Datenwerte werden beim INSERT mit ? ersetzt
    * so wird das Statement bereits vorbereitet und kompiliert. Es werden damit SQL-Injections vermieden, weil SQL-Statements wo eigentlich Daten sein sollten, nicht ausgeführt werden, wenn hardcodiert/direkt eingefügt. Mit `setString(stelle,wert), setInt(stelle,wert), ...` werden an den ? die Daten nachträglich eingetragen; auf Datentyp achten!
* `executeUpdate()`: Statement führt Änderung der Daten aus; gibt die Anzahl der betroffenen Reihen zurück
* Es kann nur durch das SQL-Statement selbst zwischen Einfügen, Aktualisieren und Löschen unterschieden werden. Der Grundaufbau ist für alle gleich.



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

## JDBC Intro 2
In diesem zweiten Teil werden zwei neue Pattern behandelt. Die neuen Inhalte werden am Beispiel eines Kurssystems angewendet, für das in einer weiteren Aufgabe eigenständig die Erweiterung von Student und Buchungen textuell beschrieben werden soll. Ich habe diese Erweiterung ausprogrammiert. Deswegen wird hier nur eine kurze Beschreibung und ein UML-Diagramm eingefügt.

### Singleton
> "Es stellt sicher, dass von einer Klasse genau ein Objekt existiert."
> https://de.wikipedia.org/wiki/Singleton_(Entwurfsmuster)
* In unserem Beispiel wird nur eine Verbindung aufgebaut, die im Datenfeld gespeichert wird (also ein Connection-Objekt, das immer wieder verwendet wird). Bei jeder Erstellung von einem neuen Objekt wird überprüft, ob bereits eine Verbindung existiert und dann diese zurückgegeben.

```java
public class MySQLDBConnection {
    private static Connection conn = null;

private MySQLDBConnection(){} //Konstruktor privat, damit mit new kein Objekt erstellt werden kann; Connection Objekt muss immer von getConn bekommen werden

public static Connection getConn(String url, String user, String pwd) throws ClassNotFoundException, SQLException {
    if(conn != null){ //wenn im Datenfeld schon Objekt drinnen, wird Objekt zurückgegeben; somit nur eine Verbindung erzeugt
        return conn;
    } else {
        Class.forName("com.mysql.cj.jdbc.Driver"); //throws ClassNotFoundException
        conn = DriverManager.getConnection(url,user,pwd); //throws SQLException
        return conn;
    }
  }
}
```

### DAO (Data Access Object)
> "...ist ein Entwurfsmuster, das den Zugriff auf unterschiedliche Arten von Datenquellen (z. B. Datenbanken, Dateisystem) so kapselt, dass die angesprochene Datenquelle ausgetauscht werden kann, ohne dass der aufrufende Code geändert werden muss."
>  https://de.wikipedia.org/wiki/Data_Access_Object
* Daten aus einer DB werden so aufgearbeitet, dass sie als Objekt verfügbar sind; somit ist damit als Programmierer leichter zu arbeiten als mit einem Datensatz; welche Quelle ist egal, jede Quelle erstellt immer das gleiche Objekt

* für jede Entity und den Zugriff wird das Interface ausimplementiert


![Alt text](../../../../../../C:/school/5AAIF/FSE_LAND/sv_fse_land/datenbankzugriff/pics/dao.jpg) http://best-practice-software-engineering.ifs.tuwien.ac.at/patterns/dao.html



Im Beispiel werden die Pattern für folgendes verwendet:
* Singleton: Zugriff auf die Quelle (Verbindung soll nur einmal hergestellt werden)
* DAO: Datensätze von SQL erhalten und in Objekt umgewandelt

### Objektrelationales Mapping
> "... ist eine Technik der Softwareentwicklung, mit der ein in einer objektorientierten Programmiersprache geschriebenes Anwendungsprogramm seine Objekte in einer relationalen Datenbank ablegen kann."
> https://de.wikipedia.org/wiki/Objektrelationale_Abbildung

### CLI
Es gibt einen Default-Aufbau einer CLI, der immer wieder verwendet werden kann:
* "Endlos"-Schleife mit Bedingung für Abbruch
* Methode zur Menüübersicht: `showMenu()`
* Methode zum Melden bei nicht verfügbaren Case: `inputError()`
* Methoden für die Funktionen + I/O: `deleteCourse()`
* Methode start() zum Starten der CLI: `start()`
    * Switch-Case in denen die Funktionen (I/O) aufgerufen werden
```java
public class CLI {

    //Datenfelder & Konstruktor hier ausgelassen

    public void start()
    {
        String input = "";

        while(!input.equals("x"))
        {
            showMenu();
            input = this.scanner.nextLine();
            switch (input)
            {
                case "1":
                    addCourse();
                    break;
                case "2":
                    showAllCourses();
                    break;
                case "3":
                    showCourseDetails();
                    break;
                case "4":
                    updateCourseDetails();
                    break;
                case "5":
                    deleteCourse();
                    break;
                default:
                    inputError();
                    break;
            }
        }
        scanner.close();
    }

    private void showMenu() //Menü für die Übersicht der Möglichkeiten
    {
        System.out.println("------ Kursmanagement -----------------------------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3)Kursdetails");
        System.out.println("(4) Kursdetails aktualisieren \t (5)Kurs löschen");
        System.out.println("(x) Ende");
    }

    private void inputError() //für Eingaben die nicht im Switch-Case behandelt werden
    {
        System.out.println("Bitte nur Möglichkeiten der Menüauswahl eingeben!");
    }

    private void deleteCourse(){
        System.out.println("Welche Kurs löschen (ID)?");
        Long courseIDToDelete = Long.parseLong(scanner.nextLine());

        try {
            courseRepo.deleteById(courseIDToDelete);
            courseRepo.getAll();
        } catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Eingabefehler: "+illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException){
            System.out.println("Kursdaten nicht korrekt angegeben: "+invalidValueException.getMessage());
        } catch (MySQLDBException sqldbException){
            System.out.println("DB Fehler beim Löschen: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Löschen: "+e.getMessage());
        }
    }

}

```
### Exceptions
Exceptions verfolgen folgendes Prinzip:
1. es wird versucht einen Codeabschnitt aufzuführen(try)
1. bei einem aufgetretenen Fehler, wirft dieser Abschnitt eine Exception(throw)
1. diese muss dann aufgefangen werden(catch)
```java
//Bsp wo eine Exception geworfen wird, wenn ein Fehler auftaucht
public void setName(String name) throws InvalidValueException { //muss nicht, weil unhandled expection weil von RuntimeException erbt
    if (name != null && name.length() > 1) {
        this.name = name;
    } else {
        throw new InvalidValueException("Kursname muss mindestens 2 Zeichen lang sein!");
    }
}

//Bsp wo diese Exception auftreten kann und gefangen wird
//Beispiel stark gekürzt!

//...
try{
    Optional<Course> optionalCourse = courseRepo.insert(new Course(name,desc,hours,beginDate,endDate,courseType));

    if(optionalCourse.isPresent()){
        System.out.println("Kurs angelegt: "+optionalCourse.get());
    } else {
        System.out.println("Kurs konnte nicht angelegt werden");
    }
} catch (InvalidValueException invalidValueException){
System.out.println("Kursdaten nicht korrekt angegeben: "+invalidValueException.getMessage());
} 

//...
```
Es gibt viele vorgefertigte Exceptions, bei deinen zwischen zwei Typen unterschieden werden muss. Dadurch wird auch klar, ob diese Exception behandelt werden muss oder nicht:
* checked: werden nicht von der Laufzeitumgebung behandelt und müssen vom Programmierer im Code abgefangen werden sowie in throws Klausel des Methodenkopfs "vermerkt sein" (`Exception`)
* unchecked: werden von der Laufzeitumgebung behandelt (`RuntimeException`)

Es können auch eigene Exceptions erstellt werden. Sie müssen nur von `RuntimeException` oder `Exception` erben (je nachdem ob Exception unchecked/checked sein soll), im Konstruktur einen String-Parameter haben und diesen Parameter an den Mutterkonstruktur übergeben.
```java
public class MySQLDBException extends RuntimeException {
    public MySQLDBException(String message) {
        super(message);
    }
}
```
### Erben von Interfaces
Interfaces können von anderen Interfaces erben. Dies wird benötigt, wenn ein Interface grundlegende Funktionen vorgibt(z.B. `BaseRepository` mit CRUD-Operationen) und das andere Interface mehr maßgeschneiderte Funktionen(z.B. `MyCoursesRepository` mit Funktionen die nur für die Entity `Course` funktionieren/Sinn ergeben) bereitstellt und auch die grundlegenden vom einen Interface beinhalten möchte. 

### Domänenklassen
Die Domänenklassen sind dafür da, die Entitäten als Objekt abzubilden. Anzumerken hierbei ist, dass jede Domänenklassen über 2 Konstruktor verfügt. Hierfür ist einer für Insert in die Datenbank von neuen Objekten (id==null; weil von auto increment selbst erzeugt), der andere ist für Datensätze, die bereits in der Datenbank bestehen (haben bereits ID), aus dieser geholt und als Objekt gemappt werden.

* `BaseEntity` (abstrakte Klasse, die ID als Datenfeld hat -> jede Entity hat ID!)
    * `Booking`
    * `Courses`
    * `Student`

### Kurssystem grundlegend (DB-seitig)
* DB: kurssystem
    * Tabelle: courses 
        * kursnummer
        * name
        * beschreibung 
        * stunden
        * startdatum 
        * enddatum
        * kurstyp
    * Tabelle: student
        * studentennummer
        * name
        * email
    * Tabelle: bookings
        * id
        * fk_s (StudentID)
        * fk_c (CourseID)
        * datum
#### Aufbau der Repos grundlegend
* `BaseRepostitory` mit Standart-CRUD-Methoden
    * `MyEntityRepository` mit speziellen Methoden für die spezielle Entity (jede Entity = eigenes Repository)
        * `MySQLEntityRepository` Ausimplementierung speziell für SQL (jede Datenquelle = eigenes Repository)

#### Aufbau der Repos für Kurssystem
* `BaseRepostitory`
    * `MyBookingsRepository` 
    * `MyCourseRepository` 
    * `MyStudentRepository` 
        * `MySQLCourseRepository`
        * `MySQLStudentRepository`
        * `MySQLBookingRepository`
### CRUD-Operationen
Die Basic-CRUD Operation (insert,select*,select by id, update, delete) sind unabhängig von der Entity gleich. 

```java
public interface BaseRepository<T,I> { //parametrisiert! bei implements festlegen

    Optional<T> insert(T entity); 
    Optional<T> getById(I id); 
    List<T> getAll();
    Optional<T> update(T entity);
    void deleteById(I id);

}
```
#### Parametrisierung
Unter Parametrisierung wird verstanden, dass beim `implements/extends` also nachträglich festlegt wird, mit welchen Typen dieses Interface arbeitet. Somit ist es dynamischer. Im obigen Beispiel wird `BaseRepository<T,I>` mit `<T,I>` parametrisiert. `T` steht hier für die Entity und `I` für den Typ der ID. Diese Parameter ziehen sich durch alle Methoden. Erst beim `implements/extends` müssen die Parameter gesetzt werden:
```java
//Hier wurde mehrere Codeabschnitte zur besseren Codeübersicht ausgelassen

//MyBookingsRepository extends BaseRepository; dieses Repository wird nur mit Entitäten vom Typ Booking arbeiten, die ID ist wie bei den anderen Long
public interface MyBookingsRepository extends BaseRepository<Booking,Long>{/******/}

//Hier sieht man, dass T & I mit den gesetzten Typ ersetzt wurden!
public class MySqlBookingsRepository implements MyBookingsRepository{
    public Optional<Booking> insert(Booking entity) {/*******/}
    public Optional<Booking> getById(Long id) {/*******/}
    public List<Booking> getAll() {/*******/}
    public Optional<Booking> update(Booking entity) {/*******/}
    public void deleteById(Long id) {/*******/}
}
```
#### Optional
> "Ein Optional ist eine Objekt, das man sich als Datenbehälter vorstellen kann, der entweder einen Wert enthält oder leer (empty) ist. Leer ist hier auch nicht gleichbedeutend mit null!"
> https://javabeginners.de/Grundlagen/Datentypen/Optionals.php

* leeres Optional: `Optional<String> oe = Optional.empty();`
* Optional befüllen: `Optional<String> os = Optional.of("Hallo Welt!");`
    * Typ in `of()` muss mit Typ bei Deklaration übereinstimmen!
* Zugriff auf Inhalt in Optional: `os.get()`
    * wenn Inhalt jedoch leer/null, dann wird `NoSuchElementException` geworfen
    * deswegen vorher prüfen mit: `os.isPresent()` oder `os.isEmpty()` - liefert true oder false
* If-Else (iterativ): wenn not empty/null dann sout, sonst getWarning(): 
    `onb.ifPresentOrElse(s -> System.out.println("Inhalt vorhanden!"),getWarning());`

### Implementierung mit Student und Bookings (Kurzbeschreibung + fertiges UML)
> Für die komplette Ausimplementierung bitte im Repository die Codeteile einsehen!
Vorgehensweise:
* Tabelle bookings und student erstellen
* jeweils zwei Einträge erstellen (für Tests)
* Entity-Klasse für student und booking erstellen
    * Bussinesslogik in Setter einbauen
* Repository für Student und Bookings erstellen und von `BaseRepository` erben lassen
* `CLI`-Konstruktor um zwei Parameter erweitern und zwei Datenfelder hinzufügen (Typ `MyBookingsRepository` & `MyStudentRepository`)
* `MySqlBookingsRepository`  erstellen, von `MyBookingsRepository` erben lassen, CRUD und spezielle Methoden ausimplementieren
    * `conn` Objekt in Konstruktor über `getConn()` holen und als Datenfeld speichern
* `MySqlStudentRepository` erstellen, von `MyStudentRepository` erben lassen, CRUD und spezielle Methoden ausimplementieren
    * `conn` Objekt in Konstruktor über `getConn()` holen und als Datenfeld speichern
* `CLI` um Funktionen der beiden Klassen erweitern
* testen!
![Alt text](pics/Screenshot%202022-12-10%20103625.png)