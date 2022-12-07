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
        Assert.notNull(entity);

        try
        {
            String sql = "INSERT INTO `courses`(`name`, `description`, `hours`, `begindate`, `enddate`, `coursetype`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getDescr());
            preparedStatement.setInt(3,entity.getHours());
            preparedStatement.setDate(4,entity.getBeginDate());
            preparedStatement.setDate(5,entity.getEndDate());
            preparedStatement.setString(6,entity.getCourseType().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows==0)
            {
                return Optional.empty();
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next())
            {
                return this.getById(generatedKeys.getLong(1));
            } else {
                return Optional.empty();
            }
        } catch (SQLException sql){
            throw  new MySQLDBException(sql.getMessage());
        }
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

        Assert.notNull(entity);

        String sql = "UPDATE `courses` SET `name`=?,`description`=?,`hours`=?,`begindate`=?,`enddate`=?,`coursetype`=? WHERE `id`=?";

        if(countCoursesInDBWithId(entity.getId())==0){
            return Optional.empty();
        } else {

            try
            {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,entity.getName());
                preparedStatement.setString(2,entity.getDescr());
                preparedStatement.setInt(3,entity.getHours());
                preparedStatement.setDate(4,entity.getBeginDate());
                preparedStatement.setDate(5,entity.getEndDate());
                preparedStatement.setString(6,entity.getCourseType().toString());
                preparedStatement.setLong(7,entity.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if(affectedRows == 0){
                    return Optional.empty();
                } else {
                    return this.getById(entity.getId());
                }
            } catch (SQLException sqlException){
                throw new MySQLDBException(sqlException.getMessage());
            }
        }
    }

    /**
     * CRUD
     */
    @Override
    public void deleteById(Long id)
    {
        Assert.notNull(id);

        String sql = "DELETE FROM `courses` WHERE `id`=?";
        if(countCoursesInDBWithId(id) == 1)
        {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new MySQLDBException(e.getMessage());
            }

        }
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
        try
        {
            String sql = "SELECT * FROM `courses` WHERE LOWER(`description`) LIKE LOWER(?) OR LOWER(`name`) LIKE LOWER(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%"+searchText+"%");
            preparedStatement.setString(2,"%"+searchText+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Course> courses = new ArrayList<>();

            while (resultSet.next()){
                courses.add(new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype")) //String kommt zurück aber wird brauchen enum, umwandeln
                ));
            }
            return courses;

        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }
    }

    @Override
    public List<Course> findAllCoursesByCourseType(CourseType courseType) {
        return null;
    }

    @Override
    public List<Course> findAllRunningCourses() {
        String sql = "SELECT * FROM `courses` WHERE NOW()<`enddate`";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Course> courses = new ArrayList<>();

            while (resultSet.next()){
                courses.add(new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype")) //String kommt zurück aber wird brauchen enum, umwandeln
                ));
            }
            return courses;
        } catch (SQLException e) {
            throw new MySQLDBException(e.getMessage());
        }
    }

    @Override
    public List<Course> findAllCoursesByStartDate(Date startdate) {
        return null;
    }
}
