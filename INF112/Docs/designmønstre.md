# Designmønstre – Kodeeksempler

> Alle eksempler er skrevet i Java. Hvert mønster viser interface-deklarasjoner med kort dokumentasjon og klasseerklæringer som viser arv/implementasjon.

---

## Innholdsfortegnelse

### Kreative mønstre (Creational)
- [[#Abstract Factory]]
- [[#Builder]]
- [[#Factory Method]]
- [[#Prototype]]
- [[#Singleton]]

### Strukturelle mønstre (Structural)
- [[#Adapter]]
- [[#Bridge]]
- [[#Composite]]
- [[#Decorator]]
- [[#Facade]]
- [[#Flyweight]]
- [[#Proxy]]

### Atferdsmønstre (Behavioral)
- [[#Chain of Responsibility]]
- [[#Command]]
- [[#Iterator]]
- [[#Mediator]]
- [[#Memento]]
- [[#Observer]]
- [[#State]]
- [[#Strategy]]
- [[#Template Method]]
- [[#Visitor]]

---

## Kreative mønstre

### Abstract Factory

> Gir et grensesnitt for å opprette familier av relaterte objekter uten å spesifisere konkrete klasser.

```java
/** Fabrikk som oppretter relaterte UI-komponenter for ett tema. */
interface UIFactory {
    Button createButton();   // Oppretter en knapp tilpasset temaet.
    Checkbox createCheckbox; // Oppretter en avkrysningsboks tilpasset temaet.
}

/** En knapp med tema-spesifikk oppførsel. */
interface Button {
    void render(); // Tegner knappen på skjermen.
}

/** En avkrysningsboks med tema-spesifikk oppførsel. */
interface Checkbox {
    void render(); // Tegner avkrysningsboksen på skjermen.
}

// --- Konkrete klasser ---

class WindowsFactory   implements UIFactory { ... }
class MacFactory       implements UIFactory { ... }

class WindowsButton    implements Button    { ... }
class MacButton        implements Button    { ... }

class WindowsCheckbox  implements Checkbox  { ... }
class MacCheckbox      implements Checkbox  { ... }

// --- Bruk ---

class Application {
    private Button button;
    private Checkbox checkbox;

    Application(UIFactory factory) {
        this.button   = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    void render() {
        button.render();
        checkbox.render();
    }
}

// Klient velger fabrikk basert på OS
UIFactory factory = new WindowsFactory();
Application app   = new Application(factory);
app.render();
```

---

### Builder

> Skiller konstruksjon av et komplekst objekt fra representasjonen, slik at samme prosess kan gi ulike resultater.

```java
/** Definerer steg-for-steg-bygging av et hus. */
interface HouseBuilder {
    void buildWalls();      // Bygger veggene.
    void buildRoof();       // Bygger taket.
    void buildWindows();    // Setter inn vinduer.
    House getResult();      // Returnerer det ferdige huset.
}

// --- Konkrete klasser ---

class WoodenHouseBuilder implements HouseBuilder { ... }
class StoneHouseBuilder  implements HouseBuilder { ... }

class House { ... } // Produktklassen

/** Styrer byggeprosessen i riktig rekkefølge. */
class Director {
    private HouseBuilder builder;

    Director(HouseBuilder builder) { this.builder = builder; }

    House buildSimpleHouse() {
        builder.buildWalls();
        builder.buildRoof();
        return builder.getResult();
    }

    House buildFullHouse() {
        builder.buildWalls();
        builder.buildRoof();
        builder.buildWindows();
        return builder.getResult();
    }
}

// --- Bruk ---

HouseBuilder builder = new WoodenHouseBuilder();
Director director    = new Director(builder);
House house          = director.buildFullHouse();
```

---

### Factory Method

> Definerer et grensesnitt for å opprette et objekt, men lar underklasser bestemme hvilken klasse som instansieres.

```java
/** Et dokument som kan åpnes og lagres. */
interface Document {
    void open();  // Åpner dokumentet.
    void save();  // Lagrer dokumentet.
}

/** Fabrikklasse med en abstrakt fabrikksmetode. */
abstract class Application {
    /** Oppretter riktig dokumenttype – implementeres av underklassen. */
    abstract Document createDocument();

    void newDocument() {
        Document doc = createDocument(); // Factory Method
        doc.open();
    }
}

// --- Konkrete klasser ---

class WordDocument      implements Document     { ... }
class SpreadsheetDoc    implements Document     { ... }

class WordApplication   extends Application    {
    @Override Document createDocument() { return new WordDocument(); }
}

class ExcelApplication  extends Application    {
    @Override Document createDocument() { return new SpreadsheetDoc(); }
}

// --- Bruk ---

Application app = new WordApplication();
app.newDocument(); // Oppretter og åpner et WordDocument
```

---

### Prototype

> Spesifiserer hvilke typer objekter som skal opprettes ved å klone et eksemplar (prototype).

```java
/** Et objekt som kan klone seg selv. */
interface Prototype {
    Prototype clone(); // Returnerer en dyp kopi av objektet.
}

// --- Konkrete klasser ---

class Circle    implements Prototype { ... }
class Rectangle implements Prototype { ... }

// --- Bruk ---

Circle original  = new Circle(10, "red");
Circle kopi      = (Circle) original.clone();
// kopi er en selvstendig kopi – endringer påvirker ikke original
```

---

### Singleton

> Sikrer at en klasse kun har én instans og gir global tilgang til den.

```java
class DatabaseConnection {
    private static DatabaseConnection instance;

    /** Privat konstruktør hindrer direkte instansiering utenfra. */
    private DatabaseConnection() { ... }

    /** Returnerer den eneste instansen, oppretter den om nødvendig. */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql) { ... } // Utfører en SQL-spørring.
}

// --- Bruk ---

DatabaseConnection db = DatabaseConnection.getInstance();
db.query("SELECT * FROM users");
```

---

## Strukturelle mønstre

### Adapter

> Konverterer grensesnittet til én klasse til et annet grensesnitt som klienten forventer.

```java
/** Grensesnittet klienten forventer. */
interface MediaPlayer {
    void play(String filename); // Spiller av en mediefil.
}

/** Grensesnitt for avansert spiller med ekstra formater. */
interface AdvancedMediaPlayer {
    void playVlc(String filename);  // Spiller av VLC-format.
    void playMp4(String filename);  // Spiller av MP4-format.
}

// --- Konkrete klasser ---

class VlcPlayer       implements AdvancedMediaPlayer { ... }
class Mp4Player       implements AdvancedMediaPlayer { ... }

/** Tilpasser AdvancedMediaPlayer til MediaPlayer-grensesnittet. */
class MediaAdapter    implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;

    MediaAdapter(String type) {
        if (type.equals("vlc")) advancedPlayer = new VlcPlayer();
        else                     advancedPlayer = new Mp4Player();
    }

    @Override
    public void play(String filename) {
        advancedPlayer.playVlc(filename); // eller playMp4
    }
}

class AudioPlayer     implements MediaPlayer {
    @Override
    public void play(String filename) {
        if (filename.endsWith(".mp3")) { /* direkte avspilling */ }
        else new MediaAdapter(getExtension(filename)).play(filename);
    }
}

// --- Bruk ---

MediaPlayer player = new AudioPlayer();
player.play("movie.vlc");
player.play("song.mp3");
```

---

### Bridge

> Skiller en abstraksjon fra implementasjonen slik at de to kan variere uavhengig.

```java
/** Implementasjonssiden: ulike tegne-APIer. */
interface Renderer {
    void renderCircle(int radius);    // Tegner en sirkel med gitt radius.
    void renderSquare(int side);      // Tegner et kvadrat med gitt sidekant.
}

// --- Konkrete implementasjoner ---

class OpenGLRenderer implements Renderer { ... }
class VulkanRenderer  implements Renderer { ... }

/** Abstraksjonsside: former med en referanse til en Renderer. */
abstract class Shape {
    protected Renderer renderer; // Bro til implementasjonssiden.

    Shape(Renderer renderer) { this.renderer = renderer; }

    abstract void draw();  // Tegner formen via renderer.
    abstract void resize(double factor); // Skalerer formen.
}

// --- Konkrete abstraksjoner ---

class Circle extends Shape {
    private int radius;
    Circle(int radius, Renderer r) { super(r); this.radius = radius; }

    @Override void draw()                 { renderer.renderCircle(radius); }
    @Override void resize(double factor)  { radius = (int)(radius * factor); }
}

class Square extends Shape { ... }

// --- Bruk ---

Shape circle = new Circle(5, new OpenGLRenderer());
circle.draw();
circle.resize(2.0);
```

---

### Composite

> Setter sammen objekter i trestrukturer for å representere del-helhet-hierarkier.

```java
/** Felles grensesnitt for både enkeltfiler og mapper. */
interface FileSystemComponent {
    void display(String indent); // Viser komponenten med innrykk.
    int getSize();               // Returnerer total størrelse i bytes.
}

// --- Konkrete klasser ---

/** Bladnode: kan ikke inneholde barn. */
class File   implements FileSystemComponent { ... }

/** Sammensatt node: kan inneholde andre komponenter. */
class Folder implements FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();

    void add(FileSystemComponent c)    { children.add(c); }    // Legger til barn.
    void remove(FileSystemComponent c) { children.remove(c); } // Fjerner barn.

    @Override
    public int getSize() {
        return children.stream().mapToInt(FileSystemComponent::getSize).sum();
    }
}

// --- Bruk ---

Folder root = new Folder("root");
root.add(new File("readme.txt", 1024));

Folder src = new Folder("src");
src.add(new File("Main.java", 4096));
src.add(new File("Utils.java", 2048));

root.add(src);
root.display("");
System.out.println("Total: " + root.getSize() + " bytes");
```

---

### Decorator

> Legger dynamisk til ny funksjonalitet til et objekt uten å endre klassen.

```java
/** Grunnleggende kaffebestilling. */
interface Coffee {
    String getDescription(); // Returnerer beskrivelse av kaffen.
    double getCost();        // Returnerer pris i kroner.
}

// --- Konkret basiskomponent ---

class SimpleCoffee implements Coffee { ... }

/** Basisdekoratør som delegerer til det innpakkede objektet. */
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // Det innpakkede kaffeobioktet.

    CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }

    @Override public String getDescription() { return coffee.getDescription(); }
    @Override public double getCost()        { return coffee.getCost(); }
}

// --- Konkrete dekoratører ---

class MilkDecorator   extends CoffeeDecorator {
    MilkDecorator(Coffee c) { super(c); }
    @Override public String getDescription() { return coffee.getDescription() + ", melk"; }
    @Override public double getCost()        { return coffee.getCost() + 5.0; }
}

class SugarDecorator  extends CoffeeDecorator { ... }
class VanillaDecorator extends CoffeeDecorator { ... }

// --- Bruk ---

Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
coffee = new VanillaDecorator(coffee);

System.out.println(coffee.getDescription()); // Kaffe, melk, sukker, vanilje
System.out.println(coffee.getCost());        // 45.0
```

---

### Facade

> Gir et forenklet grensesnitt til et komplekst subsystem.

```java
// --- Komplekse subsystemklasser (ingen felles interface nødvendig) ---

class CPU        { void freeze() {...} void jump(long pos) {...} void execute() {...} }
class Memory     { void load(long pos, byte[] data) {...} }
class HardDrive  { byte[] read(long lba, int size) {...} }

/** Fasade som skjuler kompleksiteten ved oppstart av en datamaskin. */
class ComputerFacade {
    private CPU       cpu       = new CPU();
    private Memory    memory    = new Memory();
    private HardDrive hardDrive = new HardDrive();

    /** Starter datamaskinen – klienten trenger ikke kjenne subsystemene. */
    void start() {
        cpu.freeze();
        memory.load(0x00, hardDrive.read(0, 1024));
        cpu.jump(0x00);
        cpu.execute();
    }
}

// --- Bruk ---

ComputerFacade computer = new ComputerFacade();
computer.start(); // Én enkel metode i stedet for mange steg
```

---

### Flyweight

> Bruker deling for å effektivt støtte et stort antall finkornet objekter.

```java
/** Delt tilstand for tegnpunkter i et tekstdokument. */
interface CharacterFlyweight {
    /** Tegner tegnet med ekstrinsisk (unik) posisjon og størrelse. */
    void draw(int x, int y, int fontSize);
}

// --- Konkret Flyweight ---

class CharacterGlyph implements CharacterFlyweight {
    private final char symbol; // Intrinsisk (delt) tilstand
    private final String font;

    CharacterGlyph(char symbol, String font) { ... }

    @Override
    public void draw(int x, int y, int fontSize) { ... }
}

/** Fabrikk som sikrer gjenbruk av eksisterende flyweights. */
class FlyweightFactory {
    private Map<String, CharacterFlyweight> cache = new HashMap<>();

    /** Returnerer eksisterende glyph eller oppretter ny om nødvendig. */
    CharacterFlyweight getCharacter(char c, String font) {
        String key = c + font;
        cache.computeIfAbsent(key, k -> new CharacterGlyph(c, font));
        return cache.get(key);
    }
}

// --- Bruk ---

FlyweightFactory factory = new FlyweightFactory();

// Millioner av tegn deler på få CharacterGlyph-objekter
for (char c : "Hello World".toCharArray()) {
    CharacterFlyweight glyph = factory.getCharacter(c, "Arial");
    glyph.draw(xPos++, 100, 12);
}
```

---

### Proxy

> Gir en stedfortreder som kontrollerer tilgang til et annet objekt.

```java
/** Grensesnitt som både ekte objekt og proxy implementerer. */
interface Image {
    void display(); // Viser bildet på skjermen.
}

/** Det ekte, ressurskrevende objektet. */
class RealImage implements Image {
    private String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(); // Dyr operasjon
    }

    private void loadFromDisk() { System.out.println("Laster " + filename); }

    @Override public void display() { System.out.println("Viser " + filename); }
}

/** Proxy som utsetter lasting til bildet faktisk trengs (lazy loading). */
class ImageProxy implements Image {
    private String    filename;
    private RealImage realImage; // null inntil display() kalles første gang.

    ImageProxy(String filename) { this.filename = filename; }

    @Override
    public void display() {
        if (realImage == null) realImage = new RealImage(filename); // Lazy init
        realImage.display();
    }
}

// --- Bruk ---

Image image = new ImageProxy("foto.jpg");
// RealImage er ikke lastet ennå
image.display(); // Laster og viser
image.display(); // Viser direkte – ingen ny lasting
```

---

## Atferdsmønstre

### Chain of Responsibility

> Sender en forespørsel langs en kjede av behandlere til én av dem håndterer den.

```java
/** En behandler i kjeden. */
interface SupportHandler {
    void setNext(SupportHandler next); // Setter neste behandler i kjeden.
    void handle(int level, String issue); // Håndterer saken eller sender videre.
}

// --- Abstrakt basisbehandler ---

abstract class BaseHandler implements SupportHandler {
    private SupportHandler next;

    @Override public void setNext(SupportHandler next) { this.next = next; }

    /** Sender videre til neste behandler om denne ikke kan håndtere. */
    protected void passToNext(int level, String issue) {
        if (next != null) next.handle(level, issue);
        else System.out.println("Ubehandlet: " + issue);
    }
}

// --- Konkrete behandlere ---

class FirstLineSupport  extends BaseHandler { ... } // Håndterer nivå 1
class SecondLineSupport extends BaseHandler { ... } // Håndterer nivå 2
class ExpertSupport     extends BaseHandler { ... } // Håndterer nivå 3

// --- Bruk ---

SupportHandler first  = new FirstLineSupport();
SupportHandler second = new SecondLineSupport();
SupportHandler expert = new ExpertSupport();

first.setNext(second);
second.setNext(expert);

first.handle(1, "Glemt passord");
first.handle(3, "Databasekorrupsjon");
```

---

### Command

> Kapsler inn en forespørsel som et objekt, slik at forespørsler kan parametriseres, køes og angres.

```java
/** En kommando som kan utføres og angres. */
interface Command {
    void execute(); // Utfører kommandoen.
    void undo();    // Angrer kommandoen.
}

// --- Mottaker (Receiver) ---

class TextEditor {
    private StringBuilder text = new StringBuilder();

    void insertText(String t, int pos) { text.insert(pos, t); } // Setter inn tekst.
    void deleteText(int pos, int len)  { text.delete(pos, pos + len); } // Sletter tekst.
    String getText()                   { return text.toString(); }
}

// --- Konkrete kommandoer ---

class InsertCommand implements Command {
    private TextEditor editor;
    private String text;
    private int position;

    InsertCommand(TextEditor editor, String text, int pos) { ... }

    @Override public void execute() { editor.insertText(text, position); }
    @Override public void undo()    { editor.deleteText(position, text.length()); }
}

class DeleteCommand implements Command { ... }

// --- Invoker ---

class CommandHistory {
    private Deque<Command> history = new ArrayDeque<>();

    /** Utfører kommandoen og legger den i historikken. */
    void executeCommand(Command cmd) { cmd.execute(); history.push(cmd); }

    /** Angrer siste kommando. */
    void undo() { if (!history.isEmpty()) history.pop().undo(); }
}

// --- Bruk ---

TextEditor editor   = new TextEditor();
CommandHistory hist = new CommandHistory();

hist.executeCommand(new InsertCommand(editor, "Hei verden", 0));
hist.executeCommand(new InsertCommand(editor, "!", 9));
hist.undo(); // Angrer "!"
```

---

### Iterator

> Gir en måte å sekvensielt aksessere elementene i en samling uten å eksponere den underliggende strukturen.

```java
/** Iteratoren som traverserer en samling. */
interface Iterator<T> {
    boolean hasNext(); // Returnerer true om det finnes flere elementer.
    T next();          // Returnerer neste element og avanserer markøren.
}

/** En samling som kan produsere en iterator. */
interface IterableCollection<T> {
    Iterator<T> createIterator(); // Oppretter en iterator for samlingen.
}

// --- Konkrete klasser ---

class BookShelf implements IterableCollection<Book> {
    private List<Book> books = new ArrayList<>();

    void addBook(Book b) { books.add(b); }

    @Override
    public Iterator<Book> createIterator() {
        return new BookShelfIterator(books);
    }
}

class Book { ... }

class BookShelfIterator implements Iterator<Book> {
    private List<Book> books;
    private int index = 0;

    BookShelfIterator(List<Book> books) { this.books = books; }

    @Override public boolean hasNext() { return index < books.size(); }
    @Override public Book next()       { return books.get(index++); }
}

// --- Bruk ---

BookShelf shelf = new BookShelf();
shelf.addBook(new Book("Clean Code"));
shelf.addBook(new Book("Design Patterns"));

Iterator<Book> it = shelf.createIterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

---

### Mediator

> Definerer et objekt som kapsler inn hvordan et sett av objekter samhandler, og fremmer løs kobling.

```java
/** Mediatoren som koordinerer kommunikasjon mellom kolleger. */
interface ChatMediator {
    void sendMessage(String msg, User sender); // Formidler melding til alle andre brukere.
    void addUser(User user);                   // Registrerer en bruker i chatten.
}

/** En kollega som kommuniserer via mediatoren. */
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    User(ChatMediator mediator, String name) { ... }

    abstract void send(String msg);    // Sender melding via mediator.
    abstract void receive(String msg); // Mottar melding fra mediator.
}

// --- Konkrete klasser ---

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    @Override public void addUser(User user)   { users.add(user); }

    @Override
    public void sendMessage(String msg, User sender) {
        users.stream()
             .filter(u -> u != sender)
             .forEach(u -> u.receive(msg));
    }
}

class ChatUser extends User { ... }

// --- Bruk ---

ChatMediator room = new ChatRoom();
User alice = new ChatUser(room, "Alice");
User bob   = new ChatUser(room, "Bob");
User carol = new ChatUser(room, "Carol");

room.addUser(alice);
room.addUser(bob);
room.addUser(carol);

alice.send("Hei alle!"); // Bob og Carol mottar meldingen
```

---

### Memento

> Fanger og eksternaliserer et objekts interne tilstand slik at objektet kan gjenopprettes til denne tilstanden.

```java
/** Lagrer en bestemt tilstand av editoren (ugjennomtrengelig for andre). */
class EditorMemento {
    private final String content; // Lagret innhold – kun editor kan lese dette.
    private final int caretPos;

    EditorMemento(String content, int caretPos) { ... }

    String getContent()  { return content; }  // Returnerer lagret tekst.
    int    getCaretPos() { return caretPos; } // Returnerer markørposisjon.
}

/** Originator: objektet hvis tilstand vi ønsker å lagre. */
class Editor {
    private String content  = "";
    private int    caretPos = 0;

    void type(String text)               { content += text; caretPos += text.length(); }

    /** Lagrer nåværende tilstand i et memento. */
    EditorMemento save()                 { return new EditorMemento(content, caretPos); }

    /** Gjenoppretter tilstand fra et memento. */
    void restore(EditorMemento memento)  { content = memento.getContent(); caretPos = memento.getCaretPos(); }
}

/** Caretaker: holder på mementoer uten å kjenne innholdet. */
class History {
    private Deque<EditorMemento> stack = new ArrayDeque<>();

    void push(EditorMemento m)  { stack.push(m); } // Lagrer en tilstand.
    EditorMemento pop()         { return stack.pop(); } // Henter siste lagrede tilstand.
}

// --- Bruk ---

Editor  editor  = new Editor();
History history = new History();

editor.type("Hei");
history.push(editor.save());

editor.type(" verden");
history.push(editor.save());

editor.type("!!!");
editor.restore(history.pop()); // Tilbake til "Hei verden"
editor.restore(history.pop()); // Tilbake til "Hei"
```

---

### Observer

> Definerer en én-til-mange-avhengighet mellom objekter: når ett objekt endres, varsles alle avhengige automatisk.

```java
/** Subjektet som observeres. */
interface Subject {
    void attach(Observer o);  // Registrerer en observer.
    void detach(Observer o);  // Fjerner en observer.
    void notifyObservers();   // Varsler alle registrerte observere om endring.
}

/** En observer som mottar varsler. */
interface Observer {
    void update(String event, Object data); // Kalles når subjektet endrer seg.
}

// --- Konkrete klasser ---

class EventStore implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String         lastEvent;
    private Object         lastData;

    @Override public void attach(Observer o)         { observers.add(o); }
    @Override public void detach(Observer o)         { observers.remove(o); }
    @Override public void notifyObservers()          { observers.forEach(o -> o.update(lastEvent, lastData)); }

    /** Publiserer en hendelse og varsler alle observere. */
    void publish(String event, Object data) {
        this.lastEvent = event;
        this.lastData  = data;
        notifyObservers();
    }
}

class EmailNotifier  implements Observer { ... } // Sender e-post ved varsling.
class LoggingService implements Observer { ... } // Logger hendelsen ved varsling.
class Dashboard      implements Observer { ... } // Oppdaterer visning ved varsling.

// --- Bruk ---

EventStore store    = new EventStore();
store.attach(new EmailNotifier());
store.attach(new LoggingService());
store.attach(new Dashboard());

store.publish("ORDER_PLACED", new Order(42)); // Alle tre observere varsles
```

---

### State

> Lar et objekt endre sin atferd når den interne tilstanden endres – objektet ser ut til å endre klasse.

```java
/** En tilstand i en automat. */
interface VendingMachineState {
    void insertCoin();  // Håndterer myntnedlegging.
    void selectItem();  // Håndterer varevalg.
    void dispense();    // Håndterer utlevering av vare.
}

// --- Kontekstobjektet ---

class VendingMachine {
    private VendingMachineState currentState;

    VendingMachine() { this.currentState = new IdleState(this); }

    /** Bytter til ny tilstand. */
    void setState(VendingMachineState state) { this.currentState = state; }

    void insertCoin()  { currentState.insertCoin(); }
    void selectItem()  { currentState.selectItem(); }
    void dispense()    { currentState.dispense(); }
}

// --- Konkrete tilstander ---

class IdleState      implements VendingMachineState { ... } // Venter på mynt.
class HasCoinState   implements VendingMachineState { ... } // Mynt er lagt inn.
class DispensingState implements VendingMachineState { ... } // Leverer vare.
class OutOfStockState implements VendingMachineState { ... } // Tom for varer.

// --- Bruk ---

VendingMachine machine = new VendingMachine();
machine.insertCoin();   // Går til HasCoinState
machine.selectItem();   // Går til DispensingState
machine.dispense();     // Leverer vare, går tilbake til IdleState
```

---

### Strategy

> Definerer en familie av algoritmer, kapsler dem inn og gjør dem utbyttbare.

```java
/** En sorteringsalgoritme. */
interface SortStrategy {
    void sort(int[] data); // Sorterer arrayen på plass.
}

// --- Konkrete strategier ---

class BubbleSort    implements SortStrategy { ... }
class QuickSort     implements SortStrategy { ... }
class MergeSort     implements SortStrategy { ... }

// --- Kontekstobjektet ---

class DataProcessor {
    private SortStrategy strategy;

    /** Bytter sorteringsstrategi ved kjøretid. */
    void setStrategy(SortStrategy strategy) { this.strategy = strategy; }

    /** Sorterer data med valgt strategi. */
    void process(int[] data) {
        strategy.sort(data);
    }
}

// --- Bruk ---

DataProcessor processor = new DataProcessor();

int[] data = {5, 2, 8, 1, 9};

processor.setStrategy(new QuickSort());
processor.process(data); // Bruker QuickSort

processor.setStrategy(new MergeSort());
processor.process(data); // Bruker MergeSort – ingen endring i klient-koden
```

---

### Template Method

> Definerer skjelettet til en algoritme i en basisklasse, og lar underklasser fylle inn spesifikke steg.

```java
/** Mal for å lage en varm drikke – fikser rekkefølgen, lar subklasser tilpasse steg. */
abstract class HotBeverageTemplate {
    /** Template method – kan ikke overrides (final). */
    final void prepare() {
        boilWater();    // Felles steg: kokes av basisklassen.
        brew();         // Spesifikt steg: implementeres av subklasse.
        pourInCup();    // Felles steg.
        addCondiments();// Valgfritt steg: kan overrides.
    }

    private void boilWater() { System.out.println("Koker vann"); }
    private void pourInCup() { System.out.println("Heller i kopp"); }

    /** Koker/trekker selve drikken – må implementeres av subklasse. */
    abstract void brew();

    /** Legger til smakstilsetning – subklasse kan overstyre. */
    void addCondiments() { /* Standard: ingenting */ }
}

// --- Konkrete subklasser ---

class Tea extends HotBeverageTemplate {
    @Override void brew() { System.out.println("Trekker te"); }
    @Override void addCondiments() { System.out.println("Tilsetter sitron"); }
}

class Coffee extends HotBeverageTemplate {
    @Override void brew() { System.out.println("Filtrerer kaffe"); }
    @Override void addCondiments() { System.out.println("Tilsetter melk og sukker"); }
}

// --- Bruk ---

HotBeverageTemplate te = new Tea();
te.prepare();
// Koker vann → Trekker te → Heller i kopp → Tilsetter sitron
```

---

### Visitor

> Lar deg legge til ny operasjon til objektstrukturer uten å endre klassene til elementene.

```java
/** En operasjon som utføres på elementer i en struktur. */
interface ShapeVisitor {
    void visitCircle(Circle circle);       // Besøker en sirkel.
    void visitRectangle(Rectangle rect);   // Besøker et rektangel.
    void visitTriangle(Triangle triangle); // Besøker en trekant.
}

/** Et element som kan ta imot en visitor. */
interface Shape {
    void accept(ShapeVisitor visitor); // Sender seg selv til visitoren (double dispatch).
}

// --- Konkrete elementer ---

class Circle implements Shape {
    double radius;
    @Override public void accept(ShapeVisitor v) { v.visitCircle(this); }
}

class Rectangle implements Shape {
    double width, height;
    @Override public void accept(ShapeVisitor v) { v.visitRectangle(this); }
}

class Triangle implements Shape { ... }

// --- Konkrete visitorer (nye operasjoner uten å endre formklassene) ---

class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0;

    @Override public void visitCircle(Circle c)       { totalArea += Math.PI * c.radius * c.radius; }
    @Override public void visitRectangle(Rectangle r) { totalArea += r.width * r.height; }
    @Override public void visitTriangle(Triangle t)   { ... }

    double getTotalArea() { return totalArea; } // Returnerer beregnet totalareal.
}

class XMLExporter implements ShapeVisitor { ... } // Eksporterer former til XML.
class SVGRenderer    implements ShapeVisitor { ... } // Tegner former som SVG.

// --- Bruk ---

List<Shape> shapes = List.of(new Circle(), new Rectangle(), new Triangle());

AreaCalculator calc = new AreaCalculator();
shapes.forEach(s -> s.accept(calc));
System.out.println("Totalareal: " + calc.getTotalArea());
```

---

## Oversikt

| Mønster | Kategori | Hensikt |
|---|---|---|
| Abstract Factory | Kreativ | Lag familier av relaterte objekter |
| Builder | Kreativ | Bygg komplekse objekter steg for steg |
| Factory Method | Kreativ | La subklassen bestemme hvilken klasse som instansieres |
| Prototype | Kreativ | Klon eksisterende objekter |
| Singleton | Kreativ | Sikre én enkelt instans |
| Adapter | Strukturell | Koble sammen inkompatible grensesnitt |
| Bridge | Strukturell | Skill abstraksjon fra implementasjon |
| Composite | Strukturell | Tre-strukturer for del-helhet |
| Decorator | Strukturell | Legg til ansvar dynamisk |
| Facade | Strukturell | Forenklet grensesnitt til subsystem |
| Flyweight | Strukturell | Del tilstand mellom mange objekter |
| Proxy | Strukturell | Kontroller tilgang til et objekt |
| Chain of Responsibility | Atferd | Send forespørsel langs en kjede |
| Command | Atferd | Kapsl inn forespørsler som objekter |
| Iterator | Atferd | Traverser samlinger uten å eksponere struktur |
| Mediator | Atferd | Sentraliser kommunikasjon mellom objekter |
| Memento | Atferd | Lagre og gjenopprett objekttilstand |
| Observer | Atferd | Varsle avhengige om endringer |
| State | Atferd | Endre atferd basert på intern tilstand |
| Strategy | Atferd | Bytt algoritmer ved kjøretid |
| Template Method | Atferd | Definer algoritme-skjelett i basisklasse |
| Visitor | Atferd | Legg til operasjoner uten å endre klasser |
