package at.itkolleg.studentenverwaltung.controller;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;
import at.itkolleg.studentenverwaltung.exceptions.StudentValidationFailed;
import at.itkolleg.studentenverwaltung.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student, BindingResult bindingResult) throws StudentValidationFailed //in BindingResult Validierungserrors
    {
        String errors = "";
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()){
                errors += "\n Validationerror for object: " + error.getObjectName() +
                        " in field " + ((FieldError)error).getField() +
                        " with problem " + error.getDefaultMessage();
            }
            throw new StudentValidationFailed(errors);
        } else {
            return ResponseEntity.ok(this.studentService.addStudent(student));
        }
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
