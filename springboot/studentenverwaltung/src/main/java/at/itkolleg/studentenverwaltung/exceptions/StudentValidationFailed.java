package at.itkolleg.studentenverwaltung.exceptions;

public class StudentValidationFailed extends Exception{
    public StudentValidationFailed(String message)
    {
        super(message);
    }
}
