# INF112 – Eksamenssvar

**Team:** *A-Laget* – Isak Graarud, Johannes Helle Moe, Theodor Flornes, Tuva Kvamme, Synne Hermansen, Bernhard Bors

**Prosjekt:** Mario Bros-inspirert 2D-plattformspill (LibGDX + Box2D + TiledMap)

---

## 1. Prosjekterfaringer og metodikk

### 1.1 Bakgrunn

**a) To ting vi hadde hatt nytte av å kunne bedre før vi begynte:**

For det første kunne vi gjerne ha hatt mer grunnleggende kompetanse på **Git i et team-setting**. De fleste på teamet hadde kun erfaring fra INF101/INF102, der man jobber alene mot eget repo med `add`, `commit` og `push`. Når seks personer skal jobbe parallelt i samme kodebase blir branching-strategi, merge requests, rebase og konfliktløsning helt sentralt. Vi mistet en del tid de første ukene på små merge-konflikter som kunne vært unngått om vi hadde landet en felles rutine fra dag én. Det vi til slutt fant ut – egen branch per oppgave, merge request med review, riktig bruk av rebase – fungerte veldig godt, men hadde vi kunnet dette på forhånd ville vi kommet raskere i gang med selve spillet.

For det andre kunne vi hatt nytte av å kunne **LibGDX og Box2D** bedre fra start. Spesielt Box2D sin kollisjonshåndtering med `ContactListener`, body-typer og fixture-filtre tok lang tid å sette seg inn i. Vi brukte mye tid de første sprintene på å forstå hvorfor spilleren noen ganger falt gjennom bakken eller hoppet rart, og dette forsinket implementasjonen av selve spillmekanikken. Hadde vi gått gjennom et lite eksempelprosjekt med Box2D før vi begynte på det egentlige spillet, ville den første sprinten vært mer produktiv.

**b) En ting vi trodde ville være viktig, men som ikke ble det:**

Vi brukte mye tid tidlig på å diskutere arkitektur i detalj før vi hadde noe spill å snakke om. Det viste seg å være mindre viktig enn forventet – det var først når vi faktisk hadde noen klasser og features på plass at vi forsto hvilke abstraksjoner som faktisk trengtes. Vi refaktoriserte til en ren MVC- og SOLID-struktur ganske sent i prosjektet, og det gikk faktisk fort fordi vi da visste hvilke ansvarsområder som hørte sammen. Tidlig overdesign hadde gjort det vanskeligere å komme i gang.

---

### 1.1 Metodikk (gruppens valg)

Gruppen ble enig om å bruke en **hybrid mellom Scrum og Kanban**. Vi satte milepæler/sprints med konkrete mål (typisk én uke om gangen), og brukte et Kanban-board i Trello til å fordele og spore oppgaver. Vi hadde faste roller (Team Lead, Test Queen, Strukturansvarlig, DJ, Designansvarlig, Rapportansvarlig) men ikke en streng Scrum-rolledeling med Product Owner og Scrum Master.

I praksis så flyten slik ut: vi hadde **fast fysisk møte én gang i uka** hvor vi gikk gjennom hva alle hadde gjort siden sist (stand-up), fordelte nye oppgaver, og diskuterte strategiske valg. Hver utvikler plukket en task fra Trello, lagde en egen branch, implementerte featuren med tester, og opprettet en merge request som minst én annen reviewde før den ble merget til main. Etterpå ble Trello oppdatert og dokumentasjon lagt til. Utenom møtene kommuniserte vi via Discord og fysisk på lesesalen.

Vi avvek noen ganger fra det idealet – spesielt med å huske å oppdatere Trello i travle perioder, og med å skrive tester samtidig som ny kode (vi havnet med en del testskriving mot slutten). Men selve hybride Scrum/Kanban-tilnærmingen fungerte godt og passet et lite team som vårt.

---

### 1.2 Metodikk (råd til 2026-studenter)

Som 2026-gruppeleder ville jeg anbefalt **en lett Scrum/Kanban-hybrid** med ukentlige sprinter, omtrent slik vi gjorde det. Et team på 5–6 studenter trenger struktur nok til at alle vet hva de skal gjøre, men ikke så mye prosess at all energien går til møter og statusrapportering. Konkret vil jeg anbefale: én fast fysisk møtetid i uka med stand-up og oppgavefordeling, et felles oppgaveboard (Trello, GitHub Projects eller lignende) som faktisk blir oppdatert, og en chat-kanal (Discord) for løpende kommunikasjon. Det viktigste er ikke metodikken i seg selv, men at man faktisk følger den – velg det enkleste oppsettet som hele teamet kommer til å gjennomføre.

Jeg vil sterkt anbefale å bruke **roller, men holde dem fleksible**. Hos oss hadde alle hovedansvar for ett område (test, arkitektur, design, lyd, dokumentasjon, koordinering), men alle bidro også på tvers. Roller skaper eierskap og hindrer at viktige områder som testing og dokumentasjon blir glemt fordi "noen burde ta tak i det". Sett rollene tidlig, gjerne basert på hva folk faktisk har lyst til å lære.

Til slutt: **definer "definition of done" tidlig**. Vi gjorde ikke det, og endte med flere features som var "ferdige" men manglet tester eller dokumentasjon. Hvis dere fra starten av blir enige om at en brukerhistorie er ferdig først når den er testet, dokumentert og merget gjennom review, så slipper dere en hektisk innspurt med testskriving i siste sprint. Skriv tester *samtidig* som koden, ikke etterpå.

---

### 1.2 Teamarbeid

Det viktigste jeg har lært om teamarbeid er **verdien av tydelige, lavterskel kommunikasjonsrutiner**. Vi hadde stand-up på det ukentlige møtet, en Trello-tavle der alle kunne se hva de andre jobbet på, og Discord til løpende spørsmål. Dette gjorde at jeg som regel visste hva alle de andre fem holdt på med, uten at jeg trengte å spørre eksplisitt. Det reduserte mengden små avbrytelser drastisk, og gjorde at man kunne fokusere på sitt eget arbeid i lange perioder. Når noen først kom til et problem som blokkerte, var det lav terskel for å ta det opp – enten i Discord eller på neste møte. Den åpne kulturen for å si fra tidlig om både problemer og uenigheter sparte oss for større konflikter senere.

Den andre tingen er **viktigheten av rolle- og ansvarsfordeling**. Da vi delte teamet i seks roller (Team Lead, Test Queen, Struktur, Design, DJ, Rapport) fikk hver enkelt et tydelig eierskap til ett område. Det betydde for eksempel at noen alltid passet på at testdekningen ikke falt, og noen alltid passet på at MVC-strukturen ikke ble brutt. Uten den fordelingen hadde det vært lett at viktige men "kjedelige" områder som dokumentasjon og test-coverage falt mellom stolene fordi alle antok at noen andre tok ansvar. Samtidig var rollene fleksible – ingen ble låst til kun ett område, og alle bidro på flere felt. Den balansen mellom tydelig ansvar og samarbeid på tvers er noe jeg vil ta med meg videre.

---

### 1.3 Kunnskap

**Strukturering av kode med designprinsipper (SOLID + arkitekturmønstre).** Å skrive kode som *fungerer* er bare halve jobben – den må også være mulig å vedlikeholde og utvide. Vi opplevde dette konkret i prosjektet: den første versjonen hadde tung kobling mellom skjermer, modell og lyd, og det ble fort smertefullt å legge til nye features. Etter at vi refaktoriserte til MVC og innførte en event-buss for løs kobling mellom lag, ble det plutselig lett å legge til nye fiender, nye power-ups og nye skjermer uten å bryte noe annet. SOLID er ikke abstrakt akademisk teori – det er konkrete grep (interfaces fremfor konkrete klasser, små klasser med ett ansvar, åpne for utvidelse men lukket for endring) som direkte oversetter til kode som er mulig å jobbe i over tid. Som utvikler kommer du nesten alltid inn i en eksisterende kodebase, og evnen til både å forstå og produsere godt strukturert kode er det som skiller folk som kan jobbe i team fra folk som lager noe som "fungerer på min maskin".

**Versjonskontroll (Git) i et samarbeidsmiljø.** Git er fundamentet for alt teamarbeid på kode. Det betyr ikke bare å kunne `commit` og `push`, men å forstå branching, merge requests, code review, konfliktløsning og rebase. I prosjektet vårt hadde vi merge requests med review som standard, og det fanget både bugs og dårlige designvalg før de havnet i main. Code review er også en av de beste måtene å lære fra de andre på teamet, og å spre forståelsen av kodebasen jevnt utover. Uten god Git-praksis ender man enten med kaos (alle pusher til main og overskriver hverandre) eller frustrasjon (folk er redde for å committe fordi de ikke skjønner hva som skjer). Dette er ikke valgfri "ekstra" kunnskap – det er et basiskrav i alle profesjonelle utviklingsmiljøer.

---

### 1.3 Fremtid

**Mer dyp kunnskap om testing og test-drevet utvikling (TDD).** Vi nådde 81 % testdekning, men mye av det skrev vi mot slutten – det er kjent som "tester etter koden er ferdig", og det fanger ikke designfeil tidlig nok. Hadde jeg vært flinkere på TDD ville jeg skrevet testen først, og oppdaget at en klasse hadde for mange ansvar eller for hard kobling før jeg implementerte den. Testing er også mer enn JUnit på rene metoder: integrasjonstesting, kontraktstesting og hvordan man tester ting som er vanskelig å teste (UI, asynkron logikk, fysikksimulering) er områder jeg vil lære mer om. God testing reduserer feilraten i produksjon dramatisk og gjør at man tør å refaktorisere, som igjen gir bedre kode på sikt.

**Design og bruk av designmønstre i praksis.** Jeg kjenner til de fleste designmønstrene fra pensum (Observer, Strategy, Factory, Singleton, Composite osv.), men det er forskjell på å gjenkjenne dem og å selv velge riktig mønster i en konkret situasjon. I prosjektet brukte vi blant annet en variant av Observer-mønsteret med vår event-buss, og Strategy/Component-mønstre for entitetsoppførsel, men jeg merker at jeg ofte bruker det mønsteret jeg så sist heller enn det som passer best. Å bli tryggere på når man *ikke* skal bruke et mønster er like viktig – overdesign er en reell felle. Mer praktisk erfaring med ulike mønstre, og evne til å lese et problem og se hvilket mønster som passer, vil gjøre meg bedre til å designe systemer som er enkle å utvide.

---

### 1.4 Kunstig intelligens

**a1) Bruk av AI-verktøy i prosjektet:**

Ja, vi brukte AI-verktøy underveis, primært ChatGPT/Claude og noe Copilot. Den klart mest nyttige bruken var **boilerplate-kode og repetitive ting**: JavaDoc, getter/setter-kode, JUnit-tester for enkle metoder, og oppslag i LibGDX-dokumentasjonen ("hvordan setter jeg opp en TiledMap-renderer?"). Verktøyene var også fine til å forklare feilmeldinger og foreslå feilsøking når noe ikke virket. På den måten sparte de tid og senket terskelen for å komme i gang med en oppgave.

Vi ble også ledet på villspor en del. Spesielt med **Box2D** ga verktøyene ofte plausibel kode som faktisk ikke fungerte fordi den blandet API-versjoner eller foreslo callbacks som ikke fantes. Vi opplevde også at AI-genererte forslag kunne være elegante isolert, men passet dårlig inn i vår eksisterende MVC- og event-buss-struktur, og innførte tett kobling vi måtte refaktorisere bort. Konklusjonen er at AI er nyttig som assistent for korte, isolerte oppgaver – men du må forstå nok selv til å luke ut feil og holde fast på arkitekturen.

**a2) Vil AI erstatte mennesker i utviklingsprosessen de neste fem årene?**

Nei, ikke erstatte – men sterkt endre rollen til utviklere. Det vi har sett er at AI er god på å produsere kode for *velavgrensede, ofte-løste problemer* (sortering, CRUD-endepunkter, parsing, boilerplate). Den er fortsatt svak på å forstå større systemer, ta arkitekturvalg, holde konsistens på tvers av en stor kodebase, eller forstå tvetydige forretningskrav. Programvareutvikling er bare delvis kodeskriving – mye av jobben er å forstå hva som faktisk skal bygges, snakke med brukere, ta avveininger mellom motstridende hensyn, og holde et stort system fra å falle fra hverandre over tid. Det er oppgaver der modeller per i dag kommer til kort.

Det jeg tror vil skje er at utviklere blir mer produktive per person, og at rollen flytter seg mer mot å være redaktør, arkitekt og kvalitetssjekker av AI-generert kode. Junior-stillinger kan bli vanskeligere fordi de "enkleste" oppgavene blir automatiserte. Men ansvaret for at systemet faktisk virker, er sikkert og oppfyller brukernes behov, vil fortsatt ligge hos mennesker. Fem år er kort tid – de fundamentale begrensningene rundt resonnering, kontekstforståelse og ansvar er ikke løst, og det er lite som tyder på at de blir det innen kort tid.

**b) Nytten av designprinsipper og metodikk i lys av AI:**

Det jeg lærte i INF112 har blitt *mer* viktig, ikke mindre, etter at AI-verktøy ble vanlige. Når AI skriver mye av selve koden, blir det enda viktigere at *du* kan vurdere om koden er god – om den følger SOLID, om den passer arkitekturen, om den er testbar, om den introduserer skjult kobling. AI er villig til å gi deg kode som fungerer akkurat nå men er umulig å vedlikeholde om seks måneder; det er bare et menneske med forståelse for designprinsipper som kan stoppe det.

Metodikk-delen (Scrum, code review, CI/CD, testing) blir også viktigere, ikke mindre. AI-generert kode må reviewes – kanskje enda nøyere enn menneskeskrevet kode, fordi den ofte ser overbevisende ut selv når den er feil. CI/CD og automatisk testing fanger feilene AI introduserer. Brukerhistorier og krav-arbeid er noe AI fortsatt sliter med, og det er der menneskelig dømmekraft og kommunikasjon med brukere er avgjørende. INF112 lærte oss å være *ingeniører*, ikke bare programmerere, og den forskjellen blir tydeligere når verktøyene blir kraftigere.

---

## 2. Verktøy og arbeidsflyt

### 2.1 Git arbeidsflyt

**a) Hvordan jeg ville lagt opp Git-arbeidsflyten:**

For et lite team på 4–6 utviklere ville jeg brukt en forenklet **feature branch workflow** – ikke full GitFlow, det er overkill for et team av denne størrelsen. Konkret:

- **Én hovedbranch: `main`.** Den skal alltid være "grønn" (alle tester passerer, kode lar seg bygge) og kunne deployes når som helst.
- **Ingen direkte push til `main`.** All endring går via merge request (MR).
- **Egen branch per oppgave.** Ikke samle flere features i én branch. Hold endringene små og fokuserte.
- **Navngiving:** `<type>/<kort-beskrivelse>`, for eksempel `feat/leaderboard-screen`, `fix/jump-bug`, `refactor/event-bus`, `test/score-calculator`. Type-prefiksene gjør at man ser kategorien i listen.
- **Merge request med minst én reviewer.** Selv små endringer bør reviewes – det fanger bugs og sprer kunnskap om kodebasen.
- **CI må passere før merge.** Pipeline skal kjøre kompilering og tester automatisk; rød pipeline = ingen merge.
- **Squash merge eller rebase før merge** for å holde main-historikken ren. Ingen `Merge branch 'main' into feature-x` i historikken.
- **Skriv ordentlige commit-meldinger:** kort linje på toppen (50 tegn), eventuell utfyllende tekst under. Imperativ form: "Add leaderboard screen", ikke "Added" eller "Adds".

Dette gir et lite team både fleksibilitet (alle kan jobbe parallelt på egne brancher) og kvalitetskontroll (review + CI fanger feil før de havner i main).

**b) Hjelp med en merge-konflikt:**

Først, ikke få panikk – merge-konflikter er normalt og fikses systematisk. Anta at du jobber på `feature/min-branch` og skal merge inn endringer fra `main`:

1. **Hent siste main:** `git checkout main && git pull`.
2. **Bytt tilbake til din branch:** `git checkout feature/min-branch`.
3. **Rebase (eller merge) main inn i branchen din:** `git rebase main`. Git sier ifra om hvilke filer som har konflikt.
4. **Åpne hver konfliktende fil i IDE-en.** Du vil se markører som `<<<<<<< HEAD`, `=======` og `>>>>>>> main`. Den øverste delen er din kode, den nederste er det som ligger på main.
5. **Bestem hva som skal bli den endelige koden.** Noen ganger vil du beholde din versjon, noen ganger den andre, ofte en blanding. Slett konfliktmarkørene når du er ferdig.
6. **Merk konflikten som løst:** `git add <fil>`.
7. **Fortsett rebasen:** `git rebase --continue`. Hvis det er flere konflikter, gjentar Git prosessen for hver commit.
8. **Test at koden fortsatt virker** før du pusher. `git push --force-with-lease` siden rebase endrer historikken på din branch.

Hvis det går helt galt: `git rebase --abort` setter alt tilbake til der du startet. Da er det ofte bedre å spørre en teamkollega om å se på det sammen, fremfor å gjøre hastige valg du angrer på.

---

### 2.2 Designmønstre

Jeg viser **Observer-mønsteret** her, fordi det er et av mønstrene vi faktisk brukte i prosjektet (via vår event-buss for å koble lyd, score-system og spillerhendelser løst sammen).

```java
/** Subjektet som observeres – holder en liste av observers og varsler dem ved endringer. */
public interface Subject {
    /** Registrerer en ny observer som skal motta varsler. */
    void attach(Observer o);

    /** Fjerner en observer slik at den ikke lenger mottar varsler. */
    void detach(Observer o);

    /** Varsler alle registrerte observere om at en hendelse har skjedd. */
    void notifyObservers();
}

/** En observer som mottar varsler fra et Subject. */
public interface Observer {
    /** Kalles av Subject når en hendelse oppstår. event identifiserer typen, data er event-spesifikk payload. */
    void update(String event, Object data);
}

// --- Konkrete klasser ---

/** Konkret subjekt som publiserer spillhendelser (hopp, skade, drepte fiender osv.). */
class GameEventBus implements Subject { /* ... */ }

/** Lytter på hendelser og spiller av riktig lyd-effekt. */
class AudioManager implements Observer { /* ... */ }

/** Lytter på hendelser og oppdaterer spillerens score. */
class ScoreCalculator implements Observer { /* ... */ }

/** Lytter på hendelser og oppdaterer HUD-en (liv, score, osv.). */
class HUDView implements Observer { /* ... */ }
```

Fordelen er at `GameEventBus` ikke trenger å vite noe om hvem som lytter – AudioManager, ScoreCalculator og HUDView kan registrere seg uten at bussen endres. Nye lyttere kan legges til uten å endre eksisterende kode (open/closed-prinsippet), og koblingen mellom modell-laget og presentasjonslaget blir løs.

---

### 2.2 IDE

**a) Hvilken IDE vi brukte:**

Hele teamet brukte **IntelliJ IDEA** som hovedutviklingsmiljø, noe som var et bevisst valg fra start. At alle brukte samme IDE ga flere praktiske fordeler: samme kodeformatering på tvers av teamet (vi hadde delt en `.editorconfig` og IntelliJ-formatinnstillinger via prosjektet), samme run-konfigurasjoner for å starte spillet og kjøre tester, og enkel deling av plugins som SonarLint og SpotBugs-integrasjon. Det betydde også at når én person fant ut hvordan en spesifikk feilmelding skulle løses, kunne de andre kopiere fremgangsmåten direkte.

**b) Hva man bør se etter når man velger IDE:**

Det viktigste er **god integrasjon med byggesystemet og språket** – for Java-prosjekter med Maven eller Gradle bør IDE-en automatisk forstå avhengigheter, gi autofullføring basert på reell type-informasjon, og kunne kjøre tester direkte. Like viktig er **navigasjon**: å kunne hoppe til definisjon, finne alle bruksstedene av en metode, søke i hele prosjektet, og refaktorisere trygt (rename, extract method, move class) er det som virkelig sparer tid i et større prosjekt.

For øvrig vil jeg se etter: **integrert debugger** med breakpoints og variabel-inspeksjon (uvurderlig for å feilsøke fysikkbugs i Box2D), **støtte for statiske analyseverktøy** (SpotBugs, SonarLint som plugin), **god Git-integrasjon** (helst med visuell diff og merge-konflikt-løsning), og **plugin-økosystem** for det språket og rammeverket du bruker. At hele teamet bruker samme IDE er også en stor fordel – det reduserer "fungerer-på-min-maskin"-problemer dramatisk og gjør parprogrammering enklere.

---

### 2.3 CI/CD

**a) Hvorfor CI-testing på serveren er nyttig selv om du tester lokalt:**

Det er flere grunner til at server-side CI er verdt det selv om du kjører tester lokalt før push. For det første kjører ikke alltid maskinen din **samme miljø som serveren** – ulike OS, ulike Java-versjoner, ulike installerte avhengigheter. En test som passerer på din Mac kan godt feile på Linux-serveren fordi en filsti er case-sensitive eller en avhengighet mangler. Vi opplevde dette konkret med en lydfil der noen hadde lagt inn en absolutt filsti i stedet for relativ – det fungerte på utvikleren sin maskin, men ble fanget av pipelinen.

For det andre er CI **uforsoneren** – den glemmer aldri å kjøre testene. Det er fort gjort som menneske å hoppe over `mvn test` når man har det travelt før et møte og "bare denne lille endringen". CI tvinger alle pushes gjennom samme prosess, så ingen kan ved et uhell merge inn kode som ikke kompilerer eller har feilende tester. Det gir også **transparens for hele teamet**: alle ser på samme prosjektside om main er grønn, og hvis pipelinen blir rød er det tydelig at noe må fikses før mer arbeid bygges oppå. I tillegg legger CI grunnlaget for å automatisere mer over tid – statisk analyse, deploy, dokumentasjonsbygging – uten å gjøre det avhengig av at en enkeltperson husker å kjøre noe lokalt.

**b) Fanget CI-serveren feil du ikke visste om?**

Ja, ved flere anledninger. Det vanligste var **filsti- og ressursfeil** – Linux-serveren er case-sensitive på filnavn, mens macOS/Windows ofte ikke er det, så kode som lastet en sprite med litt feil navn fungerte lokalt men feilet på CI. Vi opplevde også **flaky tester** som passerte konsistent lokalt men feilet av og til på CI, ofte på grunn av tidsavhengighet eller delt tilstand mellom tester – det var nyttig fordi det avslørte at testene egentlig ikke var helt isolerte. En gang fant CI også en regresjon der en endring i en av audio-klassene brøt en test i en helt annen del av koden – ikke fordi pushet var dårlig, men fordi noen tester hadde implisitt avhengighet til hverandre. Det førte til en runde med opprydding av testene.

---

### 2.4 Statisk analyse

**a) Brukte du SonarQube?**

Ja, vi tittet på SonarQube spesielt mot slutten av prosjektet for å rydde i koden før innlevering. Det viste mange små ting – ubrukte imports, variabler som kunne vært `final`, magiske tall som burde vært konstanter, metoder som var unødvendig komplekse, og felt som burde vært `private`. De fleste enkeltfunnene var små, men summen ga betydelig bedre kvalitet og lesbarhet. Vi brukte også SpotBugs underveis og fikk fanget noen reelle bugs (blant annet en `equals`-implementasjon som ikke håndterte `null` riktig).

Trenger vi enhetstester når vi har SonarQube – eller omvendt? Begge deler. De fanger forskjellige typer feil og er komplementære:

**b) Eksempel på feil statisk analyse kan oppdage, men som er vanskelig med enhetstester:**

- **Innkapsling-brudd:** et felt som burde vært `private` men er `public`, eller en variabel som burde vært `final`. Enhetstester sjekker oppførsel, ikke synlighet – koden kan oppføre seg helt korrekt selv om den er strukturelt dårlig.
- **Død kode / ubrukte variabler / ubrukte imports.** Tester kan ikke fange noe som aldri blir kalt, fordi det per definisjon ikke påvirker oppførsel.
- **Kodeduplisering:** to nesten identiske metoder i forskjellige klasser. Begge kan ha grønne tester, men det er et vedlikeholdsproblem.
- **For høy syklomatisk kompleksitet:** en metode med 15 nestede if-er kan godt være riktig, men er vanskelig å forstå og lett å bryte ved endringer. Tester sier ikke noe om kompleksitet.
- **Resource leaks:** en `FileInputStream` som ikke blir lukket i en sjelden feilsti kan fungere i normale tester, men SpotBugs fanger mønsteret.

**c) Eksempel på feil tester fanger, men som er vanskelig for statisk analyse:**

- **Logikkfeil:** en metode som *skal* returnere summen returnerer differansen. Koden er strukturelt perfekt, kompilerer fint, men gir feil resultat. Bare en test som sammenligner forventet vs. faktisk verdi fanger det.
- **Off-by-one-feil:** løkken kjører ett steg for mye eller for lite. Statisk analyse vet ikke hva som er "riktig" antall iterasjoner.
- **Forretningsregel-brudd:** "spilleren skal miste ett liv ved kollisjon med fiende, men ikke flere ganger innen 2 sekunder". Det er bare en test som verifiserer den faktiske oppførselen som kan sikre dette.
- **Integrasjonsfeil:** to klasser ser hver for seg riktige ut, men når de jobber sammen oppstår feil – f.eks. at scoreCalculator og leaderboard ikke har samme idé om når en runde er ferdig. Statisk analyse ser kun én klasse om gangen.
- **Regresjoner:** "denne feilen ble fikset i fjor – sørg for at den ikke kommer tilbake". En test som verifiserer det opprinnelige problemet er den eneste pålitelige måten.

Kort sagt: statisk analyse fanger **strukturelle og stilistiske problemer**, mens tester fanger **oppførselsfeil**. Du trenger begge.

---

## 3. Design og abstraksjon

### 3.1 Valg av abstraksjoner

Geitesimulatoren er et API som **biologer** primært vil bruke – ikke utviklere med dyp Java-kjennskap. Det skal være lett å lese, intuitivt, og ikke kreve at brukeren forstår LibGDX-interne typer. Det taler sterkt for **egne abstraksjoner** for kart, posisjon og retning, fremfor å eksponere `MapSquare[][]`, `Vector2` og rå grader.

**Kart:** Jeg ville laget en egen `Map`-type fremfor å eksponere `MapSquare[][]` direkte. Et 2D-array gir dårlig API – brukeren må holde styr på indeksgrenser, off-by-one, og hva som ligger på hvert kvadrat. En egen `Map` kan gi metoder som `get(Position)`, `getAll()` (for å iterere over alle ruter), `getNeighbors(Position)`, og kapsle inn både bounds-sjekker og hva som faktisk ligger på hver rute. Innholdet på en rute representeres godt med en `MapCell`-record som holder `Position` og hva som er der (`Food`, `Obstacle`, etc.).

**Posisjon:** En egen `Position`-abstraksjon, ikke direkte `Vector2` eller to int-er. `Position` blir et førsteklasses begrep i API-et med metoder som `distanceTo(Position)`, `shiftedBy(Direction, int steps)` og `movedTo(Position)`. Internt kan den godt bruke `Vector2` for beregninger, men det er en implementasjonsdetalj brukeren ikke skal forholde seg til. Posisjon i koordinater er meningsfullt for biologer på en helt annen måte enn en LibGDX-vektor.

**Retning:** En egen `Direction`-abstraksjon. Tall i grader er feilutsatt – brukeren må huske hvilken konvensjon (0° = øst eller nord? med eller mot klokka?). En `Direction` med både en grov enum (`NORTH`, `SOUTH`, `EAST`, `WEST`) og presise grader via `getDegrees()` gir det beste av to verdener. Geitens oppførsel kan da sjekke "snu mot nord" leselig (`goat.turnTo(Direction.NORTH)`), eller bruke presise vinkler når det trengs.

Hovedargumentet er **lesbarhet for ikke-utviklere**. `position.shiftedBy(direction, 3)` er øyeblikkelig forståelig; `new Vector2(pos.x + 3 * Math.cos(angle), pos.y + 3 * Math.sin(angle))` er det ikke. Den lille ekstra jobben med å definere abstraksjonene tjener man inn flere ganger over på enklere bruk og færre bugs.

---

### 3.2 Å mutere eller ikke mutere?

**Hvorfor ikke-muterbare objekter kan være nyttig:** Når et objekt er immutable, er det umulig for én del av koden å endre tilstanden i et objekt en annen del holder en referanse til. Det fjerner en hel kategori subtile bugs – spesielt "aliasing"-feil, der to deler av koden uventet deler tilstand, og endringer i den ene påvirker den andre. Immutable objekter er også **trygt å dele mellom tråder** uten låsing, og enklere å resonnere om: hvis du har en `Position`, vet du at den representerer ett bestemt punkt for alltid. Det gjør koden mer forutsigbar og lettere å lese. Ulempen er hyppigere allokering (et nytt objekt for hver endring) – men for små verditypede objekter som posisjon og retning er den kostnaden ubetydelig.

**Hva jeg ville valgt:** Jeg ville gjort **`Position` og `Direction` immutable**, men kanskje latt `Map` være mutable (eller i hvert fall ha en mutable variant) siden kartet endrer seg over tid (mat blir spist, geiter beveger seg). For posisjon og retning er det viktigste at en metode som `position.shiftedBy(direction, 3)` returnerer et **nytt** Position-objekt, ikke endrer det opprinnelige. Det gjør det tydelig hvem som "eier" hvilken posisjon, og biologen som bruker API-et kan trygt sende `Position`-objekter rundt uten å være redd for at en metode skal endre dem bak ryggen.

For å gjøre dette tydelig i API-et bør metodenavnene reflektere det: bruk `shiftedBy(...)` og `movedTo(...)` (returnerer nytt objekt) fremfor `shift(...)` og `moveTo(...)` (muterer). Java's `String` følger samme prinsipp: `s.toUpperCase()` returnerer en ny streng, det endrer ikke `s`.

---

### 3.3 Java interface

```java
/**
 * Representerer en posisjon på kartet, definert ved diskrete koordinater (x, y).
 * Position er <b>immutable</b> – alle metoder som "endrer" posisjonen returnerer et nytt objekt.
 */
public interface Position {

    /** Returnerer x-koordinaten til posisjonen. */
    int getX();

    /** Returnerer y-koordinaten til posisjonen. */
    int getY();

    /**
     * Returnerer en NY posisjon flyttet et gitt antall steg i en gitt retning.
     * Den opprinnelige posisjonen endres ikke.
     * @param dir retningen å flytte i (må ikke være null)
     * @param steps antall steg (kan være negativt for å gå motsatt vei)
     */
    Position shiftedBy(Direction dir, int steps);

    /**
     * Returnerer avstanden (i ruter, Manhattan-distance) fra denne posisjonen til en annen.
     * @param other annen posisjon (må ikke være null)
     */
    int distanceTo(Position other);

    /**
     * Returnerer retningen fra denne posisjonen mot en annen posisjon.
     * Hvis posisjonene er like, returneres null.
     */
    Direction directionTo(Position other);
}


/**
 * Representerer en retning i 2D-rommet. Direction er <b>immutable</b>.
 * Tilbyr både grov retning (nord/sør/øst/vest) og presis retning i grader.
 */
public interface Direction {

    /** De fire kardinalretningene som en enum-verdi for enkel sammenligning og switch. */
    enum Cardinal { NORTH, SOUTH, EAST, WEST }

    /**
     * Returnerer den nærmeste kardinalretningen til denne retningen.
     * For eksempel: 45° (nordøst) returnerer NORTH eller EAST avhengig av avrunding.
     */
    Cardinal getCardinal();

    /** Returnerer retningen som en vinkel i grader [0, 360), der 0 = øst og 90 = nord. */
    double getDegrees();

    /** Returnerer den motsatte retningen (rotert 180°). */
    Direction opposite();

    /**
     * Returnerer en NY retning rotert et antall grader fra denne.
     * @param degrees grader å rotere (positivt = mot klokka)
     */
    Direction rotatedBy(double degrees);
}


/**
 * Representerer en celle på kartet med en posisjon og innholdet på cellen.
 * MapCell er en record (immutable).
 */
public record MapCell(Position position, CellContent content) { }


/**
 * Innholdet på en rute. Kan utvides med flere typer ved behov (Water, Rock, osv.).
 */
public enum CellContent { EMPTY, FOOD, OBSTACLE }


/**
 * Representerer kartet geitesimuleringen foregår på.
 * Kartet er en mutable struktur – innholdet kan endres over tid (mat spises osv.).
 * Brukere bør IKKE holde lange referanser til returverdier hvis kartet endres samtidig.
 */
public interface Map {

    /** Returnerer kartets bredde (antall ruter i x-retning). */
    int getWidth();

    /** Returnerer kartets høyde (antall ruter i y-retning). */
    int getHeight();

    /**
     * Returnerer cellen på den gitte posisjonen.
     * @param pos posisjon (må være innenfor kartets grenser)
     * @return cellen på pos
     * @throws IllegalArgumentException hvis pos er utenfor kartet
     */
    MapCell get(Position pos);

    /**
     * Setter innholdet på en gitt celle.
     * @throws IllegalArgumentException hvis pos er utenfor kartet
     */
    void set(Position pos, CellContent content);

    /** Returnerer alle celler på kartet. Rekkefølgen er ikke garantert. */
    Iterable<MapCell> getAll();

    /**
     * Returnerer cellene innenfor en gitt radius fra en posisjon (sirkulært nabolag).
     * Brukes typisk når et dyr "ser seg rundt" etter mat eller hindringer.
     * @param center sentrum (må være innenfor kartet)
     * @param radius radius i ruter (≥ 0)
     */
    Iterable<MapCell> getNeighborhood(Position center, int radius);

    /**
     * Returnerer true hvis posisjonen er innenfor kartets grenser.
     */
    boolean isInside(Position pos);
}
```

---

### 3.4 Gammel kode

Implementasjonen er **ikke et godt grunnlag for vedlikehold og videreutvikling**, og bryter med flere SOLID-prinsipper:

- **Single Responsibility:** Metoden gjør altfor mye – den itererer over kartet, sjekker om en rute har mat, regner ut avstand, og endrer geitens retning. Hver av disse burde vært egne, navngitte operasjoner.
- **Dependency Inversion:** Koden er bundet direkte til primitive typer og lavnivå-strukturer (`int[][] foodMap`, rå indekser, magiske tall). Den avhenger ikke av abstraksjoner, men av konkrete representasjoner. Hvis kartrepresentasjonen endres (f.eks. for å støtte andre typer mat eller hindringer), må `findFood()` skrives om fra bunnen av.
- **Open/Closed:** Koden er så tett vevd sammen med antagelser om hvordan kartet og maten er representert at den er praktisk talt umulig å utvide uten å endre den. Vil du legge til en ny "matvurderings"-regel? Skriv om hele metoden.
- **Interface Segregation:** Det finnes ingen interfaces i det hele tatt. Alt er konkrete klasser og primitive arrays. Det gjør koden umulig å mocke og dermed vanskelig å teste isolert.

I tillegg er det rene **lesbarhets- og vedlikeholdsproblemer**: nestede løkker med indeks-aritmetikk, magiske tall (radius for søk, retningskonvensjoner), uklare variabelnavn, og forretningslogikk blandet med representasjonsdetaljer. En ny utvikler som skal legge til en feature ("geita skal foretrekke fersk gress over tørt") må forstå hele metoden før hen kan endre noe trygt.

Kort sagt: det fungerer kanskje akkurat nå, men hver fremtidig endring kommer til å koste betydelig. Refaktorisering til API-et med `Map`, `Position` og `Direction` er ikke "nice to have" – det er en forutsetning for at koden skal kunne leve videre.

---

### 3.5 Renovasjonsoppgaven

```java
public class Goat {

    private Position position;
    private Direction facing;
    private final Map map;
    private final int sightRadius;

    /**
     * Leter etter mat i nabolaget og snur geiten mot nærmeste matrute.
     * @return true hvis mat ble funnet og geiten snudd, false ellers
     */
    public boolean findFood() {
        // 1. Hvis geita allerede står på mat, trenger den ikke snu seg
        if (map.get(position).content() == CellContent.FOOD) {
            return true;
        }

        // 2. Let gjennom nabolaget etter matruter, finn nærmeste
        MapCell nearest = null;
        int nearestDistance = Integer.MAX_VALUE;

        for (MapCell cell : map.getNeighborhood(position, sightRadius)) {
            if (cell.content() != CellContent.FOOD) continue;

            int distance = position.distanceTo(cell.position());
            if (distance < nearestDistance) {
                nearest = cell;
                nearestDistance = distance;
            }
        }

        // 3. Hvis vi fant mat, snu geita mot den
        if (nearest != null) {
            this.facing = position.directionTo(nearest.position());
            return true;
        }

        return false;
    }
}
```

Antagelser jeg gjør:
- `Goat` har feltvariabler `position` (en `Position`), `facing` (en `Direction`), `map` (en `Map`) og `sightRadius` (hvor langt geita kan se).
- `Map.getNeighborhood(pos, radius)` returnerer cellene innenfor synsavstand, og `Position.distanceTo(...)` regner ut avstand på en måte som matcher hvordan geita beveger seg (her Manhattan-distance).
- `Position.directionTo(...)` returnerer retningen mot en målposisjon, eller `null` hvis posisjonene er like (sjekken i punkt 1 sørger for at vi ikke kommer dit).

Endringen i forhold til den gamle: i stedet for å iterere over hele kartet med rå indekser og blande sammen flere ansvar, gjør jeg én ting per blokk, og hver blokk leser nesten som naturlig språk. Strukturen er "(1) er jeg allerede på mat? (2) hvis ikke, finn nærmeste mat i synet, (3) snu meg mot den hvis den finnes".

---

### 3.6 Refleksjon

Den nye implementasjonen er en klar forbedring. Logikken er **delt i tre tydelige steg** (sjekk gjeldende rute, søk i nabolaget, snu mot nærmeste mat), og hvert steg uttrykker hva som skjer fremfor hvordan det implementeres. Bruk av `Position.distanceTo(...)` og `Position.directionTo(...)` skjuler den kjedelige aritmetikken, og `Map.getNeighborhood(...)` skjuler at vi egentlig itererer over et grid med bounds-sjekker.

Resultatet er ikke bare kortere, men også **enklere å utvide**. Vil vi at geita skal foretrekke fersk mat? Endre filterbetingelsen i løkken. Vil vi at den skal kunne se lengre? Endre `sightRadius`. Vil vi bytte ut distanseberegningen til Euklidsk? Endre `Position.distanceTo(...)` – ingen andre steder i koden trenger å vite om det. Vi har skilt **forretningslogikk** ("geita finner nærmeste mat") fra **representasjonsdetaljer** ("hvordan er kartet lagret"), og det er nettopp det god abstraksjon handler om.

Koden er også mye lettere å teste: jeg kan lage en mock-`Map` med kjent innhold og verifisere at `findFood()` returnerer riktig og snur geita i riktig retning, uten å bygge opp et helt grid med int-arrays.

---

## 4. Brukere og universell utforming

### 4.1 Brukerhistorier

**Brukerhistorie 1 – Stabilt mellomlagre:**
*Som student som tar en lang eksamen, ønsker jeg at svarene mine lagres automatisk og kontinuerlig i bakgrunnen, slik at jeg aldri mister arbeid hvis nettleseren krasjer, internett faller ut eller jeg ved et uhell lukker fanen.*

**Akseptansekriterier:**
- Tekst lagres automatisk minst hvert 10. sekund og umiddelbart etter at jeg navigerer mellom oppgaver.
- Hvis jeg åpner eksamen i en ny fane eller etter nettverksbrudd, ser jeg siste lagrede versjon.
- En tydelig indikator viser at "alt er lagret" eller "lagrer ...", slik at jeg vet status.

**Brukerhistorie 2 – Bedre kodeformatering:**
*Som student som besvarer en programmeringsoppgave i Inspera, ønsker jeg en faktisk kode-editor for kodeblokker med syntax-highlighting, tab-innrykk og monospaced font, slik at jeg kan skrive lesbar Java-kode uten å miste tid på formatering.*

**Akseptansekriterier:**
- Når jeg skriver i en kodefelt, brukes en monospaced font.
- Tab-tasten gir innrykk istedenfor å hoppe ut av feltet.
- Jeg får enkel syntax-highlighting for valgt språk.
- Innrykk og linjeskift bevares når sensor leser svaret mitt – det jeg ser er det de ser.

---

### 4.2 Universell utforming

**a) Konsekvenser for en virksomhet som ikke oppfyller minimumskravene:**

I Norge kan **UU-tilsynet** gi pålegg om utbedring og **tvangsmulkt** for nett- og digitale tjenester som ikke oppfyller forskriften. Virksomheter risikerer også **omdømmetap** og kan miste kunder dersom tjenestene ikke er tilgjengelige for personer med funksjonsnedsettelser. For offentlige virksomheter er konsekvensene strengere – de kan bryte både Likestillings- og diskrimineringsloven og forvaltningens egne krav. Innenfor EU er WCAG 2.1 AA blitt et de facto minimumskrav gjennom EAA (European Accessibility Act), og fra 2025 omfatter dette mange flere private aktører enn før. I tillegg risikerer virksomheten **søksmål fra enkeltpersoner** som diskrimineres ved at de ikke får brukt tjenesten på lik linje.

**b) Et tilfelle der brukeropplevelsen kan være til hinder for noen:**

Et eksempel fra digitale eksamener er **tidsbegrensningen kombinert med skrivetempo**. Mange eksamener i samfunnsfag og programutvikling forventer relativt lange tekstsvar på begrenset tid. For studenter som har dysleksi, dyspraksi, leddsmerter, eller som har norsk som andrespråk, kan selve produksjonen av tekst gå merkbart saktere – ikke fordi de kan stoffet dårligere, men fordi de bruker mer kognitiv kapasitet på selve skrivingen. Resultatet kan bli at de svarer kortere og mer overflatisk enn de egentlig kunne, selv om sensor bare ser sluttproduktet og ikke prosessen.

En annen sak er **tilgang til hjelpemidler under selve eksamen**. Mange studenter er vant til å bruke skjermleser, talegjenkjenning, eller ergonomisk tastatur i hverdagen, og må enten få spesialtilrettelegging på forhånd (som ofte krever dokumentasjon og søknad i god tid) eller klare seg uten. Det betyr i praksis at man tester ferdighetene under uvant verktøy-bruk. En mer fleksibel eksamensplattform som tillot flere innstillinger – større tekst, høyere kontrast, kompatibilitet med skjermleser, tilkobling av eget tastatur – uten at det krever forhåndsgodkjenning, ville senket terskelen for mange uten å gå på kompromiss med selve vurderingen.

---

*Slutt på besvarelsen.*
