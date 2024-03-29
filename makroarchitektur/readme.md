# Teil 1  
- Theorie: Recherchiere zu folgenden Fragestellungen und fasse deine Erkenntnisse übersichtlich und illustrativ zusammen!
    - Was ist Sofwarearchitektur?

        - „eine strukturierte oder hierarchische Anordnung der Systemkomponenten sowie Beschreibung ihrer Beziehungen“
        - Architekturkomponenten bilden eine Zerlegung des Gesamtsystems, d.h. jedes Softwareelement ist genau einer Architekturkomponente zugeordnet
        - die Softwarearchitektur wird durch die nicht-funktionalen Eigenschaften (Modifizierbarkeit, Wartbarkeit, Sicherheit, etc.) bestimmt
        - eine einmal eingerichtete Softwarearchitektur ist später nur mit hohem Aufwand abänderbar
        - ist einer der kritischsten und wichtigsten Punkte im Entwicklungsprozess einer Software

    - Wie kann man Softwarearchitektur dokumentieren?

       meist muss es in mehreren Sichten passieren, da man alles nicht in einer Sicht darstellen kann

       mögliche Dokumentationsformen:
        - 4+1 Sichten Modell von Kruchten
        - 4 Sichten von Hofmeister, Nord und Soni
        - Viewtypes und Styles des SEI 
        - Composite Structure Diagram
        - Canonical Model Structure

    - Welches sind die wichtigsten Eigenschaften von Langlebigen Softwarearchitekturen (Lilienthal)
        - **Entwurf nach Zuständigkeit**: Sind die Bausteine eines Systems modular gestaltet, so sollte man für jeden Baustein die Frage beantworten können: Was ist sein Aufgabe? Der entscheidende Punkt dabei ist, dass der Baustein wirklich eine Aufgabe hat und nicht mehrere. Diese Frage ist natürlich nur im fachlichen Kontext des jeweiligen Systems gemeinsam mit dem Entwicklerteam zu klären. Anhaltspunkte bei der Suche nach Bausteinen mit unklarer Zuständigkeit sind:
        - Der Name des Bausteins – der Name sollte seine Aufgabe beschreiben. Ist der Name schwammig, so sollte man ihn sich ansehen.
        - Seine Größe (s. nächster Punkt).
        - Der Umfang seiner Kopplung mit anderen Bausteinen – wird ein Baustein sehr viel von allen möglichen anderen Bausteinen verwendet, so liegt die Vermutung nahe, dass er ein Sammelbecken von vielfältigen nicht unbedingt zusammenhängenden Funktionalitäten ist.
        - Seine mangelnde Musterkonsistenz.
        - **Ausgewogene Größenverhältnisse**: Bausteine, die auf einer Ebene liegen, also die Schichten, die fachlichen Module, die Packages, die Klassen oder die Methoden, sollten untereinander ausgewogene Größenverhältnisse haben. Hier lohnt es sich, die sehr großen Bausteine zu untersuchen, um festzustellen, ob sie Kandidaten für eine Zerlegung sind.
        - **Zusammengehörigkeit durch Kopplung untereinander** : Bausteine sollten Subbausteine enthalten, die zusammengehören. Eine Klasse sollte beispielsweise Methoden enthalten, die gemeinsam ein Ganzes ergeben. Dasselbe gilt für größere Bausteine, wie Packages, Komponenten, Module und Schichten. Haben die Subbausteine mehr mit anderen Bausteinen zu tun, als mit ihren "Schwestern und Brüdern", dann stellt sich die Frage, ob sie nicht eigentlich in einen anderen Baustein gehören.

    - Was ist ein Modulith?
        
        Er tritt nach außen als großes Ganzes auf, ist aber im Inneren gut strukturiert und aufgeteilt.
        Laut Dr. Carola Lilienthal ist der Modulith ein gut strukturierter Monolith. Man verfolgt mit dem Modulith hohe Kohäsion und geringe Kopplung.
        Ein gut strukturierter Modulith kann später einfach in Microservices umgemünzt werden, weil er bereits eigenständige Module enthält.
        ![Alt text](pics/lilienthal_microservices_6.jpg)
![Alt text](pics/microservicesmodulithmonolith.png)
        - Microservices - physikalisch und logisch getrennt
        - Modulith - physikalisch ganz, logisch getrennt
        - Monolith - physikalisch und logisch ganz

    - Wie funktioniert die Ports and Adapters Architektur?

        "Input und Output am Edge von technologieunabhängigen Code (Domain) zu haben; die Domain soll nicht auf Technologien, Framework oder Real World Things referenzieren und nur die Businesslogik enthalten"
        
        Real World Things (Actor) mit denen die Domain interagiert können sein: User, Testfall oder eine Single-Page Application
        Es gibt zwei Typen von Actor: 
            - driving actors (verwenden Domain)
            - driven actors (stellen Funktionalitäten bereit, die von der Domain gebraucht wird)
                - Recipient: empfängt nur (z.B. Drucker)
                - Repository: empfängt und stellt zur Verfügung (z.B. Datenbank)

        An der Kante der Domain befinden sich Ports. Ports sind eine Sammlung von Interaktionen mit eine speziellen Sinn aber ohne vorauszusetzen, dass dieser Port eine spezielle Implementierung braucht (z.B. im Interface ist es egal ob hinter dem Messaging SMS oder Email liegt, es wird die grundlegenden Methoden wie Send() in beiden benötigt und dort in der Implementierung dann für jeden einzelnen ausimplemetiert).

        Es wird wieder in zwei Ports unterschieden:
            - Driving port: spezifiziert wie die Domain verwendet werden kann
            - Driven port: welche Funktionalität braucht die Domain

        Die Actors kommunizieren mit Adapters nur über die Ports. Diese Adapter können als Übersetzungschicht zwischen Domain und Außenwelt angesehen werden.

        Es gibt zwei Typen von Adapter:
            - Driving adapter: konvertiert Anfrage von einer speziellen Technologie in einen technologieunabhängigen Request (z.B. GUI)
            - Driven adapter: konvertiert Aufrufe der Domain von technologieunabhängig in ein technologiespezifisch (z.B. SQL Adapter)

        Zusammengefasst:
        - trennt Corelogik von der Input-Output Infrastruktur (Business Logik von Außenwelt trennen)
        - Entkupplung zwischen Coredomain und Außenwelt (egal was von der Außenwelt kommt, Domain arbeitet intern immer gleich z.B. SQL oder MongoDB ist Domain egal, die Methoden für den Datenbankzugriff sind für sie gleich und werden in der Implementierung für die bestimmten Technologien an die Technologie angepasst)
        ![Alt text](pics/hexagonal.png)

    - DDD: Was sind die wesentlichen Bausteine des modellgetriebenen Entwurfs (Taktische Pattern) aus DDD?
    ![Alt text](pics/DomainDrivenDesign_map.png)
        - Entitäten/Entity: nicht durch Eigenschaft sondern Identität definiert (Person ist immer die gleiche Person, auch wenn sich die Eigenschaften ändern)
            - oft mit eindeutigen Identifikatoren modelliert 
            - Mitschrift aus Unterricht (hat Zustand; prüfung ob 2 entites gleich sind - ID muss gleich sein, dynamisch, Bsp Kunde)
        - Wertobjekt/Value Object (durch Eigenschaften definiert, haben/benötigen keine Identität)
            -  üblicherweise als unveränderliche Objekte (immutable) modelliert
            - damit wiederverwendbar und verteilbar
            - Mitschrift aus Unterricht: (immutable, repräsentiert Wert der sich nicht mehr verändert, keine Identität, keinen Lebenszyklus, mit Businesslogik, statisch, Bsp BestellNr.; prüfung ob 2 value object gleich sind - Über Wert)
        - Aggregate/Aggregates (Fassen Entitäten und Wertobjekte und ihre Beziehungen zueinander zu einer transaktionalen Einheit zusammen)
            - nur eine Entität für Zugriff von außen auf gesamtes Aggregat
            - alle andere Entitäten und Wertobjekte von außen nicht statisch referenziert werden
            - damit ist richtige Ausführung (Invarianten) des Aggregats gesichert
        - Services (Funktionalitäten, die ein wichtiges Konzept der Fachlichkeit darstellen und zu mehreren Objekten des Domänenmodells gehören)
            - sind zustandslos/stateless
            - wiederverwendbare klassen ohne Beziehungen
            - mit Methoden, die angebotene Funktionalitäten entsprechen
            - Methoden bekommen Wertobjekte und Entitäten übergeben, die sie zur Abarbeitung der Funktionalität brauchen
        - Repositories (damit technische Infrastruktur sowie alle Zugriffsmechanismen von Geschäftslogikschicht getrennt)
            - für alle Fachobjekt die über Infrastrukturschicht geladen, gibt es eine Repository-Klasse, die die Lade- und Suchtechnologien nach außen kapselt
            - sind Teil des Domänenmodells und somit Teil der Geschäftslogikschicht
            - greifen als einzige Objekte auf Infrastruktur-Schicht zu (Entwurfsmuster dafür wären Data Access Objects, Query Objects oder Metadata Mapping Layers)
        - Fachliche Ereignisse/Domain Events (Objekte, die Aktionen des Domänenmodells beschreiben und ein/mehrere Aktionen/Änderungen in Fachobjekten bewirken)
            - ermöglichen Modellierung von verteilten Systemen
            - Subsysteme kommunizieren ausschließlich über Domain Events
            - somit stark entkoppelt und gesamtes System somit wartbarer und skalierbarer
        - Mitschrift aus Unterricht: Service, Repositories, Aggregates (haben Zustand, der ein, mehrere Entitys oder Value Objects betrifft; Bsp. Bestellung ist Aggregat)
        https://leanpub.com/ddd-referenz/read

## Abgabe der Architekturanalyse des bestehenden erplite-Backends (erplite2-main-2)
![Alt text](pics/erplite-main2-sequencediagramm.png)

Container Diagramm - grober Überblick - Order als ganzes, greift auf DB zu, greift auf Messaging zu
im Komponenten Diagramm alles was autowired/injektionspunkte - Order genauer betrachten -> Restcontroller implementiert Service implementiert DB Access implementiert Datenbankzugriff
Code Diagramm - eine Komponente betrachtet - zeigt Ports und Adapters Struktur

Ports and Adapters!!!
alle Dinge, die über Port (Interface) nach innen gehen auf Service und die Implementierung davon ist technologieunabhängig, dann ist es ein Service
sobald über Port (Interface) geht und technologieabhängig implementiert dann ist es ein Adapter

Dokumentation (texthelle Beschreibung, Codeauszüge, C4-Diagramme, Klassendiagramme) der Ports-Und-Adapters-Architektur und der DDD-Bestandteile (taktische Muster) von Ordermanagement anhand der gegebenen Anwendungsfälle, die schon implementiert sind:
### Bestellung aufgeben
über den Rest-Controller wird ein POST auf orders gemacht   
![Alt text](pics/Screenshot%202023-05-24%20084616.png)                                 
```java
//OrderRestController.java
//der JSON wird als PlaceOrderCommand übergeben
//ein PlaceOrderCommand wird benötigt, dass alle wissen, wie so ein Order aussehen muss, ohne Abhängigkeiten zu Ordermanagement herzustellen
@PostMapping("/orders/")
public ResponseEntity placeNewOrder(@RequestBody @Valid PlaceOrderCommand placeOrderCommand, BindingResult bindingResult) {
    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling place new order api request ...");

    HashMap<String, String> errors = new HashMap<>();

    if (bindingResult.hasErrors()) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Errors in placeOrderCommand detected!");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        throw new OrderPlacedFieldValidationException("Validation errors for order placement!", errors);
    }

    //über Servicelayer wird das neue Order in handle gemappt mit den richtigen Typen und ein Order gebaut
    OrderResponse orderResponse = orderCommandService.handle(placeOrderCommand);

    String resourceLocation = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/orders/" + orderResponse.orderID();
    try {
        //GET Request mit OrderID aus OrderResponse
        //Ergebnis wird an den aufrufenden Client zurückgesendet
        return ResponseEntity.created(new URI(resourceLocation)).body(orderResponse);
    } catch (URISyntaxException e) {
        return ResponseEntity.noContent().build();
    }
}
```  
```java
//OrderCommandServiceImpl.java
@Transactional
public OrderResponse handle(PlaceOrderCommand placeOrderCommand) {
    /**Code ausgelassen**/
    //LineItems von CartItem aus PlaceOrderCommand erstellen
    List<LineItem> lineItemList = new ArrayList<>();
    int i = 1;
    for (CartItem cartItem : placeOrderCommand.cartItems()) {
        lineItemList.add(new LineItem(
                        new OrderPosition(i),
                        new ProductNumber(cartItem.productNumber()),
                        new Name(cartItem.productName()),
                        new MonetaryAmount(new BigDecimal(cartItem.priceNet())),
                        new Percentage(cartItem.tax()),
                        new Amount(cartItem.amount())
                )
        );
        i++;
    }

    //Order aus PlaceOrderCommand bauen
    Order orderToInsert = new Order(
            new OrderID("ONR" + UUID.randomUUID().toString().substring(0, 7)),
            new CustomerData(
                    new CustomerID(placeOrderCommand.customerID()),
                    new Name(placeOrderCommand.customerFirstname()),
                    new Name(placeOrderCommand.customerLastname()),
                    new Email(placeOrderCommand.customerEmail()),
                    placeOrderCommand.customerStreet(),
                    placeOrderCommand.customerZipcode(),
                    placeOrderCommand.customerCity(),
                    placeOrderCommand.customerCountry()
            ),
            LocalDateTime.now(),
            lineItemList,
            OrderState.PLACED
    );

    //über das Repository wird das Order eingefügt und bekommt die fertige Order als Optional zurück
    Optional<Order> orderOptional = this.orderRepository.insert(orderToInsert);

    if (orderOptional.isPresent()) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Publishing order placed domain event ...");
        //Event wird getriggert (über Port OrderOutgoingMessageRelay), dass Order erstellt wurde und auch der Inhalt der Order wird ausgegeben
        orderOutgoingMessageRelay.publish(new OrderPlacedEvent(OrderResponseMapper.toResponseFromDomain(orderOptional.get())));
        //aus Order wird eine OrderResponse gemappt, diese an den aufrufenden REST-Controller zurückgeliefert
        return OrderResponseMapper.toResponseFromDomain(orderOptional.get());
    } else {
        throw new OrderPlacementNotSuccessfullException("OrderQueryServiceImpl: Order could not be placed!");
    }
}
``` 
### Bestellung auf bezahlt setzen
![Alt text](pics/Screenshot%202023-05-24%20094430.png)
![Alt text](pics/Screenshot%202023-05-24%20094456.png)
```java
//OrderRestController.java

//im Rest-Controller wird auf checkPayment mit OrderID
@PostMapping("/orders/checkpayment/{orderid}")
public ResponseEntity validatePaymentForOrderWithId(@PathVariable String orderid) {
    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling check payment for order api request ...");

    //über Servicelayer wird an handle als OrderPaymentCheckCommand übergeben
    this.orderCommandService.handle(new OrderPaymentCheckCommand(orderid));
    //aufrufender Client bekommt ein Accepted zurück
    return ResponseEntity.accepted().body("Order payment check executed. Order payment ok!");
}
```
```java
@Override
@Transactional
public void handle(OrderPaymentCheckCommand orderPaymentCheckCommand) throws OrderPaymentCheckFailedException {
    /**Code ausgelassen**/
    //Order über Repository aus DB holen mit OrderID
    Optional<Order> optionalOrderToCheck = this.orderRepository.getById(new OrderID(orderPaymentCheckCommand.orderID()));
    if (optionalOrderToCheck.isPresent()) {
        //Order aus dem Optional holen
        Order order = optionalOrderToCheck.get();
        try { //versuchen, den State zu verändern
            order.orderStateTransitionTo(OrderState.PAYMENT_VERIFIED);
            //State auch in der DB aktualisieren
            this.orderRepository.updateOrderWithNewState(order);
            //Event getriggert, dass Payment verifiziert ist, mit OrderResponseMapper in OrderResponse mappen
            this.orderOutgoingMessageRelay.publish(new OrderPaymentValidatedEvent(OrderResponseMapper.toResponseFromDomain(order)));
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Payment validated event published!");
        } catch (OrderStateChangeNotPossibleException orderStateChangeNotPossibleException) {
            throw new OrderPaymentCheckFailedException("Order payment check not possible. Order in wrong state! " + orderStateChangeNotPossibleException.getMessage());
        }
    } else {
        throw new OrderPaymentCheckFailedException("Order with Id " + orderPaymentCheckCommand.orderID() + " not found for payment check!");
    }
}
```
### Packliste generieren
![Alt text](pics/Screenshot%202023-05-24%20094732.png)
![Alt text](pics/Screenshot%202023-05-24%20101052.png)
![Alt text](pics/Screenshot%202023-05-24%20101106.png)
![Alt text](pics/Screenshot%202023-05-24%20101151.png)
```java
//PackingRestController.java
//Packing List für ein spezielles Order abrufen
@GetMapping("/packings/whithorderid/{ordernr}")
public ResponseEntity<Packing> getPackingByOrderNr(@PathVariable String ordernr) {
    //über Repository werden die PackingItems geholt und in Packing in Liste eingefügt
    Packing p = this.packingRepository.findByOrderId(ordernr);
    if (p != null) {
        return ResponseEntity.ok(p);
    } else {
        return ResponseEntity.notFound().build();
    }
}
```
```java
//StockIncomingMessageHandler.java
//wenn Payment Verifizierung erfolgreich, wird Event von OrderOutgoingSpringMessageRelayImpl veröffentlicht
//diese wird von StockIncomingMessageHandler erkannt und erstellt dann ein Packing und die PackingItemList
public void onApplicationEvent(OrderPaymentValidatedSpringEvent event) {
Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling order payment validated spring event ...");

//aus dem Event die OrderResponse holen
OrderResponse orderResponse = event.getOrderResponse();

//leeres Packing erstellen
Packing packingToSaveToDb =
        Packing.builder()
                .id(null)
                .orderId(orderResponse.orderID())
                .deliveryData(new DeliveryData(
                                orderResponse.customerFirstname() + " " + orderResponse.customerLastname(),
                                orderResponse.customerStreet(),
                                orderResponse.customerZipcode(),
                                orderResponse.customerCity(),
                                orderResponse.customerCountry()
                        )
                ).packingItemList(null) // List is generated down under
                .build();

//PackingItemList in leerem Packing mit den Werten aus OrderResponse befüllen
List<PackingItem> packingItemList = new ArrayList<>();
for (LineItemResponse lineItemResponse : orderResponse.orderLineItems()) {
    packingItemList.add(
            new PackingItem(
                    null,
                    lineItemResponse.productNumber(),
                    lineItemResponse.productName(),
                    lineItemResponse.amount(),
                    false,
                    packingToSaveToDb
            )
    );
}
//PackingItemList setzen
packingToSaveToDb.setPackingItemList(packingItemList);
//Packing über Repository speichern
this.packingRepository.save(packingToSaveToDb);
Logger.getLogger(this.getClass().getName()).log(Level.INFO, "New packing list created and saved in db ...");
}
```
### Packlistenitems abhaken
```java
//PackingRestController.java
//Über PackingItemId wird PackingItem in Packing als verpackt gesetzt
@PostMapping("/setPackedForPacking/{packingItemId}")
public void setPackingItemPackedForPacking(@PathVariable Long packingItemId) {
Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling packing for item# " + packingItemId);

//PackingItem mit Repository herausholen
Optional<PackingItem> optionalPackingItem = this.packingItemRepository.findById(packingItemId);
if (optionalPackingItem.isPresent()) {
    PackingItem packingItem = optionalPackingItem.get();
    //Packed auf true setzen
    packingItem.setPacked(true);
    //PackingItem über Repository wieder speichern
    packingItemRepository.save(packingItem);

    
    Long packingId = packingItem.getPacking().getId();
    //Packing des PackingItem holen
    Optional<Packing> packing = this.packingRepository.findById(packingId);

    boolean allpaked = true;
    for (PackingItem item : packing.get().getPackingItemList()) { //alle PackingItems prüfen, ob sie packed sind
        if (!item.isPacked()) allpaked = false;
    }

    if (allpaked) {//wenn alle verpackt sind
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "All items for order# " + packing.get().getOrderId() + "packed. Publishing event ...");
        this.stockMessagePublisher.publishOrderPackedSpringEventForOrderId(packing.get().getOrderId()); //Event (OrderPackedEvent) veröffentlichen, dass Packing fertig ist
    }
}
}
```
### Bestellung auf IN_DELIVERY setzen wenn alle Packlistenitems gepackt sind
![Alt text](pics/Screenshot%202023-05-24%20101310.png)
```java
//Event OrderPackedEvent wurde veröffentlicht und triggert nun diesen Methodenaufruf
@Transactional
public void handle(OrderPackedEvent orderPackedEvent) {
    //Meterialize object into Memory, place changes, and forward the domain object to repository
    //this ensures, that businesslogic will be executed und object is in consistent state.
    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling order packed event ...");

    //Über Repository die Order holen, OrderID aus OrderPackedEvent bekommen
    Optional<Order> optionalOrderToCheck = this.orderRepository.getById(orderPackedEvent.orderId());
    if (optionalOrderToCheck.isPresent()) {
        Order order = optionalOrderToCheck.get();
        try {

            //State der Order auf PREAPRING_FOR_DELIVERY
            order.orderStateTransitionTo(OrderState.PREPARING_FOR_DELIVERY);
            //Order updaten
            this.orderRepository.updateOrderWithNewState(order);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Order state changed to preparing_for_delivery, changed order persisted!");
        } catch (OrderStateChangeNotPossibleException orderStateChangeNotPossibleException) {
            throw new OrderPaymentCheckFailedException("Order state change to preparing for delivery not possible! " + orderStateChangeNotPossibleException.getMessage());
        }
    } else {
        throw new OrderWithGivenIDNotFoundException("Order with Id " + orderPackedEvent.orderId().id() + " not found for state change to preparing for delivery!");
    }

}
```

### Wo findet man DDD-Bestandteile?
in Ordermanagement sind
#### Value Objects
![Alt text](pics/valueobjects.png)
```java
//Beispiel Amount
@ValueObjectMarker
public record Amount(int amount) {
    public Amount {
        if (!isValid(amount)) throw new IllegalArgumentException("Amount must not be positive integer!");
    }

    public static boolean isValid(int amount) {
        return amount >= 0;
    }
}
```
##### Aggregates
```java
@AggregateMarker
public final class Order {

    //ID
    private final OrderID orderID;

    //Customer-Data
    private CustomerData customerData;

    //Line-Items
    private final List<LineItem> lineItems;

    //Order-State and calcuated values
    private OrderState state;
    private final MonetaryAmount taxTotal;
    private final MonetaryAmount netTotal;
    private final MonetaryAmount grossTotal;
    private final LocalDateTime date;

    public Order(OrderID orderID, CustomerData customerData,
                 LocalDateTime date, List<LineItem> lineItems, OrderState status) {
        if (orderID == null) throw new IllegalArgumentException("OrderID must not be null!");
        this.orderID = orderID;
        if (customerData == null)
            throw new IllegalArgumentException("Customer-Data for order invalid!");
        this.customerData = customerData;
        if (lineItems == null)
            throw new IllegalArgumentException("LineItems-List must not be null, at least an empy List is needed!");
        this.lineItems = lineItems;
        if (status == null) throw new IllegalArgumentException("State must not be null!");
        this.state = status;
        this.taxTotal = this.calculateOrderTax();
        this.netTotal = this.calculateOrderNetTotal();
        this.grossTotal = this.calculateOrderGrossTotal();
        this.date = date;
    }

    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(this.lineItems);
    }

    public void orderStateTransitionTo(OrderState newState) {
        switch (newState) {
            case CANCELED -> {
                if (this.state == OrderState.IN_DELIVERY || this.state == OrderState.DELIVERED)
                    throw new OrderStateChangeNotPossibleException("Order in State " + this.state + " cannot be canceled");
                this.state = newState;
            }
            case PAYMENT_VERIFIED -> {
                if (this.state != OrderState.PLACED)
                    throw new OrderStateChangeNotPossibleException("Order must be in state PLACED for transition to PAYMENT VERIFIED!");
                this.state = newState;
            }
            case PREPARING_FOR_DELIVERY -> {
                if (this.state != OrderState.PAYMENT_VERIFIED)
                    throw new OrderStateChangeNotPossibleException("Order must be in state PAYMENT VERIFED for transition to PREPARING FOR DELIVERY!");
                this.state = newState;
            }
            case IN_DELIVERY -> {
                if (this.state != OrderState.PREPARING_FOR_DELIVERY)
                    throw new OrderStateChangeNotPossibleException("Order must be in state PREPARING FOR DELIVERY for transition to IN DELIVERY!");
                this.state = newState;
            }
            case DELIVERED -> {
                if (this.state != OrderState.IN_DELIVERY)
                    throw new OrderStateChangeNotPossibleException("Order must be in state IN DELIVERY for transition to DELIVERED!");
                this.state = newState;
            }
        }
    }
    /**Code ausgelassen**/

}
```
#### Repositories
```java
@RepositoryMarker
@Repository
class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderJPARepository orderJPARepository;

    @Override
    public Optional<Order> insert(Order order) {
        if (order == null) throw new IllegalArgumentException("Order to be inserted must not be null!");
        OrderDbEntity orderDbEntity = DbOrderMapperService.toOrm(order);
        OrderDbEntity insertedEntity = orderJPARepository.save(orderDbEntity);
        if (insertedEntity == null) return Optional.empty();
        return Optional.of(DbOrderMapperService.toDomain(insertedEntity));
    }

    @Override
    public Optional<Order> getById(OrderID id) {
        if (id == null) throw new IllegalArgumentException("OrderID for Order to get from db must not be null!");
        Optional<OrderDbEntity> orderEntityOptional = this.orderJPARepository.findById(id.id());
        if (!orderEntityOptional.isPresent()) return Optional.empty();
        return Optional.of(DbOrderMapperService.toDomain(orderEntityOptional.get()));
    }

    @Override
    public List<Order> getAll() {
        List<OrderDbEntity> list = this.orderJPARepository.findAll();
        if (list == null) return Collections.emptyList();
        return list.stream().map(dbEntity -> DbOrderMapperService.toDomain(dbEntity)).toList();
    }


    @Override
    public void deleteById(OrderID id) {
        this.orderJPARepository.deleteById(id.id());
    }

    /**Code ausgelassen**/
}
```
#### Events zwischen Aggregate
![Alt text](pics/events.png)
```java
@Service
@AllArgsConstructor
class IncomingOrderPackedSpringEventHandler implements ApplicationListener<OrderPackedSpringEvent> {

    private OrderIncomingMessagesPort orderIncomingMessagesPort;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(OrderPackedSpringEvent event) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Order packed event received for order# " + event.getOrderID());
        this.orderIncomingMessagesPort.handle(new OrderPackedEvent(new OrderID(event.getOrderID())));
    }
}
```
### Wo findet man Ports-Und-Adapters-Architektur?
![Alt text](pics/ports1.png)
- die Ports sind die Interfaces
- die Adapter sind die zugehörigen Implementierungen
- es gibt mehrere Schichten: Domäne, Service, Infrastructure
- wenn sich die Technologie bei einem Adapter verändert, hat dies keine Auswirkungen auf das restliche System; diese Änderung kann einfach implementiert werden

### C4 Diagramm
Context
```mermaid
C4Context
    Person(user, "Benutzer", "Der Benutzer, der Kunde des System ist ")
    System(erp, "ERP System", "Ermöglicht es Benutzer, Bestellungen aufzugeben und diese anzuzeigen")
    Rel(user, erp, "Uses")
    UpdateLayoutConfig($c4ShapeInRow="1", $c4BoundaryInRow="1")
```
Container
```mermaid
C4Container
    Person(user, "Benutzer", "Der Benutzer, der Kunde des System ist ")
    Container_Boundary(c1, "ERP") {
        Container(restapi1, "REST API Order", "Java, Spring", "Funktionen für Ordermanagement")
        Container(restapi2, "REST API Stock", "Java, Spring", "Funktionen für Stockmanagement")
        Container(restapi3, "REST API Customer", "Java, Spring", "Funktionen für Customermanagement")
        ContainerDb(database, "Database", "H2 In Memory", "Speichert Customer, Orders und Stock")
        Container(sharedkernel, "Shared Kernel", "Java, Spring", "Verbindet die einzelnen Module über Events")

    }

    BiRel(database, restapi1, "Uses")
    BiRel(database, restapi2, "Uses")
    BiRel(database, restapi3, "Uses")
    Rel(restapi1, sharedkernel, "Uses")
    Rel(restapi2, sharedkernel, "Uses")
    Rel(restapi3, sharedkernel, "Uses")
    BiRel(user,restapi1, "")
    BiRel(user,restapi2,  "")
    BiRel(user,restapi3,  "")
    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```
Component - Ordermanagement
```mermaid
C4Component
    Container_Boundary(c1, "REST API Order") {
        Component(restapi1, "Order REST Controller", "Java, Spring", "Controller für Ordermanagement")
        Component(restapi2, "Exception REST Controller", "Java, Spring", "Controller für Exception")
    }
    Container(sharedkernel, "Shared Kernel", "Java, Spring", "Verbindet die einzelnen Module über Events")
    Container(services, "Services", "Java, Spring", "Services für Ports&Adapters Realisierung")
    ContainerDb(database, "Database", "H2 In Memory", "Speichert Customer, Orders und Stock")

    BiRel(database, services, "Uses")
    BiRel(services, restapi1, "Uses")
    BiRel(services, sharedkernel, "Events abwickeln")
    Rel(restapi1, restapi2, "")
    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```
Die Code Ebene ist oben genauer mit Codebeispielen beschrieben und als Klassendiagramm verfügbar.

## Dokumentation (texthelle Beschreibung, Codeauszüge, Diagramme, C4-Diagramme, Klassendiagramme) der "Architektur" von Stockmanagement anhand der gegebenen Anwendungsfälle, die schon implementiert sind:
- Packingliste anlegen
- Packingitems als verpackt markieren

Ist eine Art der 3-Schichten-Architektur mit Events und Ports
- Rest Controller verwendet direkt Repository (package db) über Port
- Rest Controller published Events
- diese Events werden von den Listeners dann aufgefangen und verarbeitet
- in package business liegen die Entitäten ohne Businesslogik
- nur über die Events sind die verschiedenen Managements verbunden und über den sharedkernel (dort ist alles enthalten, damit die Kommunikation erfolgreich ist)
![Alt text](pics/stockmanagement.png)

### C4 Diagramm
Context
```mermaid
C4Context
    Person(user, "Benutzer", "Der Benutzer, der Kunde des System ist ")
    System(erp, "ERP System", "Ermöglicht es Benutzer, Bestellungen aufzugeben und diese anzuzeigen")
    Rel(user, erp, "Uses")
    UpdateLayoutConfig($c4ShapeInRow="1", $c4BoundaryInRow="1")
```
Container
```mermaid
C4Container
    Person(user, "Benutzer", "Der Benutzer, der Kunde des System ist ")
    Container_Boundary(c1, "ERP") {
        Container(restapi1, "REST API Order", "Java, Spring", "Funktionen für Ordermanagement")
        Container(restapi2, "REST API Stock", "Java, Spring", "Funktionen für Stockmanagement")
        Container(restapi3, "REST API Customer", "Java, Spring", "Funktionen für Customermanagement")
        ContainerDb(database, "Database", "H2 In Memory", "Speichert Customer, Orders und Stock")
        Container(sharedkernel, "Shared Kernel", "Java, Spring", "Verbindet die einzelnen Module über Events")

    }

    BiRel(database, restapi1, "Uses")
    BiRel(database, restapi2, "Uses")
    BiRel(database, restapi3, "Uses")
    Rel(restapi1, sharedkernel, "Uses")
    Rel(restapi2, sharedkernel, "Uses")
    Rel(restapi3, sharedkernel, "Uses")
    BiRel(user,restapi1, "")
    BiRel(user,restapi2,  "")
    BiRel(user,restapi3,  "")
    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```
Component - Stockmanagement
```mermaid
C4Component
    Container_Boundary(c1, "REST API Stock") {
        Component(publish, "StockMessagePublisher", "Java, Spring", "schickt Events")
        Component(restapi1, "Stock REST Controller", "Java, Spring", "Controller für Ordermanagement")

    }
    Container(sharedkernel, "Shared Kernel", "Java, Spring", "Verbindet die einzelnen Module über Events")
    Container(repo, "Repository", "Java, Spring", "Repository für DB Zugriff")
    ContainerDb(database, "Database", "H2 In Memory", "Speichert Customer, Orders und Stock")

    BiRel(database, repo, "Uses")
    BiRel(repo, restapi1, "Uses")
    BiRel(publish, sharedkernel, "Events abwickeln")
    Rel(restapi1, publish, "Uses")
    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```
![Alt text](pics/stockmanagement_classs.png)

### Teil 3
Aufgabe Makroarchitektur Teil 3

A) Inbetriebnahme der Microservice-Variante von erplite als Schritt für Schritt-Anleitung mit Screenshots und Text zu dokumentieren
add maven project in maven reiter
vscode -> frontend öffnen, readme, dockercompose (complex - adminer+phpmyadmin, simple - adminer)

start mit servicediscovery - hängen sich mit ip und port ein, damit kommunikation funkt
orderservice, delivery, stock, apigateway
use services anhaken
frontend mit go live server starten (muss unter port 5500 anfragen, weil sonst rückantwort nicht gegeben)
rabbitmq login guest guest

servicediscovery - dns
unterschiedliche architekturen bei order,stock,delivery
command, event = dto

#### 1 alle Services einbinden
![Alt text](pics/teil3/Screenshot%202023-05-31%20102045.png)

#### 2 IP Adresse anpassen (wenn nötig)
Da die Dockerinstanz auf einer virtuellen Maschine läuft, muss die IP-Adresse angepasst werden (statt localhost); 
das muss in jeder application.properties (delivery,order,stock) durchgeführt werden
![Alt text](pics/teil3/Screenshot%202023-05-31%20102307.png)

#### 3 Docker-Compose hochfahren
alle Container mit der Docker Compose hochfahren; Images werden gepulled wenn noch nicht verfügbar
![Alt text](pics/teil3/Screenshot%202023-05-31%20102629.png)
![Alt text](pics/teil3/Screenshot%202023-05-31%20103441.png)
#### 4 Services hochfahren
Reihenfolge: Service Discovery, Order, Stock, Delivery, ApiGateway
![Alt text](pics/teil3/Screenshot%202023-05-31%20104218.png)
#### Frontend hochfahren
Frontend mit Go Live Server starten, da nur Zugriff über Port 5500 erlaubt
![Alt text](pics/teil3/Screenshot%202023-05-31%20104255.png)
![Alt text](pics/teil3/Screenshot%202023-05-31%20104431.png)
B) Abgabe einer Architekturanalyse des bestehenden erplitems-Backends
    
B1) Schriftliche Dokumentation der Architektur als C4-Containerdiagramm und C4-Componentendiagramm incl. textuellen Beschreibungen, Codeauszügen und Screenshots.

### Kurz und Knapp: 

Der OrderMS baut auf der Ports and Adapters Struktur auf. Gehen wird davon das, dass eine neue Order erstellt wird. Dabei geht ein Post-Request an den Rest-Controller ein und die entsprechende Funktion mit dem richtigen Mapping kümmert sich darum. Diese empfängt als Parameter eine PlaceOrderCommand. Der Body wird automatisch von Spring in ein solches PlaceOrderCommand gemappt. 

![Alt text](pics/teil3/orderrest.png)

Das PlaceOrderCommand beinhaltet Datenfelder wie die Order-Domäne, jedoch sind alle als Typ String um eine geringe Kopplung zu erzielen. Außerdem enthält dieser Command nur die Datenfelder, die nicht automatisch vom System generiert werden.

![Alt text](pics/teil3/orderplacedcommand.png)

Ein Beispiel beim PlaceOrderCommand ist die OrderID, diese ist nicht enthalten, da sie beim Handling dieses Commands im OrderCommandService generiert wird. Dieser PlaceOrderCommand sowie andere Commands werden in OrderCommandService handelt. Die handle-Methode wird überladen, indem sie Parameter von verschiedenen Commands enthält. Somit weiß man, auf welchen Command reagiert wird, da die verschiedenen Commands verschiedene Zwecke erfüllen. 

![Alt text](pics/teil3/ordercommandservice.png)

Der OrderCommandService verwendet den Port OrderOutgoingMessageRelay und das OrderRepository. 

![Alt text](pics/teil3/orderplacedcommand2.png)

Somit ist klar, dass man sich mit dem OrderCommandService im Servicelayer befindet und dieser über die Ports die Datenbank und das RabbitMQ Messaging verwendet. Nachdem aus dem PlaceOrderCommand ein Order-Objekt erstellt wurde, wird es über das Repository in die Datenbank geschrieben. Weiters wird ein Event veröffentlicht. 

![Alt text](pics/teil3/repositorypublish.png)

Für dieses ist beim Message Broker eine bestimmte Queue hinterlegt.

![Alt text](pics/teil3/messageoutgoing.png)

Als Message wird aus dem Order-Objekt ein OrderResponse-Objekt (ist ein DTO => somit keine Abhängigkeiten) gemappt. Ein OrderResponse kann man sich wie Command vorstellen. Zur besseren Orientierung und das wenig Missverständnisse auftauchen, wird zwischen Response und Command unterschieden. 

![Alt text](pics/teil3/orderresponse.png)

Sollte ein andere Microservice dieses Event abonniert haben, wird dieses im IncomingMessageRelay das neue Event erkennen und handeln. Es wird mit den Parameter die verschiedenen Events unterschieden dadurch die richtige Funktion ausgeführt, wenn auf eine Queue gehört wird wo mehrere verschiedene Events veröffentlicht werden. 

![Alt text](pics/teil3/incoming.png)

Diese beschriebene Vorgehensweise kann auf alle anderen Use-Cases angeführt werden, vielleicht in anderer Reihenfolge.

```mermaid
C4Container
    title Container Diagramm für ERPLite Microservices
    Person(customer, Benutzer, "benutzt das ERPLite System", $tags="v1.0")
    Container(frontend, "Frontend", "JavaScript", "Stellt den Content dar")

    Container_Boundary(c1, "ERPLite") {
        Container(apigw, "API Gateway", "routet bei Anfrage auf richtigen MS")
        Container(sd, "Service Discovery", "Java, Spring, Eureka", "registriert MS")
        Container(messaging, "Messaging", "RabbitMQ", "Kommunikation zwischen den MS")       
        Container(orderms, "Order MS", "Java, Spring", "Microservice für Order Management")
        Container(stockms, "Stock MS", "Java, Spring", "Microservice für Stock Management")
        Container(customerms, "Customer MS", "Java, Spring", "Microservice für Customer Management")
        ContainerDb(db_order, "DB Order", "SQL Datenbank", "hält Daten von OrderMS")
        ContainerDb(db_stock, "DB Stock", "SQL Datenbank", "hält Daten von StockMS")
        ContainerDb(db_customer, "DB Customer", "SQL Datenbank", "hält Daten von CustomerMS")
    
    }


    Rel(customer, frontend, "greift zu auf")
    Rel(frontend, apigw, "greift auf Microservices zu über")
    Rel(apigw, sd, "fragt mit Name an und erhält IP und Port von")
    Rel(orderms, db_order, "")
    Rel(stockms, db_stock, "")
    Rel(customerms, db_customer, "")
    BiRel(orderms, messaging, "kommuniziert mit")
    BiRel(stockms, messaging, "kommuniziert mit")
    BiRel(customerms, messaging, "kommuniziert mit")
    Rel(orderms, sd, "")
    Rel(customerms, sd, "")
    Rel(stockms, sd, "")

    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```

Im Container Diagramm wird sehr schnell ersichtlich, wie die Microservices aufgebaut sind. Es sind getrennte Systeme, die nur über einen Messenger (RabbitMQ) miteinander kommunizieren bzw. Event veröffentlichen oder konsumieren. 

```mermaid
C4Component
    title Componentendiagramm Order
    Container(front, "Frontend", "javascript and html", "Darstellung der Funktionen")
    ContainerDb(db, "Database", "Relational Database Schema", "Tabellen für Order, Stock und Customer")

    Container_Boundary(order, "Ordermanagement") {
        Component(rest, "REST Controller", "Order Rest Controller", "Stellt verschiedene Mappings zur Verfügung")
        Component(service, "Service Component", "Service Layer", "Funktionen zwischen den Schichten")
        Component(dba, "Database Interface" "Database Access", "Zugriff auf die Datenbank")
        Component(msg, "Messenger" "Rabbitmq", "Messenger zwischen den Microservices")

    }
    Rel(front, rest, "Uses")
    Rel(rest, service, "Uses")
    Rel(service, dba, "Uses")
    Rel(service, msg, "Uses")
    Rel(dba, db, "Uses")

    UpdateRelStyle(front, rest, $offsetY="-40")
    UpdateLayoutConfig($c4ShapeInRow="3", $c4BoundaryInRow="1")
```
B2) Die beschriebenen Use-Cases (Bestellung anlegen, Payment verifizieren, Packlistenitems abhaken) entlang der Architektur beschreiben, Codeauszüge zeigen, Screenshots mit den Resultaten zeigen, textuelle Beschreibungen dazu
#### Bestellung anlegen
![Alt text](pics/teil3/Screenshot%202023-06-11%20213153.png)
![Alt text](pics/teil3/Screenshot%202023-06-11%20213608.png)

Als erstes wird mit einem POST Request auf api/v1/orders gemacht. Diese Anfrage geht an den ApiGateway. Dieser hat Routen definiert, die zu einer bestimmten URI führen. Somit kann man mit Namen anfragen und nicht mit einer IP. Die Route geht in diesem Fall auf den Microservice Orders. 

![Alt text](pics/teil3/Screenshot%202023-06-11%20215337.png)

Im Body werden Dummydaten übergeben.
Danach wird die Funktion getallorders aufgerufen.
```javascript
async function putorder()
{
    const resp = await fetch('http://localhost:9999/api/v1/orders',{
        method:'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: `{
                    "customerID": "CUS1d34e56",
                    "customerFirstname": "Caesar",
                    "customerLastname": "Franklin",
                    "customerEmail": "a.b@c.de",
                    "customerStreet": "Hollywood Boulevard 2",
                    "customerZipcode" : "3452",
                    "customerCity" : "LA",
                    "customerCountry" : "USA",
                    "cartItems": [
                        {
                        "productNumber": "P123RE123D",
                        "productName" : "MacBook Pro 2022",
                        "priceNet" : 1000,
                        "tax" : 20,
                        "amount": 1
                        },
                        {
                        "productNumber": "O12345RE12",
                        "productName" : "Ipad Pro 2021",
                        "priceNet" : 99.99,
                        "tax" : 10,
                        "amount": 10
                        }
                    ]
                }`
    })
    const data = await resp.json()
    alert(`New order with ID ${data.orderID} placed!`)
    getallorders();
}
```

Im OrderRestController springt das Post-Mapping an. 

```java
 @PostMapping("/orders")//TODO: Hier von /orders/ mit trailing slash gewechselt auf /orders
    public ResponseEntity placeNewOrder(@RequestBody @Valid PlaceOrderCommand placeOrderCommand, BindingResult bindingResult) {

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling place new order api request ...");

        HashMap<String, String> errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Errors in placeOrderCommand detected!");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new OrderPlacedFieldValidationException("Validation errors for order placement!", errors);
        }

        OrderResponse orderResponse = orderCommandService.handle(placeOrderCommand);

        String resourceLocation = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/v1/orders/" + orderResponse.orderID();
        try {
            return ResponseEntity.created(new URI(resourceLocation)).body(orderResponse);
        } catch (URISyntaxException e) {
            return ResponseEntity.noContent().build();
        }
    }
```
```java
public record PlaceOrderCommand(
        @NotNull @Size(min = 10, max = 10, message = "Customer ID must have a length of 10!") String customerID,
        @NotNull @Size(min = 1, message = "Customer firstname name must not be null and must have at least 1 Character!") String customerFirstname,
        @NotNull @Size(min = 1, message = "Customer lastname  must not be null and must have at least 1 Character!") String customerLastname,
        @Email String customerEmail,
        @NotNull @Size(min = 1, message = "Customer street must not be null and must have at least 1 Character!") String customerStreet,
        @NotNull @Size(min = 1, message = "Customer zipcode must not be null and must have at least 1 Character!") String customerZipcode,
        @NotNull @Size(min = 1, message = "Customer city must not be null and must have at least 1 Character!") String customerCity,
        @NotNull @Size(min = 1, message = "Customer country must not be null and must have at least 1 Character!") String customerCountry,
        @Valid @NotNull List<CartItem> cartItems) { //Valdation cascading for Javax-Validation with @Valid
```

Der Body wird als PlacedOrderCommand gemappt, das im Shared Kernel definiert ist. Damit zwischen den Services keine Abhängigkeiten entstehen.

Über das ordercommandservice handelt die verschiedenen Commands die im Zusammenhang mit Order auftreten können. Das ordercommandservice verwendet orderrepository und das orderoutgoingmessagerelay.

```java
@Transactional
    public OrderResponse handle(PlaceOrderCommand placeOrderCommand) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handle place order command ...");
        List<String> errors = validatePlaceOrderCommand(placeOrderCommand);
        if (errors.size() != 0) throw new OrderDataValidationException(errors);

        /**aus dem PlaceOrderCommand wird eine Order gebastelt**/

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Insert new order in DB ...");
        //Über Repository wird Order in die Datenbank eingetragen
        Optional<Order> orderOptional = this.orderRepository.insert(orderToInsert);

        if (orderOptional.isPresent()) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Publishing order placed domain event ...");

            //wenn schreiben in die DB ok, dann Message gepublisht
            orderOutgoingMessageRelay.publish(new OrderPlacedEvent(OrderResponseMapper.toResponseFromDomain(orderOptional.get())));
            //es wird ein ein OrderResponse gemappt und an den REST Controller zurückgegebene
            return OrderResponseMapper.toResponseFromDomain(orderOptional.get());
        } else {
            throw new OrderPlacementNotSuccessfullException("OrderQueryServiceImpl: Order could not be placed!");
        }
    }
```
#### Payment verifizieren
![Alt text](pics/teil3/Screenshot%202023-06-11%20221946.png)
![Alt text](pics/teil3/Screenshot%202023-06-11%20221958.png)
Es wird ein Post gesendet, mit der richtigen OrderID.
```javascript
 async function paymentok(orderid)
    {
      await fetch(
            `http://localhost:9999/api/v1/orders/checkpayment/${orderid}`,{
            method:'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        alert("Order payment set. Packing requestet in stock!")
        getallorders();
    }
```
Dieser Post kommt beim OrderREstController an.
```java
@PostMapping("/orders/checkpayment/{orderid}")
    public ResponseEntity validatePaymentForOrderWithId(@PathVariable String orderid) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling check payment for order api request ...");
        //das OrderCommand behandeln
        this.orderCommandService.handle(new OrderPaymentCheckCommand(orderid));
        return ResponseEntity.accepted().body("Order payment check executed. Order payment ok!");
    }
```
Über den ordercommandservice wird der OrderPaymentCheckCommand gehandlt.
```java
 @Transactional
    public void handle(OrderPaymentCheckCommand orderPaymentCheckCommand) throws OrderPaymentCheckFailedException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling order payment check command ...");
        if (orderPaymentCheckCommand == null)
            throw new OrderPaymentCheckFailedException("Empty command for order payment check!");
        if (!OrderID.isValid(orderPaymentCheckCommand.orderID()))
            throw new OrderPaymentCheckFailedException("Order ID for order payment check not valid!");

        //mit dem OrderRepository wird das Order mit der ID aus der DB geholt
        Optional<Order> optionalOrderToCheck = this.orderRepository.getById(new OrderID(orderPaymentCheckCommand.orderID()));
        if (optionalOrderToCheck.isPresent()) {
            Order order = optionalOrderToCheck.get();
            try { // versuchen den Status des Orders zu ändern
                order.orderStateTransitionTo(OrderState.PAYMENT_VERIFIED);
                //Status wird geupdated
                this.orderRepository.updateOrderWithNewState(order);
                //Message wird erzeugt und gepublished
                this.orderOutgoingMessageRelay.publish(new OrderPaymentValidatedEvent(OrderResponseMapper.toResponseFromDomain(order)));
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Payment validated event published!");
            } catch (OrderStateChangeNotPossibleException orderStateChangeNotPossibleException) {
                throw new OrderPaymentCheckFailedException("Order payment check not possible. Order in wrong state! " + orderStateChangeNotPossibleException.getMessage());
            }
        } else {
            throw new OrderPaymentCheckFailedException("Order with Id " + orderPaymentCheckCommand.orderID() + " not found for payment check!");
        }
    }
```
#### Packlistenitems abhaken
![Alt text](pics/teil3/Screenshot%202023-06-11%20222733.png)
![Alt text](pics/teil3/Screenshot%202023-06-11%20222750.png)
Jedes Paketitem wird einzeln als verpackt markiert. Das wird über die ItemID bestimmt.
```javascript
async function setpackedforpackingitem(packingitemid)
{
    await fetch(
        `http://localhost:9999/stock/setPackedForPacking/${packingitemid}`,{
        method:'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
    getallpackings()
}
```
Diese Anfrage geht auf dem RestController des StockMS ein.

```java
 @PostMapping("/setPackedForPacking/{packingItemId}")
    public ResponseEntity setPackingItemPackedForPacking(@PathVariable Long packingItemId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Handling packing for item# " + packingItemId);

        //holt sich das PackingItem über das Repository aus der DB
        Optional<PackingItem> optionalPackingItem = this.packingItemRepository.findById(packingItemId);

        if(!optionalPackingItem.isPresent())
        {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Packing for item# " + packingItemId + " not possible, item not present!");
            return ResponseEntity.badRequest().body("Packing for item# " + packingItemId + " not possible, item not present!");
        } else {
            PackingItem packingItem = optionalPackingItem.get();

            if(packingItem.isPacked())
            {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Packing for item# " + packingItemId + " not possible, item already packed!");
                return ResponseEntity.badRequest().body("Packing for item# " + packingItemId + " not possible, item already packed!");
            } else
            {
                //Item als verpackt setzen
                packingItem.setPacked(true);
                //Item wird über Repository in Daten gespeichert
                packingItemRepository.save(packingItem);

                Long packingId = packingItem.getPacking().getId();

                Optional<Packing> packing = this.packingRepository.findById(packingId);

                //check if all are packed, when one new item is packed (above) -> only works because we leave if item to pack that is already packed, see above
                boolean allpaked = true;
                for (PackingItem item : packing.get().getPackingItemList()) {
                    if (!item.isPacked()) allpaked = false;
                }
                String returnMessage = "";
                if (allpaked) {
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "All items for order# " + packing.get().getOrderId() + "packed. Publishing event ...");
                    //wenn alle verpackt sind, wir eine Message gepublisht
                    this.stockMessagePublisher.publishOrderPackedEventForOrderId(packing.get().getOrderId());
                    returnMessage += "All items for order# " + packing.get().getOrderId() + "packed.";
                }
                returnMessage = "Packing for item# " + packingItemId + " done!" + returnMessage;
                return ResponseEntity.ok().body(returnMessage);
            }
        }
    }
```