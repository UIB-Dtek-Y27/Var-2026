# INF112 – Eksamensoppgaver

## Innholdsfortegnelse

- [1. Prosjekterfaringer og metodikk](#1-prosjekterfaringer-og-metodikk)
  - [1.1 Bakgrunn](#11-bakgrunn)
  - [1.1 Metodikk](#11-metodikk)
  - [1.2 Metodikk](#12-metodikk)
  - [1.2 Teamarbeid](#12-teamarbeid)
  - [1.3 Kunnskap](#13-kunnskap)
  - [1.3 Fremtid](#13-fremtid)
  - [1.4 Kunstig intelligens](#14-kunstig-intelligens)
- [2. Verktøy og arbeidsflyt](#2-verktøy-og-arbeidsflyt)
  - [2.1 Git arbeidsflyt](#21-git-arbeidsflyt)
  - [2.2 Designmønstre](#22-designmønstre)
  - [2.2 IDE](#22-ide)
  - [2.3 CI/CD](#23-cicd)
  - [2.4 Statisk analyse](#24-statisk-analyse)
- [3. Design og abstraksjon](#3-design-og-abstraksjon)
  - [3.1 Valg av abstraksjoner](#31-valg-av-abstraksjoner)
  - [3.2 Å mutere eller ikke mutere?](#32-å-mutere-eller-ikke-mutere)
  - [3.3 Java interface](#33-java-interface)
  - [3.4 Gammel kode](#34-gammel-kode)
  - [3.5 Renovasjonsoppgaven](#35-renovasjonsoppgaven)
  - [3.6 Refleksjon](#36-refleksjon)
- [4. Brukere og universell utforming](#4-brukere-og-universell-utforming)
  - [4.1 Brukerhistorier](#41-brukerhistorier)
  - [4.2 Universell utforming](#42-universell-utforming)

---

## 1. Prosjekterfaringer og metodikk

### 1.1 Bakgrunn

Det kan være krevende å sette seg inn i et nytt prosjekt. Ofte må du lære mange verktøy, biblioteker og teknologier. I tillegg må du kanskje jobbe på nye eller uvante måter.

Tenk gjennom erfaringene dine, og nevn to ting…

**a)** …som du føler du eller teamet ditt hadde hatt nytte av å kunne bedre før dere begynte på prosjektet,

**b)** …eller som du trodde ville være viktige på starten av semesteret, men som viste seg ikke å være så nødvendige. Du kan velge to ting fra a eller b, eller gi ett eksempel fra hver. Husk å begrunne svaret.

---

### 1.1 Metodikk

For semesterprosjektet kunne dere selv velge utviklingsmetodikk (Scrum, Kanban, Lean, XP osv.) og teknikker/praksis (f.eks. parprogrammering, continuous integration, TDD/BDD, e.l.) dere ville bruke i prosjektarbeidet.

Forklar kort hva gruppen din ble enig om å gjøre, og hva dere gjorde i praksis.

---

### 1.2 Metodikk

Du blir ansatt som INF112-gruppeleder våren 2026. Basert på erfaringene dine og det du har lært, hva slags råd vil du gi 2026-studentene om valg og tilpasning av metodikk?

Skriv 1–3 avsnitt der du forklarer og anbefaler en passende metodikk for en INF112-gruppe.

---

### 1.2 Teamarbeid

Gjennom erfaringene dine fra vårens prosjektarbeid: hva synes du er de to viktigste tingene du har lært om teamarbeid i løpet av arbeidet med prosjektet? Forklar. *(Ca. ett avsnitt på hver ting.)*

---

### 1.3 Kunnskap

Nevn to ting du synes er viktig å kunne for å bli en god programvareutvikler, og forklar hvorfor de er viktige.

*(Ett avsnitt på hver ting.)*

---

### 1.3 Fremtid

Nevn to ting du synes du bør lære mer om for å bli en god programvareutvikler, og forklar hvorfor de er viktige.

---

### 1.4 Kunstig intelligens

Utviklingsverktøy kommer nå ofte med integrerte AI-assistenter, som f.eks. Copilot, og verktøy som ChatGPT kan generere kode i nesten hvilket som helst programmeringsspråk.

**A)**

- **a1)** Brukte du eller gruppen din noen av disse verktøyene mens dere jobbet med semesterprosjektet? I så fall, hva slags erfaringer gjorde dere? Var det et nyttig verktøy, eller ble dere ledet på villspor?

- **a2)** Tror du ChatGPT eller lignende verktøy vil kunne erstatte mennesker i utviklingsprosessen i løpet av de neste fem årene? **Forklar.**

**B)**

- **b)** Hva tenker du om nytten av designprinsippene, metodikken, osv. som du lærte av INF112, sett i lys av økende bruk av kunstig intelligens i programutvikling?

---

## 2. Verktøy og arbeidsflyt

### 2.1 Git arbeidsflyt

Se for deg at du og noen andre studenter skal begynne på et nytt programmeringsprosjekt (f.eks. som del av INF218/219). Dere er et lite team på 4–6 utviklere. Gruppen skal kode i Java. Du er valgt som teamets Git-ekspert.

**a)** Hvordan ville du lagt opp Git-arbeidsflyten slik at dere kan samarbeide best mulig, både med vedlikehold og videreutvikling? F.eks. med tanke på branch-struktur, navngiving og bruk av branches, om man skal pushe direkte til main eller bruke merge requests, osv.

**b)** Et av de andre teammedlemmene kan ikke så mye om Git, og spør deg om hjelp med en merge-konflikt. Forklar kort hvordan hen bør gå frem for å løse merge-konflikten.

---

### 2.2 Designmønstre

Velg et designmønster du kjenner til (f.eks. Abstract Factory, Builder, Observer e.l.) og lag et kodeeksempel som illustrerer bruken.

Ha med:

- Interface-deklarasjoner med kort dokumentasjon (en linje eller to om hva metoden gjør).
- Deklarasjoner av eventuelle klasser som inngår i eksemplet og hvilket/hvilke interface de implementerer – men du trenger ikke ha med feltvariabler og metoder.

---

### 2.2 IDE

Java-kode er vanlig tekst og kan skrives i en vanlig teksteditor. Likevel velger de fleste å bruke et integrert utviklingsmiljø (Integrated Development Environment – IDE), spesielt i større prosjekter. IDE-er har mange nyttige hjelpemidler, for eksempel autofullføring, navigasjon, dokumentasjonsvisning og mye annet. De gjør det også enklere å holde orden på prosjektet. Med støtte fra en IDE blir det enklere å skrive korrekt kode, finne frem i ukjent kode og gjøre endringer eller vedlikehold. Eksempler på vanlige IDE-er for Java er Eclipse, IntelliJ IDEA, NetBeans og VSCode.

**a)** Hvilken IDE (eller editor) brukte du vanligvis til å redigere koden i semesterprosjektet? Brukte alle på teamet samme IDE?

**b)** Hva tenker du er de nyttigste tingene å se etter når du skal velge IDE, slik at du får best mulig hjelp og støtte i utviklingsarbeidet?

---

### 2.3 CI/CD

Under vårens prosjektarbeid var GitLab satt opp med kontinuerlig integrasjonstesting (CI/CD – Continuous Integration / Deployment – vi brukte bare CI), slik at hver gang du pushet endringer til git.app.uib.no ble det startet en såkalt pipeline på serveren, som sjekket ut den nyeste koden, kompilerte den og kjørte JUnit-tester og evt. andre tester. Resultatet av testkjøringen ble deretter gjort tilgjengelig på prosjektsiden.

**a)** Når du utvikler, kjører du vanligvis testene selv på din egen maskin før du committer og pusher. Hvorfor kan det likevel være nyttig å også ha kontinuerlig integrasjonstesting når du pusher til serveren? Forklar.

**b)** Opplevde du i løpet av semesteret at CI-serveren fant feil/problemer du ikke var klar over før du pushet?

---

### 2.4 Statisk analyse

I løpet av semesteret satte vi også opp noen statiske analyseverktøy – SpotBugs og SonarQube. Slike verktøy analyserer koden – dvs. teksten og de kompilerte klassefilene – uten å kjøre den. Målet er å finne feil og potensielle problemer, samt ting som ikke egentlig er feil men kan være dårlig praksis eller føre til vedlikeholdsproblemer. SonarQube presenterer funnene på en nettside, sammen med litt statistikk (blant annet test coverage).

Eksempler på ting SpotBugs og SonarQube sjekker etter: metoder som er for kompliserte, literaler som burde defineres som konstanter, feltnavn som kan misforstås, definisjoner som burde vært `private`, `protected` eller `final`, osv. Java-kompilatoren gjør også litt statisk analyse – blant annet sjekker den for typefeil, variabler som ikke blir brukt, osv.

**a)** Tittet du på SonarQube-siden i løpet av semesteret? I så fall, var noen av tilbakemeldingene nyttige? *(SonarQube-oppsettet var litt eksperimentelt, så det er helt rimelig om du ikke sjekket det.)*

Du la antakelig mye arbeid i å skrive JUnit-tester i løpet av semesteret. Men trenger vi egentlig å skrive enhetstester når vi har fancy analyseverktøy som SonarQube – eller trenger vi SonarQube når vi har tester?

**b)** Gi et eksempel på feil/problemer som statisk analyse kan oppdage, men som er vanskelig å oppdage med enhetstester.

**c)** Gi et eksempel på feil/problemer som er greit å oppdage med tester, men som vil være vanskelig for et statisk analyseverktøy å oppdage.

---

## 3. Design og abstraksjon

### 3.1 Valg av abstraksjoner

En viktig designavgjørelse er hvilke ting som skal være egne abstraksjoner (egne interfaces), og hva som kan representeres direkte med primitive typer og klasser fra Java-biblioteket eller andre vanlige biblioteker. Førstnevnte kan gi et API som er bedre tilpasset bruken, mens sistnevnte har fordelen at folk gjerne kjenner det fra før (og det blir mindre jobb for deg).

For eksempel:

- Vi kan lage en egen type `Map` for kartet, eller vi kan bruke Javas `MapSquare[][]` eller en generell grid-datastruktur `Grid<MapSquare>`.
- Posisjoner kan enkelt representeres som tall, x og y – men kanskje det er lurt å ha metoder for å regne ut retninger og avstander? Burde vi i så fall lage en egen `Position`, eller heller bruke f.eks. LibGDX sin `Vector2`?
- Retninger kan være tall (i grader) eller vektorer – men det er også mulig det er nyttig med en egen `Direction`-abstraksjon.

Hvilke designvalg ville du gjort for kart, posisjon og retning?

---

### 3.2 Å mutere eller ikke mutere?

Et annet valg er om vi skal kunne oppdatere objektene (de er muterbare, *mutable*), eller om hver utregning gir et nytt objekt (de er ikke-muterbare, *immutable*, og kan ikke endres etter at de er opprettet). For eksempel er LibGDX sin `Vector2` mutable, mens Javas `String` er immutable.

Hvorfor kan det være nyttig å bruke ikke-muterbare objekter? Forklar kort.

Hva ville du valgt for dine abstraksjoner? Forklar kort.

---

### 3.3 Java interface

**Skriv Java-kode (kun interface) for eventuelle abstraksjoner (kart, posisjon og retning) du valgte å ha med i designet i de første deloppgavene.**

Ta med metoder du mener er nødvendige, og metoder du tenker vil være nyttige for de som implementerer geite-oppførselen. Skriv kort JavaDoc-dokumentasjon – en linje eller to om hva metodene gjør. Du trenger ikke ha med full `@param`-beskrivelse av parameterne, men ta med eventuell informasjon brukeren må være oppmerksom på – spesielle krav til parametere, at en metode kan returnere `null`, om metoder må kalles i en spesiell rekkefølge, osv.

---

### 3.4 Gammel kode

En annen gruppe studenter laget en prototype av geitesimuleringen i fjor. De hadde ikke tatt INF112, og kunne betydelig mindre om programutvikling enn deg. I kodeeksempelet kan du se deres implementasjon av `Goat::findFood()`, som skal lete etter mat i nærheten og snu geiten i riktig retning om den finner mat.

Studer koden – du trenger ikke bruke tid på alle detaljene i hvordan den virker, det holder å forstå hva den gjør. Tenk gjennom designprinsippene (SOLID, osv.) og det du har lært om kodekvalitet og vedlikeholdbarhet.

Hva tenker du om implementasjonen av `findFood()`? Er dette et bra grunnlag for vedlikehold og fremtidige utvidelser?

---

### 3.5 Renovasjonsoppgaven

Anta at noen allerede har implementert og testet API-et du laget, og at `Goat`-klassen er oppdatert til å bruke det nye API-et i stedet for `int[][] foodMap` o.l. Lag en ny implementasjon av `findFood()` som bruker API-et ditt. Du står fritt til å endre argumenter og gjøre passende antakelser om feltvariabler og andre metoder i `Goat`.

---

### 3.6 Refleksjon

Hva tenker du om din implementasjon av `findFood()`? Er den en forbedring i forhold til den gamle?

---

## 4. Brukere og universell utforming

### 4.1 Brukerhistorier

Som UiB-student har du erfaring som bruker (i student-rollen) i den digitale eksamensløsningen Inspera. Tenk gjennom erfaringene dine, brukeropplevelsen og hva som er viktigst for deg som student når du bruker Inspera til å besvare eksamen.

Med utgangspunkt i student-rollen og deg selv som persona, skriv to brukerhistorier for en digital eksamensløsning. For eksempel noe du synes fungerer spesielt godt eller dårlig, eller noe du savner i dagens løsning.

---

### 4.2 Universell utforming

Universell utforming av programvare er viktig for at så mange som mulig skal kunne delta i samfunnet på en likeverdig måte, uavhengig av eventuell funksjonsnedsettelse, kulturell bakgrunn, kjønnsidentitet, alder osv. Både EU, USA, Norge og andre land har regler om dette – i Norge er det regulert gjennom Likestillingsloven og Forskrift om universell utforming av IKT-løsninger, og UU-tilsynet fører tilsyn med og gir råd om universell utforming.

**a)** Hva slags konsekvenser kan det eventuelt få for en virksomhet (privat eller offentlig) hvis de tilbyr en nett-tjeneste som ikke oppfyller minimumskravene til universell utforming? *(Svar kort.)*

**b)** Universell utforming av eksamen er viktig for å sikre like muligheter i samfunnet – det er noe både utviklere og forelesere må være oppmerksom på. Tenk gjennom brukeropplevelsen på eksamen i dag og eventuelt andre eksamener du har tatt. Kommer du på et tilfelle der du føler at brukeropplevelsen kunne være til hinder for enkelte studenter, selv om den kanskje er grei for de fleste? Forklar kort. *(Det trenger ikke være snakk om et faktisk brudd på regler eller retningslinjer, bare at du tenker det kan være til hinder for noen. Hvis du ikke kommer på noe eksamensrelatert, kan du eventuelt tenke på andre tjenester/applikasjoner du bruker.)*
