package it.kolleg.ui;

import it.kolleg.dataaccess.BaseRepository;
import it.kolleg.dataaccess.MyCoursesRepository;
import it.kolleg.dataaccess.MySQLDBException;
import it.kolleg.dataaccess.MySqlCourseRepository;
import it.kolleg.domain.Course;
import it.kolleg.domain.CourseType;
import it.kolleg.domain.InvalidValueException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CLI {

    Scanner scanner;
    BaseRepository repo;


    public CLI(MyCoursesRepository repo)
    {
        this.scanner = new Scanner(System.in);
        this.repo = repo;
    }

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
                case "6":
                    //courseSearch();
                    break;
                case "7":
                    //runningCourses();
                    break;
                case "x":
                    System.out.println("bye bye");
                    break;
                default:
                    inputError();
                    break;
            }
        }

        scanner.close();

    }


    private void showMenu()
    {
        System.out.println("------ Kursmanagement -----------------------------");
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t (3)Kursdetails");
        System.out.println("(4) Kursdetails aktualisieren \t (5)Kurs löschen \t (6)Kurssuche");
        System.out.println("(7) laufende Kurse (8) alle Studenten \t (9) Kurs buchen");
        System.out.println("(x) Ende");
    }

    private void addCourse(){
        String name, desc;
        int hours;
        Date beginDate, endDate;
        CourseType courseType;

        try
        {
            System.out.println("Bitte alle Kursdaten angeben:");

            System.out.println("Name: ");
            name = scanner.nextLine();
            if(name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein"); //Eingabevalidierung

            System.out.println("Beschreibung: ");
            desc = scanner.nextLine();
            if(desc.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein");

            System.out.println("Stundenanzahl: ");
            hours = Integer.parseInt(scanner.nextLine());

            System.out.println("Startdatum (YYYY-MM-DD)");
            beginDate = Date.valueOf(scanner.nextLine());
            System.out.println("Enddatum (YYYY-MM-DD)");
            endDate = Date.valueOf(scanner.nextLine());

            System.out.println("Kurstyp (OE,BF,ZA,FF)");
            courseType = CourseType.valueOf(scanner.nextLine());

            Optional<Course> optionalCourse = repo.insert(new Course(name,desc,hours,beginDate,endDate,courseType));

            if(optionalCourse.isPresent()){
                System.out.println("Kurs angelegt: "+optionalCourse.get());
            } else {
                System.out.println("Kurs konnte nicht angelegt werden");
            }

        } catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Eingabefehler: "+illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException){
            System.out.println("Kursdaten nicht korrekt angegeben: "+invalidValueException.getMessage());
        } catch (MySQLDBException sqldbException){
            System.out.println("DB Fehler beim Einfügen: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Einfügen: "+e.getMessage());
        }
    }

    private void showCourseDetails(){
        System.out.println("Für welche Kurs Details anzeigen?");
        Long courseID = Long.parseLong(scanner.nextLine());
        try
        {
            Optional<Course> courseOptional = repo.getById(courseID);
            if(courseOptional.isPresent())
            {
                System.out.println(courseOptional.get());
            } else {
                System.out.println("Kurs mit ID "+courseID+" nicht gefunden...");
            }
        } catch (MySQLDBException sql){
            System.out.println("Datenbankfehler bei Kursdetailsanzeige: "+sql.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Kursdetailsanzeige: "+e.getMessage());
        }
    }

    private void updateCourseDetails() {
        System.out.println("Für welche KursID Details ändern?");
        Long courseID = Long.parseLong(scanner.nextLine());

        try
        {
            Optional<Course> courseOptional = repo.getById(courseID);
            if(courseOptional.isEmpty()){
                System.out.println("Kurs mit ID "+courseID+" nicht gefunden");
            } else {
                Course course = courseOptional.get();
                System.out.println("Änderungen für folgenden Kurs: ");
                System.out.println(course);

                String name, desc, hours, dateFrom, dateTo, courseType;
                System.out.println("Bitte neue Kursdaten angeben (Enter falls keine Änderung gewünscht ist)");
                System.out.println("Name: ");
                name = scanner.nextLine();
                System.out.println("Beschreibung: ");
                desc = scanner.nextLine();
                System.out.println("Stunden: ");
                hours = scanner.nextLine();
                System.out.println("Startdatum (YYYY-MM-DD): ");
                dateFrom = scanner.nextLine();
                System.out.println("Enddatum (YYYY-MM-DD): ");
                dateTo = scanner.nextLine();
                System.out.println("Kurstyp (OE,BF,ZA,FF): ");
                courseType = scanner.nextLine();

                Optional<Course> courseOptionalUpdated = repo.update(new Course(course.getId(),name.equals("") ? course.getName() : name,desc.equals("") ? course.getDescr() : desc,hours.equals("") ? course.getHours() : Integer.parseInt(hours),dateFrom.equals("") ? course.getBeginDate() : Date.valueOf(dateFrom),dateTo.equals("") ? course.getEndDate() : Date.valueOf(dateTo),courseType.equals("") ? course.getCourseType():CourseType.valueOf(courseType)));

                courseOptionalUpdated.ifPresentOrElse(
                        (c)-> System.out.println("Kurs aktualisiert: "+c), //if true
                        ()-> System.out.println("Kurs konnte nicht aktualisiert werden") //else
                );
            }
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Kurs Update: "+e.getMessage());
        }
    }

    private void deleteCourse(){
        System.out.println("Welche Kurs löschen (ID)?");
        Long courseIDToDelete = Long.parseLong(scanner.nextLine());

        try {
            repo.deleteById(courseIDToDelete);
            repo.getAll();
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

    /*
    private void courseSearch(){
        System.out.println("Geben Sie einen Suchbegriff ein: ");
        String searchString = scanner.nextLine();
        List<Course> courseList;

        try
        {
            courseList = (MySqlCourseRepository)repo.findAllCoursesByNameOrDescr(searchString);
            for (Course course : courseList)
            {
                System.out.println(course);
            }
        } catch (MySQLDBException sqlException){
            System.out.println("Datenbankfehler bei Kurssuche: "+sqlException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler: "+e.getMessage());
        }
    }

    private void runningCourses(){
        System.out.println("Aktuell laufende Kurse:");
        List<Course> list;

        try
        {
            list = repo.findAllRunningCourses();
            for(Course course:list){
                System.out.println(course);
            }
        } catch (MySQLDBException sqldbException){
            System.out.println("Datenbankfehler für laufende Kurse: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler für laufende Kurse: "+e.getMessage());
        }
    }
*/
    private void inputError()
    {
        System.out.println("Bitte nur Möglichkeiten der Menüauswahl eingeben!");
    }

    private void showAllCourses(){
        List<Course> list = null;
        list = repo.getAll();

        try{
            if (list.size() > 0) {
                for (Course c :
                        list) {
                    System.out.println(c);
                }
            } else {
                System.out.println("Kursliste leer!");
            }
        } catch(MySQLDBException e) {
            System.out.println("DB Fehler bei Anzeige aller Kurse: "+e.getMessage());
        } catch(Exception e){
            System.out.println("Unbekannter Fehler bei Anzeige aller Kurse: "+e.getMessage());
        }

    }


}
