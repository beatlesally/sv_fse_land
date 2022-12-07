package it.kolleg.dataaccess;

import it.kolleg.domain.Course;
import it.kolleg.domain.CourseType;
import it.kolleg.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCourseRepository implements MyCoursesRepository{

    private Connection conn;

    public MySqlCourseRepository() throws SQLException, ClassNotFoundException {
        this.conn = MySQLDBConnection.getConn("jdbc:mysql://localhost:3306/kurssystem", "root", "");
    }


    /**
     * CRUD
     */
    @Override
    public Optional<Course> insert(Course entity) {
        return Optional.empty();
    }

    /**
     * CRUD
     */
    @Override
    public Optional<Course> getById(Long id)
    {
        Assert.notNull(id);
        if(countCoursesInDBWithId(id)==0)
        {
            return Optional.empty();
        } else {
            try
            {
                String sql = "SELECT * FROM `courses` WHERE `id`=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                Course course = new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype")) //String kommt zurück aber wird brauchen enum, umwandeln
                );
                return Optional.of(course);
            } catch (SQLException sqlException){
                throw new MySQLDBException(sqlException.getMessage());
            }
        }
    }

    private int countCoursesInDBWithId(long id)
    {
        try
        {
            String countSQL = "SELECT COUNT(*) FROM `courses` where `id`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(countSQL);
            preparedStatement.setLong(1,id);
            ResultSet resultSetCount = preparedStatement.executeQuery();
            resultSetCount.next();
            int courseCount = resultSetCount.getInt(1);
            return courseCount;
        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }

    }

    /**
     * CRUD
     */
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
            }
            return courseList;
        } catch (SQLException e){
            throw new MySQLDBException("Database error occured!");
        }
    }

    /**
     * CRUD
     */
    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    /**
     * CRUD
     */
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
