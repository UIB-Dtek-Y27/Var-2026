---
title: SOLID eksempler
---





## Eksempler på Dependency Inversion Principle og Dependency Injection

### Eksempel 1: SL4J Logger

SL4J er et rammeverk som støtter mange forskjellige implementasjoner av logging – for at det skal funke kan ikke enkeltklasser være avhengige av en konkret implementasjon. Dette er særlig viktig når man lager et bibliotek, slik at det funker sammen med andre biblioteker og applikasjoner. SL4J APIet funker slik:

```java
private static final Logger logger = …

// …
		logger.info("Hello, {}!", "world"); // {} erstattes med argumenter
		logger.warn("Beware, {}!", "world");
		logger.debug("Hello, {}!", "bugs");
		logger.error("Oops", ex);           // standard måte for å logge unntak
```

Nøyaktig hvordan loggen blir seende ut er avhengig av implementasjon og instillinger, men med `sl4j-simple` og standard konfigurasjon vil koden skrive følgende (til `System.err`):

```stderr
[main] INFO inf112.variant2.app.Controller - Hello, world!
[main] WARN inf112.variant2.app.Controller - Beware, world!
[main] ERROR inf112.variant2.app.Controller - Oops
java.lang.Exception
	at inf112.variant2.app.Controller.<init>(Controller.java:63)
	at inf112.variant2.app.MVCDemo.main(MVCDemo.java:18)
```

#### 1.1 `Logger`-fabrikk

For å få tak i logger-objektet, bruker man en loggefabrikk, `ILoggerFactory`. Vi kan «injisere» logger-avhengigheten inn i `Controller` via konstruktøren:

```java
	private Logger logger;
	
	public Controller(ILoggerFactory loggerFactory) {
		logger = loggerFactory.getLogger(getClass().getName());
	}
```

Hvordan får vi tak i loggefabrikken? Da trenger vi en `LoggerFactoryFactory` – heldigvis gir SL4J oss nettopp det:

```java
Controller ctrl = new Controller(LoggerFactory.getILoggerFactory());
```

##### *Spørsmål*

* **Hvorfor trenger hver klasse sitt eget `logger`-objekt?**
* **Hvorfor gir vi `Controller` en `ILoggerFactory` i stedet for å bare gi den et `Logger`-objekt direkte?** (f.eks. `new Controller(new MyLogger("Controller"))`)

#### 1.2 Standard logge-oppsett

Siden hele applikasjonen skal bruke samme type logging, trenger vi ikke gjøre det så komplisert – `LoggerFactory` har allerede metoden som trengs for å finne riktig fabrikk og lage ny logger:

```java
public class Controller {
	private final Logger logger = LoggerFactory.getLogger(getClass());
// eller
	private final static Logger logger = LoggerFactory.getLogger(Controller.class);
```

`LoggerFactory`  bruker Javas modulsystem for å velge riktig implementasjon, så alt utvikleren trenger å gjøre er å legge til f.eks. `sl4j-simple` (eller en annen implementasjon) i `pom.xml` (eller `CLASSPATH`).

## Eksempel 2: bli kvitt `new`

* [Controller](https://git.app.uib.no/inf112/25v/inf112.25v.lectures/-/blob/cce950f96637b2a7ab33c912ed9ae956b58bc4c8/src/main/java/inf112/variant2/app/Controller.java) er avhengig av mange konkrete klasser, inkludert `ArrayList`, `View`, `Json`, `InputAdapter`, …:

Alle disse er tilfeller av at vi er avhengig av *noe konkret* i stedet for å bare være avhengig av *abstraksjoner*. I denne sammenhengen betyr *konkret avhengighet* f.eks.:

* vi bruker `new ClassName()` for å opprette objekt *(veldig konkret avhengighet)*
* vi bruker klassenavn i stedet for interfacenavn (`ArrayList` vs `List`) *(ganske konkret avhengighet)*
* vi bruker et mer spesifikt («større») interface enn nødvendig (f.eks. `List` der vi kunne brukt `Collection` (mer abstrakt) eller `Iterable` (enda mer abstrakt)) *(litt konkret avhengighet)*

Dette gir tettere kobling enn nødvendig, og gjør at mister vi fleksibilitet. Det bryter derfor med *Dependency Inversion Principle* som sier at vi skal være avhengig av abstraksjoner og ikke konkrete ting.

Det er ikke alltid det er praktisk mulig å unngå konkrete ting – f.eks. finnes det ikke noe interface for `Random`, `PrintWriter`, og vår `View`-klasse og LibGDX sin `Json`-klasse har bare én implementasjon. Det kan kanskje likevel være nyttig med mer fleksibilitet:

* kanskje vi vil bytte ut standard `Random` med LibGDX sin `RandomXS128`, eller `SecureRandom` som lager kryptografisk sterke slumptall, eller `ThreadLocalRandom` som kan være raskere i en flertrådet applikasjon.
* vi har kanskje lyst på en `PrintWriter` som skriver til en streng, eller til en annen fil enn `"/tmp/log.json"`
* det er flere fornuftige valg for `Viewport` – `FitViewport`, `FillViewport`, `StretchViewport`

#### 2.1 Injisering via konstruktør

For å unngå `new` er vi avhengig av bruke *Factory pattern*. Vi kan sende inn fabrikkene via konstruktøren:

```java
public class Controller {
	public Controller( //
			Supplier<List<ModelObject>> listFactory, // og ikke List<?> som på forelesning
			Supplier<Random> randomFactory,
			Supplier<View> viewFactory,
			Supplier<Viewport> viewportFactory,
			Supplier<Json> jsonFactory,
			Supplier<PrintWriter> pwFactory) {
		
		models = (List<ModelObject>) listFactory.get();
		random = randomFactory.get();
		viewport = viewportFactory.get();
		json = jsonFactory.get();
		logFile = pwFactory.get();
```

Når vi oppretter `Controller` må vi sende inn fabrikkene:

```java
	public static void main(String[] args) {
		Viewport vp = new FitViewport(1920, 1080);
		Controller ctrl = new Controller( //
				ArrayList::new, // plukk konstruktøren
				RandomXS128::new, //
				() -> new View(vp), // lambdauttrykk
				() -> vp, // oops! samme objekt vær gang
				Json::new, //
				() -> new PrintWriter("/tmp/log.json") // denne vil ikke funke pga. throws FileNotFoundException
		);
```

Dette er selvfølgelig både rotete og upraktisk. Fabrikken for `Viewport` er ikke en gang en ekte fabrikk, siden den returnerer samme objektet hver gang (nødvendig fordi den trengs i `new View(vp)`) – her hadde det kanskje vært bedre å sende `Viewport`-objektet direkte.

[[`Controller.java`](https://git.app.uib.no/inf112/25v/inf112.25v.lectures/-/blob/4e713155c3fcb3a87933ea4b8f6e720dc88d3e2e/src/main/java/inf112/variant2/app/Controller.java), [factory eksempelkode](https://git.app.uib.no/inf112/25v/inf112.25v.lectures/-/tree/main/src/main/java/inf112/codeexamples/factories?ref_type=heads)]

#### 2.2 Parametre

Det kan være nyttig å gi argumenter til fabrikk-metoden. I enkle tilfeller kan man bruke `Function` eller `BiFunction`:

```java
	public Controller(BiFunction<Integer, Integer, View> viewFactory) {
		view = viewFactory.apply(1920, 1080);
	}
```

I mer kompliserte tilfeller kan man bruke [*Builder pattern*](https://refactoring.guru/design-patterns/builder):

```java
public interface ViewBuilder {
	/** select width (default 1920) */
	ViewBuilder width(int width);

	/** select height (default 1080) */
	ViewBuilder height(int height);

	/** use FitViewport (default) */
	ViewBuilder fit();

	/** use FitViewport */
	ViewBuilder fill();

	/** use FitViewport */
	ViewBuilder stretch();
	
	/** create view object */
	View build(); // ← this is the actual factory method

}
```

#### 2.3 Typeparametre *(mindre viktig)*

I eksemplet over vil `ListFactory` potensielt lage trøbbel med typeparameteret (hvis vi ikke har et factory per elementtype). Vi kan ordne det ved å ta en `Class` som argument – i såfall må vi lage et eget interface for det (`Supplier` eller `Function` gjør ikke helt jobben):

```java
public interface ListFactory {
	<T> List<T> list(Class<T> cls);
}
```

Parameteret `cls` er med for at Java skal finne ut av elementtypen til returverdien – men det kunne i prinsippet også bli brukt til å velge forskjellige listeimplementasjoner avhengig av elementtypen. F.eks:

```java
	<K, V> Map<K, V> map(Class<K> keyType) {
		// use TreeMap if the key type implements Comparable
		// (due to type erasure, there is unfortunately no way to check
		// if it implements Comparable<K>)
		if (Comparable.class.isAssignableFrom(keyType))
			return new TreeMap<>();
		else
			return new HashMap<>();
	}
```

(Hvis dette ikke er aktuelt, kan vi droppe `Class`-parameteret: `<T> List<T> list()` – Java skjønner at `list()` vil gi en liste med riktig elementtype.)

<details>
<summary><code>Class&lt;T&gt; – klasseobjekter</summary>
I Java er hver type representert som et objekt:

```java
Class<TypeNavn> TypeNavn.class;
```

så hvis vi gjør `listFactory.createList(Banana.class)`, så vil `T` være `Banana` og vi får en `List<Banana>`
</details>

Java lar oss ikke lage lambda-uttrykk for generiske metoder, så i praksis måtte vi gjort noe slikt som:

```java
public class MyCollectionsFactory implements ListFactory {

	public <T> List<T> list(Class<T> cls) {
		return new ArrayList<>();
	}
}
```

og brukt den slik: `new Controller(myCollectionsFactory)`.

#### 2.4 Enkle statiske fabrikker

I mange tilfeller vil vi være fornøyde med at alle deler av programmet bruker samme implementasjon av noe (så lenge vi har fleksibilitet til å være *hvilken* implementasjon) – `Logger` og `LoggerFactory` (over) er et godt eksempel på det. I enkleste tilfelle kan vi låse oss til en enkelt implementasjon. Vi har et par eksempler på det i Java:

* `Integer.valueOf()`, `Double.valueOf()` osv – her *kan* vi bruke f.eks. `new Integer(42)`, men `Integer.valueOf(42)` gir Java mulighet til å bruke samme objekt for de vanligste tallene. (F.eks., så er `Integer.valueOf(42) == Integer.valueOf(42)` men `new Integer(42) != new Integer(42)`)
* `List.of(…)` – Lager immuterbare lister. Java bruker samme objekt for alle tomme lister og alle lister som bare inneholder `null`, og har spesialimplementasjoner av liste med ett eller to elementer.

Selv om disse er låst til én måte å konstrukere objekter på, er de ikke nødvendigvis låst til å bruke én spesifikk klasse, slik vi ville vært hvis vi brukte `new` – `List.of(…)` bruker flere forskjellige listeklasser.

En OK og lettvint måte å gå fra å bruke klasser til å bruke interface er å først kjøre *Extract interface* på klassen, og så legge til noen statiske fabrikkmetoder i interfacet og så skjule klassen (gjøre konstruktøren `private` eller klassen ikke-`public`). For eksempel:

```java
public interface Point extends PositionVector {
    static final Point ZERO = point(0, 0);

    static Point point(double x, double y) {
        return x == 0 && y == 0 ? ZERO : new Point2Impl(x, y);
    }
```

#### 2.5 Fabrikk-klasser
I mer krevende tilfeller kan man lage en egen klasse med fabrikkmetoder:

```java
public class StaticCollectionsFactory {

	public static <T> List<T> list() {
		return new ArrayList<>();
	}

	public static <K, V> Map<K, V> map(Class<K> keyType) {
		// use TreeMap if the key type implements Comparable
		// (due to type erasure, there is unfortunately no way to check
		// if it implements Comparable<K>)
		if (Comparable.class.isAssignableFrom(keyType))
			return new TreeMap<>();
		else
			return new HashMap<>();
	}
}
```

Hvis vi også trenger muligheten til å ha flere fabrikker, men vil ha et global standardvalg, kan vi bruke [*Singleton pattern*](https://refactoring.guru/design-patterns/singleton):

```java
public class CollectionsFactory {
	// note: correct initialization of singletons can be tricky!
	protected static CollectionsFactory instance = new CollectionsFactory();

	public static CollectionsFactory getInstance() {
		return instance;
	}

	/**
	 * A private constructor prevents other classes from instantiating this class
	 */
	protected CollectionsFactory() {
	}

	public <T> List<T> list(Class<T> cls) {
		return new ArrayList<>();
	}

	public <K, V> Map<K, V> map(Class<K> keyType) {
		// use TreeMap if the key type implements Comparable
		// (due to type erasure, there is unfortunately no way to check
		// if it implements Comparable<K>)
		if (Comparable.class.isAssignableFrom(keyType))
			return new TreeMap<>();
		else
			return new HashMap<>();
	}
}
```

Eventuelle alternative implementasjoner kan lages som subklasser:

```java
/** A version that always uses HashMap */
public class AlternativeCollectionsFactory extends CollectionsFactory {

	public static void use() {
		CollectionsFactory.instance = new AlternativeCollectionsFactory();
	}

	/**
	 * A private constructor prevents other classes from instantiating this class
	 */
	private AlternativeCollectionsFactory() {
	}

	@Override
	public <K, V> Map<K, V> map(Class<K> keyType) {
		return new HashMap<>();
	}
}
```

eller vi kan gjøre tilsvarende vha. interface.

#### 2.5 FactoryFactoryFactoryFactoryFactory

![Turtles All The Way Down](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/PSM_V10_D562_The_hindoo_earth.jpg/320px-PSM_V10_D562_The_hindoo_earth.jpg)

