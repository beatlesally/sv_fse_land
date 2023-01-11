package at.itkolleg.studentenverwaltung.services;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;

import java.util.List;

/**
 * Service Layer für Business Logic zuständig
 * Ähnlich zu Data Layer, jedoch wird hier geprüft, ob Student schon existiert, etc.
 */
public interface StudentService {

    List<Student> getAllStudents();
    Student addStudent(Student student);
    Student getStudentByID(Long id) throws StudentNotFound;
    List<Student> getAllStudentsFromPLZ(String plz);
    void deleteStudentbyID(Long id) throws StudentNotFound;

}
