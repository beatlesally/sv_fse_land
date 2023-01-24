# Fullstack Development Spring Boot

## Table of Contents

**Backend**
- [Getting Started with Spring Boot](#getting-started)


**Frontend**
- [JS Frontend](#frontend-mit-js-vanilla)
- [Thymeleaf Frontend](#frontend-mit-thymeleaf)


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

### @RestController
Ist eine Kombination von @Controller und @ResponseBody. 

#### Dependency Injection
> Als Dependency Injection [...] wird in der objektorientierten Programmierung ein Entwurfsmuster bezeichnet, welches die Abhängigkeiten eines Objekts zur Laufzeit reglementiert: Benötigt ein Objekt beispielsweise bei seiner Initialisierung ein anderes Objekt, ist diese Abhängigkeit an einem zentralen Ort hinterlegt – es wird also nicht vom initialisierten Objekt selbst erzeugt.
> (https://de.wikipedia.org/wiki/Dependency_Injection)

### @Entity
Ist für das objektrelationale Mapping. Mappt einen Datensatz automatisiert in ein Objekt.
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
Kann die Size einer Variable festlegen. Wird ein neues Objekt erstellt und die Size entspricht nicht dem angegebenen, wird eine Fehler geworfen.
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
Ein zentraler Exception Handler können alle Exceptions der Applikation handeln; auch angepasste Nachrichten an den Benutzer im Body zurückliefern und nicht nur die Exceptionmessage am Server ausgeben. 
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

### DTO
Ein Data-Transfer-Object ist ein Entwurfsmuster in der Softwareentwicklung. Somit können mehrere Daten gebündet zwischen Prozessen und mit nur einem Programmaufruf übertragen werden. (https://en.wikipedia.org/wiki/Data_transfer_object & https://de.wikipedia.org/wiki/Transferobjekt)

### @CrossOrigin
Somit wird dem Browser signalisiert, dass :5500 für HTTP-Requests erlaubt ist, sonst gibt es eine Fehlermeldung im Browser und die Webseite kann nicht angezeigt werden.
```java
@CrossOrigin(origins = "http://127.0.0.1:5500")
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
Mit bestimmten Bezeichnungen von Methoden kann Spring Boot die Funktion automatisch ausprogrammieren.

## application.properties
Konfigurationsdatei über die z.B. für den DB-Zugriff Einstellungen vorgenommen werden können. 

```java
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

## generated-requests.http
Hier können in IntelliJ HTTP-Request durchgeführt werden.

## Ports & Adapters Architektur
![Ports & Adapters Architektur](img/Screenshot%202023-01-11%20090901.png)

## Dokumentation
Mit Swagger-UI kann eine volle Dokumentation automatisch generiert werden. Diese kann abgerufen werden über: (http://localhost:8080/swagger-ui/index.html)

# Frontends
Das Frontend dient der Benutzerinteraktion. Es gibt mehrere Möglichkeiten, wie das Frontend mit dem Backend kommuniziert. Entweder das Frontend ist komplett losgelöst vom Backend oder direkt am Backend angebracht.
- JS: Frontend separat zum Backend
- Thymeleaf: Frontend und Backend zusammen

## Frontend mit JS (Vanilla)
In JS wird mit asynchronen Aufrufen gearbeitet und über JS die Inhalte der Webseite aufgebaut.

```js
//GET Request
async function getAll(){
 try {
        const response = await fetch('http://localhost:8080/api/v1/studenten',  //URL für die Anfrage
        {
            method: 'GET', //HTTP-Request Methode
            cache : 'no-cache',
            headers: {
                'Accept':'application/json'
            }
        }
        )

        const data = await response.json() //Antwort der Anfrage in JSON umwandeln

        /****/

         data.forEach((student) => 
        {
            var row = table.insertRow()
            var id = row.insertCell(0)
            var name = row.insertCell(1)
            var plz = row.insertCell(2)
            var action = row.insertCell(3)

            id.innerHTML = student.id
            name.innerHTML = student.name
            plz.innerHTML = student.plz
            action.innerHTML = `<a href="updatestudent.html?id=${student.id}&name=${student.name}&plz=${student.plz}" class="btn btn-primary" role="button">bearbeiten</a> <button type="button" class="btn btn-warning" onclick="deleteStudent(${student.id})">löschen</button>`
        })

        /***/ 
}

//POST-Request
async function sendData()
{
    const name= $('input[name=name]').val()
    const plz= $('input[name=plz]').val() //jQuery - Daten aus input-Felder holen

    try {
        const response = await fetch('http://localhost:8080/api/v1/studenten',
        {
            method: 'POST',
            cache : 'no-cache',
            headers: {
                'Content-Type':'application/json',
                'Accept':'application/json'
            },
            body: JSON.stringify({"name":name, "plz":plz})
        })

        const data = await response.json()
        /***/
```

## Frontend mit Thymeleaf
Für Thymeleaf wird eine Dependency in der pom.xml eingefügt. 
```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
Um mit Thymeleaf die HTTP-Request verarbeiten zu können, wird ein eigener Controller erstellt. Thymeleaf arbeitet nicht mit dem RestController, weil als Rückgabe ein String (Name der html-Datei) erwartet wird. 

Es wird wie im Restcontroller mit dem Servicelayer gearbeitet!

### Get Request
```java
@GetMapping
public String getAllStudents(Model model) //Model wird durch Injektion erstellt
{
    model.addAttribute("getAllStudents", this.studentenService.alleStudenten()); //attributeName == über das kann aufgerufen werden
    return "allstudents"; //muss gleich wie Filename sein! //dieses HTML file wird bei Get zurückgegeben
}
```
```html
<tbody>
    <tr th:each="student: ${getAllStudents}"> <!--Thymeleaf foreach mit model attribute-->
        <td th:text="${student.id}"></td> <!--aus Element id nehmen-->
        <td th:text="${student.name}"></td> 
        <td th:text="${student.plz}"></td> 
        <td>
            <a th:href="@{/web/v1/studenten/update/{id}(id=${student.id})}" class="btn btn-secondary" role="button">Update</a>
            <a th:href="@{/web/v1/studenten/delete/{id}(id=${student.id})}" class="btn btn-warning" role="button">Delete</a> <!--verlinken-->
        </td>
    </tr>
</tbody>
```


### Einfügen bzw Ändern von Daten
```java
//wir beim Aufruf der URL aufgerufen
@GetMapping("/insert")
public String addStudentForm(Model model)
{
    Student student = new Student();
    model.addAttribute("student", student); //leeres Studentenobjekt wird übergeben zu weiteren Befüllung
    return "addstudent";
}

//beim Drücken auf den Button wird Post abgesetzt mit eingefügten Inhalt
@PostMapping("/insert")
public String addStudent(@Valid Student student, BindingResult bindingResult) //wenn mit @Valid Probleme (Validierungsprobelem), in BindingResult gespeichert
{
    if(bindingResult.hasErrors())
    {
        return "addstudent";
    } else {
        this.studentenService.studentEinfuegen(student);
        return "redirect:/web/v1/studenten"; //redirect auf studentenliste
    }
}
```
```html
<!--Code ausgelassen-->

<form th:object="${student}" th:action="@{/web/v1/studenten/insert}" method="POST"> <!--Basisobjekt holen für Weiterarbeiten, @ = Serverpfad-->
  <!--th object gültig für das gesamte formular-->
  <div class="m-md-3">
    <label for name="name" class="form-label">Name</label>
    <input type="text" class="form-control" id="name" name="name" th:field="*{name}"> <!--* = auf Studentenobjekt zuzugreifen, name muss mit Datenfeld zusammenpassen-->
    <div class="form-text" th:if="${#fields.hasErrors('name')}" th:errorclass="error" th:errors="*{name}"></div>  <!--wenn Fehler bei @Valid auftreten, werden sie hier angezeigt-->
  </div>

  <div class="m-md-3">
    <label for name="plz" class="form-label">Plz</label>
    <input type="text" class="form-control" id="plz" name="plz" th:field="*{plz}"> <!--* = auf Studentenobjekt zuzugreifen-->
    <div class="form-text" th:if="${#fields.hasErrors('plz')}" th:errorclass="error" th:errors="*{plz}"></div>
  </div>

  <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Speichern</button>

</form>

<!--Code ausgelassen-->
```