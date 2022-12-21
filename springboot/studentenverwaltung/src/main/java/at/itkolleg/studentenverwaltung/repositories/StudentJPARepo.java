package at.itkolleg.studentenverwaltung.repositories;

import at.itkolleg.studentenverwaltung.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //nicht zwingend, der Vollständigkeit
public interface StudentJPARepo extends JpaRepository<Student,Long> {
    List<Student> findAllByPlz(String plz);
}
