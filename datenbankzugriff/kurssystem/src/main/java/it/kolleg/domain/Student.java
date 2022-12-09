package it.kolleg.domain;

public class Student extends BaseEntity{

    private String studentname;
    private String studentmail;

    public Student(Long id, String studentname, String studentmail) {
        super(id);
        this.studentname = studentname;
        this.studentmail = studentmail;
    }

    public Student(String studentname, String studentmail) {
        super(null);
        this.studentname = studentname;
        this.studentmail = studentmail;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) throws InvalidValueException{
        if(studentname.length() < 200 && studentname.length() > 3) {
            this.studentname = studentname;
        } else {
            throw new InvalidValueException("Studentenname muss im Bereich liegen (3-200 Zeichen)");
        }
    }

    public String getStudentmail() {
        return studentmail;
    }

    public void setStudentmail(String studentmail) throws InvalidValueException {
        if(studentmail.length() < 200 && studentmail.length() > 3) {
            this.studentmail = studentmail;
        } else {
            throw new InvalidValueException("Studentenmail muss im Bereich liegen (3-200 Zeichen)");
        }
    }

    @Override
    public String toString() {
        return "[studentid] " + getId() + " [studentname] "+ getStudentname() + " [studentmail] " + getStudentmail();
    }
}
