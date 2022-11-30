package it.kolleg.dataaccess;

import it.kolleg.domain.Course;
import it.kolleg.domain.CourseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCourseRepository implements MyCoursesRepository{

    private Connection conn;

    public MySqlCourseRepository() throws SQLException, ClassNotFoundException {
        this.conn = MySQLDBConnection.getConn("jdbc:mysql://localhost:3306/kurssystem", "root", "");
    }

    @Override
    public Optional<Course> insert(Course entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM `courses`";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Course> courseList = new ArrayList<>();
            while(resultSet.next()){
                //course: id, name, descr, hours, begindate, enddate, coursetype
                courseList.add(new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype")) //String kommt zurück aber wird brauchen enum, umwandeln
                        )
                );
                return courseList;
            }
        } catch (SQLException e){
            throw new MySQLDBException("Database error occured!");
        }
        return null;
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Course> findAllCoursesByID(long id) {
        return null;
    }

    @Override
    public List<Course> findAllCoursesByDesc(String descr) {
        return null;
    }

    @Override
    public List<Course> findAllCoursesByNameOrDescr(String searchText) {
        return null;
    }

    @Override
    public List<Course> findAllCoursesByCourseType(CourseType courseType) {
        return null;
    }

    @Override
    public List<Course> findAllCoursesRunningCourses() {
        return null;
    }

    @Override
    public List<Course> findAllCoursesByByStartDate(Date startdate) {
        return null;
    }
}
