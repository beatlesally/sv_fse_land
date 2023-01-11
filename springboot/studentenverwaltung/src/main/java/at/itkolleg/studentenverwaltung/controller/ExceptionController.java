package at.itkolleg.studentenverwaltung.controller;

import at.itkolleg.studentenverwaltung.exceptions.ExceptionDTO;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;
import at.itkolleg.studentenverwaltung.exceptions.StudentValidationFailed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(StudentNotFound.class) //Exception für die diese Methode zutreffen soll
    public ResponseEntity<ExceptionDTO> ExcStudentNotFound(StudentNotFound studentNotFound) //wird aufgerufen wenn StudentNotFound-Exception im System geworfen wird
    {
        return new ResponseEntity<>(new ExceptionDTO("1000",studentNotFound.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentValidationFailed.class)
    public ResponseEntity<ExceptionDTO> ExcStudentValidationFailed(StudentValidationFailed studentValidationFailed) //wird aufgerufen wenn StudentNotFound-Exception im System geworfen wird
    {
        return new ResponseEntity<>(new ExceptionDTO("9000",studentValidationFailed.getMessage()), HttpStatus.NOT_FOUND);
    }
}
