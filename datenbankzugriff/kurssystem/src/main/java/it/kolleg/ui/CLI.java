package it.kolleg.ui;

import java.util.Scanner;

public class CLI {

    Scanner scanner;

    public CLI()
    {
        this.scanner = new Scanner(System.in);
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
                    System.out.println("kurs anzeigen");
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
}
