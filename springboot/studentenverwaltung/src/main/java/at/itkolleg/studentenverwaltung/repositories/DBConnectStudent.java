package at.itkolleg.studentenverwaltung.repositories;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;

import java.util.List;

/**
 * Methoden, für die, die Datenlayer verwenden möchten, brauchen können
 */
public interface DBConnectStudent {

    Student studentSave(Student student);
    List<Student> getAllStudents();
    List<Student> getAllStudentsFromPLZ(String plz);
    Student getStudentWithID(Long id) throws StudentNotFound;
    void deleteStudent(Long id);

}
