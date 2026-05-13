# INF112 Eksamensguide

Denne guiden er basert på kursnotater, prosjektdokumentasjon og tidligere eksamensoppgaver fra 2023, 2024 og 2025. Temaer merket **Høy prioritet** dukker opp både i undervisningsmaterialet og i eksamensoppgavene, og er derfor de tryggeste stedene å bruke repetisjonstid.

## Kursoversikt

| Område | Hva det dekker | Prioritet |
| --- | --- | --- |
| Team og prosess | Personas, brukerhistorier, MVP, akseptansekriterier, Scrum/Kanban, retrospektiv, roller | Høy |
| Git og samarbeid | Brancher, merge requests, merge-konflikter, commit-hygiene, GitLab issues/boards | Høy |
| Testing og kvalitet | JUnit, mocking, headless-testing, dekning, CI, statisk analyse | Høy |
| Arkitektur og design | OOP, abstraksjon, grensesnitt, MVC, avhengighetsinversjon, SOLID, mønstre | Høy |
| API-design | Gode/dårlige APIer, builder/fluent-grensesnitt, fabrikker, kontrakter, uforanderlighet | Høy |
| Modell- og visningsseparasjon | Frakobling av spillogikk fra grafikk/input/lyd | Høy |
| UX og tilgjengelighet | Inspera/Canvas-lignende UI, universell utforming, feilprone grensesnitt | Middels |
| Jus og etikk | Opphavsrett, lisenser, personvern, plagiat, personvern | Middels |
| Diagrammer og dokumentasjon | UML, klassediagrammer, aktivitetsdiagrammer, README/rapportkvalitet | Middels |

## Jukselapp

| Tema | Hva du bør huske | Vanlig eksamensvinkel |
| --- | --- | --- |
| Brukerhistorier | Rolle + behov + verdi. Hold dem konkrete og testbare. | "Skriv to brukerhistorier", "utled akseptansekriterier" |
| Akseptansekriterier | Målbare betingelser som beviser at en historie er ferdig. | "Hvordan vet vi at det fungerer?" |
| MVP | Den minste spillbare/nyttige versjonen. | "Hva ville du prioritert først?" |
| Scrum vs. Kanban | Scrum er iterasjons-/sprint-fokusert; Kanban er flyt-/VIP-fokusert. | "Hva gjorde teamet i praksis?" |
| Git-arbeidsflyt | Kortlivede brancher, små commits, merge requests, kodegjennomgang, hyppige sammenslåinger. | "Hvordan ville du organisert samarbeidet?" |
| Merge-konflikt | Rediger markører, velg/slå sammen endringer, `git add`, `git commit`. | "Forklar til en nybegynner." |
| Testing | Enhetstester på forretningslogikk først; integrasjon/system/akseptanse etter behov. | "Hvordan ville du testet uten grafikk?" |
| Mocking | Erstatt eksterne eller tunge avhengigheter slik at én enhet kan testes isolert. | "Hvorfor/når bruke Mockito?" |
| Headless-modus | Kjør kode uten et ekte vindu/OpenGL-backend. | "Hvordan tester du libGDX-kode?" |
| Observer / listener / command | Reager på hendelser uten tett kobling mellom avsender og mottaker. | "Hvordan håndtere input eller endringshendelser?" |
| MVC | Modell, visning og input/kontroller-logikk bør separeres. | "Hvorfor er denne GUI-koden vanskelig å teste?" |
| Avhengighetsinversjon | Avheng av grensesnitt/abstraksjoner, ikke konkrete klasser. | "Hvorfor er `instanceof` i algoritmen et rødt flagg?" |
| SOLID | Kjenn alle fem prinsippene og vær i stand til å navngi det som brytes. | "Hvilket prinsipp brytes?" |
| Strategi | Injiser adferd som et objekt i stedet for hardkodede forgreninger. | "Bedre løsning for ulik pathfinding-atferd?" |
| Builder/fluent-grensesnitt | Kjede konfigurasjonsanrop og avslutt med `build()`/`calculate()`. | "Hvorfor bruke et `PathFinder`-objekt i stedet for én metode?" |
| Statisk analyse | Finner lukt, stil og strukturelle problemer uten å kjøre kode. | "Hva kan SonarQube finne som tester ikke kan?" |
| Diagrammer | Klassediagram, objektdiagram og aktivitetsdiagram brukes til å forklare struktur og flyt. | "Skisser en løsning eller forklar strukturen." |
| Refaktorering | Endre struktur uten å endre atferd; betal ned technical debt tidlig. | "Hvordan forbedre gammel kode?" |
| Tilgjengelighet | Gjør grensesnitt lesbare, tastaturvennlige og lavrisikobaserte. | "Hva er galt med dette brukergrensesnittet?" |
| Lisensiering | Kjenn forskjellen mellom opphavsrett, varemerke, patenter og programvarelisenser. | "Hva må dokumenteres i prosjektet?" |
| IDE og verktøy | Eclipse/IntelliJ/VSCode, refactorering, analyseverktøy og Git-integrasjon. | "Hvilke verktøy er nyttige og hvorfor?" |

## Nøkkelbegreper i detalj

### 1. Krav, brukerhistorier og MVP

**Hva det er**
- En brukerhistorie beskriver et behov på formen "Som en [rolle], ønsker jeg [mål], slik at [verdi]." Den sier hvem som trenger noe, hva som trengs, og hvorfor det er nyttig.
- Akseptansekriterier er målbare og observerbare betingelser som sier når historien er ferdig. De skal være testbare, ikke bare beskrivelser av intensjon.
- MVP er den minste versjonen som fortsatt er meningsfull og brukbar. Den skal gi verdi tidlig, selv om resten av ideen ikke er ferdig ennå.

**Hvorfor det er viktig**
- Det holder prosjektet fokusert på verdi fremfor tilfeldige funksjoner.
- Det gir deg et grunnlag for prioritering og for å vurdere om et krav faktisk er ferdig.

**Hvordan det fungerer i praksis**
1. Finn rollen eller personaen som har behovet, for eksempel spiller, lærer eller administrator.
2. Formuler behovet som en kort brukerhistorie med verdi for brukeren.
3. Skriv akseptansekriterier som kan verifiseres konkret, for eksempel "når X skjer, skal Y skje".
4. Bryt historien ned i oppgaver som kan løses av teamet.
5. Prioriter det som må være med i MVP først, og utsett det som bare gjør løsningen bedre senere.

**Slik kan du svare på eksamen**
- Forklar først hvem funksjonen er for, hva den skal gjøre, og hvorfor den gir verdi.
- Vis deretter at du kan skille mellom behov, testbare kriterier og teknisk implementasjon.
- Hvis oppgaven handler om prioritering, si hva som må med i MVP og hva som kan vente til en senere iterasjon.

**Vanlige eksamensoppgaver**
- Skriv brukerhistorier fra en rolle/persona og legg til akseptansekriterier.
- Gjør en historie om til et krav som kan testes.
- Sammenlign hva teamet planla versus hva de faktisk gjorde.
- Forklar hva som bør inngå i MVP kontra senere iterasjoner.

### 2. Teamprosess, Scrum og Kanban

**Hva det er**
- Scrum er en iterativ arbeidsform der teamet jobber i korte sprint-er med et tydelig mål. En typisk Scrum-syklus er backlog -> sprint planlegging -> daglig oppfølging -> review -> retrospektiv.
- Kanban er en flytbasert arbeidsform der oppgaver flyttes visuelt gjennom et board. Teamet trekker nytt arbeid når det finnes kapasitet, og WIP-grenser hindrer for mye pågående arbeid samtidig.
- Teamprosess handler også om roller, kommunikasjon, møtevaner og hvordan oppgaver faktisk blir fulgt opp i praksis.

**Hvorfor det er viktig**
- Kurset behandler gjentatte ganger programvareutvikling som teamarbeid, ikke bare koding.
- God prosess er en viktig del av evalueringen i prosjektarbeidet.

**Hvordan det fungerer i praksis**
1. I Scrum lager teamet en backlog med brukerhistorier og velger hva som skal inn i neste sprint.
2. Under sprinten jobber teamet mot sprintmålet, helst med små oppgaver som kan fullføres raskt.
3. På daglige møter deler alle status, hindringer og neste steg.
4. Mot slutten av sprinten demonstreres resultatet i en review, og teamet diskuterer hva som fungerte i en retrospektiv.
5. I Kanban er flyten mindre styrt av tidsbokser og mer av kontinuerlig prioritering og flytting av kort på et board.
6. WIP-grenser brukes for å unngå at alle starter på nytt arbeid før noe er ferdig.

**Slik kan du svare på eksamen**
- Beskriv først om teamet jobbet sprintbasert, flytbasert eller en blanding.
- Forklar hva som skjer med oppgaver fra idé til ferdig leveranse.
- Knytt svaret til konkrete teamvaner som board, møter, rollefordeling og retrospektiv.
- Hvis du sammenligner Scrum og Kanban, si at Scrum passer best når man vil planlegge i faste sykluser, mens Kanban passer best når man vil styre kontinuerlig flyt og kapasitet.

**Vanlige eksamensoppgaver**
- Sammenlign Scrum/Kanban med hva teamet faktisk gjorde.
- Anbefal en arbeidsflyt for et nytt studentteam.
- Forklar hva teamet lærte om samarbeid og møtevaner.
- Nevn rollefordeling, planlegging, kommunikasjon og oppfølging av arbeid.

### 3. Git, brancher, merge-konflikter og CI

**Hva det er**
- Git sporer endringshistorikk slik at du kan se hva som ble endret, av hvem og når. Brancher lar utviklere jobbe parallelt uten å ødelegge hovedlinjen.
- Merge requests er stedet der endringer blir gjennomgått, diskutert og slått sammen.
- CI kjører bygg og tester automatisk på serveren for å oppdage feil tidlig.

**Hvorfor det er viktig**
- Samarbeid er enklere når arbeid er isolert til det er klart.
- CI fanger plattformproblemer, manglende filer og integrasjonsproblemer du kanskje ikke ser lokalt.

**Hvordan det fungerer i praksis**
1. Lag en kortlivet feature-branch for én konkret oppgave.
2. Gjør små, meningsfulle commits slik at historikken blir lett å lese.
3. Push branchen og åpne en merge request for gjennomgang.
4. Hvis en konflikt oppstår, åpner du filen, fjerner konfliktmarkørene og velger eller kombinerer endringene som skal beholdes.
5. Kjør tester lokalt før du pusher, og bruk CI som en ekstra kontroll av at alt fortsatt bygger og virker.
6. Fiks feil så tidlig som mulig i samme branch før de blir spredt videre.

**Slik kan du svare på eksamen**
- Forklar arbeidsflyten fra branch til merge request til ferdig sammenslått kode.
- Si hvorfor små commits og korte brancher gjør feilsøking lettere.
- Ved merge-konflikter bør du vise at du forstår at Git ikke vet hva som er riktig automatisk, og at du derfor må sammenligne og ta et bevisst valg.
- Ved CI bør du forklare at serveren kan avsløre ting som manglende filer, ulik JDK-versjon eller tester som bare feiler i ren bygging.

**Vanlige eksamensoppgaver**
- Beskriv en anbefalt Git-arbeidsflyt.
- Forklar hvordan man løser en merge-konflikt for en nybegynner.
- Forklar hvorfor CI er nyttig selv om du kjører tester lokalt.
- Diskuter hvordan GitLab issues, boards og merge requests støtter prosjektet.

### 4. Testing, mocking, headless-kjøring og dekning

**Hva det er**
- Enhetstester sjekker små deler av logikken isolert, for eksempel en regel, en beregning eller en tilstandsovergang.
- Integrasjonstester sjekker at flere deler virker sammen, for eksempel modell og lagring eller modell og service-lag.
- System- og akseptansetester sjekker hele produktet fra brukerens perspektiv.
- Mock-objekter og fakes erstatter tunge, eksterne eller ikke-deterministiske avhengigheter.
- Headless-kjøring betyr at du kjører kode uten ekte grafikkvindu, ofte for å få testbar spilllogikk uten UI.

**Hvorfor det er viktig**
- Kursets prosjekt og eksamener understreker gjentatte ganger at modelllogikk skal kunne testes uten grafikk.
- Tester er det som gjør refaktorering og endring mulig uten frykt.

**Hvordan det fungerer i praksis**
1. Flytt all viktig logikk til kode som ikke trenger grafikk for å kjøre.
2. Test små regler først, for eksempel "hva skjer når en spiller møter en vegg?".
3. Bruk mocks eller fakes når en test ellers måtte snakke med tilfeldighet, klokke, filsystem eller grafikk.
4. Bruk headless-oppsett når du vil teste libGDX-kode uten å starte et ekte vindu.
5. Når du trenger å teste `Stage` eller andre libGDX-deler i headless-modus, mock `Viewport` og `SpriteBatch` og test bare det som faktisk er testbart.
6. Bruk Mockito når du vil kontrollere returverdier med `when(...).thenReturn(...)` eller verifisere samspill med `verify(...)`.
7. Bruk testdekning som et signal, men vurder alltid om testene faktisk dekker riktig atferd.

**Slik kan du svare på eksamen**
- Forklar at testbar kode vanligvis har tydelig separasjon mellom modell og visning.
- Si at du tester atferd, ikke private detaljer i implementasjonen.
- Nevn at headless-testing og mocking er nyttig når grafikk eller eksterne avhengigheter gjør vanlige tester ustabile.
- Gi et konkret eksempel på hva Mockito kan gjøre, for eksempel å få `size()` til å returnere en bestemt verdi eller sjekke at `act()` ble kalt.
- Hvis du får spørsmål om dekning, si at høy dekning ikke er nok hvis testene er svake eller tester feil ting.

**Vanlige eksamensoppgaver**
- Hvordan teste spillogikk når libGDX-grafikk er i veien.
- Hvilke typer tester bør kjøres før et spill slippes.
- Hva statisk analyse kan finne som tester ikke kan.
- Hvorfor testdekning alene ikke er nok hvis testene er dårlige.

### 5. Abstraksjon, ADT-er, grensesnitt, generics og uforanderlighet

**Hva det er**
- En abstrakt datatype skjuler representasjonen bak et definert grensesnitt. Brukeren skal vite hva noe gjør, ikke hvordan dataene er lagret.
- Grensesnitt beskriver atferd og lar deg avhenge av en type fremfor en konkret klasse.
- Generics parametriserer typer slik at én abstraksjon kan fungere for mange elementtyper.
- Uforanderlige objekter kan ikke endres etter opprettelse, noe som gjør dem tryggere som delte data og som nøkler i samlinger.

**Hvorfor det er viktig**
- Abstraksjon reduserer kobling og gjør kode enklere å bruke, teste og erstatte.
- Uforanderlige data unngår feil fra utilsiktet delt mutasjon.

**Hvordan det fungerer i praksis**
1. Velg et grensesnitt når du bare trenger en kontrakt, ikke en bestemt implementasjon.
2. Bruk generics når den samme strukturen skal fungere for flere typer, for eksempel `List<T>` eller et eget `Repository<T>`.
3. Velg immutability når data skal deles trygt mellom flere deler av programmet.
4. Unngå å bruke mutbare objekter som nøkler i maps, fordi endringer kan gjøre oppslag uforutsigbare.
5. Husk at `equals` og `hashCode` må være konsistente når objekter skal brukes i samlinger.

**Slik kan du svare på eksamen**
- Si at abstraksjon handler om å gjemme detaljer og eksponere en stabil kontrakt.
- Forklar at interfaces og generics gjør API-et mer fleksibelt og testbart.
- Hvis du får spørsmål om maps eller samlinger, si hvorfor muterbare nøkler er farlige.
- Gi et konkret eksempel fra prosjektet eller Java-standardbiblioteket når du forklarer hvorfor en viss type er bedre enn en annen.

**Vanlige eksamensoppgaver**
- Design et API for en quiz, et kart eller en pathfinder.
- Forklar hvorfor mutbare nøkler i hashmaps er farlig.
- Forklar hvorfor `Vector2` som map-nøkkel kan være et problem.
- Sammenlign konkrete klasser versus grensesnitt i et API.

### 6. MVC, separasjon av bekymringer og avhengighetsinversjon

**Hva det er**
- MVC separerer modell-, visnings- og kontroller-/inputlogikk. Modellen inneholder regler og tilstand, visningen tegner, og kontrolleren oversetter input til handling.
- Avhengighetsinversjon sier at høynivåkode skal avhenge av abstraksjoner, ikke konkrete detaljer.
- Hendelser, lyttere og observatører er en vanlig måte å frakoble interaksjon på, slik at én del kan reagere uten å kjenne den andre delen direkte.

**Hvorfor det er viktig**
- Dette er en av hovedgrunnene til at prosjektet kan testes uten et GUI.
- Det er også en av hovedgrunnene til at eksamener gjentatte ganger spør om kode som blander grafikk og logikk.

**Hvordan det fungerer i praksis**
1. Legg spilltilstand, regler og beslutninger i modellen.
2. La visningen bare lese tilstand og tegne den.
3. La kontrolleren omsette tastetrykk, mus eller andre hendelser til modellhandlinger.
4. Bruk grensesnitt eller hendelser når du vil bytte ut deler av systemet uten å endre resten.
5. Håndter ofte input som events, og bruk polling bare når det passer bedre med den aktuelle oppgaven.
6. Når du merker at logikk begynner å kjenne grafikk, lyd eller UI direkte, har du som regel for dårlig separasjon.

**Konkret om input og events**
- `InputProcessor` og `InputAdapter` er nyttige når du vil reagere på `keyDown`, `keyUp` eller musehendelser.
- `InputMultiplexer` lar flere mottakere behandle input i rekkefølge.
- `Stage` er også en `InputProcessor`, så input kan sendes videre til `Actor`-er.
- Polling med `isKeyPressed(...)` eller `isButtonJustPressed(...)` passer bedre når du vil spørre om nåværende tilstand i en spill-loop.
- `keyDown` og `keyUp` handler om rå tastetrykk, mens `keyTyped` handler om tegnbrukeren faktisk skriver.
- Command-pattern er nyttig når en hendelse skal oversettes til et objekt eller en operasjon som kan lagres, køes eller gjenbrukes.

**Slik kan du svare på eksamen**
- Beskriv kort hvilken del som eier hva: modell, visning og kontroller.
- Forklar at modellen bør kunne testes uten grafikk fordi den ikke er avhengig av visningen.
- Forklar gjerne forskjellen mellom events og polling hvis oppgaven handler om inputdesign.
- Hvis oppgaven handler om en designlukt, kan du peke på hardkobling, `instanceof`-kjeder eller UI-kode inne i kjernealgoritmen.
- Når du nevner avhengighetsinversjon, si at høy-nivålogikk bør bruke en kontrakt eller et grensesnitt i stedet for å opprette konkrete objekter direkte.

**Vanlige eksamensoppgaver**
- Forklar hvorfor modellkode som laster teksturer er vanskelig å teste.
- Foreslå en måte å separere pathfinding fra grafikk.
- Forklar hvorfor en `instanceof`-kjede inne i kjernealgoritmen er en designlukt.
- Diskuter observer/lytter/kommando som hendelseshåndteringsteknikker.

### 7. SOLID og designmønstre

**Hva det er**
- **SRP**: én klasse bør ha én tydelig ansvarsdimensjon, altså én god grunn til å endre seg.
- **OCP**: kode bør kunne utvides uten at du må redigere det som allerede fungerer.
- **LSP**: en subtype skal kunne brukes der supertypen forventes uten å ødelegge kontrakten.
- **ISP**: del opp store grensesnitt i små, relevante deler.
- **DIP**: høy-nivåkode skal avhenge av abstraksjoner, ikke konkrete implementasjoner.
- Vanlige mønstre i dette kurset: builder, factory, strategy, observer/lytter, adapter, singleton og chain of responsibility.

**Hvorfor det er viktig**
- Tidligere eksamener elsker å spørre hvilket prinsipp som brytes og hvordan man fikser det.
- Mønstre er ofte "språket" som brukes for å beskrive løsningen.

**Hvordan det fungerer i praksis**
1. Start med problemet, ikke mønsteret: spør hva som varierer, hva som blir for stort, og hva som er vanskelig å teste.
2. Hvis oppretting er komplisert, bruk factory.
3. Hvis det er mange valgfrie innstillinger, bruk builder eller fluent-grensesnitt.
4. Hvis adferd varierer, bruk strategy.
5. Hvis noe skal reagere på hendelser, bruk observer/lytter.
6. Hvis et eksisterende grensesnitt ikke passer, bruk adapter.
7. Bruk SOLID-navnet som forklaring på hvorfor endringen er bedre, ikke bare som pynt.

**Slik kan du svare på eksamen**
- Identifiser først problemet i koden, for eksempel for stor klasse, hardkobling eller mange `if`-grener.
- Navngi deretter hvilket SOLID-prinsipp som brytes.
- Forklar én konkret forbedring, for eksempel et nytt grensesnitt, en strategy eller en factory.
- Hvis du skriver om designmønstre, si alltid hvorfor mønsteret passer i akkurat den situasjonen.

**Vanlige eksamensoppgaver**
- Identifiser det brutte SOLID-prinsippet i en rotete `if (x instanceof ...)`-metode.
- Forklar builder versus én metode med mange parametere.
- Foreslå strategy/avhengighetsinjeksjon for pathfinding eller spillvariasjon.
- Gi eksempler på LSP eller ISP fra Java eller prosjektet.

### 8. API-design, refaktorering og dokumentasjon

**Hva det er**
- Et godt API føles naturlig, unngår overraskende atferd og hjelper brukeren til å gjøre det riktige. Målet er at riktige valg skal være enkle og feil valg vanskelige.
- Refaktorering endrer struktur uten å endre atferd.
- Dokumentasjon og diagrammer er en del av designet, ikke bare ekstra papirarbeid.

**Gode API-prinsipper**

| Prinsipp | Hva du gjør | Hvorfor det hjelper |
| --- | --- | --- |
| Smale grensesnitt | Eksponer bare metodene brukeren faktisk trenger | Mindre å implementere og mindre å bruke feil |
| Domenespråk | Bruk navn som passer problemet, ikke bare tekniske navn | API-et blir lettere å forstå for andre |
| Abstraksjoner først | Ta inn grensesnitt eller abstrakte typer der det er naturlig | Gjør koden mer fleksibel og testbar |
| Builder eller config-objekt | Flytt mange valgfrie parametere ut av én lang metode | Gir lesbar kode og færre feil ved kall |
| Uforanderlighet | Returner nye objekter eller beskytt intern tilstand | Reduserer utilsiktede sideeffekter |
| Tydelige kontrakter | Dokumenter hva som skjer ved ugyldig input og hvilke verdier som kan være null | Brukeren slipper å gjette |
| Enkle standardvalg | Gi fornuftige default-verdier | API-et blir raskere å ta i bruk |

**Hvorfor det er viktig**
- Flere eksamensoppgaver ber deg om å lage grensesnitt eller kritisere et eksisterende API.
- Prosjektkravene ber også om lesbar dokumentasjon og et klassediagram.

**Hvordan det fungerer i praksis**
1. Start med hvem som skal bruke API-et og hvilke oppgaver API-et skal støtte.
2. Velg grensesnitt og typer som uttrykker domenet tydelig.
3. Unngå at brukeren må forstå interne detaljer eller kalle mange metoder i feil rekkefølge.
4. Bruk builder, factory eller config-objekter hvis en metode får for mange valgfrie parametere.
5. Gjør tilstander og kontrakter tydelige, og dokumenter hva som er lov og ikke lov.
6. Refaktorer ved å dele opp store klasser og metoder, ikke bare ved å flytte kode rundt.

**Slik kan du svare på eksamen**
- Beskriv først hva brukeren prøver å gjøre, og hva API-et bør gjøre enkelt.
- Nevn minst ett prinsipp som gjør API-et bedre, for eksempel ISP, DIP eller uforanderlighet.
- Hvis du får en dårlig API-skisse, forklar hva som er uklart, for langt eller for tett koblet.
- Hvis oppgaven ber om forbedring, vis hvordan et builder- eller fluent-grensesnitt kan gjøre koden mer lesbar.

**Vanlige eksamensoppgaver**
- Design quiz/spørsmål-APIer, kart-APIer eller pathfinder-APIer.
- Sammenlign en dårlig API-opplevelse med en god.
- Forklar hvordan et builder- eller fluent-API forbedrer lesbarhet.
- Diskuter hvordan man omstrukturerer gammel kode for å gjøre den vedlikeholdbar.

### 9. Kvalitet, statisk analyse og kodelukt

**Hva det er**
- Statisk analyse inspiserer kode uten å kjøre den.
- Kodelukt er tegn på dypere design- eller vedlikeholdsproblemer.
- Verktøy som SonarQube og SpotBugs hjelper deg med å finne disse problemene tidlig.

**Hvorfor det er viktig**
- Tester fanger ikke alt.
- Kvalitetsverktøy hjelper deg med å holde kodebasen sunn mens prosjektet vokser.

**Hvordan det fungerer i praksis**
1. Kjør statisk analyse for å oppdage feil, kompleksitet, duplisering og risiko for nullproblemer.
2. Bruk tester for å verifisere ønsket atferd og beskytte funksjonalitet.
3. Når verktøyene peker på et mønster, spør hva det egentlig betyr for designet.
4. Refaktorer når du ser at koden blir vanskelig å lese, teste eller utvide.
5. Ikke stol blindt på ett verktøy alene; bruk analyse, tester og kodegjennomgang sammen.

**Slik kan du svare på eksamen**
- Forklar forskjellen mellom statisk analyse og dynamiske tester.
- Gi ett eksempel på noe verktøyet kan finne, for eksempel duplisert kode, kompleksitet eller potensielle nullreferanser.
- Si hvorfor refaktorering er bedre enn å la dårlig struktur bli stående.
- Hvis du blir bedt om å kritisere et verktøy, si at det er nyttig, men ikke kan erstatte god design og gode tester.

**Vanlige eksamensoppgaver**
- Gi et eksempel på noe statisk analyse kan oppdage, men tester ikke kan.
- Gi et eksempel på noe tester kan oppdage, men statisk analyse ikke kan.
- Forklar hvorfor hyppig refaktorering er bedre enn å vente til slutten.

### 10. UX, universell utforming og juridiske/etiske spørsmål

**Hva det er**
- UX handler om hvordan systemet oppleves av brukerne; universell utforming betyr at det bør fungere for så mange mennesker som mulig.
- Opphavsrett, lisenser, varemerke, patenter og personvernregler påvirker hva du kan bruke og hvordan.

**Hvorfor det er viktig**
- Kurset behandler programvare som et sosioteknisk system, ikke bare kode.
- Virkelige prosjekter mislykkes når brukerne ikke forstår grensesnittet eller når juridiske begrensninger ignoreres.

**Hvordan det fungerer i praksis**
1. Design for klar tilbakemelding, lesbar tekst, tydelig kontrast og god tastaturstøtte.
2. Gjør farlige handlinger mindre sannsynlige og gi brukeren hjelp når noe går galt.
3. Sørg for at layout og meldinger er forståelige også for nye brukere.
4. Dokumenter alle ressurser, biblioteker og lisenser som brukes i prosjektet.
5. Skille mellom bearbeidede verk og originalverk: permisive lisenser som MIT/BSD/Apache gir mer frihet, mens copyleft-lisenser som GPL, AGPL og LGPL stiller strengere krav til videre deling.
6. Husk at programvare kan være fri til å bruke, men ikke nødvendigvis fri til å endre, redistribuere eller bygge videre på.
7. Skill mellom brukerfeil og systemfeil i stedet for å legge all skyld på brukeren.

**Slik kan du svare på eksamen**
- Forklar hvordan grensesnittet kan bli lettere å forstå og bruke.
- Nevn konkrete tilgjengelighetstiltak, for eksempel kontrast, tekststørrelse, tastatur og tydelig feilmelding.
- Forklar forskjellen mellom opphavsrett, varemerke, patent og personvern hvis det er relevant.
- Hvis spørsmålet handler om lisens eller etikk, vis at du vet at ikke all kode, grafikk eller lyd kan brukes fritt.
- Trekk gjerne inn hvordan god dokumentasjon og tydelig ressursbruk forebygger problemer i prosjektet.

**Prosjektfiler**
- Legg en lisens i `LICENSE`-filen når dere publiserer kode.
- Bruk `CONTRIBUTING.md` hvis dere vil forklare hvordan andre kan bidra.
- Legg til `CODE_OF_CONDUCT.md` hvis dere ønsker tydelige samhandlingsregler.
- På GitLab og GitHub finnes det ofte egne valg for å legge til disse filene raskt.

**Vanlige eksamensoppgaver**
- Kritiser et forvirrende UI eller en dato-/tidsvelger.
- Forklar hva som kan forbedres for tilgjengelighet.
- Diskuter hvordan prosjektdokumentasjon bør registrere ressurser og lisenser.
- Reflekter over juridiske eller etiske ansvarsområder i programvareutvikling.

### 11. Diagrammer og dokumentasjon

**Hva det er**
- Diagrammer er abstrakte modeller som brukes til å spesifisere, dokumentere, kommunisere og utforske et system.
- Klassediagram viser typer, arv, implementering og relasjoner mellom klasser og interfaces.
- Objektdiagram viser konkrete objekter og tilstanden deres på et bestemt tidspunkt.
- Aktivitetsdiagram viser arbeidsflyt og beslutningspunkter.

**Hvorfor det er viktig**
- Diagrammer hjelper deg å forklare struktur uten å drukne i implementasjonsdetaljer.
- På eksamen kan et godt diagram vise at du forstår forholdet mellom klasser, dataflyt og avhengigheter.

**Hvordan det fungerer i praksis**
1. Velg bare de delene av systemet som er relevante for problemet du vil forklare.
2. Bruk pil for arv, stiplet pil for implementering og streker for assosiasjoner.
3. Bruk komposisjon når et objekt eier og styrer levetiden til et annet, og aggregering når ting er mer løst koblet.
4. Bruk objektdiagram når du vil vise konkret tilstand, for eksempel et eksempel på et oppsett i minnet.
5. Bruk aktivitetsdiagram når du vil forklare en prosess, for eksempel hvordan oppgaver eller input flyter.

**Slik kan du svare på eksamen**
- Skisser det viktigste først: typer og relasjoner, ikke alle metoder og felt.
- Hvis du får en arkitekturoppgave, forklar hva som er modell, visning, kontroll og avhengigheter.
- Si gjerne hva diagrammet ikke viser hvis det er relevant, for eksempel detaljer som ligger i kode eller API-dokumentasjon.

### 12. Refaktorering og verktøy

**Hva det er**
- Refaktorering endrer struktur uten å endre atferd.
- Technical debt oppstår når du utsetter opprydding og lar midlertidige løsninger bli stående.
- Code smells er tegn på at koden kan være vanskelig å vedlikeholde.
- IDE-er og analyseverktøy kan hjelpe med å oppdage og gjennomføre forbedringer.

**Hvorfor det er viktig**
- Når du refaktorerer tidlig, blir det lettere å bygge videre uten å forsterke dårlig design.
- Automatiske refaktoreringer reduserer risikoen for feil når du flytter eller omformer kode.

**Hvordan det fungerer i praksis**
1. Bruk automatiske refaktoreringer som rename, extract interface, change method signature, extract method og move når det passer.
2. Bruk extract interface og use supertype where possible når du vil bytte konkrete typer ut med abstraksjoner.
3. Bruk pull up når felles kode bør flyttes til en superklasse, men pass på at du ikke sletter viktig forskjellig oppførsel i subklassene.
4. Bruk introduce factory når du vil bort fra direkte `new`-kall og heller samle oppretting ett sted.
5. Se etter duplisering, svært lange metoder og klasser som gjør for mye.
6. Bruk IDE-er som Eclipse eller IntelliJ når du vil ha sterk refactor-støtte, og vurder analyser som SpotBugs, PMD, CheckStyle og SonarQube for ekstra kvalitetssjekk.

**Slik kan du svare på eksamen**
- Forklar hvorfor en kodeendring er en forbedring selv om den ikke endrer funksjonalitet.
- Knytt refaktorering til vedlikehold, testbarhet og mindre kobling.
- Hvis du nevner verktøy, si hva de typisk er best på: stil, mønstre, kompleksitet, bugs eller helhetlig kvalitet.

## Eksamensintelligenens

### Hva som gjentas

| År | Hovedtemaer |
| --- | --- |
| 2023 | Teamprosess, testing, pathfinding, designmønstre, API/arkitektur, kritikk av virkelige systemer |
| 2024 | Git/GitLab, IDE-valg, CI/CD, statisk analyse, API-design, brukbarhet/tilgjengelighet, SOLID |
| 2025 | Prosjektrefleksjon, Git-arbeidsflyt, IDE-valg, testing, GUI-fri testdesign, pathfinding, designmønstre |

### Tilbakevendende spørsmålstyper

- Forklar hvorfor noe er nyttig eller problematisk.
- Sammenlign to tilnærminger og knytt dem til hva teamet faktisk gjorde.
- Gi konkrete eksempler fra prosjektet, Javas standardbibliotek eller et kjent bibliotek.
- Skisser en designløsning i stedet for å skrive full implementasjonskode.
- Identifiser hvilket SOLID-prinsipp eller designmønster som er relevant.
- Reflekter over teamarbeid, kommunikasjon og prosess.

### Tilbakevendende formuleringer

- "Forklar kort" betyr vanligvis et kortfattet svar med noen presise punkter.
- "Gi eksempel" vil vanligvis ha et konkret eksempel, ikke en generisk definisjon.
- "Skissér en løsning" vil vanligvis ha arkitektur- eller mønstervalg.
- "Hvilke(t) prinsipp(er) blir brutt?" vil vanligvis ha både navnet på prinsippet og den konkrete grunnen.
- "Hva ville du gjort annerledes?" vil ha refleksjon over prosess og avveininger, ikke skyldfordeling.

### De mest sannsynlige høyverdi-temaene

1. Brukerhistorier, akseptansekriterier, MVP og prioriteringer.
2. Teamprosess, Scrum/Kanban, møter, retrospektiver og roller.
3. Git-arbeidsflyter, merge-konflikter og CI.
4. Testing, mocking, headless-kjøring og dekning.
5. MVC og separasjon av modell, visning og input.
6. SOLID og mønstrene knyttet til det: builder, factory, strategy, observer, adapter.
7. API-design med grensesnitt, abstraksjon og lesbare kontrakter.
8. Mutbare vs. uforanderlige data og fallgruver med samlingssnøkler.
9. UX og tilgjengelighetsproblemer i praktiske systemer.
10. Juridiske/etiske ansvarsområder og dokumentasjon av ressurser.

### Hvordan svare godt

- Start med konseptnavnet.
- Gi ett konkret eksempel fra prosjektet, et bibliotek eller en lærebokcase.
- Forklar hvorfor det hjelper eller skader.
- Nevn avveiningen hvis det finnes en.
- Knytt svaret tilbake til testing, vedlikehold, samarbeid eller brukerverdi.

## Prosjektnotat-spørsmål og svar

Disse svarene er forankret i teamets egne rapporter, møtenotater og brukerhistorier. De passer godt til eksamensformen fordi de er reflekterende, konkrete og knyttet til prosess og arkitektur.

### S1. Hva var deres MVP, og hvordan utviklet den seg?

**Svar:** Teamet startet med et Kanban-inspirert, MVP-først-tårn-forsvarskonsept og klargjorde senere MVP-en til et 2D topp-ned-brett, spillerbevegelse, kollisjon, én bakterietype, én fiendtlig bølge, ett tårn, automatiske tårnangrep, start-/hjelp-/spillover-skjermer, lyd og en forsvarbar base. Senere rapporter viser at prosjektet beveget seg utover MVP da kraftoppgraderinger, animasjoner og mer polish ble lagt til.

### S2. Hva lærte teamet om prosess og teamarbeid?

**Svar:** Den sterkeste lærdommen er at kommunikasjon betød mer enn de formelle rolleetikettene. Teamet brukte ukentlige tirsdagsmøter, Discord mellom møter, og GitLab issues/boards for å holde alle samkjørte. Rapportene viser også at gruppen fungerte godt sosialt, men trengte hyppigere møter og klarere kortsiktige mål for å opprettholde momentum.

### S3. Hva ville du forbedret hvis du startet på nytt?

**Svar:** Teamet sier gjentatte ganger at de ville definert og låst MVP-en tidligere, startet testing og dokumentasjon tidligere, og planlagt arkitektur raskere. De sier også at Git-regler, branching-vaner og commit-disiplin burde ha blitt etablert fra begynnelsen i stedet for å bli forbedret sent i prosjektet.

### S4. Hvordan ble roller brukt i praksis?

**Svar:** Seks roller ble definert: gruppeledelse, spillmekanikk, testing/kvalitet, lyd/ressurser/lisensiering, grafikk/input, og arkitektur/design. I praksis var rollene nyttige som ansvarsmarkører, men arbeidet ble likevel delt på tvers av teamet. Gruppeledelsesrollen var den klareste praktiske rollen; de andre ble mer meningsfulle senere når prosjektet trengte testing, refaktorering og arkitekturopprydding.

### S5. Hvordan støttet GitLab prosjektet?

**Svar:** GitLab var det operative arbeidsverktøyet: issues sporet alle funksjoner, etiketter sporet status, prioritet og område, og issue-boardet ble brukt som et Kanban-brett. Teamet brukte også milestones og et overordnet MVP-issue for å opprettholde fokus. Det er nettopp den typen arbeidsflyt INF112 liker fordi den gjør fremgang synlig og støtter samarbeid.

### S6. Hvilke brukerhistorier var viktigst?

**Svar:** De sterkeste historiene er de som mapper direkte til vanlig eksamenssteori: bevegelse, kollisjon, startskjerm/hjelpeskjerm, bakteriebølger, tårn, spillover og kraftoppgraderinger. Disse historiene er enkle å forklare i form av MVP, akseptansekriterier og avhengigheter, noe som er nøyaktig formen til mange INF112-spørsmål.

### S7. Hvorfor er MVC viktig i dette prosjektet?

**Svar:** Rapportene sier eksplisitt at grafikk/input bør separeres fra spillogikk, og teamet delte bevisst koden inn i modell, visning, kontroller, ressurser, lyd og skjermer. Det er et naturlig eksamensvar fordi INF112 gjentatte ganger spør hvordan man tester logikk uten grafikk og hvordan man reduserer kobling mellom modell og visning.

### S8. Hvorfor er testing og dekning så viktig her?

**Svar:** Notatene viser at testing startet sent, og deretter ble et stort kvalitetshensyn, med sluttrapporten som peker på 89 % testdekning. Kurset kobler dette direkte til headless-testing, mocking og modell/visning-separasjon. Et sterkt eksamensvar er at tester gjør refaktorering tryggere og beviser at kjernen i spillogikken fortsatt fungerer når UI-et endres.

### S9. Hva er de mest eksamenvennlige brukerhistoriene i prosjektet?

**Svar:** De mest eksamenvennlige historiene er kjernestoriene: startskjerm, bevegelse, kollisjon, synlig brett, bakteriebølger, basebeskyttelse, spillover og lyd ved hendelser. Disse er enkle å begrunne som MVP-elementer og enkle å knytte til akseptansekriterier, noe som gjør dem til godt materiale for kortsvar.

### S10. Hva var den største prosessfeilen?

**Svar:** Den gjentatte feilen var å bruke for lang tid på organisering og for lite tid på kjernebyggingen tidlig. Rapportene sier at MVP-en ikke ble definert og fullført tidlig nok, og at teamet ble seriøse med testing, Git-disiplin og arkitektur først etter at prosjektet allerede hadde vokst. Det er et sterkt refleksjonsvar fordi det er konkret og knyttet til faktiske prosjektbevis.

### S11. Hvordan ville du beskrive teamets utviklingsstil i én setning?

**Svar:** Teamet brukte en Kanban-inspirert, issue-drevet arbeidsflyt med ukentlige iterasjoner, delt ansvar og økende vekt på arkitektur, testing og MVP-fokus etter hvert som prosjektet modnet.

### S12. Hva er den beste måten å koble prosjektnotatene til eksamensvar på?

**Svar:** Bruk prosjektnotatene som bevis, ikke som et manus. Nevn de konkrete artefaktene: issues, etiketter, møterytme, MVP-liste, brukerhistorier, arkitekturdeling, testdekning og retrospektive lærdommer. Knytt deretter hvert artefakt til teorien kurset gjentatte ganger returnerer til: Scrum/Kanban, MVC, DIP, testing og refaktorering.

## Hurtigreferanse

- Brukerhistorie = rolle, behov, verdi.
- Akseptansekriterium = testbart bevis.
- MVP = minste nyttige versjon.
- Git-branch = isoler arbeid; merge request = gjennomgangspunkt.
- Konflikt = rediger markører, `git add`, `git commit`.
- Enhetstest = isolert logikk.
- Mock = falsk avhengighet.
- Headless = ingen ekte grafikk/vindu.
- MVC = separer modell, visning, kontroller/input.
- DIP = avheng av grensesnitt.
- Strategi = injiser varierende atferd.
- Builder = mange konfigurasjonstrinn, flytende kjede.
- Factory = skjul oppretting.
- Uforanderlig nøkkel = trygg map-nøkkel.
- Statisk analyse = finner lukt/tester kan ikke.
- Tilgjengelighet = lesbart, brukbart, lavrisikogrensesnitt.

---

## Sensorveiledning og modellsvar (2025-eksamen)

Denne seksjonen gir eksakte svar basert på 2025-eksamens løsningsforslag og vurderingskriterier. **Oppgavenes totale vekt er 60 %** av karakteren; semesterprosessen utgjør 40 %.

### Oppgave 1: Erfaringer (15 poeng total)

#### 1.1 Bakgrunn (5 poeng)

**Spørsmål:** Nevn to ting: (a) som teamet hadde hatt nytte av å kunne bedre før prosjektet, eller (b) som viste seg ikke å være så nødvendige.

**Graderingskriterier:** 
- 5 poeng for to gjennomtenkte eksempler med god begrunnelse
- Eksemplene bør være konkrete og basert på projekterfaring, ikke teori

**Modellsvar (fra 2025-eksamens løsningsforslag):**

Du kan svare enten (a) eller (b), eller ett eksempel fra hver.

*Eksempel på (a) – Hadde hatt nytte av å kunne bedre:*
- **Better software design principles (SOLID, DIP):** "Vi brukte hele semesteret på å bygge første versjon av spillogikken før vi refaktorerte. Hvis vi hadde kjent til dependency injection og interface-design fra starten, ville vi spart mye tid på omstruktureringer senere. Vi kunne bygget bedre fra start, som ville gjort testing og samarbeid enklere."
- **Git-disiplin og branching-strategi:** "Vi brukte de første ukene på ad-hoc commits til main. En klar strategi for feature-branches og merge-requests tidlig ville redusert konflikter og gjort det enklere å rulle tilbake feil."
- **MVP-definisjon:** "Vi startet på så mange funksjoner samtidig. Hvis vi hadde fastslått MVP tydeligt fra dag 1 (f.eks. bare bevegelse + kollisjon + startskjerm i uke 1), ville vi vært klare for refaktorering og testing tidligere."

*Eksempel på (b) – Viste seg ikke å være så nødvendige:*
- **Komplekse designmønstre:** "Vi planla bruken av Strategy, Observer og Factory fra starten, men endte opp med bare å trenge Observer for input-hendelser. Mye av det planleggingen var overkill."
- **Detaljert arkitekturdokumentasjon:** "Vi brukte tid på å tegne detaljerte UML-diagrammer før vi kodde. I praksis endret arkitekturen seg likevel, og det viste seg at læring-by-doing var mer effektivt for teamet enn å planlegge alt på forhånd."

#### 1.2 Teamarbeid (5 poeng)

**Spørsmål:** Hva synes du er de to viktigste tingene du har lært om teamarbeid gjennom prosjektet?

**Graderingskriterier:**
- 5 poeng for to lærdom med god begrunnelse
- Skal være generelle innsikter, ikke bare tekniske fakta
- Begrunnelsen bør vise refleksjon, ikke bare beskrivelse

**Modellsvar:**

*Eksempel 1:*
"**Kommunikasjon må være forpliktende.** Vi startet med løse møtevaner der ikke alle møtte eller hvor vi ikke skrev ned hva som var avtalt. Når vi gikk til faste ukentlige møter, tydelige issues og etiketter på GitLab, reduserte vi misforståelser drastisk. Lærdom: et team er bare så sterkt som sitt interne kommunikasjonssystem."

*Eksempel 2:*
"**Spesialisering balansert med fleksibilitet.** Vi prøvde først med helt separate roller (en for grafikk, en for testting osv.). Det ble flaskehals når noen var syke eller fast på enkeltoppgaver. Når vi tillot mer flyt og overlapping, både høyeste kvalitet og gjennomstrøm forbedret. Lærdom: roller er nyttige for fokus, men teamet må dele kunnskap bredt."

*Eksempel 3:*
"**Retrospektiv må føre til handling.** Vi snakket endelig hele tiden, men ikke alltid med tema eller oppfølging. Når vi startet strukturerte retrospektiver med konkrete åtgjerder for neste sprint (f.eks. 'ingen commits direkte til main' eller 'kodegjennomgang på alle pull requests'), begynte vi faktisk å endring arbeidsmåte. Lærdom: refleksjon uten tiltak er bare meningsløs prat."

#### 1.3 Fremtid (5 poeng)

**Spørsmål:** Nevn to ting du bør lære mer om for å bli en god programvareutvikler, og forklar hvorfor.

**Graderingskriterier:**
- 5 poeng for to relevante emner med god begrunnelse
- Ikke automatisk høy score bare fordi du nevner avanserte emner; begrunnelsen er viktig
- Emner bør kunne kobles til INF112 eller profesjonell utvikling

**Modellsvar:**

*Eksempel 1:*
"**Avansert testning og TDD (Test-Driven Development).** Vi skrev for det meste tester etter implementasjonen, og endte opp med 65 % testdekning. Hvis jeg hadde skrevet tester først, ville jeg tvinget meg selv til bedre design (fordi dårlig design er vanskelig å teste). Det hadde også gjort refaktorering tryggere. Test-driven design er en ferdighet som forbedrer hele arbeidsflyt."

*Eksempel 2:*
"**Systemdesign og arkitekturmønstre i større skala.** I INF112 designet vi ett prosjekt på ~20k linjer. I industrien må du forstå hvordan store systemer (100k+ linjer, mikrotjenester, distribuerte systemer) organiseres. Det handler ikke bare om å skrive god kode, men å planlegge for at koden skal vokse og vedlikeholdes over år. Arkitekturkunnskap er det som skiller junior- og senior-utviklere."

*Eksempel 3:*
"**DevOps, containerization og deployment.** Vi brukte CI/CD på GitLab, men bare for tester. Å faktisk deploye kode til produksjon, håndtere konfigurasjoner, skale systemer og feilsøke i produtkjsmiljøer er helt andre ferdigheter. Det er essensielt for å gå fra klasseprosjekt til profesjonell utvikling."

---

### Oppgave 2: Verktøy (16 poeng total, eller 13 poeng hvis SonarQube-spørsmål utelates)

#### 2.1 Git-arbeidsflyt (6 poeng)

**Del a) Gitflow-strategi (3 poeng)**

**Spørsmål:** Hvordan ville du lagt opp Git-arbeidsflyten for samarbeid med 4–6 utviklere?

**Graderingskriterier:**
- 3 poeng for feature-branch-strategi med merge requests
- Ekstra poeng for å nevne Gitflow, develop-branch eller kodegjennomgang
- Punkt for å tenke på vedlikehold OG videreutvikling

**Modellsvar:**

"**Feature-branch-strategi med merge requests:**

1. **Hovedbrancher:** Vi beholder `main` som stabil produksjonskode (eller release-branch for prosjekt) og `develop` som integrasjonsbranch for aktiv utvikling.
2. **Feature-branches:** For hver ny funksjonalitet, bug-fix eller forbedring opprettes en kort-livet branch fra `develop`. Navn som `feature/player-movement` eller `bugfix/collision-detection` gjør det klart hva som jobbes på.
3. **Merge requests som kvalitetssjekkpunkt:** Før en feature-branch merges tilbake til `develop`, må den gjennom en merge request. Minst én annen teammedlem må godkjenne koden, og alle CI-tester må passere. Dette reduserer feil som smugler seg inn.
4. **Hyppige merges:** Vi merger små batches ofte (1–3 dager per feature) i stedet for massive merges. Det gjør konflikthåndtering enklere.
5. **Releases:** Når `develop` er stabil og klar til release, opprettes en `release`-branch som blir grundig testet og merges både til `main` og tilbake til `develop` for synkronisering.

Fordel: Ordning, clear ansvar, og god mulighet for parallelt arbeid."

**Del b) Mergekonflikt-oppløsning (3 poeng)**

**Spørsmål:** Forklar kort hvordan man løser en merge-konflikt.

**Graderingskriterier:**
- **Må ha:** Rediger koden mellom `<<<<<<<`, `=======`, `>>>>>>>` markørene; `git add`; `git commit` for å fullføre.
- **Bonus:** Nevn hvordan man avbryter merge, eller velger "vår" vs "deres" ende.

**Modellsvar:**

"**Løsning av merge-konflikt:**

1. **Identifiser konflikt:** `git status` viser filer med konflikter merket som 'both modified'.
2. **Åpne filen i editor:** Konflikten er merket med:
   ```
   <<<<<<< HEAD
   (din versjon av koden)
   =======
   (deres versjon av koden fra den branch som merges inn)
   >>>>>>>
   ```
3. **Rediger koden:** Velg enten din versjon, deres versjon, eller slå dem sammen manuelt. Fjern markeringslinjer.
4. **Lagre og stage:** `git add <filnavn>` for å si at konflikten er løst.
5. **Fullføre merge:** `git commit` (med en merge-commit-melding som forklarer valget).

Hvis mergen blir for komplisert, kan du starte på nytt med `git merge --abort`."

#### 2.2 IDE (4 poeng)

**Del a) Hvilken IDE brukte du? (1 poeng)**

**Modellsvar:** "VSCode eller IntelliJ IDEA (eller Eclipse/NetBeans)." 1 poeng for å nevne en konkret IDE. Hvis hele teamet brukte samme IDE, nevn det; hvis ikke, ok også.

**Del b) Nyttigste IDE-egenskaper? (3 poeng)**

**Graderingskriterier:**
- 3 poeng for å nevne minst 3–4 konkrete egenskaper UTENFOR det som er nevnt i oppgaven
- Begrunnelse er viktig; ikke bare en liste

**Modellsvar:**

"**Nyttigste egenskaper:**

1. **Kodenavigering (Go to definition, find references):** Vi jobbet med ukjent LibGDX-API hele tiden. `Ctrl+Click` for å hoppe til kildekode eller dokumentasjon sparte enormt tid når jeg trengte å forstå et bibliotek.
2. **Automatisk refaktorering (Rename, Extract method, Move):** I midten av prosjektet bestemte vi oss for at MVC-struktur var viktig. Med IDE-refaktorering kunne vi flytte og omdøpe klasser på tvers av hele prosjektet på sekunder uten å introdusere bugs.
3. **Integrert debugging:** Vi kunne sette breakpoints direkte i IDE-en og inspektere variable under kjøring. Dette gjorde det langt raskere å finne bugs enn å skrive print-setninger.
4. **Git-integrasjon:** Commit, pull og push rett fra IDE-en reduserte kontekstbytting til terminal.
5. **Code generation (hashCode/equals, getters/setters):** IDE-en kunne generere vanlig standard-kode som vi ellers måtte skrive for hånd."

#### 2.3 CI/CD (3 poeng)

**Del a) Hvorfor CI selv om man tester lokalt? (estimert 1–2 poeng)**

**Graderingskriterier:**
- Må nevne at CI tester på felles servermiljø (ikke bare lokal maskin)
- Bonus for å nevne integrasjonstesting, merge-request-testing, eller andre platformers som Linux vs. Mac/Windows

**Modellsvar:**

"**Viktige grunner til CI:**

1. **Integrasjonstesting:** Selv om mine tester passerer lokalt, kan de feile når koden merges med andres endringer. CI oppdager klassisk 'it works on my machine'-problemer.
2. **Merge request testing:** CI kan teste koden slik den vil være ETTER en merge, ikke bare som isolation.
3. **Platform-uavhengighet:** De fleste av oss brukte Windows/Mac, men CI kjørte på Linux. Noen ganger finner CI problemer med stor/små bokstaver i filnavn eller linjeavslutninger som vi ikke kunne oppdage lokalt.
4. **Glemt filer:** CI detekterer hvis noen glemte å committe avhengige filer eller ressurser.
5. **Konsistent miljø:** Alle teammedlemmer kan ha litt ulike setup. CI gir et definert, gjentagbart miljø som alle koden garantert må fungere i."

**Del b) Hadde CI-server nytte av å finne problemer? (1 poeng)**

"Ja, flere ganger. For eksempel tester som brukte grafikk-bibliotek som ikke var installert på serveren, eller ressursfiler med feil navn-case som ikke ble plukket opp av Windows/Mac men feiler på Linux."

---

### Oppgave 3: Design (29 poeng total)

#### 3.1 Designvalg for Map, Position, Direction (5 poeng)

**Spørsmål:** Hvilke designvalg ville du gjort for kart, posisjon og retning?

**Graderingskriterier:**
- 2–3 poeng for å erkjenne at disse bør være egne abstraksjoner
- 1–2 poeng for å begrunne med domenespråk ("kart", "posisjon", "retning" er substantiv i domenet)
- Minst poeng for `MapSquare[][]` som kart (som oppgaven advarer implisitt mot)

**Modellsvar:**

"**Designfilosofi:** Kart, posisjon og retning er alle substantiv i geitesimuleringdomenet, så de bør være egne typer med meningsfulle metoder.

1. **Kart (Map interface):**
   - *Alternativ A (best)* – Egen `Map`-interface med metoder som `findFood(Position pos, double maxDist)`, `search(Position pos, Distance radius, Predicate<MapSquare>)`, og `getSquareAt(Position pos)`. Dette gir et domene-spesifikt API som biologer lett forstår.
   - *Alternativ B (ok)* – Bruk `Grid<MapSquare>` fra et bibliotek og implementer Map-interface oppå den. Fordel: ikke oppfinn hjulet på nytt. Ulempe: kan mangle metoder biologer trenger.
   - *Ikke anbefalt:* `MapSquare[][]` direkte – det eksponerer datastrukturen, gjør det vanskelig å endre hvis vi senere vil ha irregular grids, og mekanismen for å søke på kartet ligger i Goat i stedet for hvor den hører hjemme.

2. **Posisjon (Position interface):**
   - Egne `Position`-objekt (ikke bare `double x, y`) fordi:
     - Vi kan ha metoder som `directionTo(Position other)`, `distanceTo(Position other)`, `move(Direction dir, Distance dist)`.
     - Det blir enklere å skifte fra (x,y) til andre koordinatsystemer senere hvis det behøves.
     - Det er lettere å retur fra metoder (kan ikke returne `(x,y)` i Java).

3. **Retning (Direction interface):**
   - Kan være enten vinkel (double i radianer/grader) eller vektor (`Vector2`). Begge duger.
   - Hvis vi bruker `Vector2` direkte, sjekk at det er immutable eller at vi behandler det som immutable (Side 3.2)."

#### 3.2 Mutable vs. Immutable (2 poeng)

**Spørsmål:** Hvorfor kan det være nyttig å bruke ikke-muterbare objekter?

**Graderingskriterier:**
- 1 poeng for å forklare immutable = lettere å resonnere om
- 1 poeng for konkret valg med begrunnelse

**Modellsvar:**

"**Hvorfor immutable er nyttig:**
- Immutable objekter er mye lettere å resonnere om. Hvis flere deler av koden holder en referanse til samme `Position`, vet du at ingen andre kan endre den under dine fødder.
- Med mutable objekter kan en endring gjennom éen referanse komme helt uventet på andre deler av koden som holder samme referanse.

**Mitt valg:**
- `Position` og `Direction` bør være **immutable.** De er verdier; det gir ikke mening å 'endre' en retning på plass. I stedet returnerer `move()` en ny posisjon.
- `Map` bør være **mutable.** Kartet endrer seg når geiter spiser og slipper avlægg. Immutable kart ville vært ineffektivt.
- (Bonus innsikt: Immutable kart ville faktisk passe hvis alle geiter tar avgjørelser basert på samme kartbilde samtidig – som i celleautomater – men det gjør systemet komplekst.)"

#### 3.3 Java Interface (6 poeng)

**Spørsmål:** Skriv Java-kode (interfaces) for Map, Position, Direction med JavaDoc.

**Graderingskriterier:**
- 3 poeng for grensesnitt med opplagte og nødvendige metoder
- 1–2 poeng for god JavaDoc
- 2–3 poeng for design som støtter brukeradferd (ikke bare datastrukturer)

**Modellsvar:**

```java
/**
 * Represents a game map with food and terrain.
 */
public interface Map {
    /**
     * Search for the nearest map square matching predicate within radius.
     * Returns null if nothing found.
     */
    MapSquare search(Position pos, double radius, Predicate<MapSquare> predicate);
    
    /**
     * Get all squares within radius of a position that match predicate.
     */
    List<MapSquare> findAll(Position pos, double radius, Predicate<MapSquare> predicate);
    
    /**
     * Get the square at a given position. Null if out of bounds.
     */
    MapSquare getSquareAt(Position pos);
}

/**
 * Represents a position on the map.
 * Immutable: all operations return new Position objects.
 */
public interface Position {
    /**
     * Get the direction from this position to another.
     */
    Direction directionTo(Position other);
    
    /**
     * Get the Euclidean distance to another position.
     */
    double distanceTo(Position other);
    
    /**
     * Move in a direction for a given distance.
     * Returns a new Position; does not modify this.
     */
    Position move(Direction direction, double distance);
    
    double getX();
    double getY();
}

/**
 * Represents a direction (heading).
 * Immutable: can be a vector or angle.
 */
public interface Direction {
    /**
     * Get the angle in radians [-π, π].
     */
    double getAngle();
    
    /**
     * Get as a unit vector.
     */
    Vector2 asVector();
}
```

#### 3.4 Gammel kode-gjennomgang (3 poeng)

**Spørsmål:** Analyser den gamle `findFood()`-implementasjonen og vurder den mot designprinsippene.

**Graderingskriterier:**
- Må nevne brudd på Single Responsibility Principle (Goat burde ikke vite om kartstruktur)
- Må nevne at det er dårlig for vedlikehold/utvidelse
- Bonus for å nevne andre SOLID-brudd (Dependency Inversion)

**Modellsvar:**

"**Kritikk av den gamle implementasjonen:**

1. **Alvorlig brudd på Single Responsibility Principle:** `Goat` vet alt om kartstrukturen (`int[][]`), koordinatberegning, og søkealgoritme. Hvis vi endrer kartet fra `int[][]` til f.eks. en sparse struktur eller hexagon-grid, må vi endre `Goat`. Det er ikke Goats ansvar.

2. **Dårlig for vedlikehold:** Hvis vi senere vil ha Sheep som også søker etter mat, må vi duplisere hele søkelogikken. Eller hvis vi vil endre søkealgoritmen, må vi redigere flere klasser.

3. **Dependency Inversion-brudd:** `Goat` er hardkodet til å avhenge av konkrete ting: `int[][]`, `Math.atan2()`, koordinater som doubles. Den burde avhenge av abstrakte `Map` og `Position` i stedet.

4. **Positiv:** Algoritmen selv er fin; den søker lagvis og har god dokumentasjon. Den bør bare flyttes til riktig sted."

#### 3.5 Refaktorer findFood() (4 poeng)

**Spørsmål:** Implementer `findFood()` ved bruk av det nye API-et.

**Graderingskriterier:**
- 4 poeng for implementasjon som bruker abstraksjonene riktig
- Inntrekk for unødvendige konkrete detaljer

**Modellsvar:**

```java
public class Goat {
    private Map map;
    private Position currentPosition;
    private Direction facing;
    
    public boolean findFood(double maxDist) {
        MapSquare foodSquare = map.search(currentPosition, maxDist, 
            square -> square.food() > 0);
        
        if (foodSquare != null) {
            Position foodPos = map.getPosition(foodSquare); // eller Position returneres fra search
            facing = currentPosition.directionTo(foodPos);
            startWalking();
            return true;
        }
        return false;
    }
}
```

**Eller enda renere (med fluent grensesnitt):**
```java
map.search(currentPosition, maxDist, sq -> sq.food() > 0)
   .ifPresent(foodPos -> {
       facing = currentPosition.directionTo(foodPos);
       startWalking();
   });
```

#### 3.6 Refleksjon (3 poeng)

**Spørsmål:** Hva tenker du om din nye implementasjon? Er den en forbedring?

**Graderingskriterier:**
- 3 poeng for å evaluere egen løsning med innsikt

**Modellsvar:**

"**Ja, det er en stor forbedring:**

1. **Mye kortere og lettere å lese:** Den gamle var ~25 linjer nested loops. Den nye er 5–10 linjer, og intensjonen er klar med èn blikk.

2. **Ansvar er spredt riktig:** `Map` håndterer å søke på kartet, `Position` håndterer retningsberegning. `Goat` vet bare at den vil finne mat og så gå dit. Vi kan endre kartimplementasjon, søkealgoritme, eller posisjonsrepresentasjon uten å røre Goat.

3. **Testbar:** Hvis jeg vil teste `Goat` sin innehavelse, kan jeg now mocke `Map` og `Position`. Før måtte jeg mocke hele `int[][]` strukturen.

4. **Maktpotensialet:** Hvis vi senere vil ha flere dyrearter eller flere søkeatferd, kan vi gjenbruke samme `Map` og `Position`-abstraksjon."

---

### Oppgave 4: Brukeropplevelse og Universell Utforming (15 poeng total)

#### 4.1 Brukerhistorier for eksamensløsning (3 poeng)

**Spørsmål:** Skriv to brukerhistorier for Inspera (eller en digital eksamensløsning) basert på din erfaring.

**Format:** "Som [ROLLE] ønsker jeg [MÅL] slik at [VERDI]"

**Graderingskriterier:**
- 1,5 poeng per brukerhistorie
- Skal være konkret og basert på faktisk brukeropplevelse
- Må ha rolle, mål og verdi

**Modellsvar:**

"**Brukerhistorie 1 (Godt):**
Som student med dysleksi ønsker jeg at oppgaveteksten kan endres med større linjeavstand og bakgrunnsfarger, slik at jeg kan lese lange oppgaver uten å miste fokus og få hodepine.

**Brukerhistorie 2 (Godt):**
Som student ønsker jeg en tidsvisning som viser hvor mye tid jeg har igjen i prosent, vektet etter antall oppgaver som gjenstår, slik at jeg ikke bruker 30 minutter på oppgave 1 og blir stresset over oppgave 4.

**Brukerhistorie 3 (Eksempel):**
Som student med ADHD ønsker jeg å kunne notere private kommentarer og oppfølgingspunkter på hver oppgave uten at disse vises sensoren, slik at jeg ikke glemmer noe jeg tenker på underveis.

**Brukerhistorie 4 (Eksempel):**
Som blind student med skjermleser ønsker jeg at all kode presenteres som tekst (ikke bilder), slik at skjermleseren kan lese den for meg i stedet for at den er bare bilder."

#### 4.2 Universell Utforming (8 poeng total)

**Del a) Konsekvenser av brudd på UU-krav (1–2 poeng)**

**Graderingskriterier:**
- Må vise at regelbrudd har konsekvenser
- Trenger ikke detaljert juridisk kunnskap, bare at det finnes sanksjoner

**Modellsvar:**

"**Konsekvenser:**
- **Fra UU-tilsynet:** Pålegg om utbedring innen oppgitt frist.
- **Dagmulkt:** Hvis virksomheten ikke retter seg, kan de få dagmulkt (f.eks. har UiB fått pålegg på 100.000 kr for tidligere brudd på UU-regler).
- **Søksmål:** Brukere som blir diskriminert kan søke og vinne erstatning.
- **Reputasjon:** Offentlig kritikk skader merkevare og tillit.

Norge er forpliktet til å håndheve Likestillingsloven, og UU-tilsynet fører aktivt tilsyn."

**Del b) Barrierer i eksamen (2–4 poeng)**

**Graderingskriterier:**
- 2 poeng for å identifisere en barriere
- 4 poeng for innsiktsfull analyse

**Modellsvar:**

"**Barrier i Inspera (eller digital eksamen):**

1. **Liten linjeavstand ved lange oppgaver:** Inspera har tradisjonelt veldig små marginer og avstand mellom avsnitt. For folk med dysleksi, ADHD eller svaksynthet gjør det det veldig vanskelig å lese en 200-ord oppgave. Det er ikke et brudd på regelverket per se, men det hindrer likeverdig deltakelse.

2. **Kode som bilder i PDF-er:** Hvis oppgaver inneholder kode (som ofte i INF112), og den er bare bilder, kan blinde studenter med skjermleser ikke lese den.

3. **Tidspress uten fleksibilitet:** Studenter med fokusseringsavvik trenger ofte lengre tid uten å være mindre smarte. Et rigidt 3-timer format kan være en barriere. (Dette håndteres vanligvis via tilrettelegging, men systemet selv gir ikke mulighet for individuell justering.)

4. **Mangel på tastaturnavigasjon:** Hvis eksamenssystemet krever mus (f.eks. for å trykke på knapper), blir det en barriere for folk med motoriske vansker.

**Konklusjon:** De fleste av disse er ikke brudd på regelverket, men de gjør eksamenen mindre likeverdig. Universell utforming handler om å tenke bredt på hvem som bruker systemet ditt."

---

## Oppsummering av eksamensstrategier

### For høy score:
1. **Konkret og spesifikk:** Bruk eksempler fra ditt eget prosjekt, ikke generelle teorier.
2. **Struktur:** Svar på hver underdel tydelig (a, b, c osv.).
3. **Begrunnelse:** Det er ikke nok å si "jeg brukte VSCode" – si *hvorfor* det var nyttig.
4. **Påstå, demonstrer, reflekter:** (1) Hva er ditt standpunkt? (2) Hva er beviset? (3) Hva lærte du?
5. **Respekt tid:** 60 % av eksamensgrunnlaget er oppgavene. Bruk gjennomsnittlig ~30 minutter per oppgave.
6. **Skriv leserlig:** Bruk overskrifter, lister og avsnitt. Oppgavene sier det er ok, og det gjør lesing for sensor enklere.

### For lavere score – typiske feil:
- Å skrive massa uten struktur ("jeg lærte mye om koding")
- Å blande personlig opplevelse med dårlig begrunnelse ("jeg likte Python bedre")
- Å kopiere løsningsforslag ordrett fra vedlegg (plagiat-advarsel gjelder fortsatt)
- Å la være å svare på alle deler av spørsmål (a, b, c)
- Å bruke 45 minutter på oppgave 1 og 15 på oppgave 3
