package at.itkolleg.jokeappspringbootdemo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JokesRepository extends JpaRepository<Joke,Long> { //bietet alle CRUD Methoden
    public List<Joke> getAllByGenre(String genre); //Spring generiert automatisch die Implementierung
}
