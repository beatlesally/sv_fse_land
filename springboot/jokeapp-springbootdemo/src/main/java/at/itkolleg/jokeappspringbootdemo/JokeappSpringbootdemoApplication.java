package at.itkolleg.jokeappspringbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JokeappSpringbootdemoApplication implements ApplicationRunner {

    @Autowired //Springboot sucht nach Implementierung von JokesRepository = Dependency Injection (zur Laufzeit)
    JokesRepository myJokesRepository;
    public static void main(String[] args) {
        SpringApplication.run(JokeappSpringbootdemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myJokesRepository.save(new Joke(null,"Chuck Norris pausiert Online-Spiele", "Chuck-Norris-Witze",0));
        myJokesRepository.save(new Joke(null,"Chuck Norris kann ein Feuer entfachen, indem er zwei Eiswürfel aneinander reibt.", "Chuck-Norris-Witze",0));
        myJokesRepository.save(new Joke(null,"Chuck Norris hat seine Führerscheinprüfung bestanden – und zwar zu Fuß.", "Chuck-Norris-Witze",5));
        myJokesRepository.save(new Joke(null,"Chuck Norris kann Hardware downloaden.", "Chuck-Norris-Witze",0));
        myJokesRepository.save(new Joke(null,"Wenn Chuck Norris in die Steckdose greift, dann bekommt der Strom einen Schlag.", "Chuck-Norris-Witze",0));
        System.out.println("Hallo ApplicationRunner");
    }
}
