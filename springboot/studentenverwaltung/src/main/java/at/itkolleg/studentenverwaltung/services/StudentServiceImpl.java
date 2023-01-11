package at.itkolleg.studentenverwaltung.services;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;
import at.itkolleg.studentenverwaltung.repositories.DBAccessStudent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Hier würde überall Business Logic verbaut sein, würden es nicht einfache CRUD Methoden sein!
 */
@Component
public class StudentServiceImpl implements StudentService{

    private DBAccessStudent dbAccessStudent;

    public StudentServiceImpl(DBAccessStudent dbAccessStudent) {
        this.dbAccessStudent = dbAccessStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return this.dbAccessStudent.getAllStudents();
    }

    @Override
    public Student addStudent(Student student) {
        return this.dbAccessStudent.saveStudent(student);
    }

    @Override
    public Student getStudentByID(Long id) throws StudentNotFound {
        return this.dbAccessStudent.getStudentWithID(id);
    }

    @Override
    public List<Student> getAllStudentsFromPLZ(String plz) {
        return this.dbAccessStudent.getAllStudentsFromPLZ(plz);
    }

    @Override
    public void deleteStudentbyID(Long id) throws StudentNotFound {
        this.dbAccessStudent.deleteStudent(id);
    }
}
