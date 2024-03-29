package it.kolleg.ui;

import it.kolleg.dataaccess.*;
import it.kolleg.domain.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CLI {

    Scanner scanner;
    private MyStudentRepository studentRepo;
    private MyCoursesRepository courseRepo;
    private MyBookingsRepository bookingsRepo;

    public CLI(MyStudentRepository studentrepo, MyCoursesRepository courserepo, MyBookingsRepository bookingrepo)
    {
        this.scanner = new Scanner(System.in);
        this.studentRepo = studentrepo;
        this.courseRepo = courserepo;
        this.bookingsRepo = bookingrepo;
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
                    courseSearch();
                    break;
                case "7":
                    runningCourses();
                    break;
                case "8":
                    showAllStudents();
                    break;
                case "9":
                    createStudent();
                    break;
                case "10":
                    changeStudent();
                    break;
                case "11":
                    deleteStudent();
                    break;
                case "12":
                    findStudentByNameLike();
                    break;
                case "13":
                    allBookings();
                    break;
                case "14":
                    showBookingDetails();
                    break;
                case "15":
                    createBooking();
                    break;
                case "16":
                    deleteBooking();
                    break;
                case "17":
                    allBookingBeforeDate();
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
        System.out.println("(7) laufende Kurse (8) alle Studenten \t (9) Student anlegen");
        System.out.println("(10) Student ändern \t (11) Student löschen \t (12) Student bei Namen finden");
        System.out.println("(13) alle Buchungen \t (14) Buchungsdetails \t (15) Buchung erstellen");
        System.out.println("(16) Buchung löschen \t (17) Buchungen vor best. Datum");
        System.out.println("(x) Ende");
    }



    //-------------------------------Booking----------------------------------------------------------------

    private String getStudentNameByFk(Booking b){
        return studentRepo.getById(b.getFk_s()).get().getStudentname();
    }

    private String getCourseNameByFk(Booking b){
        return courseRepo.getById(b.getFk_c()).get().getName();
    }

    private void allBookings(){
        try {
            List<Booking> bookings = bookingsRepo.getAll();
            for(Booking b:bookings){
                System.out.println(b+" [student] "+ getStudentNameByFk(b)+" [course] "+getCourseNameByFk(b));
            }
        } catch (MySQLDBException sqldbException){
            System.out.println("DB Fehler bei Anzeige aller Buchungen");
        }
    }

    private void showBookingDetails(){
        try {
            System.out.println("Für welche BuchungsID?");
            allBookings();
            String bookingID = scanner.nextLine();
            Optional<Booking> optionalBooking = bookingsRepo.getById(Long.parseLong(bookingID));

            if(optionalBooking.isPresent()){
                Booking booking = optionalBooking.get();
                System.out.println(booking+" [student] "+getStudentNameByFk(booking)+" [course] "+getCourseNameByFk(booking));
            }

        } catch (MySQLDBException sqldbException){
            System.out.println("DB-Fehler: "+sqldbException.getMessage());
        }
    }

    private void createBooking(){
        System.out.println("Buchungsdaten angeben:");

        System.out.println("StudentID: ");
        Long s_id = Long.parseLong(scanner.nextLine());
        System.out.println("KursID: ");
        Long c_id = Long.parseLong(scanner.nextLine());

        try {
            Optional<Booking> optionalBooking = bookingsRepo.insert(new Booking(s_id,c_id));
            if(optionalBooking.isPresent()){
                Booking booking = optionalBooking.get();
                System.out.println(booking+" [student] "+getStudentNameByFk(booking)+" [course] "+getCourseNameByFk(booking));
            }
        } catch (MySQLDBException sqldbException){
            System.out.println("DB Fehler: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Fehler: "+e.getMessage());
        }
    }

    private void deleteBooking(){
        System.out.println("Für welche BuchungsID?");
        Long bookingID = Long.parseLong(scanner.nextLine());
        bookingsRepo.deleteById(bookingID);
    }

    private void allBookingBeforeDate(){
        System.out.println("Bitte Datum angeben (YYYY-MM-DD)");
        Date searchdate = Date.valueOf(scanner.nextLine());

        List<Booking> bookingsbefore = bookingsRepo.showAllBookingsBeforeDate(searchdate);
        if(bookingsbefore.size() == 0){
            System.out.println("Keine Buchungen vor "+searchdate);
        } else {
            for(Booking b:bookingsbefore){
                System.out.println(b+" [student] "+getStudentNameByFk(b)+" [course] "+getCourseNameByFk(b));
            }
        }
    }


    //------------------------------Student------------------------------------------------------------------
    private void showAllStudents(){
        List<Student> students = studentRepo.getAll();
        if(students.size() == 0){
            System.out.println("Keine Studenten verfügbar");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }

    }

    private void createStudent(){
        String name, email;
        try
        {
            System.out.println("Bitte alle Studentendaten angeben:");

            System.out.println("Name: ");
            name = scanner.nextLine();
            if(name.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein"); //Eingabevalidierung

            System.out.println("Email: ");
            email = scanner.nextLine();
            if(email.equals("")) throw new IllegalArgumentException("Eingabe darf nicht leer sein");

            Optional<Student> optionalStudent = studentRepo.insert(new Student(name,email));

            if(optionalStudent.isPresent()){
                System.out.println("Student angelegt: "+ optionalStudent.get());
            } else {
                System.out.println("Student konnte nicht angelegt werden");
            }

        } catch (IllegalArgumentException illegalArgumentException){
            System.out.println("Eingabefehler: "+illegalArgumentException.getMessage());
        } catch (InvalidValueException invalidValueException){
            System.out.println("Studentendaten nicht korrekt angegeben: "+invalidValueException.getMessage());
        } catch (MySQLDBException sqldbException){
            System.out.println("DB Fehler beim Einfügen: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Einfügen: "+e.getMessage());
        }
    }

    private void changeStudent(){
        System.out.println("Für welche StudentenID Details ändern?");
        Long studentID = Long.parseLong(scanner.nextLine());

        try
        {
            Optional<Student> studentOptional = studentRepo.getById(studentID);
            if(studentOptional.isEmpty()){
                System.out.println("Student mit ID "+ studentID +" nicht gefunden");
            } else {
                Student student = studentOptional.get();
                System.out.println("Änderungen für folgenden Studenten: ");
                System.out.println(student);

                String name, mail;
                System.out.println("Bitte neue Studentendaten angeben (Enter falls keine Änderung gewünscht ist)");
                System.out.println("Name: ");
                name = scanner.nextLine();
                System.out.println("Email: ");
                mail = scanner.nextLine();

                Optional<Student> studentOptionalUpdated = studentRepo.update(new Student(student.getId(),name.equals("") ? student.getStudentname() : name,mail.equals("") ? student.getStudentmail() : mail));

                studentOptionalUpdated.ifPresentOrElse(
                        (c)-> System.out.println("Student aktualisiert: "+c), //if true
                        ()-> System.out.println("Student konnte nicht aktualisiert werden") //else
                );
            }
        } catch (Exception e){
            System.out.println("Unbekannter Fehler bei Student Update: "+e.getMessage());
        }

    }

    private void deleteStudent(){
        try {
            System.out.println("Welchen StudentID löschen?");
            Long studentID = Long.parseLong(scanner.nextLine());
            studentRepo.deleteById(studentID);
        } catch (Exception e){
            System.out.println("Unbekannter Fehler beim Student Löschen");
        }
    }

    private void findStudentByNameLike(){
        System.out.println("Suchbegriff eingeben: ");
        String search = scanner.nextLine();

        try {
            List<Student> students = studentRepo.findAllStudentsByNameLike(search);
            for (Student s:students){
                System.out.println(s);
            }
        } catch (MySQLDBException sqldbException) {
            System.out.println(sqldbException.getMessage());
        }
    }


    // ------------------------- Course ------------------------------------------------

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

            Optional<Course> optionalCourse = courseRepo.insert(new Course(name,desc,hours,beginDate,endDate,courseType));

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
            Optional<Course> courseOptional = courseRepo.getById(courseID);
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
            Optional<Course> courseOptional = courseRepo.getById(courseID);
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

                Optional<Course> courseOptionalUpdated = courseRepo.update(new Course(course.getId(),name.equals("") ? course.getName() : name,desc.equals("") ? course.getDescr() : desc,hours.equals("") ? course.getHours() : Integer.parseInt(hours),dateFrom.equals("") ? course.getBeginDate() : Date.valueOf(dateFrom),dateTo.equals("") ? course.getEndDate() : Date.valueOf(dateTo),courseType.equals("") ? course.getCourseType():CourseType.valueOf(courseType)));

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


    private void courseSearch(){
        System.out.println("Geben Sie einen Suchbegriff ein: ");
        String searchString = scanner.nextLine();
        List<Course> courseList;

        try
        {
            courseList = courseRepo.findAllCoursesByNameOrDescr(searchString);
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

            list = courseRepo.findAllRunningCourses();
            for(Course course:list){
                System.out.println(course);
            }
        } catch (MySQLDBException sqldbException){
            System.out.println("Datenbankfehler für laufende Kurse: "+sqldbException.getMessage());
        } catch (Exception e){
            System.out.println("Unbekannter Fehler für laufende Kurse: "+e.getMessage());
        }
    }

    private void inputError()
    {
        System.out.println("Bitte nur Möglichkeiten der Menüauswahl eingeben!");
    }

    private void showAllCourses(){
        List<Course> list = null;
        list = courseRepo.getAll();

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
