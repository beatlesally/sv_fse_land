package it.kolleg.dataaccess;

import it.kolleg.domain.Student;

import java.util.List;

public interface MyStudentRepository extends BaseRepository<Student,Long> {
    public List<Student> findAllStudentsByNameLike(String search);
}
