# Rapport – innlevering 3
**Team:** **A-Laget** 

**Medlemmer:**
* Isak Graarud
* Johannes Helle Moe
* Theodor Flornes
* Tuva Kvamme
* Synne Hermansen
* Bernhard Bors

---
## **Spillbeskrivelse**
Vårt spill er et Mario Bros-inspirert plattform-spill. Spilleren navigerer gjennom flere nivåer hvor målet er å løse hver level ved å overkomme hindringer, beseire fiender og til slutt komme til målområdet.


Viktige aspekter i spillet:
1. Spillfigur:
* Spilleren kan styres med piltaster - gå til høyre/venstre, hoppe
* Underveis kan man få "power-ups" ved å hoppe på lucky blocks som endrer spillerens egenskaper

2. Spillverden:
* 2D-Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»)
* Vegg – vertikal flate som spilleren ikke kan gå gjennom
* Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle
* Verden er større enn skjermen, og skjermen beveger seg bortover med spilleren (a la Mario Bros)
3. Fiender:
* Fiender (zombier) som beveger seg og er skadelige ved berøring
* Kan bekjempes ved å hoppe på dem eller ved bruk av spesielle evner spilleren plukker opp
4. Død og progresjon
* For å låse opp ny level må man fullføre foregående level
* Om man blir tatt av zombiene mister man ett av tre liv. Om spilleren ikke har liv igjen eller faller utenfor verden må man starte levelen på nytt
* Man mister ikke progresjon fra tidligere leveler ved å dø
* En spillers score opparbeides basert på tid brukt, zombies drept og powerups plukket opp. De beste scorene lagres i en highscore-oversikt.
5. Ressurser
* Ved å finne lucky blocks eller bekjempe fiender kan man oppnå power-ups - for eksempel at karakteren blir raskere, hopper høyere eller får et liv tilbake.
* Man starter med tre liv - power-ups kan gi ekstra liv.
6. Brukergrensesnitt og stil:
* Spillet har en klassisk, Mario-aktig tegneserie-estetikk
* Hovedmeny med innstillinger, oversikt over nivåer og highscores.


---
## Utviklingsprosess

Vi bruker en slags Scrum tilnærming og setter milepæler som vi jobber mot. De består av flere TODOS som utgjør en større feature eller MVP.

Flyten ser slik ut:
1. Finn en task i [Trello](https://trello.com/b/UkQgtCrS/inf112-a-laget).
2. Finn ut av hva som må til for å lage den featuren. Spør gjerne andre medlemmer om de har peiling på det eller har noe input.
3. Implementer featuren i en egen branch - lag også gjerne tester.
4. Lag en Merge Request i Git. Med mindre det er en veldig liten ting burde minst én annen reviewe forespørselen.
5. Merge inn i Main.
6. Legg til litt dokumentasjon slik at andre kan skjønne hvordan man bruker det.
7. Oppdater Trello. Fant du noen nye ting vi mangler? Er det noe som blokker og gjør at du ikke kan fullføre? Mangler featuren noe som bør legges til senere? Fant du bugs?

---
## Brukerhistorier

### Menyer og navigasjon
#### 1. Splash Screen
* **Som spiller ønsker jeg å se en splashscreen når jeg starter spillet som vises i noen sekunder**
* Her står gruppenavn, spillnavn og framework (libGDX),
  fordi jeg ønsker en profesjononell introduksjon som viser hvem som har laget spillet og hvilken teknologi som brukes.
    * **Akseptansekriterie:**
        * Når spillet åpnes skal man umiddelbart se splash screen med relevant info.
        * Skjermen forsvinner automatisk etter et gitt antall sekunder, og går over i startmenyen.

#### 2. Startmeny og instillinger
* **Som spiller vil jeg kunne starte et nytt spill for å komme i gang,**
  fordi jeg vil begynne å spille uten unødvendige steg.
* **Som spiller vil jeg gå inn i innstillinger og endre resolution, eller skru av og på musikk og sfx,**
  fordi jeg ønsker å tilpasse lydnivå og oppløsning etter eget ønske eller situasjon.
* **Som spiller vil jeg kunne gå inn i How-To-Play,**
  fordi jeg vil ha en oversiktlig måte å lære eller friske opp brukerkontroller.
* **Som spiller vil jeg kunne avslutte spillet,**
  fordi jeg trenger en ryddig måte å avslutte når jeg er ferdig med å spille.
    * **Akseptanskriterie:**
        * Etter splash screen vises det en meny med fungerende knapper som sender til riktig state.
        * Innstillingene lar spilleren skru musikk/SFX av og på, og endre oppløsning.
        * Exit-knappen lukker applikasjonen på en trygg måte.

#### 3. Levelmeny
* **Som spiller ønsker jeg en skjerm for å kunne velge level når jeg trykker start i hovedmenyen,**
  fordi jeg ønsker å kunne velge mellom de forskjellige nivåene.
    * **Akseptansekriterie:**
        * Etter å ha trykket "start" vises en skjerm med mulighet for valg av level.

### Bevegelse og Verden

#### 4. Grunnleggende bevegelse
* **Som spiller vil jeg kunne styre figuren min horisontalt og hoppe ved hjelp av tastaturet,**
  fordi jeg trenger å navigere forbi hindringer og fiender på brettet.
    * **Akseptansekriterier:**
        * Spilleren flytter seg høyre/venstre ved bruk av piltaster eller A/D.
        * Spilleren hopper ved bruk av Space, W eller pil opp.
        * Karakteren bytter til riktig animasjon (løpe, hoppe, stå i ro) basert på bevegelse.

#### 5. Fysikk og kollisjon
* **Som spiller forventer jeg at figuren min stoppes av vegger, plattformer og tyngdekraft,**
  fordi spillverdenen må føles fysisk logisk slik at jeg ikke faller gjennom bakken.
    * **Akseptansekriterier:**
        * Karakteren faller nedover når den ikke står på fast grunn.
        * Karakteren kan ikke bevege seg gjennom faste blokker i kartet.
        * Spilleren kan kun hoppe når figuren står på bakken.

#### 6. Kamerastyring (Scrolling)
* **Som spiller vil jeg at kameraet skal følge etter spillfiguren min når jeg beveger meg,**
  fordi jeg alltid trenger å se området rundt meg og hva som kommer lenger fremme på brettet.
    * **Akspetansekriterie:**
        * Kameraet sentreres rundt spilleren (eller følger etter med en viss forskyvning) under bevegelse.
        * Kameraet stopper ved kartets ytterkanter slik at man ikke ser utsiden av spillverdenen.

### Mekanikk og Kamp

#### 7. Angrep og Bekjempelse
* **Som spiller vil jeg kunne uskadeliggjøre zombier ved å hoppe på hodet deres,**
  fordi jeg trenger en metode for å rydde vei uten å ta skade selv.
    * **Akseptansekriterier:**
        * Når spillerens bunn-hitbox (føttene) treffer fiendens topp-hitbox, tar fienden skade.
        * Fienden dør og forsvinner fra kartet når HP er 0.

#### 8. Skade og Liv (HP)
* **Som spiller vil jeg ha tre liv. Jeg vil miste liv hvis jeg går inn i en fiende, og få en kort periode med udådelighet etterpå,**
  fordi spillet skal være utfordrende, men også gi meg litt tid til å reagere etter å ha tatt skade.
    * **Akseptansekriterier:**
        * Kollisjon med en fiende reduserer spillerens HP.
        * Spilleren får et kort tidsvindu (hitTimer) hvor de ikke kan ta ytterligere skade.

#### 9. Power-ups
* **Som spiller ønsker jeg å finne og plukke opp power-ups på brettet,**
  fordi jeg ønsker fordeler som gjør det lettere å overleve mot fiendene.
    * **Akseptansekriterier:**
        * Power-ups ligger skjult i "lucky-blocks" på kartet.
        * Når spilleren kolliderer med en power-up, fjernes den fra kartet og en effekt påføres (f.eks. gjenopprette HP)

#### 10. Game Over
* **Som spiller forventer jeg å se en Game-Over-skjerm hvis jeg dør, med mulighet til å prøve igjen,**
  fordi jeg raskt vil tilbake i spillet.
    * **Akseptansekriterier:**
        * Når spillerens HP er 0 eller lavere, fryses spillet, og en "Game Over"-skjerm vises.
        * Skjermen inneholder en knapp for å restarte brettet.

#### 11. Fullføre nivå
* **Som spiller vil jeg nå et målpunkt i enden av brettet for å låse opp neste nivå,**
  fordi jeg vil kjenne på en følelse av progresjon.
* Kollisjon med mål-flagget på brettet trigger en level-overgang.

---
## Retroperspektiv

### Hva fungerer bra

Rollene i teamet har fungert godt og det er ikke identifisert behov for nye roller.
* **Team Lead (Isak)** passer på at alle har oppgaver og fordeler nye oppgaver til de som ikke har.
* **Design-ansvarlig (Theodor)** fortsetter å bidra med grafikk og designer flere leveler og features.
* **Test Queen (Tuva)** holder fokus på testing og jobber aktivt med å øke testdekningen.
* **DJ (Johannes)** har implementert spillmusikk, lydeffekter og flere nyttige utils.
* **Struktur-ansvarlig (Synne)** passer på at vi følger MVC og at koden holdes modulær. Refaktorerer om nødvendig.
* **Rapport-ansvarlig (Bernhard)** fortsetter å skrive dokumentasjon og rapporter, samt følger opp at vi møter oppgavekriterier.

Teamet er fornøyd med prosjektmetodikken og valg som er tatt. Gruppedynamikken er fortsatt sterk, med god kommunikasjon og jevn fordeling av innsats. Alle er involvert og bidrar aktivt med ideer til gjennomføringen.

Vi har nådd **Minimum Viable Product (MVP)**, og er stolte av det. Flere viktige funksjoner er på plass, og spillet føles nå mer helhetlig og komplett.

### Hva vi har gjort siden sist
* Refaktorert deler av prosjektet til å følge **MVC- og SOLID-arkitektur**, som har gitt bedre struktur, og gjort det lettere å legge til nye features.
* Implementert HP, ulike zombier og funksjonalitet for fiendene, som gjør spillet spillbart og utfordrende. 
* Lagt til lucky-blocks med power-ups, samt målgang som gir mer dybde til spillet.
* Designet skjermer for pause og level-valg, slik at spillet blir mer brukervennlig.
* Begynt å skrive tester, og laget abstrakt set up for tester, slik at vi slipper duplikat kode **(DRY)**, og kan fange bugs i koden.
* Lagt til spillmusikk og flere lydeffekter, samt lydinstillinger for å maksimere brukeropplevelsen.


### Forbedringspotensial
* Vi kunne tidligere landet en konkret plan for hva spillet skulle inneholde.
* Oppdatering i Trello kan fortsatt lett glemmes i travle perioder.
* Vi kan også bli flinkere på kontinuerlig testskriving, slik at vi holder dekningen stabil og høy, fanger bugs raskere, og ikke sitter igjen med kun masse tester til slutt.

### Plan videre
* Designe ferdig de siste levelene vi jobber med, og implementere disse.
* Ferdigstille funksjonalitet knyttet til spilleren og fiender.
* Legge til noen flere power-ups.
* Øke testdekningen og holde den høy ved å teste nye features fortløpende.
* Fortsette iterativ utvikling og følge SOLID og MVC.
* Evaluere feedback og gjøre eventuelle justeringer før siste innlevering.

---
## Klassediagram
[Klassediagram](klassediagram.png)

---
## Møtereferater

Møtereferatene våre ligger i denne google doc-en:

[Møtereferater A-laget](https://docs.google.com/document/d/1DTBr6Ew_FQDBkRHb4hHOdzjmSTYw7m4FUMoYYwyxgII/edit?usp=sharing)


