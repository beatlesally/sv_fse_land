package it.kolleg.dataaccess;

import it.kolleg.domain.Booking;

import java.sql.Date;
import java.util.List;

public interface MyBookingsRepository extends BaseRepository<Booking,Long>{

    public List<Booking> showAllBookingsBeforeDate(Date date);
}
