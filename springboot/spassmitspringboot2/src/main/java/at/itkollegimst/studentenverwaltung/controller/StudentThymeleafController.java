package at.itkollegimst.studentenverwaltung.controller;


import at.itkollegimst.studentenverwaltung.domain.Student;
import at.itkollegimst.studentenverwaltung.exceptions.StudentNichtGefunden;
import at.itkollegimst.studentenverwaltung.services.StudentenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/insert")
    public String addStudentForm(Model model)
    {
        Student student = new Student();
        model.addAttribute("student", student); //leeres Studentenobjekt wird übergeben zu weiteren befüllung
        return "addstudent";
    }

    @PostMapping("/insert")
    public String addStudent(@Valid Student student, BindingResult bindingResult) //wenn mit @Valid Probleme (Validierungsprobelem), in BindingResult gespeichert
    {
        if(bindingResult.hasErrors())
        {
            return "addstudent";
        } else {
            this.studentenService.studentEinfuegen(student);
            return "redirect:/web/v1/studenten"; //redirect auf studentenliste
        }
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable Long id, Model model)
    {
        try {
            Student student = this.studentenService.studentMitId(id);
            model.addAttribute("student", student);
            return "updatestudent";
        } catch (StudentNichtGefunden studentNichtGefunden){
            return "redirect:/web/v1/studenten";
        }
    }
}
