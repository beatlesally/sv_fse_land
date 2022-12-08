package it.kolleg.domain;

import java.sql.Date;

public class Booking extends BaseEntity{

    private int fk_s;
    private int fk_c;
    private Date bookingdate;

    public Booking(Long id, int fk_s, int fk_c, Date bookingdate) {
        super(id);
        this.fk_s = fk_s;
        this.fk_c = fk_c;
        this.bookingdate = bookingdate;
    }

    public Booking(int fk_s, int fk_c, Date bookingdate) {
        super(null);
        this.fk_s = fk_s;
        this.fk_c = fk_c;
        this.bookingdate = bookingdate;
    }

    public int getFk_s() {
        return fk_s;
    }

    public void setFk_s(int fk_s) {
        if(fk_s > 0) {
            this.fk_s = fk_s;
        } else {
            throw new InvalidValueException("Studentennr muss größer 1 sein");
        }
    }

    public int getFk_c() {
        return fk_c;
    }

    public void setFk_c(int fk_c) {
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
}
