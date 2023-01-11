# Fullstack Development Spring Boot

## Table of Contents

**Backend**
- [Getting Started with Spring Boot](#getting-started)


**Frontend**
- [Thymeleaf Frontend]
- [JS Frontend]

## Getting Started
> Manche Funktionen für Spring Boot können **nicht** mit der Community Edition von IntelliJ genutzt werden. Es wird die Pro Version verwendet (als Student gratis).

### Spring Boot Project in IntelliJ erzeugen


### Spring Boot Project mit Starter

### Verwendete Technologien
- Maven (für die Dependency Management)
- Lombok
- Beans
- Spring Boot Framework

#### Annotation und die Möglichkeiten
Mit @Annotation wird Spring Boot gezeigt, was diese Klasse/Methode/Variable für Funktionen haben soll bzw wie sie funktioniert. 

Es gibt folgende Annotation:
- Klassenannotation ([RestController](#restcontroller))
    - Methodenannotation ([GetMapping](#getmapping))
        - Variablenannotation ([Autowired](#autowired))

## REST-API
...

### @RestController
Mit dieser Annotation wird die Klasse als REST API markiert.

## Mappings
Es gibt verschiedene HTTP-Request. Mit den Mappings kann für den bestimmten Request auf die Methode gezeigt werden, die dafür verwendet/aufgerufen werden soll.

HTTP-Requests (als Code markiert, werden näher beschrieben):
- `GET`
- `POST`
- PUT
- PATCH
- `DELETE`
- OPTIONS
- HEAD
- TRACE
- CONNECT

Bei den Requests werden immer `RequestEntity` zurückgegeben. Das kann einfach der Response-Code sein oder auch etwas im Body enthalten.

### @RequestMapping
...gibt den Pfad (Haupturl) an, über den zugegriffen werden kann.
```java
@RequestMapping("/api/v1/jokes")
public class JokeRestAPI {/**Code ausgelassen**/}
```

### @PostMapping
...wird für den HTTP-Request `POST` verwendet.
```java
@PostMapping
public ResponseEntity<Joke> insertJoke(@RequestBody Joke joke)
{
    joke.setId(null);
    //ok == 200
    return ResponseEntity.ok(this.jokesRepository.save(joke))
}
```

#### @RequestBody
So wird das Mitgesendete (wie bei POST) im Body aufgefangen und kann verwendet werden. Es wird automatisch in das Objekt gemappt (in diesem Fall `Joke`).

### @GetMapping
...wird für den HTTP-Request `GET` verwendet.
```java
//mit einer Variable
@GetMapping("/{id}")//{variable} um mit @PathVariable in Parameter zu setzen - müssen gleichen Namen haben!
public ResponseEntity<Joke> getJokeById(@PathVariable Long id)
{
    Optional<Joke> jokeOptional = jokesRepository.findById(id);
    if (jokeOptional.isPresent()){
        return ResponseEntity.ok(jokeOptional.get());
    } else {
        throw new JokeNotFoundException("This joke is not in our db");
    }
}

//einfacher GET-Request -> wird kein Path angegeben, wird diese Methode beim einfachen Get auf die Haupturl ausgeführt
@GetMapping
public ResponseEntity<List<Joke>> getAllJokes()
{
    return ResponseEntity.ok(jokesRepository.findAll());
}

```

### @DeleteMapping
...wird für den HTTP-Request `DELETE` verwendet.
```java
@DeleteMapping("/{id}")
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
```

#### @PathVariable
Wird in der URL eine `Variable` mitgegeben (localhost/api/v1/jokes/`1`) kann diese aufgegriffen werden. Hierfür müssen der Parameter der Methode und die Variable im Path gleich heißen.

### @Autowired
Spring Boot übernimmt die Dependency Injection. Er sucht nach einer Implementierung und fügt sie zur Laufzeit ein. Somit hat man keine Abhängigkeit mit `new Object` erzeugt.
```java
@Autowired
JokesRepository jokesRepository;
```

### @Component / @Service / @Controller / @Repository
Mit @Component (od. andere) wird Spring gesagt, dass dies ein Custom Bean ist. Somit kann er diese Komponente automatisch injezieren. @Service / @Controller / @Repository sind nur spezialisierte Namen, bringen aber die gleichen Funktionen wie @Component.


#### Dependency Injection
> Als Dependency Injection [...] wird in der objektorientierten Programmierung ein Entwurfsmuster bezeichnet, welches die Abhängigkeiten eines Objekts zur Laufzeit reglementiert: Benötigt ein Objekt beispielsweise bei seiner Initialisierung ein anderes Objekt, ist diese Abhängigkeit an einem zentralen Ort hinterlegt – es wird also nicht vom initialisierten Objekt selbst erzeugt.
> (https://de.wikipedia.org/wiki/Dependency_Injection)

### @Entity
Weil wird die h2-DB verwendet haben, wird mit Entity automatisch eine Entität in der DB erzeugt.
```java
@Entity 
public class Joke {
    @Id //als ID - Primary Key kennzeichnen
    @GeneratedValue //AutoIncrement
    private Long id; //wegen @Entity = Primary Key
    private String joketext;
    private String genre;
    private int usk;
```

### @Size
Kann die Size einer Variable festlegen. Wird ein neues Objekt erstellt und die Size entspricht nicht dem angegebenen, wird eine Exception geworfen.
```java
@Size(min = 4, max = 6) //max ist optional
private String plz;
```
### @Valid & BindingResult
Ist zu @Size auch mit der Validierung verbunden. 
```java
public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student, BindingResult bindingResult){/***Code ausgelassen***/}
```
Treten bei der Validierung Fehler auf, werden diese in BindingResult gespeichert. Über dieses BindingResult kann iteriert werden.
```java
if(bindingResult.hasErrors()){
    for (ObjectError error : bindingResult.getAllErrors()){
        errors += "\n Validationerror for object: " + error.getObjectName() +
                " in field " + ((FieldError)error).getField() +
                " with problem " + error.getDefaultMessage();
    }
}
```
### @ControllerAdvice
Mit dieser Annotation können Exceptions über die ganze Applikation gehandled werden. (https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f)
Somit ist es möglich einen zentralen Controller für das Exception Handling zu erstellen.
```java
@ControllerAdvice
public class ExceptionController {/***hier sind die Methoden mit @ExceptionHandler enthalten***/}
```
### @ExceptionHandler
Ein zentraler Exception Handler kann die Exceptions der ganzen Applikation handeln; auch angepasste Nachrichten an den Benutzer im Body zurückliefern und nicht nur die Exceptionmessage am Server ausgeben. 
Wenn eine Exception geworfen wird, springt dieser ExceptionHandler ein (systemweit) und führt die Methode aus. Hierfür muss aber für diese Exception auch ein ExceptionHandler bestehen!
```java
/**
 * In diesem Beispiel wurde eine eigene Klasse ExceptionDTO erstellt, die
 * zwei Datenfelder mit code und message beinhaltet.
 */
 @ExceptionHandler(StudentNotFound.class) //Exception für die diese Methode ausgeführt werden soll
    public ResponseEntity<ExceptionDTO> ExcStudentNotFound(StudentNotFound studentNotFound)
    {
        return new ResponseEntity<>(new ExceptionDTO("1000",studentNotFound.getMessage()), HttpStatus.NOT_FOUND);
    }
```



## Lombok
Einfache, sich immer wiederholende Codeteile wie Getter, Setter, Konstruktor, etc. werden von Lombok erzeugt (Boilerplate). Somit bleibt der Code übersichtlicher.
### Getter, Setter und Konstruktor
```java
@Getter //alle Getter
@Setter //alle Setter
@AllArgsConstructor //Konstruktor mit allen Datenfeldern als Parameter
@NoArgsConstructor //Konstruktor ohne Datenfelder als Parameter
public class Joke {/**Code ausgelassen**/}
```

### Automatisch implementierte Methoden
....
## application.properties
Konfigurationsdatei über die z.B. für den DB-Zugriff, Einstellungen vorgenommen werden können. 

```java
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

## generated-requests.http
Hier können in IntelliJ HTTP-Request durchgeführt werden.

## Ports & Adapters Architektur aufgebaut. 
Driving und Driven Adapters

# Frontends

swagger-ui (http://localhost:8080/swagger-ui/index.html) volle Dokumentation automatisch generiert

## Frontend mit JS (Vanilla)


## Frontend mit Thymeleaf