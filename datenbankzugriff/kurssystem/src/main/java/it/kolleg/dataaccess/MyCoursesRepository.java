package it.kolleg.dataaccess;

import it.kolleg.domain.Course;
import it.kolleg.domain.CourseType;

import java.sql.Date;
import java.util.List;

/**
 * erbt alle Methodenköpfe von BaseRepository; spezielle Methoden kommen für Course Entity hinzu
 */
public interface MyCoursesRepository extends BaseRepository<Course,Long> {

    List<Course> findAllCoursesByID(long id);
    List<Course> findAllCoursesByDesc(String descr);
    List<Course> findAllCoursesByNameOrDescr(String searchText);
    List<Course> findAllCoursesByCourseType(CourseType courseType);
    List<Course> findAllCoursesRunningCourses();
    List<Course> findAllCoursesByByStartDate(Date startdate);

}
