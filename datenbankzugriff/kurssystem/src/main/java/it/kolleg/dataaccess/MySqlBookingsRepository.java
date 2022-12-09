package it.kolleg.dataaccess;

import it.kolleg.domain.Booking;

import java.util.List;
import java.util.Optional;

public class MySqlBookingsRepository implements MyBookingsRepository{
    @Override
    public Optional<Booking> insert(Booking entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Booking> getAll() {
        return null;
    }

    @Override
    public Optional<Booking> update(Booking entity) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Booking> showAllBookingsWithStudentsCourses() {
        return null;
    }
}
