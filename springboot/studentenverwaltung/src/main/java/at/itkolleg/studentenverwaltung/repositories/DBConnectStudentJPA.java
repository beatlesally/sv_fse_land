package at.itkolleg.studentenverwaltung.repositories;

import at.itkolleg.studentenverwaltung.domain.Student;
import at.itkolleg.studentenverwaltung.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //für Dependency Injection & @Autowired; initierbare Komponente
public class DBConnectStudentJPA implements DBConnectStudent{

    /*@Autowired*/
    private StudentJPARepo studentJPARepo;

    public DBConnectStudentJPA(StudentJPARepo studentJPARepo) { //Alternative zu Autowired
        this.studentJPARepo = studentJPARepo;
    }

    @Override
    public Student studentSave(Student student) {
        return this.studentJPARepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentJPARepo.findAll();
    }

    @Override
    public List<Student> getAllStudentsFromPLZ(String plz) {
        return this.studentJPARepo.findAllByPlz(plz);
    }

    @Override
    public Student getStudentWithID(Long id) throws StudentNotFound {
        return this.studentJPARepo.findById(id).orElseThrow(() -> new StudentNotFound("Student with ID:"+id+" not found!"));
    }

    @Override
    public void deleteStudent(Long id) {
        this.studentJPARepo.deleteById(id);
    }
}
