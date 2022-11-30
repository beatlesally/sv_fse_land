package it.kolleg.domain;

import java.sql.Date;

public class Course extends BaseEntity {

    private String name;
    private String descr;
    private int hours;
    private Date beginDate;
    private Date endDate;
    private CourseType courseType;

    //für ... Statements
    public Course(Long id, String name, String descr, int hours, Date beginDate, Date endDate, CourseType courseType) throws InvalidValueException{
        super(id);
        this.setName(name);
        this.setDescr(descr);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCourseType(courseType);
    }

    // für ... Statements
    public Course(String name, String descr, int hours, Date beginDate, Date endDate, CourseType courseType) throws InvalidValueException{
        super(null);
        this.setName(name);
        this.setDescr(descr);
        this.setHours(hours);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setCourseType(courseType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValueException { //muss nicht, weil unhandled expection weil von RuntimeException erbt
        if (name != null && name.length() > 1) {
            this.name = name;
        } else {
            throw new InvalidValueException("Kursname muss mindestens 2 Zeichen lang sein!");
        }
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) throws InvalidValueException {
        if (descr != null && descr.length() > 10) {
            this.descr = descr;
        } else {
            throw new InvalidValueException("Kursbezeichnung muss mindestens 10 Zeichen lang sein!");
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) throws InvalidValueException {
        if (hours > 0 && hours < 10) {
            this.hours = hours;
        } else {
            throw new InvalidValueException("Kursstunden muss zwischen 1 und 9 liegen!");
        }
        this.hours = hours;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) throws InvalidValueException {
        if (beginDate != null) {
            if (this.endDate != null) {
                if (beginDate.before(this.endDate)) {
                    this.beginDate = beginDate;
                } else {
                    throw new InvalidValueException("Kursbeginn muss vor Kursende sein!");
                }
            } else {
                this.beginDate = beginDate;
            }
        } else {
            throw new InvalidValueException("Startdatum darf nicht leer sein!");
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) throws InvalidValueException {
        if (endDate != null) {
            if (this.beginDate != null) {
                if (endDate.after(this.beginDate)) {
                    this.endDate = endDate;
                } else {
                    throw new InvalidValueException("Kursende muss nach Kursbeginn sein!");
                }
            } else {
                this.endDate = endDate;
            }
        } else {
            throw new InvalidValueException("Enddatum darf nicht leer sein!");
        }
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) throws InvalidValueException {
        if (courseType != null) {
            this.courseType = courseType;
        } else {
            throw new InvalidValueException("Kurstyp darf nicht leer sein!");
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", hours=" + hours +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", courseType=" + courseType +
                '}';
    }
}
