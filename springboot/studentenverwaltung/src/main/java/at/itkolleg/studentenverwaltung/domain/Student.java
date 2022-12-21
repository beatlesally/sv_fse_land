package at.itkolleg.studentenverwaltung.domain;

import at.itkolleg.studentenverwaltung.StudentenverwaltungApplication;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Size(min = 2) //Validierung (Validation)
    private String name;
    @Size(min = 4, max = 6)
    private String plz;

    public Student(String name, String plz)
    {
        this.name = name;
        this.plz = plz;
    }

}
