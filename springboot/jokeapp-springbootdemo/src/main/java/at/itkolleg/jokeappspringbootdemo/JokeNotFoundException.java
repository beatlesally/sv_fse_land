package at.itkolleg.jokeappspringbootdemo;

public class JokeNotFoundException extends RuntimeException {
    public JokeNotFoundException(String s) {
        super(s);
    }
}
