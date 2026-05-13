# INF112 25V Obligatorisk oppgave 0–4: Prosjekt $SJANGER-spill

Dette semesterets programmeringsprosjekt er å designe og implementere lage et (enkelt/prototype) [dataspill](https://no.wikipedia.org/wiki/Videospill). Dere står relativt fritt til å velge sjanger og konsept (se tips under), men vi har visse krav til design, utvikling og implementasjon som må oppfylles (se avsnitt om krav for detaljer):

* Spillet skal ha grafikk og lyd. Vi anbefaler sterkt å gå for [2D–grafikk](https://en.wikipedia.org/wiki/2D_computer_graphics), gjerne [tile-based](https://en.wikipedia.org/wiki/Tile-based_video_game).
* Spillet skal ha både spillkarakterer som styres av en menneske-spiller (med tastatur / mus / gamepad) og som er kontrollert av maskinen.
* Spillet skal programmeres i Java, men dere står fritt til (og anbefales) å bruke ekstra biblioteker. Prosjektet skal kunne bundles som en [JAR-fil](https://en.wikipedia.org/wiki/JAR_(file_format)) som kan kjøres på både Windows, Linux og Mac med Java SE 19.
* Spillet kan være en «klone» av et eksisterende spill, men dere må følge lov om [opphavsrett](https://no.wikipedia.org/wiki/Opphavsrett), [varemerker](https://no.wikipedia.org/wiki/Varemerke) osv, og [vanlige regler om plagiat](https://www.uib.no/studiekvalitet/77864/hva-er-fusk-og-hvilke-konsekvenser-f%C3%A5r-det) – dere må gjøre arbeidet selv, og alle på teamet må delta.

## Vurdering og innleveringer

* Del A0 har frist **7. februar 2025**, og resten av Del A har frist **14. februar 2025**.

* Del B består av å videreutvikle prosjektet, og rapportere om fremdrift og erfaringer. Leveres som Oblig 2–4, og fristene er **7. mars 2025**, **11. april 2025** *(release candidate)*, **2. mai 2025** *(final)*.

* Del C er presentasjon av prosjektet, og skjer i mai (evt slutten av april).

* Semesteroppgaven teller 40% (TODO) av sluttkarakteren – se [liste over vurderingskriteriene](prosjekt/vurdering).

* Alle innleveringer og prosjektpresentasjone må være godkjent for å fullføre emnet.

Se [mer om innlevering her](prosjekt/innlevering) og i oppgaveteksten under. Lag gjerne en sjekkliste så dere får med dere alle punktene.

## Tips til konsept

I fjor fikk studentene samme oppgave som i år; året før laget de [plattformspill](https://en.wikipedia.org/wiki/Platform_game) (se [skjermbilder](https://git.app.uib.no/inf112/22v/tips/-/wikis/Prosjektpresentasjoner-INF112-2022) og [oppgavetekst](https://git.app.uib.no/inf112/22v/lectures/-/tree/master/prosjekt)). De siste årene før det har de laget et konkret brettspill - [RoboRally](https://en.wikipedia.org/wiki/RoboRally) (https://git.app.uib.no/inf112/22v/lectures/-/tree/master/2021/studentTasks). Noen aktuelle ideer (ca. i anbefalingsrekkefølge):

* Hvis dere har problemer med å velge noe, velg plattformspill - det tilfredsstiller kravene, og det finnes masse tips og ressurser på nettet. F.eks. [Super Mario Bros](https://en.wikipedia.org/wiki/Super_Mario_Bros.) ([prøv online!](https://supermarioplay.com/)), [Sonic the Hedgehog](https://en.wikipedia.org/wiki/Sonic_the_Hedgehog), og [Bubble Bobble](https://en.wikipedia.org/wiki/Bubble_Bobble). 
* [Shoot 'em up](https://en.wikipedia.org/wiki/Shoot_%27em_up) - som f.eks. [Space Invaders]() eller [Galaga](https://en.wikipedia.org/wiki/Galaga). (Space Invaders har vært både eksamen og oblig i INF101)
* [Roguelike/lite](https://en.wikipedia.org/wiki/Roguelike) – som f.eks. [Rogue](https://en.wikipedia.org/wiki/Rogue_(video_game)) og [NetHack](https://en.wikipedia.org/wiki/NetHack). Dette har vært oblig i INF101 i flere år, og har fordelen av å være veldig populært blant hobbyutviklere, så det er [enkelt å få hjelp](https://www.reddit.com/r/roguelikedev/) og finne [tutorials](https://www.reddit.com/r/roguelikedev/wiki/python_tutorial_series/).
* [Racing game](https://en.wikipedia.org/wiki/Racing_game) – som f.eks. [Super Mario Kart](https://en.wikipedia.org/wiki/Super_Mario_Kart)
* [Tower Defence](https://en.wikipedia.org/wiki/Tower_defense) – som f.eks. [Plants vs. Zombies](https://en.wikipedia.org/wiki/Plants_vs._Zombies)
* En del varianter av [puzzle game](https://en.wikipedia.org/wiki/Puzzle_video_game) kan også være egnet, f.eks. [Boulder Dash](https://en.wikipedia.org/wiki/Boulder_Dash) ([eksempel fra 2017](https://www.youtube.com/watch?v=woGsuwArWo0)) eller [Lode Runner](https://en.wikipedia.org/wiki/Lode_Runner).
* Evt. en enkel utgave [livssimulator](https://en.wikipedia.org/wiki/Life_simulation_game) – liknende [Stardew Valley](https://en.wikipedia.org/wiki/Stardew_Valley) eller [Animal Crossing](https://en.wikipedia.org/wiki/Animal_Crossing)

Det er viktig at dere velger noe som **alle på teamet er kjent med og komfortable med**. Dere blir vurdert som programvareutviklere, ikke spilldesignere, og ting som regeldesign og utvikling av plott og bakgrunn er *veldig tidkrevende*. Det er viktigere å legge vekt på god utviklingsmetododikk og god teknisk arkitektur og design enn å komme opp med et kreativt konsept. Dvs. plattformspill eller shoot 'em up er gode valg, men dere kan velge selv (vi har ikke tenkt å tvinge dere til å ta kjedelige men fornuftige konsept-valg!).

*(Hvis dere undrer dere over at de fleste eksemplene er fra 80-tallet, så er det ikke bare fordi foreleser spilte dataspill på 80-tallet, men også fordi omfanget er rimelig overkommelig for et ett-semesters studentprosjekt. Moderne spill krever gjerne massive investeringer, store teams og mange års utvikling.)*


## Krav til prosjektet
Se forøvrig krav til innlevering i hver oppgave. Det kan være vi endrer litt på kravene underveis, men vi skal prøve å være rimelig greie kunder :)

### Oppsett/biblioteker
* Java: Spillet skal skrives i Java (f.eks. Java 17 eller 21)
* JUnit: For testing skal JUnit 5 brukes
* Anbefalt oppsett: Vi lager en fork til dere av [libGDX skjelettprosjektet](https://git.app.uib.no/inf112/25v/inf112.25v.libgdx-template). Det er satt opp med Maven, inneholder litt eksempelkode og er også satt opp for kontinuerlig integrasjonstesting (CI).
    * Alternativt oppsett: hvis noen *virkelig* vil, så går det an å bruke et annet grafikkrammeverk (f.eks. JavaFX) eller byggesystem (f.eks. Gradle). Ta i såfall kontakt.Byggesystem:  Vi anbefaler Maven, men det går også an å bruke Gradle.
    * Fysikk/kollisjon: Velg selv (om det trengs), men [Box2D](https://libgdx.com/wiki/extensions/physics/box2d) eller [jbump](https://github.com/tommyettinger/jbump) er populære muligheter. Realistisk fysikk pleier ofte å gi dårligere opplevelse hvis man skal styre en figur på skjermen.
* Versjonskontroll: Bruk Git til versjonskontroll, og gjør aktiv bruk av kollaborativ funksjonalitet i GitLab. Innlevering skjer ved å gi oss tilgang til prosjektet i [git.app.uib.no](https://git.app.uib.no/)

### Krav til generelle egenskaper
* Vi legger vekt på god bruk av abstraksjon (pakke vekk detaljer bak et veldefinert grensesnitt).
* Koden bør følge vanlige, moderne objektorienterte designprinsipper ([SOLID](https://en.wikipedia.org/wiki/SOLID), etc.)
* Koden skal ha generelt god kvalitet og være dokumentert slik at andre kan legge til utvidelser uten å måtte gå inn og lese alle implementasjonsdetaljene.
* Det må være enkelt for oss å følge med på hva dere har gjort, hva som endrer seg, og hvordan vi bruker/tester spillet.

### Krav til spesifikke egenskaper
(Vi går gjennom disse tingene i løpet av semesteret – og det går helt fint å starte uten, og heller refaktorere når man blir kjent med systemet. Kravene er til *sluttproduktet*, ikke milepælene.)

* Spillet skal ha en forside, og en hjelpeside som viser hvordan kontrollene funker (det kan være samme siden).
* Grafikk/brukegrensesnitt skal være mest mulig adskilt¹ fra spill-logikken ([MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) e.l.). (Det kan være vanskelig å få dette til med *scene graph*-systemene i libGDX og JavaFX, men i det minste bør input være adskilt (slik at f.eks. spiller-objektet ikke jobber direkte med tastaturet))
* Det skal være minst én lyd som er koblet til en hendelse i spillet (f.eks. tyggelyder når noen spiser). Koden som håndterer lyd må også være adskilt.
* Det skal være mulig å opprette enheter i spillet ved hjelp av [objektfabrikker](https://en.wikipedia.org/wiki/Factory_(object-oriented_programming)) – f.eks. å tegne kart basert på en streng (`#` er vegg, `|` er en søyle, `o` er et hull i bakken, e.l.)
* Fabrikkene skal ikke være hardkodet (f.eks. med et stort `switch`-statement) – dvs. man kan legge til flere ting uten å endre koden til selve fabrikken (→ ha mulighet til å registrere nye type objekter)
* Det skal være mulig å ha ting i spillet som endrer oppførselen til spiller (eller andre) – f.eks. power-ups ([spis en sopp, bli kjempestor](https://mario.fandom.com/wiki/Super_Mushroom) / [plukk en blomst, skyt flammer](https://mario.fandom.com/wiki/Fire_Flower)) eller klær ([putt på støvler, gå lange skritt](https://en.wikipedia.org/wiki/Seven-league_boots) / [ta på kappe, bli usynlig](https://en.wikipedia.org/wiki/Cloak_of_invisibility)). Det bør være mulig å legge til nye power-ups uten å endre koden til spilleren (eller andre som blir påvirket).
* Koden skal ha tester, med minimum 75% coverage.
* Public metoder må være dokumentert (som minimum: metoder som brukes på tvers av pakker)
* Automatiske tester skal kunne kjøres uten å kreve interaksjon med brukeren eller bruk av grafikk/lyd.
* *Kilder må oppgis* for all grafikk og lyd, og dere må ha rett til å bruke det (f.eks. ting som er dekket av [Creative Commons](https://creativecommons.org/licenses/?lang=no)-lisens eller andre open source lisenser (minimum «fri ikke-kommersiell bruk»)). For ting dere lager selv må dere legge ved lisens som klargjør hvordan ting (evt) kan gjenbrukes.
* Generell dokumentasjon, referater og svar på oppgaver skrives i Markdown og legges i `doc/` undermappen, hovedfilen for hver innlevering skal ha navnet `doc/obligX.md`. Det går evt. an å lenke til vedlagte bilde/HTML/PDF-ressurser – men *all tekst/vedlegg som inngår i en innlevering må være tydelig lenket til fra hovedfilen*. Ting skrevet i tekstbehandler må konverteres først.

¹ *Med «adskilt» tenker vi at implementasjonen av klasse A ikke skal være direkte avhengig av klasse B (spesielt på tvers av pakker), men heller jobber mot et interface I. Dvs. hvis B f.eks. er lydavspilleren, så har A «abstrahert bort» hvordan lyd funker. (Se [coupling](https://en.wikipedia.org/wiki/Coupling_(computer_programming)), [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns), [dependency inversion priciple](https://en.wikipedia.org/wiki/Dependency_inversion_principle), og [single responsibility principle](https://en.wikipedia.org/wiki/Single-responsibility_principle))*

### Krav til deltakelse
Alle i teamet må…
* delta i design- og utviklingsarbeidet – men dere kan ha spesialiserte roller,
* møte på gruppe, eller være i kontakt med de andre i løpet av uken (dersom det er grunner til at det ikke går an, må man orientere/avtale det med de andre i teamet),
* bidra i programmerings-, skrive- og testearbeidet og committe ting til Git (evt. nevne om man har parprogrammert) – vi sjekker at Git-loggen er rimelig balansert,
* ha tilstrekkelig oversikt til å kunne forklare arkitektur og slikt – men det er ikke nødvendig å kjenne alle implementasjonsdetaljer,
* kunne forklare hva som skjer i kode man har skrevet selv, og i *minst én* pakke/klasse/etc som noen andre har skrevet,
* ha skrevet *minst én* test,
* ha skrevet noe kode som interagerer med noen andres kode (mer enn bare å kalle `doit()`).
* følge opp arbeidsoppgaver man avtaler i teamet,
* bidra til, følge opp og ta ansvar for at alle i teamet (+gruppelederne!) har et trygt og godt arbeidsmiljø, uavhengig av bakgrunn/kjønn/legning/livssyn/funksjonsnedsettelse/etc.

#### Arbeidsmiljø
Tenk på prosjektet som en (deltids)jobb, og ta ansvar for at du selv og dine medstudenter har et godt arbeidsmiljø. Relevante ressurser som det er verd å sette seg inn i: [Arbeidsmiljøloven](https://lovdata.no/dokument/NL/lov/2005-06-17-62) (spesielt [§2-3](https://lovdata.no/lov/2005-06-17-62/§2-3), [§4](https://lovdata.no/lov/2005-06-17-62/§4-1) og [§13](https://lovdata.no/lov/2005-06-17-62/§13-1))), samt [UiBs retningslinjer](https://regler.app.uib.no/regler/Del-2-Forskning-utdanning-og-formidling/2.2-Utdanning/2.2.7.-Studentvelferd/Retningslinjer-for-konflikter-mobbing-trakassering-og-seksuell-trakassering-som-beroerer-studenter-ved-Universitetet-i-Bergen/).

# Del A
## Organiser teamet
### Oppgave A0
(**Frist 7. februar 2025** – «leveres» ved å se til at gruppeleder er orientert)

* Gruppen trenger et gruppenavn, som også blir navnet på GitLab-gruppen deres.
* Opprett en kanal for teamet på Discord-serveren.
* Kartlegg hvilken kompetanse de ulike medlemmene av teamet har, og ta med en kort oppsummering i innleveringen.

### Oppgave A1
(**Frist 14. februar 2025** – men gjerne før! Se til at gruppeleder finner prosjektet og at [alle i undervisningsteamet har tilgang](prosjekt/innlevering) (som developer/maintainer).)

Dere trenger et Git repo for prosjektet. Gi beskjed om hva teamet skal hete, så fikser vi det:

1. Én av dere sender melding om team-navn.
2. Vi oppretter prosjekt og legger til en av dere som eier.
3. Logg inn på [git.app.uib.no](https://git.app.uib.no/) med Feide / Dataporten. (Brukere som har logget inn med GitHub har færre rettigheter.)
4. Gå til *Manage → Members*, velg *Invite members* øverst til høyre, og legg til resten av team-medlemmene (som *Owner* eller *Maintainer*)

* `README.md`-filen skal (gjennom hele semesteret) holdes oppdatert med:
    * navn på team-medlemmene, teamet og prosjektet, samt gruppenummer
    * kort beskrivelse av spillet og hvordan det brukes (f.eks. hvilke tastetrykk som gjør hva).
    * hvordan koden kjøres.
    * hvor evt. grafikk/lyd-ressurser er hentet fra.

F.eks. (nedkortet utgave):
```markdown
# INF112 Project – *Kurt-Mario in the Land of the Mushroom Princess*

* Team: *De hundre tollerne* (Gruppe 5): *Ole-Per Javasen, Jon-Jan Gitland, Nils-Katrine Mavensen, Birgitte-Ingrid C. Sharpee*
* Lenke til GitLab/Trello/etc.

## Om spillet
*«Kurt-Mario er i trøbbel igjen! Han er sent ute til eksamen, han har ikke lest nok – og hvor er egentlig eksamenslokalet? Hjelp Kurt-Mario å hoppe fra etasje til etasje på Høyteknologisenteret, plukke opp viktig kunnskap (og snacks!) på veien og nå frem til eksamen i tide. Men pass opp for de skumle professorene – vi har ikke tid til å høre om flere teoremer nå!»*

## Kjøring
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/kurt-mario-1.0-SNAPSHOT-fat.jar`
* Krever Java 21 eller senere

## Kjente feil
Kurt-Mario blir *mindre* – ikke større – når han spiser sopp fra Nygårdsparken.

## Credits
Tileset fra https://opengameart.org/content/2d-cave-platformer-tileset-16x16
```

Dere skal så fordele roller dere bestemmer dere for (kan feks være
teamlead, kundekontakt osv). Skriv en kort begrunnelse for hvilke
roller dere bestemmer dere for og hvorfor. Sett opp et project board
([GitLab issue board](https://docs.gitlab.com/ee/user/project/issue_board.html) eller Trello, f.eks.). Dette må settes opp, og det er viktig at alle i
gruppen vet hvordan de bruker verktøyene dere velger.

## A2–A5: Iterativ Prosess

**Frist 14. februar 2025** – men dere skal jobbe videre med og forbedre ting i løpet av semesteret, så dette er bare et enkelt førsteutkast.

## Oppgave A2: Konsept

Lag en røff beskrivelse av hva som inngår i spillet. F.eks., for et plattform-spill vil viktige aspekter typisk være:

```
* Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover
* Todimensjonal verden:
   * Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»)
   * Vegg – vertikal flate som spilleren ikke kan gå gjennom
   * Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle
* Fiender som beveger seg og er skadelige ved berøring
* Spilleren kan samle poeng ved å plukke opp ting
* Utfordringen i spillet er gjerne en eller flere av: å bevege seg gjennom terrenget uten å falle utfor, å samle nok poeng, å bekjempe fiendene, å nå frem til og bekjempe en «big boss» 
```

Vanlige aspekter dere kan vurdere å ha med:

```
* Verden er bygget opp av blokker med fast størrelse (felter i et 2D-rutenett)
* Verden har plattformer eller stiger som man kan hoppe opp gjennom
* Verden er større enn skjermen og scroller horisontalt eller vertikalt
* Plattformer som beveger seg
* Spilleren kan drepe fiendene ved å hoppe på dem eller skyte dem
* «Power-ups» som gir spilleren spesielle krefter
* Skjulte gjenstander
* Akrobatikk
```

Referer gjerne til eksisterende spill som illustrerer konseptet. F.eks. velkjente eksempler på plattformspill er [Donkey Kong (1981)](https://en.wikipedia.org/wiki/Donkey_Kong), [Mario Bros. (1983)](https://en.wikipedia.org/wiki/Mario_Bros.), [Metroid (1986)](https://en.wikipedia.org/wiki/Metroid), [Bubble Bobble (1986)](https://en.wikipedia.org/wiki/Bubble_Bobble), [Castlevania (1986)](https://en.wikipedia.org/wiki/Castlevania), [Sonic the Hedgehog (1991)](https://en.wikipedia.org/wiki/Sonic_the_Hedgehog) – alle disse er fremdeles populære og tilgjengelige i nye utgaver.

Dere kan forbedre og endre på konseptbeskrivelsen underveis, etterhvert som dere får bedre innsikt i hva dere vil – men det er viktig å ha formulert konkrete tanker på et tidlig tidspunkt.

## Oppgave A3: Velg og tilpass en prosess for teamet

Dere må finne ut om dere vil følge en bestemt prosjektmetodikk (XP,
Scrum, Kanban, parprogrammering, testing osv), evt hvilke elementer
fra ulike prosjektmetodikker dere vil ha med.

Diskuter i teamet hvilke metoder som hjelper teamet med å utvikle
fungerende og veldokumentert programvare under prosjektet. Diskuter
også hvilke tilpassninger som trengs for å fungere godt i et slikt
studentprosjekt. Involver gjerne gruppeleder i diskusjonen om mulige
problemer.

Vurder viktige aspekter ved prosessen, for eksempel hvordan organisere
møter, definisjon og tildeling av oppgaver, oppfølging av arbeid,
hvilke programvareutviklingsaktiviteter som trengs (og når), hvilke
prosjekteringsaktiviteter som trengs (og når). Skriv en kort
beskrivelse av prosessen i prosess- og prosjektplanen.

Diskuter i teamet hvordan dere skal organisere dere under
prosjektet. Noen viktige elementer:

* Møter og hyppighet av dem
* Kommunikasjon mellom møter
* Arbeidsfordeling
* Oppfølging av arbeid
* Deling og oppbevaring av felles dokumenter, diagram og kodebase

(Titt på f.eks. [Agile Principles](https://www.agilealliance.org/agile101/12-principles-behind-the-agile-manifesto/), [Agile 101](https://www.agilealliance.org/agile101/).)

Skriv en kort beskrivelse av hvordan teamet planlegger å
organisere prosjektet den første tiden. (Dere kommer sikkert til å forbedre og tilpasse prosessen underveis.)

## Oppgave A3: Få oversikt over forventet produkt

Dere skal lage et [FYLL INN KONSEPT HER]-spill. Spillet må inneholde det som er nevnt i innledningen (øverst) og i *Krav til prosjektet*, dere kan ellers velge hva dere vil ha med, og hva dere vil legge mest vekt på. I første omgang er det viktig å holde fokus på å nå frem til et *minimum viable product* (MVP) – enklest mulig, men fremdeles spillbart.

Dere skal lage en spesifikasjon som inneholder:
* En kort beskrivelse av det overordnede målet for applikasjonen
* Krav til [*Minimum Viable Product* (MVP)](https://www.agilealliance.org/glossary/mvp/) – se eksempel under
* En liste over [brukerhistorier](https://www.agilealliance.org/glossary/user-stories/) til systemet basert på MVP-kravene (ha gjerne konkrete [personas](https://www.agilealliance.org/glossary/personas/) i tankene)
* For hver brukerhistorie, skal dere ha [akseptansekriterier](https://www.productplan.com/glossary/acceptance-criteria/) og arbeidsoppgaver, samt beskrivelse av hvilke krav brukerhistorien oppfyller (dette lager dere kun for historier dere er ferdige med, holder på med, eller skal til å begynne med)
* En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven).

Eksempel på MVP for et plattformspill (burde være mulig å oppnå innen andre innlevering / midten av mars):

```
1. Vise et spillebrett
2. Vise spiller på spillebrett
3. Flytte spiller (vha taster e.l.)
4. Spiller interagerer med terreng
5. Spiller har *poeng* og interagerer med poenggjenstander
6. Vise fiender/monstre; de skal interagere med terreng og spiller
7. Spiller kan dø (ved kontakt med fiender, eller ved å falle utfor skjermen)
8. Mål for spillbrett (enten et sted, en mengde poeng, drepe alle fiender e.l.)
9. Nytt spillbrett når forrige er ferdig
10. Start-skjerm ved oppstart / game over
```

### Brukerhistorier
Brukerhistorier er en måte å spesifisere funksjonalitet på ved hjelp av en liten historie:  Som *rolle* trenger jeg *funksjonalitet* for å *oppnå nytteverdi*. Dette gjør det klart *hva* som trengs, men også *hvorfor* det trengs (slik at man kan prioritere) og *hvem* som er målgruppen (hva vil være passende/egnet for brukeren?).

For eksempel: «Som spiller trenger jeg å kunne skille plattformer/vegger fra bakgrunnselementer slik at jeg avgjøre hvordan jeg skal styre spillfiguren.»  (Avhengig av bruker (svaksynt? barn? fargeblind?) vil dette påvirke den grafiske utformingen.)  Eller: «Som programmør trenger jeg å kunne skille plattformer, vegger og bakgrunnselementer fra hverandre, slik at jeg kan avgjøre om spillfiguren kan bevege seg i en gitt retning»

## Oppgave A4: Kode

En del av leveransen for denne oppgaven skal være kode:

* **Prosjektoppsett som kompilerer og viser noe på skjermen (inkludert i utleverte templates)**

**For følgende punkter trenger dere ikke levere inn kode, bare rapportere veldig kort hva dere har gjort:**

* Gjør litt utprøving for å bli kjent med rammeverk og verktøy – f.eks. få noe til å bevege seg med mus/tastatur, vis en animasjon, e.l.
* Det kan være lurt å bruke litt tid på å prøve ut rammeverk før dere bestemmer dere. I fjor brukte de fleste `libGDX` (som er generelt veldig populært), men ikke alle var like fornøyde med opplevelsen – dere kan f.eks. dele utforskningen mellom dere, teste ting litt, og så ta en avgjørelse. (Det finnes ingen perfekte rammeverk for å lage spill eller annen programvare – så at man irriterer seg over et valg betyr ikke nødvendigvis at man ville irritert seg mindre om man valgte noe annet.)
* Alle på teamet skal gjøre noe (par)programmering for å bli kjent med ting

Det går an å eksperimentere på egne Git-branches om dere vil, eller evt. ved å lage en personlig fork av prosjektet.


Fokuset i starten *(frem mot innlevering 2)* bør være på å levere et minimum viable product (MVP) så raskt som mulig – innen andre innlevering – så bruk gjerne tiden på ting som er relevante for det. *

## Oppgave A5: Oppsummering

Utfør et kort prosjekt-retrospektiv og diskuter hva som gikk bra, hva som ikke
fungerte helt som forventet, hva (om noe) som ikke virket i det hele tatt, og
eventuelle nye aktiviteter eller verktøy som teamet vil prøve ut i løpet av
neste obligatoriske oppgave. Diskuter hvorfor ting fungerte eller ikke fungerte.
Skriv opp en kort oppsummering av diskusjonen, og last opp til team repo-et.

På slutten av denne oppgaven kan dere gjøre en liten vurdering av hvor bra dere
traff på oppgaven. Dette kan dere bruke til å justere hvor mange oppgaver dere
tenker å få inn i neste iterasjon, som da leveres med obligatorisk oppgave 2. (Det er helt vanlig å feilestimere tidsbruk / hvor vanskelig ting er.)

## Innlevering av Del A.

Oblig 1 består av deloppgave A0–A5.

**Sjekkliste:**

* [ ] Oppsett av Git-gruppe/repo, README-fil; teamorganisering i `doc/oblig1.md` (A1)
* [ ] Beskrivelse av konsept i `doc/oblig1.md` (A2)
* [ ] Kort beskrivelse av hvordan av prosess / prosjekt-organisering i `doc/oblig1.md` (A3)
* [ ] Oppsett av kode-skjelett (i prosjekt-repo), ha begynt å eksperimentere med rammeverk/kode (A4)
* [ ] Kort oppsummering / retrospektiv i `doc/oblig1.md` (A5)


Innleveringsfrist: **14. februar 2025, klokken 23:59**. Innlevering skjer i Git-repo tilgjengelig for gruppeleder, og den aktuelle versjonen [tagges](https://git-scm.com/book/en/v2/Git-Basics-Tagging) `oblig1`. [Se her for tekniske tips](prosjekt/innlevering).

### Forventninger

Til denne innleveringen forventer vi at dere har satt opp og
organisert team og prosjekt, at dere skriver brukerhistorier (husk å
få med hvilke krav brukerhistoriene dekker i beskrivelsen) og
(minimal/prototype) kode for de første MVP-punktene.

*Målet er å **komme i gang** og få tilbakemelding tidlig i prosessen – dere vil sikkert endre på ting til senere innleveringer, etterhvert som dere lærer mer.*

Dere fortsetter med de neste kravene (med tanke på neste innlevering) etterhvert som dere blir ferdig.

### Vurderingskriterier og vekting

For å få oppgaven godkjent må gruppen:
* Opprettet en repo-struktur som beskrevet i A1.
* Skrive fornuftige commit meldinger når endringer lastes opp i gruppens repo.
* Laste opp de etterspurte resultatene i repo.
* Laste opp leveransen til gitlab i markdown-format
* Den skriftlige delen skal leveres i Markdown-format i filen `doc/oblig1.md` (stavet *nøyaktig slik*, med *små bokstaver*). Hver obligatoriske oppgave blir da `obligX.md` i `doc/`-mappen.
* Tag en commit av kildekoden som er leveransen til hver innlevering. Dette gjør det enkelt for de som skal vurdere å gjøre det på riktig tidspunkt.

Måten dere jobber sammen og løfter alle medlemmene i teamet vil vurderes sammen
med kodekvalitet, oversiktlighet og dokumentasjon. ‘Formelle ting’, som at prosjektet er satt opp med navn, er tilgjengelig og inneholder informasjonen vi trenger for vurdering inngår også i vurderingen.

**Vektlegging:** Semesteroppgaven utgjør totalt **40%** av sluttkarakteren, hvorav halvparten er design/prosjekt/rapportering (den skriftlige delen av innleveringen), og den andre halvparten er programvaren dere bygger. Dere får tilbakemelding på begge deler underveis, men vi venter til siste innlevering med å sette endelig poeng på programvare-delen. Dvs.: leveransen i `doc/oblig1.md` poengsettes nå og teller 5% av sluttkarakteren, koden dere leverer vil også utgjøre 5%, men der vurderer vi sluttproduktet.

### Generelt om vurdering av semesterprosjektet

Hovedvekten av vurderingen på sluttproduktet ligger på
spillmekanikken, måten dere jobber sammen og refleksjon over
prosessen, ikke det visuelle. Det er ok å bruke f.eks. [Open Game
Art](https://opengameart.org/) for grafikk – men pass på at dere
overholder lisensvilkårene, og alltid opplyser om hvor grafikk/lyd er
hentet fra (også om dere har laget det selv).

Koden skal være plattformuavhengig, altså skal den fungere uavhengig
av operativsystem.

### Generelle tips/krav til kode
Dere kommer til å lære om disse tingene i løpet av kurset, så ikke få panikk om det høres ukjent ut:
* **Kvalitet:** Test grundig, bruk verktøy som [SpotBugs](https://spotbugs.github.io/) for å finne feil, hold koden lesbar med autoformattering osv.
* **Abstraksjon/[dependency inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle):** Tenk *interfaces* og skjul klassene – det gjør det enklere å bytte ut funksjonalitet og å «mocke» resten av systemet når du tester en enhet.
* **Decoupling:**  Sørg for at forskjellige enheter i programmet er mest mulig uavhengig av hverandre – spesielt med tanke på *model* (intern representasjon av spillet) og *view* (grafisk representasjon beregnet på brukeren) – se [Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

# Del B

For de neste innleveringene skal dere jobbe videre iterativt – forbedre og tilpasse prosessen og spesifikasjonen; videreutvikle implementasjonen i tråd med kravene; og vurdere/reflektere over arbeidet. Dvs. forbedre Del A iterativt og inkrementelt inntil dere kommer i land med et ferdig produkt.

* Lag korte referat fra team-møtene (ha med dato, hvem som var tilstede, hva dere diskuterte, hvilke avgjørelser dere tok, og hva dere ble enige om å gjøre til neste gang)

## Prosjektrapport
(Leveres i `doc/obligX.md`.)

Følgende skal med i team/prosjekt-rapporten, enten ved at det går frem fra referatene, og/eller at dere skriver en oppsummering. Sjekk at dere har vært innom alle punktene, selv om det bare er for å si at det fungerer bra.

* Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?
* Trenger dere andre roller? Skriv ned noen linjer om hva de ulike rollene faktisk innebærer for dere.
* Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
* Hvordan er gruppedynamikken? Er det uenigheter som bør løses?
* Hvordan fungerer kommunikasjonen for dere?
* Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.
* Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)
* Referat fra møter siden forrige leveranse skal legges ved (mange av punktene over er typisk ting som havner i referat).
* Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
* *For siste innlevering (Oblig 4)*: Gjør et retrospektiv hvor dere vurderer hvordan hele prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt? 

## Krav og spesifikasjon
(Leveres i `doc/obligX.md`.)

* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet. 
* For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
* Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.
* Forklar kort hvordan dere har prioritert oppgavene fremover
* Har dere gjort justeringer på kravene som er med i MVP? Forklar i så fall hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?
* Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. 
* Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs). 
* Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.

## Produkt og kode

(Evt. tekst / kommentarer til koden kan dere putte i en egen `## Kode`-seksjon i `doc/obligX.md`.)

* *Utbedring av feil:* hvis dere har rettet / forbedret noe som er påpekt tidligere, lag en liste med «Dette har vi fikset siden sist», så det er lett for gruppelederne å få oversikt.
* I `README.md`: Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelederne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
* Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OS X – dere kan f.eks. spørre de andre teamene på gruppen om dere ikke har tilgang til alle platformene. *OBS!* Den vanligste grunnen til inkompatibilitet med Linux er at filnavn er *case sensitive*, mens store/små bokstaver ikke spiller noen rolle på Windows og OS X. Det er viktig å sjekke at stiene til grafikk og lyd og slikt matcher eksakt. Det samme vil antakelig også gjelde når man kjører fra JAR-fil.
* Lag og lever et [klassediagram](https://en.wikipedia.org/wiki/Class_diagram). (Hvis det er veldig mange klasser, lager dere for de viktigste.) Det er ikke nødvendig å ta med alle metoder og feltvariabler med mindre dere anser dem som viktige for helheten. (Eclipse har [forskjellige verktøy for dette](https://marketplace.eclipse.org/category/free-tagging/class-diagram).)
* Kodekvalitet og testdekning vektlegges. Dersom dere ikke har automatiske tester for GUI-et, lager dere manuelle tester som gruppelederne kan kjøre basert på akseptansekriteriene.
* Statiske analyseverktøy som [SpotBugs](https://spotbugs.github.io/) eller [SonarQube](https://www.sonarqube.org/) kan hjelpe med å finne feil dere ikke tenker på. Hvis dere prøver det, skriv en kort oppsummering av hva dere fant / om det var nyttig.
* Automatiske tester skal dekke forretningslogikken i systemet (unit-tester). *Coverage* kan hjepe med å se hvor mye av koden som dekkes av testene – i Eclipse kan dette gjøres ved å installere *EclEmma* gjennom Eclipse Marketplace.
* Utførte oppgaver skal være ferdige. Slett filer/kode som ikke virker eller ikke er relevant (ennå) for prosjektet. (Så lenge dere har en egen git branch for innlevering, så er det ikke noe stress å fjerne ting fra / rydde den, selv om dere fortsetter utviklingen på en annen gren.

## Innlevering

Som tidligere, tag en Git-commit med `obligX`, og se til at gruppeleder vet at innleveringen er klar.

# Del C

Hold presentasjon hvor teamet presenterer prosjeket for alle:

* Dere skal ha med demo av spillet, og
* hva er det viktigste dere har lært om å jobbe i et team?
* hva er det viktigste dere har lært om å jobbe på et større prosjekt over lengre?
* hva har dere lært om programmering/programutvikling?
* hva ville dere gjort noe annerledes om dere hadde gjort det igjen?




