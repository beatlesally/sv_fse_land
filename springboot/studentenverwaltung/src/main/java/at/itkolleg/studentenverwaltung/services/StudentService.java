package at.itkolleg.studentenverwaltung.services;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;

import java.util.List;

/**
 * Service Layer für Business Logic zuständig
 */
public interface StudentServices {

    List<Student> getAllStudents();
    Student addStudent();
    Student getStudentByID() throws StudentNotFound;
    List<Student> getAllStudentsFromPLZ(String plz);
    void deleteStudentbyID(Long id) throws StudentNotFound;

}
