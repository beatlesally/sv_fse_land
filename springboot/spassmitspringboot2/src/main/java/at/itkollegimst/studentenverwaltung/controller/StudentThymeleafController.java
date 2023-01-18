package at.itkollegimst.studentenverwaltung.controller;


import at.itkollegimst.studentenverwaltung.services.StudentenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/v1/studenten")
public class StudentThymeleafController {

    private StudentenService studentenService;

    public StudentThymeleafController(StudentenService studentenService) { //Konstruktorinjektion
        this.studentenService = studentenService;
    }

    @GetMapping
    public String getAllStudents(Model model) //bei Thymeleaf wird String zurückgegeben
    {
        model.addAttribute("getAllStudents", this.studentenService.alleStudenten()); //attributeName == über das kann aufgerufen werden
        return "allstudents"; //muss gleich wie Filename sein! //dieses HTML file wird bei Get zurückgegeben
    }
}
