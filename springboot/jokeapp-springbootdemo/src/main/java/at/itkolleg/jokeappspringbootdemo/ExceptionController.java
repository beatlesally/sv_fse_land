package at.itkolleg.jokeappspringbootdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //wenn Exception geworfen, wird Handler für spezielle Exception gesucht, wenn gefunden, wird das zurückgegeben
public class ExceptionController {

    @ExceptionHandler(JokeNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> genericException(JokeNotFoundException jokeNotFoundException)
    {
        return new ResponseEntity<>(new ExceptionResponseDTO("1000",jokeNotFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }
    /*public ResponseEntity<String> genericException(JokeNotFoundException jokeNotFoundException)
    {
        return new ResponseEntity<>(jokeNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }*/
}
