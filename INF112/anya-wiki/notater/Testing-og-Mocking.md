
## Eksempel på testing av komplisert interaksjon mellom objekter (fugler som flyr i flokk)

[Se eksempler med forklaring i `FlockingTest.java`](https://git.app.uib.no/inf112/25v/inf112.25v.lectures/-/blob/main/src/test/java/inf112/skeleton/app/FlockingTest.java?ref_type=heads) (fra 2025-03-24)

## Mockito

[Mockito](https://site.mockito.org/) er et rammeverk for [mocking](https://en.wikipedia.org/wiki/Mock_object) som gjør det enkelt å lage «fake» objekter til bruk i testing. 

Mocking er særlig aktuelt for å:

* erstatte deler av systemet som ikke kan kjøres mens man tester, eller som har oppførsel som fungerer dårlig med testing
* erstatte ting som ikke hører til enheten man tester, så man kan konsentrere seg om én enkelt enhet
* kunne observere hvordan koden man tester bruker andre objekter

Sistnevnte er kanskje det nyttigste grunnen til å bruke mocking. Førstnevnte er aktuelt f.eks. i forbindelse med grafikk og interaksjon med brukere eller eksterne systemer (over nettverk, f.eks.). Det kan også være aktuelt for ikke-deterministisk kode – f.eks. for å erstatte `Random` eller en klokke med faste verdier så testingen blir forutsigbar.

### Oppsett

Mockito er veldig lettvint å bruke. Legg til følgende i `<dependencies>` i `pom.xml` (se [dokumentasjonen](https://github.com/mockito/mockito/wiki/Declaring-mockito-dependency) for Gradle etc):

```xml
		<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>5.11.0</version>
			<scope>test</scope>
		</dependency>
```

Det er best å bruke `static` import:

```java
import static org.mockito.Mockito.*;
```

### Bruk

For å lage en «fake» implementasjon av en klasse eller et interface:

```java
List<Actor> actors = mock(List.class); // mock et grensesnitt
Gator gator = mock(Gator.class);       // mock en klasse
```

Metoden `mock` vil automatisk lage en klasse som av riktig type (dvs., en klasse `extends` / `implements` klassen / grensesnittet du oppga), med metoder som gjør ingenting annet enn å (eventuelt) returnere `null`, `0` eller `false`. F.eks., en falsk alligator:

```java
var gator = mock(Gator.class);
stage.addActor(gator);
stage.act(); // funker fint, kaller gator.act() (som gjør ingenting)
```

En falsk liste – alle metodene fungerer tilsynelatende, men de gjør egentlig ingenting:

```java
	@Test
	void testMockList() {
		// ekte liste
		List<String> realList = new ArrayList<>();
		realList.add("mango");
		assertEquals(1, realList.size()); // OK
		assertTrue(realList.contains("mango")); // OK


		// fake liste
		List mockedList = mock(List.class);
		mockedList.add("mango"); // fungerer tilsynelatende, men gjør ingenting
		assertEquals(1, mockedList.size()); // feiler, size() returnerer alltid 0
		assertTrue(mockedList.contains("mango")); // feiler, contains() returnerer alltid false
	}
```

### Få libGDX `Stage` til å funke

Med [`HeadlessApplication`](https://github.com/libgdx/libgdx/tree/master/backends/gdx-backend-headless/src/com/badlogic/gdx/backends/headless) går det an å bruke LibGDX uten å faktisk åpne et vindu og bruke grafikk, men en del ting fungerer ikke. F.eks., trenger `Stage` en `SpriteBatch`, og `SpriteBatch` bruker OpenGL-funksjoner som ikke er implementert i `HeadlessApplication`. Vi kan fikse dette med mocking:

```java
	static Stage makeStage() {
		Viewport viewport = mock(Viewport.class);
		viewport.setCamera(mock(Camera.class));
		return new Stage(viewport, mock(SpriteBatch.class));
	}
}
```

Dette gir oss en `Stage` som vi kan bruke sammen med `Actor`s for å teste *model*-delen av funksjonaliteten (*view*-delen er fremdeles avhengig av grafikksystemet). Dvs. det funker fint å bruke `stage.addActor()` og `stage.act()`, men `stage.draw()` vil komme til å feile.

### Legge til metodeimplementasjoner

Vi kan også be Mockito returnere spesifikke verdier når mockede metoder blir kalt. F.eks., over kunne vi gjort:

```java
		List mockedList = mock(List.class);
		mockedList.add("mango"); // fungerer tilsynelatende, men gjør ingenting

		// spesifiser oppførsel
		when(mockedList.size()).thenReturn(1);
		when(mockedList.contains("mango")).thenReturn(true);
		when(mockedList.contains("banana")).thenReturn(false);

		// sjekk oppførsel
		assertEquals(1, mockedList.size()); // ok, vi har programmert size() til å returnere 1
		assertTrue(mockedList.contains("mango")); // også ok
		assertFalse(mockedList.contains("banana")); // også ok siden vi sa at mockedList.contains("banana") == false
		assertFalse(mockedList.contains("apple")); // også ok siden false er default

```

Metoden `when()` merker seg hvilken metode som nettopp ble kalt (siden Java evaluerer argumentene først, så vil siste metoden som ble kalt før `when()` være metodekallet vi gjorde i argumentet) og lar deg velge hva som skal skje. Du kan oppgi en eller flere returverdier (for påfølgende kall) med `.thenReturn(…)`, eller kaste en exception med `.thenThrow(…)`, eller videresende til den ekte implementasjonen med `.thenCallRealMethod()` eller ordne din egen implementasjon med `.then(…)`/`.thenAnswer(…)`.

### Sjekke om metoder blir kalt

Det er lett å sjekke om en metode har blitt kalt:

```java
	@Test
	void testActorAct() {
		var stage = makeStage();
		var actor = mock(Actor.class);
		
		stage.addActor(actor);
		stage.act(0f);
		
		verify(actor).act(0f); // sjekk at act()-metoden ble kalt, med deltaTime=0
	}
```

Metoden `verify()` returnerer samme (mockede) objektet, og legger merke til hvilken metode du kaller og hvilke argumenter du bruker, og så sjekker den at den aktuelle metoden allerede har blitt kalt med de samme argumentene. For eksempel:

```java
var list = mock(List.class);
verify(list).add("mango");  // feiler, vi har ikke kalt add()

list.add("mango");
verify(list).add("mango");  // ok, vi *har* kalt add()
verify(list).add("banan");  // feiler, vi har kalt add(), men ikke med "banan"
```


## Hvordan funker det?
Mockito funker litt annerledes enn mange andre Java-biblioteker – det er et eksempel på et API som er designet for å gi en «naturlig» måte å beskrive oppførsel på.

Det er mulig å bli litt forvirret over hvordan man bruker Mockito (litt avhengig av hva slags mental modell du har av hvordan Java funker – f.eks., med `when(obj.doSomething(42)).thenReturn(7)` hvordan kan `thenReturn` «vite» hvilken metode jeg kalte?). Så, hvis du tror det hjelper, så gir koden under et veldig forenklet bilde på hvordan `when()` og `verify()` funker. Du kan se en mer komplett, fungerende versjon i [eksempelprosjektet](https://git.app.uib.no/inf112/25v/inf112.25v.lectures/-/blob/main/src/main/java/inf112/codeexamples/M%C3%B6ckit%C3%B6.java?ref_type=heads).

```java
public class Mockito {
	// vi noterer oss hvert metodekall ved å lagre navn og argumenter
	static final Set<List<Object>> calls = new HashSet<>();
	// oversikt over riktig svar for forskjellige metodekall
	static final Map<List<Object>, Object> anwers = new HashMap<>();
	// true hvis brukeren har kalt verify()
	static boolean verifyActive = false;
	// forrige metode som ble kalt (for when())
	static List<Object> lastMethodCalled = null;


	public static List<?> mock(Class<List> realClass) {
		// we can't really implement it this way; we need reflection to do this
		return new MockList<?>();
	}

	public static <T> T verify(T mock) {
		verifyActive = true;
		return mock;
	}


	public static <T> Stubbing<T> when(T t) {
		return new Stubbing<T>();
	}

	static class Stubbing<T> {
		public Stubbing<T> thenReturn(T value) {
			answers.put(lastMethodCalled, value);
			lastMethodCalled = null;
			return this;
		}
	}
}

public class MockList<T> implements List<T> {
	public T get(int i) {
		List<Object> callSignature = List.of("java.util.List", "add", i);
		if(Mockito.verifyActive) {
			assertTrue(Mockito.calls.contains(callSignature));
			Mockito.verifyActive = false;
		} else {
			Mockito.calls.add(callSignature);
			Mockito.lastMethodCalled = callSignature;
		}
		if(Mockito.returnValues.contains(callSignature))
			return Mockito.returnValues.get(callSignature);
		else
			return false;
	}
}
```
