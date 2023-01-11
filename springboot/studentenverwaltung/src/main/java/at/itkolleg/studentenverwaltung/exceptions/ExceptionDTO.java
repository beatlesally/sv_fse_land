package at.itkolleg.studentenverwaltung.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionDTO {

    private String code;
    private String message;

}
