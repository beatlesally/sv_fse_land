package it.kolleg.dataaccess;

import it.kolleg.domain.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlBookingsRepository implements MyBookingsRepository{
    private Connection conn;
    public MySqlBookingsRepository() throws SQLException, ClassNotFoundException {
        this.conn = MySQLDBConnection.getConn("jdbc:mysql://localhost:3306/kurssystem", "root", "");
    }

    @Override
    public Optional<Booking> insert(Booking entity) {

        try {
            String sql = "INSERT INTO `bookings`(`fk_s`, `fk_c`, `datum`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,entity.getFk_s());
            preparedStatement.setLong(2,entity.getFk_c());
            preparedStatement.setDate(3,entity.getBookingdate());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
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
    public Optional<Booking> getById(Long id) {
        try {
            String sql = "SELECT * FROM `bookings` WHERE `id`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Booking b = new Booking(id,resultSet.getLong(2),resultSet.getLong(3),resultSet.getDate(4));
               return Optional.of(b);
            } else {
                return Optional.empty();
            }
        } catch (SQLException sqlException){
            throw new MySQLDBException(sqlException.getMessage());
        }
    }

    @Override
    public List<Booking> getAll() {
        try {
            String sql = "SELECT * FROM `bookings`";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();

            while (resultSet.next()){
                bookings.add(new Booking(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDate(4)));
            }
            return bookings;
        } catch (SQLException e) {
            throw new MySQLDBException(e.getMessage());
        }
    }

    @Override
    public Optional<Booking> update(Booking entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        try {
            String sql = "DELETE FROM `bookings` WHERE `id`=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MySQLDBException(e.getMessage());
        }
    }

    @Override
    public List<Booking> showAllBookingsBeforeDate(Date date) {
        try {
            String sql = "SELECT * FROM `bookings` WHERE datum < ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1,date);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();

            while (resultSet.next()){
                bookings.add(new Booking(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getDate(4)));
            }
            return bookings;

        } catch (SQLException e) {
            throw new MySQLDBException(e.getMessage());
        }
    }
}
