# INF112 Øvelse 1, Våren 2025

## Discord og deltakelse - VIKTIG!

Det er viktig at du *deltar aktivt hver uke*, slik at du og teamet ditt kan planlegge, være oppdatert og ha fremgang. Vi anbefaler på det sterkeste at dere setter av gruppetiden dere er påmeldt til prosjektarbeide; det hjelper veldig å ha dedikert tid til å jobbe med ting. For å få gjort alt arbeidet i løpet av semesteret, må du også regne med å bruke en del tid i tillegg til gruppetiden (enten alene, til parprogrammering, eller noe annet dere avtaler).

### «Egenmelding» ved sykdom e.l.
Dersom du blir syk e.l., og forhindret fra å delta en uke, si fra til de andre på teamet ditt og til gruppeleder. Vær behjelpelig med å koordinere aktiviteter, og hold kontakt med de andre så langt det er mulig – det gjør det lettere å komme tilbake i «arbeid» igjen etterpå.

Om et eller annet i det virkelige liv forstyrrer og du ikke får gjort det du skal, er det viktig at du holder de andre på teamet orientert, slik at de kan hjelpe deg og/eller justere planleggingen. Det er mye bedre å si «jeg har ikke gjort det jeg skal gjøre» enn å forsvinne for en stund og ikke si noe som helst.

### Inndeling i teams
I løpet av januar skal vi dele dere inn i *teams* på 4–6 personer. Send en melding til gruppelederen din, og si hei og hvem du er, og litt om bakgrunnen din, så vi kan sette sammen godt blandede teams. Du får/har sikkert fått en melding fra gruppeleder om dette, du kan svare på Mitt UiB eller Discord (pass på at vi kan se navnet ditt!).

Teamet skal jobbe sammen resten av semesteret på et større prosjekt med obligatoriske innleveringer.

# Første øvelse
Målet for denne oppgaven er å trene på kommunikasjon, fremstilling, samt å bli kjent med verktøy vi bruker i kurset. Alle oppgavene skal gjøres sammen med teamet ditt, evt. i små grupper om vi ikke har rukket å plassere deg.

Oppgavesettet består av fire deloppgaver.

For oppsett / bruk av verktøy:

* Hjelp hverandre!
* Bruk [Stack Overflow](https://stackoverflow.com/) og nettsøk når dere står fast
* Spør gruppeleder om hjelp / spør på Discord om dere står helt fast
* Det er meningen dere skal (i større grad) finne ut av slike ting på egenhånd – eller lære å finne ut av det – derfor er det ingen detaljert guide (men du finner likevel noen tips sist i denne oppgaven)


## Oppgave 1 - Kommunikasjonsøvelse

Gå sammen 2 og 2, og bruk fem minutter på å bli kjent. Etterpå skal dere presentere hverandre for teamet deres. Denne presentasjonen skal være 1 - 1 ½ minutt, og dere kan forklare kort om fagbakgrunn, hobbyer, interesser osv. Husk å presentere den andre personen slik du ville presentert deg selv. 

## Oppgave 2 - Kommunikasjonsøvelse

Bruk 10 minutter på å lage en kort presentasjon (2 - 3  minutter maks) om noe du synes er kult. Dette kan være enten fagmessig eller fra en hobby. Etterpå skal du presentere dette for teamet ditt. Husk å passe tiden, og gi en person ansvar for å stoppe presentasjonene om de går over tiden. (Et vanlig triks er å begynne å klappe når tiden er ute)

## Oppgave 3 - GitLab og prosjektoppsett

I dette faget skal dere jobbe sammen i team med utvikling av en større applikasjon. For samarbeid på større prosjekter hjelper versjonskontrollsystemer ved å gi et felles lagringsområde for kildekode og andre ressurser for prosjektet. Et slikt system gjør det også lettere å holde kontroll på endringer, samt å gi dere muligheten til å rulle tilbake til tidligere versjoner. Git er et populært versjonskontrollverktøy, og vi bruker Git i dette kurset for å håndtere kildekode og dokumentasjon for programvare. Flere produkter bruker git. Dere har antakelig brukt [UiBs GitLab, git.app.uib.no,](https://git.app.uib.no/) tidligere, og kjenner til andre varianter, som GitHub, GitLab.com og BitBucket? I dette kurset bruker vi UiB's GitLab siden dere antakelig har konto og er kjent med den fra før.

- Gå inn på https://git.app.uib.no/ og logg inn og/eller registrer deg. *Det er viktig at du bruker «Students and employees at UiB, UiO... (via Feide)», og ikke «External Users (GitHub)» når du registrerer deg.
- Du skal jobbe med et lite prosjekt som heter `textutils`. Du finner din egen kopi («fork») av prosjektet her: https://git.app.uib.no/inf112/25v/ex/
- Klon din «fork» av prosjektet til maskinen din (`git clone git@git.app.uib.no:inf112/25v/ex/BRUKERNAVN_textutils.git`); du kan gjøre dette på kommandolinjen, eller via IDE-en (f.eks. *Import → Maven → Check out Maven Projects from SCM* i Eclipse).
- Åpne prosjektet i ditt foretrukne utviklingsverktøy (IDE). I Eclipse, *Import → Maven → Existing Maven Project* (om du ikke klonet via Eclipse); IntelliJ vil antakelig gjenkjenne det som Maven prosjekt når du importerer.

Tips: Det er lurt å sjekke ut en guide eller et cheatsheet. For eksempel  [dette](https://medium.com/@nendhruv/essential-git-commands-every-developer-should-know-1249d4d597b5).

### Maven

Maven er et system for å bygge, teste og pakke programvare. Vi skal lære mer om det senere. Eclipse og IntelliJ kan som regel håndtere Maven prosjekter på egenhånd.

- Om du ikke har Maven satt opp på maskinen din, kan det være lurt å få til det. Du vil få hjelp til det senere, men du kan prøve på egenhånd nå, om du vil.
- Kompiler og kjør tester med `mvn test`. Prøv å kjøre `mvn clean package` (bygger alt på nytt, tester og pakker i en `jar` fil). ~~Du vil se at en av testene feiler~~ Hvis du «slår på» `TestTest::failingTest` vil de se hvordan det ser ut når testene feiler.
- Prøv å fikse testen som feiler

### Utviklingsoppgave: TextUtils

Se på interfacet [`TextAligner`](https://git.app.uib.no/inf112/25v/textutils/-/blob/main/src/main/java/inf112/TextAligner.java). Det inneholder fire metoder, `center()`, `flushRight()`, `flushLeft()` og `justify()`, som alle tar en streng og en bredde, og så plasserer teksten på en eller annen måte innenfor den bredden.

Metodene er rimelig minimalt dokumentert, og etterlater veldig mye til fantasien. Hva skal f.eks. skje hvis strengen allerede er lengre enn den oppgitte bredden?

#### Diskusjon
* Diskuter med de andre i gruppen hva som er uklart, og hva man bør teste for for å se at en implementasjon håndterer alle tenkelige «corner cases» / forskjellige situasjoner. Det er *minst seks ting* som er uklart/bør testes.
Med «uklart» tenker vi situasjoner hvor forskjellige rimelige implementasjoner kan gi forskjellig svar for samme input – dvs. noe som ikke er spesifisert i dokumentasjonen
* Begynn med `center()`; `flush`-metodene er relativt like; `justify()` er en del vanskeligere, både å spesifisere oppførselen og å implementere.

#### Parprogrammering
I [`src/test/java/inf112/TestTest.java`](https://git.app.uib.no/inf112/25v/textutils/-/blob/main/src/test/java/inf112/TestTest.java) finner dere eksempler på noen enkle [JUnit](https://junit.org/junit5/docs/current/user-guide/) tester.


* Gå sammen to og to (evt. tre), og:
    1. Lag en test for noe av oppførselen
    2. Bytt på hvem som jobber, og lag en implementasjon som tilfredstiller testen
    3. Kompiler og kjør testene, evt. fiks ting
    4. Fortsett til dere har implementert og testet alt, ihvertfall for `center()`
* **Anbefalt prosjektoppsett:** Maven skiller testene (i `src/test/java`) fra resten av koden (i `src/main/java`). I vårt tilfelle er det naturlig å organisere koden slik:
    * `src/main/java/inf112/TextAligner.java` – grensesnittet som skal implementeres
    * `src/main/java/inf112/TextAlignerImpl.java` eller `src/main/java/inf112/impl/TextAlignerImpl.java` – klassen som implementerer grensesnittet
    * `src/test/java/inf112/TextAlignerTest.java` – testklassen som tester implementasjonen (må ha `Test` i navnet)

## Ekstraoppgave 4 - FizzBuzz

I denne oppgaven skal dere gjøre en "kode-kata". kode-kataer er små programmeringsoppgaver (ikke helt ulikt [kattis](https://www.kattis.com/)), for å få mengdetrening med å programmere.

Fizz Buzz er et program som printer tallene fra 1 til 100 på separate linjer, med et par modifikasjoner:

- Om tallet er delelig på tre, skal programmet printe “fizz” istedenfor tallet
- Om tallet er delelig på fem, skal programmet printe “buzz” istedenfor tallet.
- Om tallet er delelig på tre **og** fem, skal det printe “fizzbuzz”.

- Denne oppgaven skal løses med parprogrammering, og dette kan gjøres på flere måter: f.eks. med TDD (Test Driven Development), hvor den ene personen skriver test først, og så skriver den andre implementasjonskode. Eller så kan dere programmere fem minutter hver.
- Det er viktig at dere er nøye med **hvordan** dere snakker sammen, og at dere begge har full kontroll på hva som skjer i koden. Tips: Les [denne](https://medium.com/@weblab_tech/pair-programming-guide-a76ca43ff389)

Etter at dere er ferdig:

- Bruk noen minutter på å diskutere hvordan det fungerte å programmere i par. 
- Lag en liten oppsummering til resten av gruppen (1-2 minutter) hvor dere nevner noe dere synes fungerte bra, noe som kunne vært bedre og hva dere kunne gjort bedre til neste gang. 


### Videre arbeid

- Generaliser fizzbuzz slik at man selv kan velge tall som fizzbuzz skal endre på, og hvilke ord som skal printes. Bruk gjerne egne metoder.
- [codekata.com](http://codekata.com/) har mange gode oppgaver, om dere ønsker å trene mer på kode-kata.
- Det kan være lurt å registrere seg som studentbruker i github. Da får man mange gode verktøy [gratis](https://education.github.com/pack).

## Installasjonstips

* Linux: som regel er alt du trenger tilgjengelig gjennom pakkesystemet
* Mac: du får tak i det meste (tilsv. Linux) ved hjelp av [Brew](https://brew.sh/), [Fink](https://www.finkproject.org/) eller [MacPorts](https://www.macports.org/)
   * Hvis du har ny Mac med M1/M2-prosessor skal du ha *AArch64/ARM64*-utgaven av programvare, ellers er det *x64/x86-64/AMD64* som er tingen (med mindre du bruker Raspberry Pi e.l.).
* Windows: du kan ha nytte av [Linux Subsystem for Windows og andre utviklerverktøy](https://learn.microsoft.com/en-us/windows/dev-environment/)
* Java JDK – vi anbefaler at du har en rimelig ny utgave av Java (21 eller senere). For Linux finner du dette gjennom pakkesystemet; for Windows og Mac (og forsåvidt også Linux) kan man laste ned [OpenJDK fra Microsoft](https://learn.microsoft.com/en-gb/java/openjdk/download). Oracle leverer også sin egen utgave av JDK.
   * Nødvendige Java-biblioteker kommer automatisk via Maven/Gradle.
* Integrert utviklingsmiljø (IDE) – [Eclipse](https://www.eclipse.org/downloads/) eller [IntelliJ IDEA](https://www.jetbrains.com/idea/) funker bra for Java. [VSCode](https://code.visualstudio.com/) kan brukes, men sliter med litt mer komplisert oppsett + at den mangler en del refaktoreringsverktøy.
* Git – sjekk [Git for Windows](https://gitforwindows.org/), [Git for Mac](https://git-scm.com/download/mac). 
* [Maven](https://maven.apache.org/download.cgi) og/eller [Gradle](https://gradle.org/install/) 
* IDE-en din kommer antakelig med innebygget Git og Maven, men det kan være lurt om du har kommandolinjeverktøyene, så du blir vant til mer avansert bruk.
