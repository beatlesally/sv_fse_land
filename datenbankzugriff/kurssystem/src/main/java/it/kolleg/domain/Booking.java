package it.kolleg.domain;

import java.sql.Date;

public class Booking extends BaseEntity{

    private Long fk_s;
    private Long fk_c;
    private Date bookingdate;

    public Booking(Long id, Long fk_s, Long fk_c, Date bookingdate) {
        super(id);
        setFk_c(fk_c);
        setFk_s(fk_s);
        this.bookingdate = bookingdate;
    }

    public Booking(Long fk_s, Long fk_c) {
        super(null);
        setFk_c(fk_c);
        setFk_s(fk_s);
        setBookingdate();
    }

    public Long getFk_s() {
        return fk_s;
    }

    public void setFk_s(Long fk_s) {
        if(fk_s > 0) {
            this.fk_s = fk_s;
        } else {
            throw new InvalidValueException("Studentennr muss größer 1 sein");
        }
    }

    public Long getFk_c() {
        return fk_c;
    }

    public void setFk_c(Long fk_c) {
        if(fk_c > 0) {
            this.fk_c = fk_c;
        } else {
            throw new InvalidValueException("Kursnr muss größer 1 sein");
        }
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate() {
        this.bookingdate = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "[bookingID] "+getId()+" [bookingdate] "+getBookingdate();
    }
}
