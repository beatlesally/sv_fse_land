# Datenbankzugriff mit JAVA, JDBC und DAO

In dieser Mitschrift werden die Schritte der Videos aufgelistet und beschrieben. 

Inhaltsverzeichnis
* [JDBC Intro 1](#jdbc-intro-1)
    * [Umgebung einrichten](#umgebung-einrichten)


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

In `http://localhost/phpmyadmin/` eine neue Datenbank anlegen und in dieser Datenbank eine Tabelle (student: id, name, email) erzeugen. Es werden zwei Datensätze angelegt und das SQL Skript, das erzeugt wird, zwischengespeichert.


## Usage & formatting

This Readme template is written using Markdown. See [GitHub's instructions on writing in Markdown](https://help.github.com/articles/basic-writing-and-formatting-syntax/) if you aren't familiar.

Usage:

1. In your repository, create a new file and name it `README.md`.
2. Copy and paste the below template in your README.md
3. Edit the text as needed for your project

**Note** Text in `*italics*` (as under `Getting started`) indicates template instructions that should be replaced with text specific to the project. Regular text (as under `Errors and bugs`) indicates a standard block of text that you can copy/paste directly.

## The template

