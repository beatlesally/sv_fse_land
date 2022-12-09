package it.kolleg.dataaccess;

import it.kolleg.domain.Student;
import it.kolleg.util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlStudentRepository implements MyStudentRepository{

    private Connection conn;

    public MySqlStudentRepository() throws SQLException, ClassNotFoundException {
        this.conn = MySQLDBConnection.getConn("jdbc:mysql://localhost:3306/kurssystem", "root", "");
    }

    @Override
    public Optional<Student> insert(Student entity) {
        Assert.notNull(entity);

        try {
            String sql = "INSERT INTO `student`(`name`, `email`) VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,entity.getStudentname());
            preparedStatement.setString(2,entity.getStudentmail());

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

        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }

    }

    @Override
    public Optional<Student> getById(Long id) {
        try {
            String sql = "SELECT * FROM `student` WHERE `studentennummer`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Student student = new Student(id, resultSet.getString("name"), resultSet.getString("email"));
                return Optional.of(student);
            } else {
                return Optional.empty();
            }

        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }

    }

    @Override
    public List<Student> getAll() {
        try {
            String sql = "SELECT * FROM `student`";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()){
                students.add(new Student(resultSet.getLong("studentennummer"), resultSet.getString("name"),resultSet.getString("email")));
            }
            return students;
        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Student> update(Student entity) {
        try {
            String sql = "UPDATE `student` SET `name`=?,`email`=? WHERE `studentennummer`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, entity.getStudentname());
            preparedStatement.setString(2, entity.getStudentmail());
            preparedStatement.setLong(3, entity.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                return Optional.empty();
            } else {
                return this.getById(entity.getId()); //gibt bereits ein Optional<Student> zurück!
            }

        } catch(SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            String sql = "DELETE FROM `student` WHERE `studentennummer`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }
    }

    @Override
    public List<Student> findAllStudentsByNameLike(String search) {
        try {
            String sql = "SELECT * FROM `student` WHERE LOWER(`name`) LIKE LOWER(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%"+search+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();

            while (resultSet.next()){
                students.add(new Student(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3)));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
