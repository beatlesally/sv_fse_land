package at.itkolleg.jokeappspringbootdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok
@Getter //alle Getter
@Setter //alle Setter
@AllArgsConstructor //Konstruktor mit allen Datenfeldern als Parameter
@NoArgsConstructor //Konstruktor ohne Datenfelder als Parameter
@Entity //ist dann Entity in h2-DB
public class Joke {
    @Id
    @GeneratedValue //AutoIncrement
    private Long id; //wegen @Entity = Primary Key
    private String joketext;
    private String genre;
    private int usk;
}
