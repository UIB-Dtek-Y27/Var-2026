## Bruk

* Diagrammer brukes til [grafisk modellering](https://en.wikipedia.org/wiki/Modeling_language) – *modell* er  i dette tilfelle en abstrakt beskrivelse (viser de relevante egenskapene og ser bort fra de irrelevante) av system.
* Poenget med modellering er å *spesifisere* (slik har/skal vi lage systemet vårt), *dokumentere/kommunisere* (f.eks. i en team-diskusjon), og/eller å *utforske* (får oversikt over et system, eller få inntrykk av hvordan det blir etter en endring).
* [UML](https://en.wikipedia.org/wiki/Unified_Modeling_Language) er formell notasjon for modelldiagrammer. Det går an å modellere med tekst også – f.eks. med [RDF](https://en.wikipedia.org/wiki/Resource_Description_Framework) brukes til kunnskapsrepresentasjon (metadata, semantisk web – se [INFO216](https://www.uib.no/emne/INFO216)), eller med forskjellige former for logikk/algebra for å spesifisere oppførsel (se [INF220](https://www.uib.no/emne/INF220)).
* Diagrammer er ment å være *abstrakte*, dvs. fokusere på de tingene som er viktig i den situasjonen du bruker diagrammet. Detaljene ligger i koden og (interface)dokumentasjonen, og unødvendige detaljer gjør bare diagrammet vanskeligere å forstå og dermed mindre informativt.
* I praksis kan det ofte fungere greit og være effektivt å være litt uformell i notasjone – særlig hvis målet er å kommunisere / diskutere. Men det er likevel lurt å holde seg til konvensjonene utviklere er vant til (dvs. UML), så ikke folk misforstår (f.eks. hvis man snur pilen som vanligvis går fra subklasse til superklasse).

### Klassediagram og objektdiagram

* [Klassediagrammet](https://en.wikipedia.org/wiki/Class_diagram) viser klasser og interfaces (typer) og relasjonene mellom dem, og eventuelt metoder og feltvariabler. 
* Hver type er illustrert med en boks. Øverst vises navnet, og evt. *«class»*, *«interface»*, *«abstract [class]»*, *«enum»* etc om det er nødvendig å skille dem. Feltvariabler og metoder kommer i to seksjoner under, men dette er ofte unødige detaljer som er greit å utelate.
* Relasjoner tegnes med streker mellom boksene – bruk pil for arv (A → B for `A extends B`) og pil med stiplet linje for implementering (A ⇢ B for `A implements B`).
* Relasjonene kan evt. ha mengde (f.eks, *Donkey <sup>1</sup>———<sup>4</sup> Leg* «1 esel har (forhåpentligvis) 4 ben»: Donkey; eller *Pond <sup>0..1</sup>———<sup>0..n</sup> Duck* «hver andedam har null eller flere ender, hver and bor i maks én andedam, men kan også bo et annet sted»)
* [Objektdiagrammer](https://en.wikipedia.org/wiki/Object_diagram) følger samme formen, men viser objekter i stedet for klasser – dvs. hvis klassediagrammet er et «skjema», så er objektdiagrammet «fylt ut» med tilstanden på et gitt tidspunkt ved kjøretid. Tittelboksen er *variableNavn : KlasseNavn* og feltvariablene viser konkrete verdier.

![](img/uml-klassediagram.jpg)

![](img/uml-objektdiagram.jpg)

Arvepilene (extends → og implements ⇢) viser et *er en* forhold (subtyping) – «alle A er B». F.eks. «en Duck er et PondObject», «en List er en Collection», «en ArrayList er en List», osv. Vi kan bruke piler og streker for å vise andre relasjoner også. For eksempel:

![](img/pond-with-frog.svg)

Pil og strek er i de fleste tilfeller tilstrekkelig, men det går eventuelt an å ha med flere detaljer for å illustrer forskjellige variant av «A *har en* B». [^1]

![](img/Uml_classes_en.svg)

* *Assosiasjon* er en lenke/referanse – det er kanskje informasjonen som er mest interessant å ha med.
* *Avhengighet* er en mer diffus forbindelse – den ene klassen bruker objekter eller metoder av den andre klassen, f.eks. som parametre eller lokale variabler (`import` på toppen av klasse-filen er en god indikasjon på *dependency*), men uten å lagre referanser i feltvariabler
* *Komposisjon* ◆ er en form for assosiasjon, typisk implementert med en feltvariabel. For eksempel, «Duck har en Position» – og hvis anden forsvinner, forsvinner også objektet som lagrer posisjonen.
* *Aggregering* ◇ er som regel også gjort med feltvariabler, forskjellen er at man refererer til noe som har uavhengig levetid. F.eks. «en Duckling har en (*er [preget](https://en.wikipedia.org/wiki/Imprinting_(psychology)) på*) mor», og trenger å referere til mor-objektet slik at andungen kan svømme etter – men andemor og andunge er likevel uavhengige individer, og begge gjerne del av samme Pond.

Skillet mellom komposisjon  og aggregering kan være viktig i en del programmeringsspråk (C og C++, f.eks.), men i Java vil feltvariabler alltid *referere* (aggregering) til andre objekter. Med *assosiasjon* er vi mer interessert i *forhold mellom klasser* enn hvordan det er implementert. Hvis du er i tvil, tegn strek/pil (assosiasjon).

For eksempel:

![](img/comp-aggr-assoc.svg)

Ved assosiasjon er vi kanskje interessert i retning – om A «vet om» B, eller om B vet om A, eller om de vet om hverandre (bidireksjonell assosiasjon):

![](img/pond-duck-assoc.svg)

Dette kan ha litt betydning for implementasjonen – det er som regel lurt at informasjon (om forholdet mellom A og B) er lagret bare ett sted, ellers er det lett å gjøre feil slik at dataene blir inkonsistente. I eksemplet fra forelesning er det Pond som har en liste av Duck, så tilhørigheten er lagret bare i Pond. Når Duck av og til trenger å vite om hvilken Pond den hører til må vi evt. sende det med som et parameter. En annen mulighet er å lagre forholdet i et eget objekt, tilsvarende til en databasetabell – særlig når det er snakk om kompliserte forhold mellom flere objekter.

#### Eksempler på klassediagrammer

Fra et refaktoreringsverktøy for IntelliJ:

![](img/refactorio.png)

Fra et parse-verktøy for Python:

![](img/pyparsingClassDiagram.png)

## [Aktivitetsdiagram](https://en.wikipedia.org/wiki/Activity_diagram)

Aktivitetsdiagrammer viser arbeidsflyt – f.eks. for en prosess, eller for hvordan man tar en avgjørelse. For eksempel:

![](img/activity-diagram-sb.png)

![](img/decision-flow.png)


[^1]: By Yanpas - Own work, CC BY-SA 4.0, https://commons.wikimedia.org/w/index.php?curid=48015014