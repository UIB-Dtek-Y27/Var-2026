# Notater fra kræsjkurs før eksamen våren 2024 (3. mai)

## Design patterns

Poenget med design patterns er å faste, gjenkjennelige løsninger på problemer som dukker opp ofte. Ved å kjenne til design patterns, er det gjerne lettere å kommunisere til andre utviklere hva intensjonen bak koden er – «alle» forstår at kode som er gjenkjenbart som f.eks. Abstract Factory, mens din egen «kreative» løsning krever kanskje mer å sette seg inn i.

Design patterns er ofte en løsning på noe som programmeringsspråket ikke har en god løsning for – så det som er en *pattern* i Java er gjerne ikke det i Haskell. De mest kjente mønstrene er for objekt-orientering, men det finnes mønstre for andre paradigmer (f.eks. for å lage objekter med metoder i funksjonelle programmeringsspråk).

Måten vi lærer og forstår programmering på er også til dels mønsterbasert – etterhvert som du får mer erfaring, så har du en oversikt i hodet over hvordan du løser vanlige problemer, f.eks. *telle til 100* løser du med `for(int i = 0; i < 100; i++)`. Vi har også *anti-patterns* – kodemønstre som man bør unngå (ting som man gjerne ville klassifisere som *code smell*). F.eks.: `for(int i = 0; i < list.size(); i++) …` er var tidligere en *pattern* for å iterere over en liste i Java, men nå er det en *anti-pattern* (det er som regel bedre bruke `for(var elem : list)` eller `list.stream()` i stedet).

Vi tittet på [denne oversikten](https://refactoring.guru/design-patterns), og nevnte noen eksempler:

* Creational
     * Factory, Abstract Factory – ble brukt i prosjektet
     * Builder – vist noen eksempler på forelesning, se mer under
     * Prototype – lage objekter ved å kopiere andre objekter; OO i JavaScript funker slik
     * Singleton – også vist på forelesning; klasse som man bare kan ha én instans (objekt) av – enkelte programmeringsspråk har innebygget mulighet for dette, i Java må man kjøre konstruktøren `private`
  * Structural
     * Adapter – f.eks. for å få et bibliotek til å funke med et annet (f.eks. oversette mellom LibGDX Color og Java AWT Color)
     * Facade – kunne f.eks. vært brukt med MVC for å gi *View*-delen tilgang til *Model*-objekter, uten å gi tilgang til irrelevante detaljer; [dette er et eksempel på en slik fasade](https://git.app.uib.no/inf112/24v/libgdx-example/-/blob/main/src/main/java/inf112/variant2/view/Viewable.java?ref_type=heads)
     * Flyweight – ingen har noen gang skjønt hva dette er basert på navnet
  * Behavioural
     * Chain of Responsibility – let  gjennom flere objekter inntil vi finner det som best kan håndtere noe; f.eks. ha en kjede av event handlers som håndtere forskjellige ting (events i nettleseren funker slik)
     * Command – f.eks. binde forskjellige taster til forskjellig funksjonalitet
     * Iterator – har vi brukt masse, også i INF101
     * Observer – *listener*, *event handler* – kan brukes til mer enn bare input-håndtering
     * Strategy – velge forskjellig implementasjoner av noe; f.eks. power-ups med forskjellig funksjonalitet (se også 2023V-3c og 2023H-3z under)
     * Visitor – såvidt nevnt under (2023V-3c)


### Builder

Poenget med builder er å gjøre API-et lettere å bruke, særlig når man skal lage objekter som har komplisert tilstand, eller man trenger å velge riktig klasse på en smart måte. F.eks:

* Unngå lange parameterlister for konstruktøren
* Lettere å ha noen valgfrie valg (i andre språk – Python, f.eks. – kan man gjøre dette med keyword-argumenter)
* Ha en sjekk på at feltene er lovlige/konsistente (kan også gjøres i konstruktøren)
* Lett å gjøre objektet immutable etter at det er laget
* Lett for programmøren å utforske i IDE¹ – trykk `.`, få opp liste over hvilke innstillinger du kan velge

¹ *IDE = Integrert Utviklingsmiljø, Integrated Development Environment; f.eks. Eclipse, IntelliJ, Visual Studio, NetBeans, Android Studio, XCode. Forskjellig fra teksteditor (VSCode, Emacs, Vim, SublimeText, …) ved at det er masse integrerte verktøy som gjør det lett å utvikle store prosjekter (automatisk refaktorering, avansert navigering, versjonskontroll, debugging, osv). Grensen er flytende, og "teksteditor" har ofte mange IDE-features. F.eks. VSCode bruker Eclipse sin Java-backend, så kan gjøre mye av det samme, men mangler en del av funksjonaliteten.*

#### Typisk builder:

```java
Animal animal = AnimalBuilder.builder().weight(4.5).name("Mons").fur("brown").build();

// eller, uten "fluent" kode-stil:
AnimalBuilder ab = new AnimalBuilder();
ab.setWeight(4.5);
ab.setName("Mons");
ab.setFur("brown");
Animal animal = ab.build();
```

#### StringBuilder:

Denne er innebygget i Java, og er der for å gjøre det mer effektivt å bygge opp store strenger. Den er nødvendig fordi `String` er immutable, så `s = s + …` kopierer hele strengen for hvert steg – StringBuilder gjør at vi  effektivt kan legge til ting i bygge-fasen, og likevel få et immutable `String`-objekt til slutt.
```java
// 
StringBuilder sb = new StringBuilder().append("{");
// Hvis vi gjøre  "s = s + item.toString()" vil vi kopiere hele "s" for
// hvert element, O(n²) – StringBuilder samler opp alle delstrengene
// og gjør jobben i O(n) tid. 
for(var item : list)
    sb.append(item.toString()).append("\n");
return sb.append("}").toString();
```

#### Bygge objekter med forskjellige klasser
Det kan være vi ønsker å bruke forskjellige klasser avhengig av valgene vi har tatt. Med Builder, så kan builderen velge det som passer best, men hvis vi brukte vanlig konstruktør ville vi måtte velge en konkret klasse:
```java
List<String> list = new ArrayList<>(); // ← vi er låst til ArrayList
```

vs

```java
// Hypotetisk builder som velger passende liste-implementasjon basert
// på hvordan vi har tenkt å bruke listen
List<String> list = Collections.createList().indexValues().appendOnly().readOften().build();
```

#### Bygge objekter med forskjellige typer
I eksemplet over får vi objekter som varierer i tilstand (feltvariabler) og klasse, men returtypen er den samme, så vi kan f.eks. ikke lage `List` og `Set` eller `Mammal` og `Bird` fra samme builder. Men, det går også an å lage flere forskjellige builder-varianter og bytte til den som passer best underveis:

```java
List<String> list = Collections.create()  // ← CollectionBuilder
        .readOften() // ← fremdeles CollectionBuilder
        .sequence() // ← returnerer ListBuilder
        .from(10).to(200) // ← ListBuilder, første index er 10, siste er 200
        .build();  // returnerer List<>

// eller bruk generics til å holde rede på typen
Bird b = AnimalBuilder.create()  // ← AnimalBuilder<Animal>
        .name("Tweety")  // ← AnimalBuilder<Animal>
        .colour("yellow")  // ← fremdeles AnimalBuilder<Animal>
        .flySpeed(50)  // ← AnimalBuilder<Bird>
        .weight(0.015)  // ← fremdeles AnimalBuilder<Bird>
        .build();
```


## Eksamen høsten 2023

### 2023V-2a

Spill må testes på måte som annen programvare – Viktige former (**uthevet** er kanskje ekstra viktig) for testing er f.eks.:

* **Unit testing** – teste en klasse uavhengig av resten av systemet
* Integreringstester – teste at ting funker sammen med andre komponenter, f.eks. libgdx, box2d, … – eller sammen med andre applikasjoner, database, server; *laveste nivå av dette vil være liknende til enhetstester, dvs. kall noen metoder og se hva som skjer, men målet er å teste på tvers av komponenter*
* Systemtester: – teste hele systemet med alle komponenter
   *  **kjør spillet**, se at det funker
   *  brukergrensesnitt – trykke på alle knappene, se hva som skjer
   *  brukeopplevelse – se at mennesker kan bruke det – krever gjerne lab med «prøvekaniner» *(vi kan ha dårlig brukeropplevelse (UX) med et brukergrensesnitt (UI) som teknisk sett funker, og forsåvidt også ha ok brukeropplevelse selv om det er feil i brukergrensesnittet)*
   *  **playtesting** – la folk spille spillet og se om det er gøy, ikke for lett, ikke for vanskelig, osv. *(også forskjellig fra UX/UI-testing, mange gode spill har dårlig brukergrensesnitt)*
   * **akseptansetesting**
  


### 2023V-2b

Mulige løsninger:

* Kjøre HeadlessApplication og mocke nødvendige deler av grafikksystemet
* Mocke *hele* LibGDX
* Skille modell fra view, så vi ikke trenger grafikk for å bruke Colonist-klassen
* Evt. lage spesialtilfelle for testing, hvor Texture/grafikkting er null, og man hopper over grafikk-koden

*(I akkurat dette tilfellet, så hører stifinningensalgoritmen kanskje ikke egnetlig til i Colonist-klassen, så vi kan gjerne flytte den til en egen klasse og teste den uten å opprette HumanColonist)*


### 2023V-2z

* Med mange valgmuligheter får man mange kombinasjoner, som vil være umulig å teste
* Hvis vi er flinke til å følge SOLID-prinsippene, så er hver del uavhengig nok til at det burde gå fint å erstatte en type stifinner med en annen – men det gjør det bare mer sannsynlig at programmet funker, det fjerner ikke problemet med et eksploderende antall testekombinasjoner


### 2023V-3a

[Her er PathFinder-interfacet](../eksamen-23v/PathFinder.java).

Å gjøre det slik gjør det enkelt å ha mange innstillinger på stifinningen, uten å ha mange paramtere til stifinner-metoden. F.eks.:

```java
var path = PathFinder.finder(map).from(pos1).to(pos2).avoid(pos3);
```

vs

```java
var path = PathFinder.findPath(map, pos1, pos2, /* via */ null, pos3, PathFinder.SHORTEST);
```

Særlig aktuelt når ikke alle parametrene alltid er nødvendige.

(Dvs. likndende til *Builder*-pattern; teknikken med metode-kjeding er *fluent interface*)


### 2023V-3b

[PathFinderImpl.java](../eksamen-23v/PathFinderImpl.java), [MapCell.java](../eksamen-23v/MapCell.java)

* Problemet er at vi blander inn Pawn-oppførsel med stifinningsalgoritmen. 

* Bryter Single Responsibiliity principle, Open/Closed (gjør det vanskelig å utvide med flere varianter)

* Se [løsningsforslag](eksamen-23v/eksamen-inf112-23v_løsning) for flere punkter

* Vi har også en klassisk *code smell*: `if(pawn instanceof Bird)` → vanligvis bør vi velge hvilken variant av oppførselen vi vil ha ved å ha forskjellige metodeimplementasjoner i forskjellige klasser, ikke ved å spre valgene rundt i resten av koden og teste «er dette Bird eller Fish?»* osv. *(Et unntak er i dynamisk typede språk som JavaScript og Python, når du aksepterer argumenter av flere typer og må skille mellom dem (statisk typede språk løser dette med overlasting som velger riktig automatisk) – da må du av og til gjøre `if type(arg) == int: … elif type(arg) == str: …`)*


### 2023V-3c

En mulig løsning er å ha forskjellige `Movement`-objekter som hver (kanskje) vet noe om kostanden med terreng, vi kan sjekke alle og velge den beste eller første. *([Chain of Responsibility](https://refactoring.guru/design-patterns/chain-of-responsibility))*

For eksempel: 

* `Duck` har `SwimMovement`, `FlyMovement`, `WalkMovement`
* `HumanColonist` har `WalkMovement`, men kan legge til `RolleskateMovement`
* `FlyMovement.movementCost()` returner 1 uansett terreng, `SwimMovement.movementCost()` returner 1 når terrenget er `Water`,  `RolleskateMovement.movementCost()` returnerer 0.5 når terrenge er `Paved`

Det finnes en design-pattern for å velge riktig implementasjon basert på klassen til *to objekter* (Terrain og Pawn i dette tilfellet) – [Visitor pattern](https://en.wikipedia.org/wiki/Visitor_pattern) – men det er neppe en så veldig bra løsning her; da ville vi endt opp med å fylle Pawn med `movementCostForDirt()`, `movementCostForPaved()`, `movementCostForWater()` osv.


### 2023V-3z

* Problemet er mutable *key* i `HashMap` – hvis den endrer seg vil vi ikke finne den igjen (e.g., opprinnelig nøkkel hadde `hashCode()` 42, så vi lagret verdien i `data[42]`, men etter endringen er hash-koden 33, så da leter vi på feil sted). Det samme vil være et problem med `TreeMap` og andre varianter.

Det beste er kanskje å lage en egen GameMap, med 2D-funksjonalitet og andre nyttige ting for spillet.


## Eksamen høsten 2023

### 2023H-2a

> Med utgangspunkt i semesteroppgaven, gi et motiverende eksempel på bruk av SOLID. Tenk gjennom
> designavgjørelsene du og teamet ditt tok mens dere jobbet med semesteroppgaven, og finn en situasjon der
> dere enten benyttet SOLID-prinsippene, eller hvor du i ettertid tenker at dere burde ha gjort det. Forklar
> designvalget dere tok, hvilke(t) SOLID-prinsipp du mener er relevant, og hvorfor/hvordan det fører til
> bedre kodekvalitet (e.g., mer forståelig, fleksibel eller vedlikeholdbar kode)

*F.eks.:*

* Vi ble bedre på Open/Closed ved å dele opp Entity-funksjonaliteten i flere forskjellige spesialiseringer (MovingEntity, AttackingEntity osv)

* F.eks. hvis vi skal legge til AttackingEntity, så kan gjøre det ved å utvide Entity i stedet for å gå inn og legge til den funksjonaliten direkte i Entity. → Open/Closed, vi kan utvide uten å endre opprinnelig kode.

Dette er også bedre mtp:

* Single responsibility → vi putter ting der det hører mest hjemme

* Interface Segregation → Entity trenger ikke ha move(), attack() osv, bare de som er interessert trenger å implementere/kjenne til dette

### 2023H-3a

> I begge tilfeller er det snakk om å kunne «legge til [ting] uten å endre [visse deler av] koden ...». Hva tror du er vitsen med å kreve det? Kan det føre til bedre eller dårligere design og kodekvalitet? Forklar.

Hvis du gjør endringer mange steder, så er det mer som kan gå galt, mer som må testes, mer omfattende endringer. Kanskje man misforstår en bit av programmet man ikke kjenner så godt.

Kan bli vanskeligere fordi det f.eks. ikke er opplagt i Spiller-klassen hvordan klær, osv er implementert. Men generelt blir det *bedre*, ikke verre.


### 2023H-3b

> Alt.2: Hvis du mener løsningen dere fant kan forbedres: Forklar designet dere kom frem til, og forklar
> løsningen som du mener vil være bedre. Forklar tankegangen din og hvorfor du mener din alternative
> løsning er bedre.

Vi laget en abstrakt GameObject, alt arvet fra den, vi registrerte hver impementsajon med ObjectFactory.register("P", makePlayer).

Det fungerte greit, men vi måtte lage mange klasser som bare var forskjellige i symbolet brutk i factory og bildet for tegning på skjerm. Var lite oversiktlig, mer stress enn nødvendig å legge til flere typer ting.

Hadde vært bedre å kunne gjenbruke samme klassen mange ganger, slik at forskjellen lå i parameteret når vi kalte factory. Feks:

factory.create("P(goblin")) // eller create("P", "goblin")

### 2023H-3z

[Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory) og [Strategy](https://refactoring.guru/design-patterns/strategy)



### 2023H-4a

> Hva er de mest aktuelle rollene for et slikt eksamenssystem? For de tre viktigste (eller tre av de viktigste)
> rollene, gi en kort beskrivelse av en typisk person i den rollen (en «persona»).

* Elev – er i slutten av tenårene, er kanskje veldig nervøs, er vant med denne typen systemer, men ikke vant med å ta eksamen.
* Sensor – lærer, har kanskje ikke brukt systemet før 
* Den som lager oppgaven – …
* De som administrerer
* Eksamensvakter
* Supportpersonell

### 2023H-4b

> Lag en brukerhistorie for hver av de tre viktigste rollene. Gi et eksempel på et akseptansekriterium for hver av
> brukerhistoriene.

* Som elev vil jeg har oversikt over hvor mye jeg har gjort og hvor langt jeg har kommet, slik at jeg kan disponere tiden min bra

* Som sensor vil jeg at knapper for poeng og gå videre er i nærheten slik at jeg ikke sliter ut håndleddet på å sensurere. Kriterium: muspeker trenger ikke flytte seg mer enn 5% av skjermen mellom hver oppgave
    * Dette er gjerne en *dårlig brukerhistorie*, siden vi spesifiserer løsningen vi vil ha. Heldigvis følger det med *slik at jeg*, så vi skjønner hva sensor egentlig er ute etter, og kan løse det ved f.eks. å gjøre det mulig automatisk gå videre til neste oppgave
* Som oppgaveforfatter på eksamen i IT, så vil kunne vise kode i oppgaveteksen, slik at jeg kan lage programmeringsoppgaver.

### 2023H-4c

> Nevn fem ting som du mener bør inngå i minimum viable product (MVP) for eksamenssystemet.

* Innloggingssystem som tåler alt alle elevene tar eksamen  *(kanskje litt passiv-aggressivt...)*
* tekstoppgave, avkryssing, …
* grensesnitt for student, forfatter og sensor
* innlogging
* sikker lagring av besvarelsene


