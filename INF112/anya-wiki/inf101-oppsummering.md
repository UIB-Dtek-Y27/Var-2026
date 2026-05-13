<!--
Se også:
* **[Tidligere eksamensoppgaver](eksamensoppgaver)**
* **[Hvordan stille laboppgavene tilbake til opprinnelig tilstand så du kan gjøre dem en gang til](lab-branch)**
* **[Pensumoversikt](pensumoversikt)**
-->

# Objekter, modellering og abstraksjon


## Modellering
En *modell* er en forenklet, idealisert representasjon av noe, som
brukes til å f.eks. beskrive noe, eksperimentere med noe eller forutse
hva som vil skje i forskjellige scenarier. For eksempel:

* en klimamodell lar oss forstå hvordan klimaet kan utvikle seg i
  forskjellige scenarier uten at vi trenger å teste det i
  virkeligheten;
* en prototype lar deg se hvordan at produkt kan bli;
* en (super)modell lar deg se hvordan klær ser ut uten å måtte prøve
  dem på deg selv;
* mus og rotter er modeller for mennesker i medisinsk forskning;
* et dataspill lar deg gjøre ting du ikke kunne gjort (eller hatt lov
  til) i virkeligheten;
* lek og leker lar barn bygge “romskip” og utføre medisinsk behandling
  på “spedbarn” uten å svi av plenen eller skade lillebror.
* kilogramprototypen, arkitekttegninger, 3D designmodeller (CAD/CAM)
  osv. er beskrivende eksempler eller spesifikasjoner som brukes når
  man skal lage / bygge noe.

Programvare er ofte modeller av virkelige eller tenkte systemer og prosesser, f.eks.:

* klimamodeller er typisk matematiske modeller implementert som programvare
* [Lab 1]() – modell av baller som spretter opp fra bakken (og evt. eksploderer)
* [Lab 2]() – modell av veksten til encellede organismer
* [Lab 3]() – (veldig rar) modell av hvordan utregning kan skje
* [Sem 1]() – modell av et gammelt dataspill som er en modell av huleutforskning og rollespill (som er en modell av virkeligheten i en oppdiktet verden)
* [Sem 2]() – modell av et vanlig spill

Siden programvare i seg selv kan være veldig komplisert, er det også
vanlig å lage forenklede modeller av programvare (f.eks. i form av
diagrammer og prototyper). [Her er f.eks. en modell av de viktigste
grensesnittene i Sem
1](https://retting.ii.uib.no/inf101/inf101.v18/wikis/img/RogueInterface.png). Dette
kan gjøre det lettere å forstå hvordan systemet er bygget opp, eller å
prøve ut om det vil virke etter hensikten.

## Abstraksjon
*Abstraksjon* er å se bort fra uvesentlige detaljer, og fokusere på
det som er vesentlig / essensielt. F.eks. er en modell en abstrakt
representasjon av noe – den vil typisk bare ha detaljene vi er
interesserte i og som er nødvendige for at den skal fungere etter
hensikten.

For eksempel, i [Lab 1]() er en *Ball* noe som har farge, radius,
posisjon og hastighet. Vi har sett bort fra mange detaljer som baller
har i virkeligheten, slik som masse/tetthet, elastisitet, lukt,
temperatur, materiale, osv; selv om noen av disse detaljene ville vært
nyttige for å få en mer presis simulering. Jo flere detaljer vi har
med, jo mer komplisert blir modellen og programvaren. Lab 1 er rimelig
enkel; hvis vi tok hensyn til masse, luftmotstand, deforming av ballen
når den treffer bakken osv., så blir programmet en god del mer
komplisert; og hvis vi ville simulere alle molekylene (eller atomene
eller kvarkene) i ballen, ville problemet gjerne vært utenfor
rekkevidde.

### Forskjellige typer abstraksjon
* **Abstraksjon ved parametrisering:** vi trekker noen av de konkrete
  detaljene ut og gjør de til *parametre*, dvs. at vi fyller dem inn
  senere. F.eks. `int four() { return 2*2; }` kan gjøres mer abstrakt
  ved å parametrisere den med hvilket tall vi ønsker å gange med to:
  `int twice(int x) { return x*2; }`; hvis vi lager en liste-datatype
  vil vi gjerne abstrahere over hva slags elementer vi kan lagre i
  den, slik at vi gjerne lager `class List<T>` i stedet for
  `class IntList`.
* **Abstraksjon ved spesifikasjon:** vi abstraherer vekk detaljene om
  *hvordan* noe blir gjort, ved å heller beskrive presist *hva* som
  blir gjort / hva resultatet er. Vi kan f.eks. si at `Math.sqrt(x)`
  er en metode som er slik at `Math.sqrt(x)*Math.sqrt(x) == x`, uten å
  låse oss til en spesifikk teknikk for å regne ut kvadratrøtter. Alle de
  som *bruker* `Math.sqrt()` er interessert i *hva* den gjør, men bare
  den som implementerer den er nødt vil å vite noe om hvordan den
  funker.
* **Kontrollabstraksjon:** å abstrahere over hvilke konkrekte
  operasjoner datamaskinen skal utføre. Nå du lager en metode, lager
  du en kontrollabstraksjon – et nytt, enkelt og forhåpentligvis
  beskrivende navn på en serie med ting som kanskje ellers ville vært
  komplisert å beskrive. Java har også innebygde
  kontrollabstraksjoner; du kan f.eks. bruke `if`-setninger uten å
  vite noe om hvordan de er implementert (ganske annerledes enn de ser
  ut til!).
* **Dataabstraksjon:** å abstrahere over hvordan data er representert,
  slik at du heller forholder deg til hva du kan gjøre med dataene –
  f.eks. at du kan `add()` noe til en liste eller `move()` en and,
  uten at du vet hvordan listen eller anden er lagret. Når du lager
  nye klasser og grensesnitt i Java, lager du nye dataabstraksjoner; i
  tillegg følger det med en hel haug av dem; `ArrayList` for eksempel
  (en standard klasse). De innebygde primitive typene, slik som `int`
  er forsåvidt i praksis også abstrakte; du bruker dem uten å kjenne
  til hvordan de er implementerte (selv om datarepresentasjonen i
  prinsippet er spesifisert i standarden).

## Objekter og objekt-orientering
Vi bruker *objekter* til å modellere ting på datamaskinen. Objekter
passer veldig bra for å representere fysiske ting, slik som ballene i
Lab 1 eller endene og froskene på forelesningene. I mange tilfeller
fungerer det også bra å bruke objekter til å representere mer
abstrakte konsepter, som “posisjon”, “retning”, “bankkonto” eller
“tastetrykk-hendelse”.

* Objekter har *tilstand* og *oppførsel*. I Java bruker vi
  feltvariabler for å lagre tilstanden og metoder for å implementere
  oppførselen.
    * Det vil si at et objekt består av en mengde data (i form av
      feltvariabler), *sammen* med programkoden som skal til for å
      tolke, bruke og evt. oppdatere dataene.
    * For eksempel, for ballene i Lab 1 består tilstanden av hastighet,
      radius, posisjon og farge; og oppførselen har metoder som lar oss
      observere tilstanden og endre tilstanden, f.eks. ved at
      posisjonen endrer seg i henhold til hastighet for hvert tidssteg.
* Objektene er *innkapslet*. Det betyr at detaljene rundt tilstand og
  oppførsel er skjult bak et *grensesnitt av metoder*; vi har
  *abstrahert vekk* hvilke feltvariabler vi har, hvilke verdier som er
  lagret i dem, og hvordan koden i metodene ser ut.
    * Så lenge tilstanden / dataene skal være skjult, må objektene
      komme med alle metodene som trengs for å observere og manipulere
      tilstanden – det er tross alt ingen andre deler av programmet som
      vet hvilke feltvariabler objektet har og hvordan de skal tolkes.
    * Vi vet hvilke metoder vi kan kalle, dvs. vi vet navn,
      parameterliste, returtype, dokumentasjon; men implementasjonen er
      skjult. (Med “skjult mener vi het at andre klasser/deler av
      systemet skal være laget uavhengig av disse detaljene, selv om
      ingen ting i prinsippet hindrer oss i åpne programkoden i en
      editor og lære alt vi vil om implementasjonsdetaljene.)

Denne formen for abstraksjon kalles *dataabstraksjon*:
datarepresentasjonen er innkapslet / skjult, og man forholder seg i
stedet for til et veldefinert sett av metoder – et *grensesnitt*. Man
kaller gjerne grensesnittet for en *abstrakt datatype* (ADT).

Det går også an å ha data som ikke er innkapslet, det er vanlig i en
del andre språk som f.eks. C. I Java kan du gjøre det samme ved å la
feltvariablene være `public`, da kan andre deler av programmet bruke
dem direkte.

## Dataabstraksjon og abstrakte datatyper (ADT)
**Hva er det**: Data lagres innkapslet i objekter, hvor all tilgang skjer gjennom et veldefinert sett av metoder. Brukeren forholder seg bare til metoder (m/dokumentasjon) og trenger ikke kjenne til uvesentlige detaljer slik som hvordan data er lagret eller hvilke instruksjoner datamaskinen utfører.

I Java:

* `class` / klasse – definerer og implementerer en abstrakt datatype
    * “Oppskrift” på å lage objekter.
    * Beskriver hvordan data representeres i objekter med tilhørende
      metoder for å håndtere data.
    * Alle detaljer om datarepresentasjon og hvordan denne behandles er skjult inne i klassen.
    * `private` feltvariabler gjør at de ikke kan sees utenfor klassen;
      Java lar oss også lage objekter som ikke er innkapslet,
      f.eks. med `public` feltvariabler.
* [`interface`](http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html) / grensesnitt – en abstrakt definisjon av en ADT
    * “oppskrift” på å lage klasser – sier hvilke metoder som må
      implementeres
    * definerer også en *type*, som sier hvordan objekter kan brukes
      (hvilke metoder man kan kalle, f.eks.)
    * inneholder ikke kode eller feltvariabler, men dokumentasjon og
      tester brukes for å spesifisere oppførselen til
      metoder. (Unntaket er `default` metoder i Java 8+.)
* [`abstract
  class`](http://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)
  / abstrakt klasse – en delvis implementasjon; arves av en konkret klasse for å gi noe man kan lage objekter av
*    Innkapsling av datarepresentasjon
*    Implementasjon: datastruktur, datainvariant, ekvivalens/kongruens


# Typer, variabler og referanser
## Uttrykk og verdier
Når man utvikler programvare, bruker man gjerne mye tid på å finne og
lage gode abstraksjoner – klasser, grensesnitt, metoder, osv. Dette
utgjør ofte forskjellen på et prosjekt som er umulig å gjennomføre
(eller vedlikeholde), og et prosjekt hvor utvikling og vedlikehold er
håndterbart. Men når en applikasjon faktisk kjører, så gjør
datamaskinen det den er flinkest på (forsåvidt det *eneste* den kan!):
gjøre utregninger og flytte rundt på data.

I Java beskriver vi utregninger på to måter, som *setninger*/*utsagn*
(*statements*) og som *uttrykk* (*expressions*). Uttrykk (f.eks. `a +
5` eller `x.toString()`) brukes til å regne ut *verdier*, for eksempel
ved regneoperasjon eller metodekall, mens utsagn (f.eks. `x.m1();
x.m2();` eller `if(a > 0) x.m1();`) brukes til å bestemme hvilke
uttrykk som skal regnes ut og i hvilken rekkefølge. Verdiene kan
lagres i *variabler*, f.eks. feltvariabler, array-indekser, lokale
variabler eller metodeparametre.

## Typesystemet

Java er et *statisk typet språk*, og bruker et typesystem for å holde
rede på nok informasjon om hva uttrykkene dine vil regne ut, til å
kunne forhindre en hel haug med mulige feil. Dette fungerer ca. slik:

* Alle uttrykk i programmet har en *type*. F.eks. er typen til `"foo"`
  `String`, typen til `5` er `int`, og typen til `new Duck()` er `Duck`.
* Typen forteller hvilke metoder og operatorer man har lov til å
  kalle/bruke. F.eks. hvis man har en `String`, kan man bruke
  `+`-operatoren (`"foo" + "bar"`) og kalle en del forsjellige metoder
  (`"foo".length()`). Hvis man har en `int` kan man ikke kalle
  metoder, men man kan bruker operatorer som `+`, `*`, `%` osv. Java
  finner ut av og melder fra om dette når programmet kompileres,
  heller enn at det oppstår en feil mens programmet kjører.
* Metodene/operatorene forteller hva slags returtype de har,
  f.eks. vet vi at typen til `"foo".length()` vil være `int`, og typen
  til `1 + 2` også vil være `int`.
* Hvis vi vil lagre noe i en variabel, må typene være
  kompatible. Typen til variabelen må være den samme som eller en
  supertype til typen til uttrykket. Dvs., vi kan lagre `"foo"` i en
  variabel av typen `String` eller `Object` (en supertype av
  `String`), men ikke i en variabel av typen `Duck`.
* Når man kaller en metode, må parametertypene i deklarasjonen (de
  *formelle parametrene*) stemme overens med verdiene man gir som
  argumenter (de *aktuelle parameterne*), på samme måte som med
  variabler. Java lar deg også velge blant flere *overlastede* metoder med samme
  navn ved hjelp av argumenttypen; 
* De to siste reglene gjør at vi kan bruke variabler på en trygg
  måte. Hvis vi har en variabel `Duck d`, og senere gjør et metodekall
  `d.quack()`, så vil Java avgjøre om det er lovlig basert på om
  `Duck` har en `quack()`-metode. For å kunne garantere at dette går
  bra, må Java sørge for at `d` aldri kan inneholde noe som ikke er en
  `Duck`.

## Typer
Java har to former for typer:

* **Primitive typer:** disse er *boolean*,
  *byte*, *short*, *int*, *long*, *float*, *double*,
	og  *char*. De primitive typene er innebygde, og du kan ikke lage
  flere av dem. De har ikke metoder, men du kan bruke forskjellige
  operatorer (f.eks. `!` på `boolean` og  `+` på tall-typene).
* **Referansetyper:** dette er alle andre typer, f.eks. `String`
  (*referanse* til et `String`-objekt), `int[]` (*referanse* en array
  av `int`), `String[]` (*referanse* til en array av *referanser* til
  `String`-objekter). Du kan lage nye referansetyper med `class`,
  `interface` eller `enum`. Alle referansetyper støtter en del metoder
  (`equals`, `hashCode`, `toString` og en del andre som er definert i
  `Object`-klassen), og noen operatorer (f.eks. `==` og `!=` for å
  sammenlikne referanser; i tillegg har `String` en `+`-operator –
  dette er et spesialtilfelle).
  
Verdiene til en referansetype, det vil si det du kan lagre i en
variabel av en referansetype, er ikke objekter i seg selv, men
*referanser* som *peker på* objekter. Det vil si at du aldri egentlig
lagrer objekter i variabler (eller parametre, eller feltvariabler);
objektene lever i minnet på datamaskinen (“heap”-en), og variablene
dine peker på dem. Flere variabler kan peke på samme objekt.

For eksempel:
```java
Duck d = new Duck();
Duck e = d; // kopierer referansen inn i e

assertTrue(d == e);  // refererer til samme objekt
d.setName("Dolly");  // setter navnet til både d og e
e.setName("Donald"); // setter navnet til både d og e
assertTrue(d.getName() == e.getName()); // samme objekt, samme navn
assertEquals("Donald", d.getName());
```
For primitive verdier er det annerledes:
```java
int a = 5;
int b = a; // kopierer tallet 5 inn i b

a--; // endrer bare a
b++; // endrer bare b
assertEquals(4, a);
assertEquals(6, b);
```

## Typer vs Klasser
* *Typen bestemmer hvilke metoder du har lov til å kalle, og hvilke
  variabler du kan lagre ting i.* Dette
  avgjøres av Java-kompilatoren, som regner ut typen til alle
  uttrykkene i programmet ditt, og sjekker at alle metodene du prøver
  å kalle faktisk har blitt deklarert. Dette gir en garanti for at
  metoden vil komme til å finnes når programmet kjører, selv om vi
  ikke nødvendigvis vet nøyaktig hvilken implementasjon som vil bli brukt.
* *Klassen bestemmer hvilken kode / metodeimplementasjon som blir kjørt
  når programmet kjører.* Som nevnt tidligere består objekter av
  tilstand/data (feltvariabler) og oppførsel/kode (metoder som kan
  bruke og endre dataene); i Java er det klassen som bestemmer hvilke
  feltvariabler og metodeimplementasjoner objektene har.
* Alle klasser er også typer; metodene du har lov til å kalle er
  metodene som er deklarert i klassen.

Eksempel:
```java
interface IPondObject {
  void step();
}
class Duck implements IPondObject {
  void step() { ... }
  void quack() { ... }
}
class Duckling extends Duck {
  void step() { ... }
}
class Frog implements IPondObject {
  void step() { ... }
}
...

Duck d = new Duck();
IPondObject po1 = d; // ok, Duck implements IPondObject
IPondObject po2 = new Frog(); // ok, Frog implements IPondObject
IPondObject po3 = new Duckling(); // ok, Duckling extends Duck som implements IPondObject

po1.step();  // ok, fordi IPondObject.step() finnes, kaller Duck.step()
po2.step();  // ok, fordi IPondObject.step() finnes, kaller Frog.step()
po3.step();  // ok, fordi IPondObject.step() finnes, kaller Duckling.step()
d.step();    // ok, fordi Duck.step() finnes, kaller Duck.step()

po1.quack(); // FEIL, fordi IPondObject.quack() mangler, selv om vi kunne kalt Duck.step()
d.quack();   // ok, fordi Duck.quack() finnes, kaller Duck.quack()
d = po3;     // ok, fordi Duckling extends Duck
d.quack();   // ok, fordi Duck.quack() finnes, kaller Duck.quack()
             // fordi Duckling ikke har egen quack()

Frog f = po2; // FEIL, Java vet ikke at po2 inneholder en frosk
Frog f = (Frog)po2; // ok, vi lover at po2 er en frosk
Frog f = (Frog)po1; // ok, feiler når programmet kjører; vi har lovet at
                    // po1 er en frosk, men vi lyver
```
Legg spesielt merke til disse to metodekallene: `po1.step()` og
`po2.step()` – de ser relativt like ut, både `po1` og `po2` er av
typen `IPondObject`. Men i koden over refererer `po1` til et
`Duck`-objekt og `po2` til et `Frog`-objekt. Dvs. at når koden kjører,
så er det *forskjellige metodeimplementasjoner* som blir brukt, den
ene med andeoppførsel og den andre med froskeoppførsel. 

At vi har forskjellige implementasjoner av “samme” metode kalles
gjerne *polymorfisme*. Mekanismen som brukes for å finne riktig
implementasjon (ved å kikke på objektet og hvilken klasse det
tilhører) kalles *dynamic dispatch* (alternativet er å avgjøre hvilken
kode som skal kalles under kompileringen, bare basert på typen og uten
å vite hvilket objekt vi vil ha med å gjøre mens programmet kjører).


<!--
# Innhold
(Denne oversikten er basert på forelesningene fra 2016 – så koden lenker dit.)

* [Grensesnitt](#grensesnitt-interface)
  * [Sammenlikning](#sammenlikning-comparable-grensesnittet)
* [Generiske typer](#generiske-typer)
* [Iteratorer](#iteratorer)
* [Generatorer og egenskapstester](#generatorer-og-egenskapstester)
* [Muterbare og ikke-muterbare objekter](#muterbare-og-ikke-muterbare-objekter-hvordan-beskytte-innkapsling)
* [Arv](#arv)
* [Forkrav, datainvariant og substitusjonsprinsippet](#forkrav-datainvariant-og-substitusjonsprinsippet)
* [Unntakshåndtering](#unntakshåndtering)
* [Punktvis oppsummering av pensum](#oversikt)
-->
# Grensesnitt / Interface

## Deklarasjon og bruk av *interfaces*

* Nye interfaces lages på samme måte som man lager nye klasser (i en fil ```INavn.java``` – i Eclipse, bruk *File → New → Interface*):

    ```
    interface INavn {
      void metode1(int x);
      int metode2(String s);
    }
    ```

* Et interface inneholder kun metoderdeklarasjoner, ikke feltvariable og ikke metodeimplementasjoner.
* Du kan lage et nytt interface fra en eksisterende klasse med *Refactor → Extract interface...*. Da kan du også få Eclipse til å automatisk bruke interfacet i alle variabledeklarasjoner der det er mulig.
* Et interface definerer en ny type, på samme måte som en klasse definerer en ny type.
* En klasse kan *implementere* en eller flere interfaces; objekter av den klassen vil i såfall ha typen til klassen, og typen til alle interfaces klassen implementerer.
* *Bruk:* Man bruker interface-typen når man deklarerer lokale variabler, metodeparametre og feltvariabler.
    * En slik variabel vil kunne holde objekter av klasser som implementerer det aktuelle interfacet
    * Variabeltypen bestemmer hvilke metoder som er tilgjengelig (e.g., de metodene som er deklarert i interfacet)
    * Det går ikke an å opprette objekter av et interface – dvs. du kan ikke si ```new INavn``` – da må du bruke navnet på en konkret klasse
* *Generell regel:* Bruk interface-typen der du kan, bruk en konkret klasse bare når du skal lage nye objekter.

## Sammenlikning – Comparable-grensesnittet

Vanlig ("naturlig") sammenlikning: Grensesnittet Comparable<T> har en metode `compareTo(T o1)`. `T` sier hvilken type objekter vi kan sammenlikne med – ofte er dette samme som typen som er sammenliknbar, f.eks. `class Person implements Comparable<Person>` (Person-objekter kan sammenliknes med andre Person-objekter).

* `a.compareTo(b) == 0` hvis `a` og `b` er like
* `a.compareTo(b) < 0` hvis `a` er mindre enn `b`
* `a.compareTo(b) > 0` hvis `a` er større enn `b`

Alle objekter som er `Comparable` kan sammenliknes med `compareTo`-metoden. Dette gir bare én måte å sammenlikne på – hvis man har flere måter å sammenlikne på, må man i såfall velge den vanligste (den "naturlige" i Java-terminologi). Oftest vil man anta at `compareTo`-metoden fungerer sammen med `equals`, slik at `a.compareTo(b) == 0` hvis og bare hvis `a.equals(b)`, men dette er ikke et absolutt krav.

# Generiske typer

*Generics* eller *parametriserte/generiske* typer er et tilfelle av abstraksjon ved parametrisering. Akkurat som at man ved å ha parametre til metoder kan jobbe med verdier som man ikke vet presist hva er ennå, kan man med typeparametre lage klasser og interfaces der man ikke vet helt hva alle typene er ennå.

* I [ICollection.java](http://inf101.ii.uib.no/inf101.v16.oppgaver/inf101.v16.sem2/tree/master/src/inf101/collections/ICollection.java) finner du et grensesnitt for en generisk samling av objekter (tilsvarende Javas `Collection`-grensesnitt) – `ICollection<E>`, hvor `E` er elementtypen. Vi bestemmer hvilken type elementer vi skal lage når vi *bruker* listen, i stedet for når vi *implementerer* (eller spesifiserer) listen. Legg merke til:

    * ```interface ICollection<E> ...``` – E kan nå brukes til å oppgi returtype og argumenttyper til metodene i ICollection.

    * ```interface ICollection<E> extends Iterable<E> { ... }``` – samlingen er *itererbar*, så den kan brukes i en forenklet for-løkke, til å iterere over elementer av typen `E` (hva enn det ender opp med å være).

    * Hvis vi har spesielle krav til elementene, f.eks. at de må implementere et grensesnitt, kan vi oppgi det når vi introduserer `E`. F.eks.: ```interface ICollection<E extends Comparable<E>> extends Iterable<E> { ... }``` – for å kreve at elementene er sammenliknbare med andre objekter av samme type.
    * ```E remove()``` og ```void add(E elt)``` – vi vet ikke nøyaktig hvilken type objekter som blir lagret i samlingen ennå, men vi vet at det må være samme typen når vi legger til objekter og når vi henter ut objekter.

    * Når vil lager klassen, kan den også være generisk: ```class MyCollection<E> implements ICollection<E>``` – elementtypen er den samme i klassen som i interfacet som blir implementert.

    * (Vi kunne også laget en samling som bare håndterte én type elementer: ```class MyCollectionOfIntegers implements ICollection<Integer>```)

    * Når vi lager variabler og nye objekter, må vi oppgi hva elementtypen skal være:

        ```
ICollection<String> samlingAvStrenger = new MyCollection<String>();
ICollection<Integer> samlingAvHeltall = new MyCollection<Integer>();
        ```

    * Java sjekker at du putter objekter av riktig type inn i listen, og vet hva typen er når du henter dem ut:

        ```
samlingAvStrenger.add(2);  // feil: forventet en String og ikke en int
samlingAvHeltall.add("2"); // feil: forventet en int og ikke en String
//
String s = samlingAvStrenger.remove().toUpperCase(); // ok, listen inneholder strenger
String t = samlingAvHeltall.remove().toUpperCase();  // feil: Integer-objekter har ingen toUpperCase metode
        ```

# Iteratorer

## Forenklet for-løkke

Gå gjennom alle elementene i `liste`:
```
for(String s : liste) {
  System.out.println(s);
}
```

Løkkekroppen utføres en gang for hvert element, med `s` satt til elementet. 

## Iterable

Alle objekter av klasser som implementerer Iterable kan brukes i en forenklet for-løkke:

```
interface Iterable<T> {
   Iterator<T> iterator()
}
```

Når løkken kjøres kalles iterator-metoden som returnerer et Iterator<T> objekt. Denne *iteratoren* vet hvordan man kan gå steg for steg gjennom det itererbare objektet (listen, feks).

<!--
## en liten oppgave
* Hent prosjektet fra ` https://inf101.ii.uib.no/<brukernavn>/inf101.v16.f06.git` 

* Kjør testen du finner i `inf101.tests.RangeTest` (den feiler)

* Implementer iterator i `inf101.v16.datastructures.Range`. Den skal iterere over tallene fra og med low til men ikke med high. 

* Kjør test igjen, evt. juster koden til den virker

* Commit og push!


## Steg 2:

* Implementer `iteratorProperty` i `inf101.tests.MyListTest`.
   * Lag forenklet for-løkke (`for(T obj : list) { ... }`)
   * Legg til en teller `i` som starter på `0` og øker med 1 for hvert steg
   * Sjekk at det du får i `obj` er det samme som du får med `list.get(i)`.
   * Gjøres slik: `assertEquals(list.get(i), obj);`
   * Sjekk at du har vært gjennom alle elementene med f.eks. `assertEquals(list.size(), i)`

* Kjør testen!

* Implementer iteratoren i `inf101.v16.datastructures.MyList`.

-->
## Tips: anonyme klasser

I Range har vi laget iteratoren med en *anonym klasse*, som er veldig praktisk til dette formålet. Du oppgir navnet på et interface, og får et objekt av en klasse som implementerer interfacet:

```
return new Iterator<T>() {
   // her kan du ha feltvariabler

   // og metoder
   boolean hasNext() {...}
   T next() {...}
};
```

Pass på semikolon etter den anonyme klassen (for å avslutte return-setningen)

# Generatorer og egenskapstester
<!--[Se forøvrig Lab 3](lab-3)-->

## IGenerator

[IGenerator](https://inf101.ii.uib.no/inf101.v16.oppgaver/inf101.v16.lab3/tree/master/src/inf101/v16/util/IGenerator.java) et et grensesnitt for å generere tilfeldige data. Gitt en objekt av typen `IGenerator<T>` og et `Random`-objekt, kan du få produsert et tilfeldig objekt av typen `T` (eller eventuelt en serie objekter som er garantert å være `equals` – dette er nyttig i noen sammenhenger).

I `inf101.v16.util.generators`-pakken ligger det to klasser for å lage tilfeldige heltall og strenger.

For eksempel:

```
Random r = new Random();
IGenerator<String> strGen = new StringGenerator(5,15);
System.out.println("Tilfeldig streng: " + strGen.generate(r));
IGenerator<Integer> intGen = new IntGenerator(0, 1000);
System.out.println("Tilfeldig heltall mellom 0 og 1000: " + intGen.generate(r));
```

* Egenskaper ved objektene som genereres kan justeres når du kalle konstruktøren til generatoren. F.eks. kan du oppgi ønsket lengde på strengene, og ønsket størrelse på tallene.
* I praksis kan du også bruke generatorene uten å oppgi et `Random`-objekt – da får du et automatisk. Men når du selv skal implementere generatorer, må du gi `Random`-objektet hvis du skal kalle andre generatorer, ellers vil `generateEquals`-metoden ikke virke.


# Muterbare og ikke-muterbare objekter – Hvordan beskytte innkapsling
<!--
[Koden ligger her](/inf101/inf101v16/tree/master/inf101v16f20). Husk også [instruksene for hvordan du henter ut kode fra forelesningene](kode-fra-forelesninger).
-->
## Problemer og vanskeligheter med objekter som kan endres

Problemer kan oppstå hvis:

* Du har objekt som kan endres
* Du har mer enn en variabel eller verdi som refererer til samme objektet, uten at du er klar over det.

Da vil en endring gjort gjennom den ene variabelen føre til en endring i de andre.

For eksempel:
```
List<Person> persList = new ArrayList<>();

System.out.println("persList=" + persList);

Person per = new Person("Per", "Peersen");
Person kari = new Person("Kari", "Kaarisen");
persList.add(per);
persList.add(kari);

System.out.println("persList=" + persList);

byttNavn(persList, 1, "Pål");
		
System.out.println("persList=" + persList);
System.out.println("per heter " + per);
System.out.println("kari heter " + kari);
```
I eksemplet er objektet til personen Kari tilgjengelig både gjennom variabelen `kari` og gjennom `persList.get(1)`. En endring det ene stedet vil gi en endring det andre stedet.


I dette tilfellet var det relativt enkelt å holde styr på endringene. I litt mer kompliserte situasjoner er det ikke like enkelt å skjønne hvilke objekter som er de samme, og konsekvensen av å endre dem.

F.eks., anta at vi har en klasse `Sjef` som er en person, og har en liste med personer som er sjefens ansatte. Vi kan lage konstruktøren slik:
```
private List<Person> ansatte;

public Sjef(Navn n, List<Person> ansatte) {
	super(n);

	this.ansatte = ansatte;
}
```
og ha en metode `addAnsatt()` for å legge til ansatte, samt en metode `getAnsatte()` som returnerer listen av ansatte:
```
public List<Person> getAnsatte() {
	return ansatte;
}
```

Her er det ikke enkelt å se at man kan endre sjefens liste av ansatte uten å gå gjennom metodene til Sjef-klassen (koden finner du i [FeilSjef.java](/inf101/inf101v16/blob/master/inf101v16f20/src/inf101/immutable/FeilSjef.java)) – men det er mulig, f.eks. slik:
```
Sjef s = new Sjef(anya, new ArrayList<>());
s.addAnsatt(kari);
s.addAnsatt(per);
System.out.println("s=" + s);
		
List<Person> ansatte = s.getAnsatte();
		
ansatte.add(new Person("Trond", "Haugstei"));
System.out.println("s=" + s);
```
Her har vi lagt til en ekstra ansatt, Trond, ved å endre på listen som ble returnert fra `getAnsatte()`.  Et liknende problem kan også oppstå med ansattlisten vi gir til konstruktøren, f.eks.:
```
Sjef t = new Sjef(panya, ansatte);
ansatte.add(new Person("X"));
t.addAnsatt(kari);
t.addAnsatt(per);
System.out.println("s=" + s);
System.out.println("t=" + t);
```
Her har vi enda en sjef – og vi kan endre ansattlisten ved å endre listen vi ga til konstruktøren. Ikke nok med det, men begge sjefene deler nå ansattlisten, så `t.addAnsatt(kari)` gjør at kari dukker opp i `s` også.

## Problemer med datainvariant
Oppførselen over er som regel ikke ønskelig, fordi det gjør det mulig å bryte objektenes innkapsling, og gjøre endringer (selv når feltvariablene er private).

Problemet er:

* Du får tak i referanse til et objekt A som er feltvariabel i et annet objekt B (ved at A gis til eller returneres fra en av Bs metoder)
* og du kan endre objektet A
* da kan du gjøre endringer i B som er i strid med [klasseinvarianten](arv-forkrav-invariant-substitusjonsprinsippet#klasseinvariant-eller-datainvariant) – du kan få ugyldige feltvariabler i B

F.eks. la oss si at vi vil at ansattlisten i  Sjef-klassen aldri skal være lengre enn 5 og/eller at sjefen selv aldri er med i listen (`!ansatte.contains(this)`). Dette vil vi ikke kunne garantere, med mindre vi sørger for at ansattlisten bare kan endres gjennom `addAnsatt`.

Dvs. hvis vi skal sørge for å ha konsistente objekter, må vi forhindre ukontrollerte endringer.

## Problemer kan oppstå hvis...

* Mer enn en variabel referer til samme objekt
* Hvis du returnerer en feltvariabel som er et objekt som kan endres
* Hvis du tar imot et argumen, som er et objekt som kan endres og lagrer det (i en feltvariabel)

De to siste betyr at du har flere måter å referere til data som er inne i objektet. Hvis du kan endre data i et objekt, uten å gå via objektets metoder, så kan du ikke lengre garantere at objektet er konsistent (klasseinvariant er oppfylt) – samt at et objekt kan endre seg når et annet endrer seg, uten at du vet om det.

Alt dette er bare et problem når objekter kan endre seg.

## Resonnering om programmer
* Vi vil det skal være enkelt å resonnere om programmer, og tenke seg til hva de gjør – hvis du ikke forstår hva et program gjør og hvordan det gjør det, vil du ikke kunne videreutvikle det og vedlikeholde det.

* Mulighet for endringer i objekter gjør det *litt* vanskeligere å resonnere
* Mulighet for endringer i objekter gjennom flere referanser gjør det *mye* vanskeligere eller nesten umulig å resonnere

## Gode sider av å kunne endre objekter

At objekter kan endres, og at endringene er synlig mange steder er ikke nødvendigvis et problem. F.eks.:

* Hvis du endrer navn på en Person, så er det lurt at du bare trenger å gjøre det i ett person-objekt, og ikke alle steder der Person-objektet forekommer.

## Løsninger

For å forhindre endringer i strid med datainvariant:

* Lag en kopi av alle argumenter som lagres i feltvariabler
* Hvis du *returner* samlinger/lister, bruk Collections.unmodifiableList (gir deg en utgave av listen/samlingen som ikke lar seg endre), ellers lag kopi
* Alle feltvariablevariabler bør være `private`, og `final` hvis mulig
* `private` gjør at ingen utenfor klassen har tilgang til feltvariabelen – dette er *en* mulig måte å få tilgang til og endre et objekt (eller evt. erstatte objektet med et annet)
* `final` gjør at feltvariabelen ikke kan endres når den først er satt – dette hjelper litt, men hindrer ikke endringer i objektet som feltvariablen refererer til (f.eks. en liste)

Se korrigert utgave av sjefsklassen i [Sjef.java](http://inf101.ii.uib.no/inf101/inf101v16/blob/master/inf101v16f20/src/inf101/immutable/Sjef.java).

## Immutable objekter
Det kan lønne seg å lage objektene slik at de ikke kan endre seg – *[immutable](http://en.wikipedia.org/wiki/Immutable_object)* objekter (som i motsetning til *mutable*).

* Det er relativt vanskelig å garantere dette i Java. En del språk, f.eks. Haskell (som dere lærer i INF122), har bare immutable objekter.

* I Java er String er immutable fra før – det samme gjelder Integer, Double og en del andre klasser i `java.lang`.

For å gjøre en klasse immutable:

* Vi må fjerne alle set-metodene / alle metodene som endrer feltvariablene – evt erstatt med metoder som returnerer nye objekter
* Alle objekter som går inn/ut (argument/returverdi), *og* som lagres (i en feltvariabel) må også være immutable.

[ImmutableNavn](/inf101/inf101v16/blob/master/inf101v16f20/src/inf101/immutable/ImmutableNavn.java) er et eksempel basert på koden over.

I en del tilfeller kan det være upraktisk å ha immutable objekter. F.eks. i Java vil dessuten programmet kjøre noe tregere, fordi du må lage nye objekter hver gang noe skal endres.

* [Her er en liten oppgave om immutabilitet](https://inf101.ii.uib.no/inf101/inf101v16/wikis/immutable#oppgave)


# Arv

## Extends
`class Sub extends Super`

Sub *arver fra* Super:

* Sub blir en *sub-type* av Super
    * objekter av sub-klassen kan brukes alle steder du forventer noe av super-klassen
* Sub får alle feltvariabler og metoder (inkl kode) som Super har
* Sub implementerer alle interfaces som Super implementerer
* Når du lager et Sub-objekt, må konstruktøren kalle konstruktøren til super-klassen
* En klasse kan bare ha én superklasse (i Java) – og `Object` er superklasse til alle klasser som ikke har en oppgitt superklasse. Det er lov å ha mange subklasser.
* Superklassen kan igjen ha en superklasse osv.


## Implements
`class Impl implements Interface`

Impl *implementerer* Interface:

* Impl *må ha* alle metoder fra Interface.
* Impl implementerer også alle grenesnitt som Interface utvider (extends)
* Impl blir en *sub-type* av Interface
     * objekter av Impl kan brukes alle steder der du forventer objekter av Interface-typen.
* Mange klasser kan implementere samme grensesnitt.
* Et grensesnitt sier ingenting om konstruktør eller feltvariabler, og inneholder ingen kode, og det kan ikke lages objekter av det (dvs. `new Interface()` er ikke lov)

*Å implementere et grensesnitt har altså en del til felles med å arve fra en klasse, men det er også en del ting som er forskjellig.*

# Forkrav, datainvariant og substitusjonsprinsippet

## Forkrav

* Noe som må være sant før du kan kalle en metode
* Forkravet sier hvilke argumenter som er gyldige
* Brutt forkrav resultere som oftest i `IllegalArgumentException` eller en annen exception.
* Det er generelt *best* å sjekke forkrav selv, og kaste en passende exception.
* *Viktigst:* Dokumenter forkravene for den som skal bruke klassen (med JavaDoc).
* *Helst:* Sjekk forkravene på begynnelsen av metoden, og gi en feilmelding med én gang om noe er galt.

## Klasseinvariant eller datainvariant

* En *invariant* er et uttrykk som skal være det samme hver gang det blir regnet ut.
* *Datainvarianten* er et uttrykk som skal være *sant* hver gang en public metode blir kalt eller returnerer.
* Datainvarianten forteller om et objekt er konsistent eller ikke, dvs. om feltvariablene har gyldige verdier.
* At datainvarianten er oppfylt, er også et forkrav til alle public metoder.
* Alle public metoder skal ha som egenskap at de opprettholder datainvarianten.
* Vi kan lage en metode som sjekker datainvarianten – den metoden vil da fortelle oss om et objekt er konsistent (at kombinasjonen av verdier til feltvariablene er gyldig).
* *Viktigst:* Dokumenter datainvarianten for den som implementerer klassen (kommentar).
* *Nyttig:* Sjekk datainvarianten før du returnerer fra konstruktøren og public metoder. Da vil du kunne oppdage inkonsistente objekter så tidlig som mulig.
* *Husk:* Så lenge feltvariablene er `private`, kan de bare endres av klassens metoder. Dvs. at du bare trenger å sjekke at klassens egne public metoder opprettholder datainvarianten, siden dette er eneste stedet det kan skje endringer som evt. kan bryte den. (Men vær oppmerksom på at objekter likevel kan lekke ut, se [avsnitt om mutabilitet](#muterbare-og-ikke-muterbare-objekter-hvordan-beskytte-innkapsling).)

## Liskovs Substitusjonsprinsipp

### Substitusjonsprinsippet for forkrav og arv

`class Sub extends Super` eller `class Sub extends Interface`

* Forkrav: forkrav i Sub kan *ikke være sterkere* enn forkrav i Super/Interface. Det er i orden om Sub-klassen har *svakere* forkrav.
* Oppførsel: Sub-klassen må *minst* gjøre det Super-klassen gjør / det som er spesifisert av Interface. Dvs., *objekter av subklassen må kunne passere alle testene du har for superklassen*.

Dette betyr at et Sub-objekt alltid kan brukes der du forventer et Super- eller Interface-objekt, og det vil virke minst like bra.

Dette er *Liskovs substitusjonsprinsipp*.

### Substitusjonsprinsippet for klasseinvariant og arv

* Alle metoder (inkl konstruktør) i subklassen må opprettholde superklassens klasseinvariant (dette følger av regelen om at subklassens oppførsel skal være kompatibel med superklassens).
* Hvis Sub har en egen klasseinvariant (som er strengere enn den til Super), så må alle metodene oppretteholde den, også de som man arver fra Super. Hvis de ikke gjør det, så må de overstyres (@Override) av subklassen slik at de gjør det. (Dette er hovedsaklig et problem hvis Sub har egne feltvariabler.)

Eksempel: Klassen [Kanin](http://inf101.ii.uib.no/inf101/inf101v15/blob/master/inf101v15f12/src/inf101/dyr/Kanin.java) arver fra [Dyr](http://inf101.ii.uib.no/inf101/inf101v15/blob/master/inf101v15f12/src/inf101/dyr/Dyr.java), og har en ekstra feltvariabel, `tannLengde`. 

* Hvis vi krever at `tannLengde >= 0`, så er Kanins klasseinvariant at Dyrs klasseinvariant er oppfylt, pluss at `tannLengde >= 0`.
* Vi vet at alle metodene i `Dyr` også vil tilfredsstille `tannLengde >= 0`, fordi klassen `Dyr` ikke kjenner til `tannLengde` og kan derfor ikke sette den til noe ulovlig.
* Hvis vi derimot krever at det skal være et spesielt forhold mellom `tannLengde` og `vekt` (fra Dyr), må vi overstyre alle metodene fra `Dyr` i `Kanin`, og sørger for at de opprettholder dette forholdet. Ellers vil f.eks. `spis()`-metoden endre `vekt` uten å gjøre en tilsvarende endring i `tannLengde`.


# Unntakshåndtering
* Brukes når det skjer noe uvanlig / feil / ikke forventet i programmet.

* Unntakene er *objekter*

* Oppstår og kastes fra en `throw`-setning

* Kan fanges og håndteres i en `try/catch`-blokk

## To typer unntak

* Checked exception:

    * Arver fra `Exception`
    * Du må alltid håndtere unntaket, på et eller annet vis, ellers får du feil fra Java-kompilatoren
    * Håndteres med enten: try/catch, eller gi jobben videre til den som kaller metoden med en `throws` deklarasjon.
    * Brukes på feil som man forventer at kan skje – om det oppstår en feil som ligger utenfor programmets kontroll (dvs. feil ved lesing/skriving til filer).
* Unchecked exception:
    * Arver fra `RuntimeException`
    * Trenger ikke håndteres, kompilatoren gjør ingen sjekker
    * Brukes ofte i tilfeller hvor programmøren har gjort noe feil – Java-filosofien er at alle feil man regner med kan forekomme er checked exceptions. Unchecked er for tilfeller hvor det ville vært for mye stress å alltid måtte gjøre try/catch.
    * IndexOutOfBoundsException, f.eks. – her har programmøren glemt å sjekke indeksene, eller regnet feil.
    * Vanligvis "håndteres" unchecked exception ved at programmet avsluttes med en feilmelding.

* Vanligste måte å håndtere ting i en catch-blokk
    * gjøre ingenting:

```
			// TODO Auto-generated catch block
			e.printStackTrace();
```

Dette er årsaken til flertallet av feil i applikasjoner!

* Hva er den riktige tingen å gjøre i catch-blokken?
    * Om du ikke har en bedre ide, avslutt programmet – catch-blokken lar deg da avslutte programmet på en kontrollert måte
    * Om feilen oppstår som del av en self-contained operasjon i programmet, kan man avslutte den og rydde opp (sette brukerens data tilbake til utgangspunktet), men fortsette programmet.
    * En del ting kan håndteres, ved f.eks. å prøve en annen teknikk. F.eks. be brukeren om hjelp, prøv neste tegnsett-enkoding. 


## Exception-klasser

* Når du kaster et unntak: bruk den mest spesifikke klassen som passer for formålet.

* Når du fanger et unntak: unngå å fange Exception/RuntimeException, men det kan være ok å fange generelle unntaksklasser som IOException – da risikerer du å dekke over feil som burde vært håndtert annerledes. Hvis du skriver kode for å håndtere IO-feil, bruk IOException, ikke noe mer generelt.

* Lage din egen: lage en klasse som arver fra Exception eller RuntimeException (evt IOException, eller noe mer spesifikt). Send med en fornuftig melding til super()-konstruktøren. 


## Finally

* Finally-blokken blir kjørt etter try-blokken, uansett om det kom en exception eller ikke.

* Hvis det kommer en exception, blir catch-blokken kjørt først, om den finnes.

* Brukes til å rydde opp (viktig hvis du skriver til filer og slikt)
