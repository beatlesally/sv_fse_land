package at.itkolleg.studentenverwaltung.controller;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;
import at.itkolleg.studentenverwaltung.services.StudentService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {

    private StudentService studentService; //nur Interface, wird Autowired

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents()
    {
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        return ResponseEntity.ok(this.studentService.addStudent(student));
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) throws StudentNotFound {
          this.studentService.deleteStudentbyID(id);
          return "Student deleted!";
    }

    @GetMapping("/withplz/{plz}")
    public ResponseEntity<List<Student>> getAllStudentsByPLZ(@PathVariable String plz)
    {
        return ResponseEntity.ok(this.studentService.getAllStudentsFromPLZ(plz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws StudentNotFound {
        return ResponseEntity.ok(this.studentService.getStudentByID(id));
    }

}
