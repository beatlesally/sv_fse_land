package at.itkolleg.jokeappspringbootdemo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //annotation: als Rest API markieren
@RequestMapping("/api/v1/jokes") //über welchen Pfad; wenn auf Haupturl, dann alles (meist bei den REST-APIs so gängig)
public class JokeRestAPI {

    @Autowired
    JokesRepository jokesRepository;

    @PostMapping
    public ResponseEntity<Joke> insertJoke(@RequestBody Joke joke)
    {
        joke.setId(null); //wenn jemand id mitgibt, dann wird überschrieben, somit sichergestellt, dass keine id gesetzt ist
        return ResponseEntity.ok(this.jokesRepository.save(joke)); //save gibt eingefügte Entity zurück
    }

    @GetMapping("/{id}")//gibt an, welche http-Methode diese Methode bedienen soll -> GET; {variable} um mit @PathVariable in Parameter zu setzen - müssen gleichen Namen haben
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) //http-Response als rückgabe
    {
        //return ResponseEntity.ok("Warum sind Chuck Norris Witze so lustig?"); //ok = 200
        Optional<Joke> jokeOptional = jokesRepository.findById(id);
        if (jokeOptional.isPresent()){
            return ResponseEntity.ok(jokeOptional.get());
        } else {
            throw new JokeNotFoundException("This joke is not in our db");
        }

    }

    @GetMapping
    public ResponseEntity<List<Joke>> getAllJokes()
    {
        return ResponseEntity.ok(jokesRepository.findAll());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Joke> deleteJokeByID(@PathVariable Long id)
    {
        //Joke jokeToDelete = jokesRepository.findById(id).orElseThrow(()-> new JokeNotFoundException("This joke is not in our db")); Kurzform

        Optional<Joke> deleteJokeOptional = jokesRepository.findById(id);
        if (deleteJokeOptional.isPresent()){
            jokesRepository.deleteById(id);
            return ResponseEntity.ok(deleteJokeOptional.get());
        } else {
            throw new JokeNotFoundException("This joke is not in our db");
        }

    }
}
