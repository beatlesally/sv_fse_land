package it.kolleg.ui;

import it.kolleg.dataaccess.MyCoursesRepository;
import it.kolleg.dataaccess.MySQLDBException;
import it.kolleg.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    Scanner scanner;
    MyCoursesRepository repo;


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
                    System.out.println("Kurseingabe");
                    break;
                case "2":
                    showAllCourses();
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
        System.out.println("(1) Kurs eingeben \t (2) Alle Kurse anzeigen \t");
        System.out.println("(x) Ende");
    }

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
